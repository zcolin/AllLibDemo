/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */

package com.zcolin.outlib.views.itemarrange.drag.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zcolin.outlib.R;
import com.zcolin.outlib.views.itemarrange.AppsItemEntity;
import com.zcolin.outlib.views.itemarrange.helper.ItemDragHelperCallback;
import com.zcolin.outlib.views.itemarrange.helper.OnDragVHListener;
import com.zcolin.outlib.views.itemarrange.helper.OnItemMoveListener;

import java.util.List;


/**
 * 拖拽排序 + 增删
 */
public class AppsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemMoveListener {
    public static final int TYPE_MY_APPS_HEADER    = 0;//我的应用的标题部分
    public static final int TYPE_MY_APPS           = 1;//我的应用
    public static final int TYPE_OTHER_APPS_HEADER = 2; //其他应用的标题部分
    public static final int TYPE_OTHER_APPS        = 3;//其他应用

    private static final int COUNT_PRE_MY_HEADER    = 1;//我的应用之前的header数量:即标题部分为 1
    private static final int COUNT_PRE_OTHER_HEADER = COUNT_PRE_MY_HEADER + 1;//其他应用之前的header数量:即标题部分为 COUNT_PRE_MY_HEADER + 1

    private static final long ANIM_TIME  = 360L; //Item位移动画时间
    private static final long SPACE_TIME = 100;//touch间隔时间,用于分辨是否是 "点击"

    private long                    startTime;//touch点击开始时间
    private boolean                 isEditMode;//是否为编辑模式
    private OnAppsItemClickListener mAppsItemClickListener;// 我的频道点击事件
    public  List<AppsItemEntity>    listMyAppsItems;
    public  List<AppsItemEntity>    listOtherAppsItems;
    private ItemTouchHelper         mItemTouchHelper;
    private OnItemEditListener      onItemEditListener;
    private RecyclerView            recyclerView;
    private Handler delayHandler = new Handler();


    public AppsAdapter(RecyclerView recyclerView, List<AppsItemEntity> listMyAppsItems, List<AppsItemEntity> listOtherAppsItems) {
        this.listMyAppsItems = listMyAppsItems;
        this.listOtherAppsItems = listOtherAppsItems;
        this.recyclerView = recyclerView;

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    /**
     * 设置Item点击回调
     */
    public void setOnAppsItemClickListener(OnAppsItemClickListener listener) {
        this.mAppsItemClickListener = listener;
    }

    /**
     * 设置EditMode回调
     */
    public void setOnItemEditListener(OnItemEditListener onItemEditListener) {
        this.onItemEditListener = onItemEditListener;
    }

    @Override
    public int getItemCount() {
        // 我的应用  标题 + 我的应用.size + 其他应用 标题 + 其他应用.size
        return listMyAppsItems.size() + listOtherAppsItems.size() + COUNT_PRE_OTHER_HEADER;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_MY_APPS_HEADER;     // 我的应用 标题部分
        } else if (position == listMyAppsItems.size() + 1) {
            return TYPE_OTHER_APPS_HEADER;  // 其他应用 标题部分
        } else if (position > 0 && position < listMyAppsItems.size() + 1) {
            return TYPE_MY_APPS;            // 我的应用 内容部分
        } else {
            return TYPE_OTHER_APPS;         // 其他应用 内容部分
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case TYPE_MY_APPS_HEADER:
                return new RecyclerView.ViewHolder(inflater.inflate(R.layout.itemarrange_my_apps_header, parent, false)) {
                };
            case TYPE_OTHER_APPS_HEADER:
                return new RecyclerView.ViewHolder(inflater.inflate(R.layout.itemarrange_other_apps_header, parent, false)) {
                };
            case TYPE_MY_APPS:
                final AppsViewHolder myHolder = new AppsViewHolder(inflater.inflate(R.layout.itemarrange_apps, parent, false), 0);
                myHolder.rlMy.setOnClickListener(v -> {
                    int position = myHolder.getAdapterPosition();
                    if (isEditMode) {
                        RecyclerView recyclerView = (RecyclerView) parent;
                        View currentView = recyclerView.getLayoutManager().findViewByPosition(position);
                        int[] location = getMyToOtherTargetViewLocation(recyclerView);

                        moveMyToOther(myHolder);
                        if (location != null) {
                            startAnimation(recyclerView, currentView, location[0], location[1]);
                        }
                    } else {
                        mAppsItemClickListener.onItemClick(v, position - COUNT_PRE_MY_HEADER, 0);
                    }
                });

                myHolder.rlMy.setOnLongClickListener(view -> {
                    if (!isEditMode) {
                        edit();
                    }
                    return false;
                });

                myHolder.rlMy.setOnTouchListener((v, event) -> {
                    if (isEditMode) {
                        switch (MotionEventCompat.getActionMasked(event)) {
                            case MotionEvent.ACTION_DOWN:
                                startTime = System.currentTimeMillis();
                                break;
                            case MotionEvent.ACTION_MOVE:
                                if (System.currentTimeMillis() - startTime > SPACE_TIME) {
                                    mItemTouchHelper.startDrag(myHolder);
                                }
                                break;
                            case MotionEvent.ACTION_CANCEL:
                            case MotionEvent.ACTION_UP:
                                startTime = 0;
                                break;
                        }

                    }
                    return false;
                });
                return myHolder;
            case TYPE_OTHER_APPS:
                final AppsViewHolder otherHolder = new AppsViewHolder(inflater.inflate(R.layout.itemarrange_apps, parent, false), 1);
                otherHolder.rlMy.setOnClickListener(v -> {
                    int position = otherHolder.getAdapterPosition();
                    if (isEditMode) {
                        RecyclerView recyclerView = ((RecyclerView) parent);
                        GridLayoutManager gridLayoutManager = ((GridLayoutManager) recyclerView.getLayoutManager());
                        View currentView = gridLayoutManager.findViewByPosition(position);

                        int[] location = getOtherToMyTargetViewLocation(recyclerView);
                        if (location != null) {
                            // 如果当前位置是otherApps可见的最后一个
                            // 并且 当前位置不在grid的第一个位置
                            // 并且 目标位置不在grid的第一个位置
                            // 则 需要延迟动画时间秒数 notifyItemMove , 这是因为这种情况 , 并不触发ItemAnimator , 会直接刷新界面
                            // 导致我们的位移动画刚开始,就已经notify完毕,引起不同步问题
                            int spanCount = gridLayoutManager.getSpanCount();
                            int targetPosition = listMyAppsItems.size() - 1 + COUNT_PRE_OTHER_HEADER;
                            if (position == gridLayoutManager.findLastVisibleItemPosition() && (position - listMyAppsItems.size() - COUNT_PRE_OTHER_HEADER) %
                                    spanCount != 0 && (targetPosition - COUNT_PRE_MY_HEADER) % spanCount != 0) {
                                moveOtherToMyWithDelay(otherHolder);
                            } else {
                                moveOtherToMy(otherHolder);
                            }
                            startAnimation(recyclerView, currentView, location[0], location[1]);
                        } else {
                            moveOtherToMy(otherHolder);
                        }
                    } else {
                        mAppsItemClickListener.onItemClick(v, position - listMyAppsItems.size() - COUNT_PRE_OTHER_HEADER, 1);
                    }
                });

                otherHolder.rlMy.setOnLongClickListener(view -> {
                    if (!isEditMode) {
                        edit();
                    }
                    return false;
                });
                return otherHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AppsViewHolder && ((AppsViewHolder) holder).type == 0) {
            AppsViewHolder myHolder = (AppsViewHolder) holder;
            myHolder.tvMy.setText(listMyAppsItems.get(position - COUNT_PRE_MY_HEADER).name);
            int iconDrawable = listMyAppsItems.get(position - COUNT_PRE_MY_HEADER).iconDrawable;
            myHolder.tvMy.setCompoundDrawablesWithIntrinsicBounds(0, iconDrawable, 0, 0);

            myHolder.ivMyReduceSign.setImageResource(R.drawable.apps_reduce_icon);
            if (isEditMode) {
                myHolder.ivMyReduceSign.setVisibility(View.VISIBLE);
            } else {
                myHolder.ivMyReduceSign.setVisibility(View.INVISIBLE);
            }
        } else if (holder instanceof AppsViewHolder && ((AppsViewHolder) holder).type == 1) {
            AppsViewHolder otherHolder = (AppsViewHolder) holder;
            otherHolder.tvMy.setText(listOtherAppsItems.get(position - listMyAppsItems.size() - COUNT_PRE_OTHER_HEADER).name);
            int iconDrawable = listOtherAppsItems.get(position - listMyAppsItems.size() - COUNT_PRE_OTHER_HEADER).iconDrawable;
            otherHolder.tvMy.setCompoundDrawablesWithIntrinsicBounds(0, iconDrawable, 0, 0);

            otherHolder.ivMyReduceSign.setImageResource(R.drawable.apps_increase_icon);
            if (isEditMode) {
                otherHolder.ivMyReduceSign.setVisibility(View.VISIBLE);
            } else {
                otherHolder.ivMyReduceSign.setVisibility(View.INVISIBLE);
            }

        }
    }

    private int[] getMyToOtherTargetViewLocation(RecyclerView recyclerView) {
        View targetView = recyclerView.getLayoutManager().findViewByPosition(listMyAppsItems.size() + COUNT_PRE_OTHER_HEADER);
        if (recyclerView.indexOfChild(targetView) >= 0) {
            int[] location = new int[2];
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            int spanCount = ((GridLayoutManager) manager).getSpanCount();

            // 移动后 高度将变化 (我的频道Grid 最后一个item在新的一行第一个)
            if ((listMyAppsItems.size() - COUNT_PRE_MY_HEADER) % spanCount == 0) {
                View preTargetView = recyclerView.getLayoutManager().findViewByPosition(listMyAppsItems.size() + COUNT_PRE_OTHER_HEADER - 1);
                location[0] = preTargetView.getLeft();
                location[1] = preTargetView.getTop();
            } else {
                location[0] = targetView.getLeft();
                location[1] = targetView.getTop();
            }
            return location;
        }
        return null;
    }

    private int[] getOtherToMyTargetViewLocation(RecyclerView recyclerView) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        // 目标位置的前一个item  即当前MyApps的最后一个
        View preTargetView = manager.findViewByPosition(listMyAppsItems.size() - 1 + COUNT_PRE_MY_HEADER);
        // 如果targetView不在屏幕内,则为-1  此时不需要添加动画,因为此时notifyItemMoved自带一个向目标移动的动画
        // 如果在屏幕内,则添加一个位移动画
        if (recyclerView.indexOfChild(preTargetView) >= 0) {
            int[] location = new int[2];
            location[0] = preTargetView.getLeft();
            location[1] = preTargetView.getTop();
            int targetPosition = listMyAppsItems.size() - 1 + COUNT_PRE_OTHER_HEADER;
            GridLayoutManager gridLayoutManager = ((GridLayoutManager) manager);
            int spanCount = gridLayoutManager.getSpanCount();
            // target 在最后一行第一个
            if ((targetPosition - COUNT_PRE_MY_HEADER) % spanCount == 0) {
                View targetView = manager.findViewByPosition(targetPosition);
                location[0] = targetView.getLeft();
                location[1] = targetView.getTop();
            } else {
                location[0] += preTargetView.getWidth();

                // 最后一个item可见
                if (gridLayoutManager.findLastVisibleItemPosition() == getItemCount() - 1) {
                    // 最后的item在最后一行第一个位置
                    if ((getItemCount() - 1 - listMyAppsItems.size() - COUNT_PRE_OTHER_HEADER) % spanCount == 0) {
                        // RecyclerView实际高度 > 屏幕高度 && RecyclerView实际高度 < 屏幕高度 + item.height
                        int firstVisiblePostion = gridLayoutManager.findFirstVisibleItemPosition();
                        if (firstVisiblePostion == 0) {
                            // FirstCompletelyVisibleItemPosition == 0 即 内容不满一屏幕 , targetY值不需要变化
                            // // FirstCompletelyVisibleItemPosition != 0 即 内容满一屏幕 并且 可滑动 , targetY值 + firstItem.getTop
                            if (gridLayoutManager.findFirstCompletelyVisibleItemPosition() != 0) {
                                int offset = (-recyclerView.getChildAt(0).getTop()) - recyclerView.getPaddingTop();
                                location[1] += offset;
                            }
                        } else { // 在这种情况下 并且 RecyclerView高度变化时(即可见第一个item的 position != 0),
                            // 移动后, targetY值  + 一个item的高度
                            location[1] += preTargetView.getHeight();
                        }
                    }
                } else {
                    System.out.println("current--No");
                }
            }
            return location;
        }
        return null;
    }

    /**
     * 开始增删动画
     */
    private void startAnimation(RecyclerView recyclerView, final View currentView, float targetX, float targetY) {
        final ViewGroup viewGroup = (ViewGroup) recyclerView.getParent();
        final ImageView mirrorView = addMirrorView(viewGroup, recyclerView, currentView);

        Animation animation = getTranslateAnimator(targetX - currentView.getLeft(), targetY - currentView.getTop());
        currentView.setVisibility(View.INVISIBLE);
        mirrorView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewGroup.removeView(mirrorView);
                if (currentView.getVisibility() == View.INVISIBLE) {
                    currentView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 我的应用 移动到 其他应用
     */
    private void moveMyToOther(AppsViewHolder myHolder) {
        int position = myHolder.getAdapterPosition();

        int startPosition = position - COUNT_PRE_MY_HEADER;
        if (startPosition > listMyAppsItems.size() - 1) {
            return;
        }
        AppsItemEntity item = listMyAppsItems.get(startPosition);
        listMyAppsItems.remove(startPosition);
        listOtherAppsItems.add(0, item);
        notifyItemMoved(position, listMyAppsItems.size() + COUNT_PRE_OTHER_HEADER);
    }

    /**
     * 其他应用 移动到 我的应用
     */
    private void moveOtherToMy(AppsViewHolder otherHolder) {
        int position = processItemRemoveAdd(otherHolder);
        if (position == -1) {
            return;
        }
        notifyItemMoved(position, listMyAppsItems.size() - 1 + COUNT_PRE_MY_HEADER);
    }

    /**
     * 其他应用 移动到 我的应用 伴随延迟
     */
    private void moveOtherToMyWithDelay(AppsViewHolder otherHolder) {
        final int position = processItemRemoveAdd(otherHolder);
        if (position == -1) {
            return;
        }
        delayHandler.postDelayed(() -> notifyItemMoved(position, listMyAppsItems.size() - 1 + COUNT_PRE_MY_HEADER), ANIM_TIME);
    }


    private int processItemRemoveAdd(AppsViewHolder otherHolder) {
        int position = otherHolder.getAdapterPosition();

        int startPosition = position - listMyAppsItems.size() - COUNT_PRE_OTHER_HEADER;
        if (startPosition > listOtherAppsItems.size() - 1) {
            return -1;
        }
        AppsItemEntity item = listOtherAppsItems.get(startPosition);
        listOtherAppsItems.remove(startPosition);
        listMyAppsItems.add(item);
        return position;
    }


    /**
     * 添加需要移动的 镜像View
     */
    private ImageView addMirrorView(ViewGroup parent, RecyclerView recyclerView, View view) {
        /**
         * 我们要获取cache首先要通过setDrawingCacheEnable方法开启cache，然后再调用getDrawingCache方法就可以获得view的cache图片了。
         buildDrawingCache方法可以不用调用，因为调用getDrawingCache方法时，若果cache没有建立，系统会自动调用buildDrawingCache方法生成cache。
         若想更新cache, 必须要调用destoryDrawingCache方法把旧的cache销毁，才能建立新的。
         当调用setDrawingCacheEnabled方法设置为false, 系统也会自动把原来的cache销毁。
         */
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        final ImageView mirrorView = new ImageView(recyclerView.getContext());
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        mirrorView.setImageBitmap(bitmap);
        view.setDrawingCacheEnabled(false);
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);
        int[] parenLocations = new int[2];
        recyclerView.getLocationOnScreen(parenLocations);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(bitmap.getWidth(), bitmap.getHeight());
        params.setMargins(locations[0], locations[1] - parenLocations[1], 0, 0);
        parent.addView(mirrorView, params);

        return mirrorView;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        AppsItemEntity item = listMyAppsItems.get(fromPosition - COUNT_PRE_MY_HEADER);
        listMyAppsItems.remove(fromPosition - COUNT_PRE_MY_HEADER);
        listMyAppsItems.add(toPosition - COUNT_PRE_MY_HEADER, item);
        notifyItemMoved(fromPosition, toPosition);
    }

    public boolean isEditMode() {
        return isEditMode;
    }


    public void completeEdit() {
        endEditMode();
        if (onItemEditListener != null) {
            onItemEditListener.onComplete();
        }
    }

    public void cancelEdit() {
        endEditMode();
        if (onItemEditListener != null) {
            onItemEditListener.onCancel();
        }
    }

    public void edit() {
        startEditMode();
        if (onItemEditListener != null) {
            onItemEditListener.onEdit();
        }
    }

    /**
     * 开启编辑模式
     */
    private void startEditMode() {
        isEditMode = true;

        int visibleChildCount = recyclerView.getChildCount();
        for (int i = 0; i < visibleChildCount; i++) {
            View view = recyclerView.getChildAt(i);
            ImageView ivMyReduceSign = view.findViewById(R.id.iv_editflag);
            if (ivMyReduceSign != null) {
                ivMyReduceSign.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 完成编辑模式
     */
    public void endEditMode() {
        isEditMode = false;
        int visibleChildCount = recyclerView.getChildCount();
        for (int i = 0; i < visibleChildCount; i++) {
            View view = recyclerView.getChildAt(i);
            ImageView ivMyReduceSign = view.findViewById(R.id.iv_editflag);
            if (ivMyReduceSign != null) {
                ivMyReduceSign.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 获取位移动画
     */
    private TranslateAnimation getTranslateAnimator(float targetX, float targetY) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.ABSOLUTE, targetX, Animation
                .RELATIVE_TO_SELF, 0f, Animation.ABSOLUTE, targetY);
        // RecyclerView默认移动动画250ms 这里设置360ms 是为了防止在位移动画结束后 remove(view)过早 导致闪烁
        translateAnimation.setDuration(ANIM_TIME);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }

    public interface OnAppsItemClickListener {
        /**
         * @param itemType 按钮类型　0 我的 1 其他
         */
        void onItemClick(View v, int position, int itemType);
    }

    public interface OnItemEditListener {
        void onComplete();

        void onCancel();

        void onEdit();
    }

    private class AppsViewHolder extends RecyclerView.ViewHolder implements OnDragVHListener {
        private int            type;//0 我的 1 其他
        private RelativeLayout rlMy;
        private TextView       tvMy;
        private ImageView      ivMyReduceSign;


        AppsViewHolder(View itemView, int type) {
            super(itemView);
            this.type = type;
            rlMy = itemView.findViewById(R.id.rl_my);
            tvMy = itemView.findViewById(R.id.tv_name);
            ivMyReduceSign = itemView.findViewById(R.id.iv_editflag);
        }

        /**
         * item 被选中时
         */
        @Override
        public void onItemSelected() {
            tvMy.setBackgroundResource(R.drawable.bg_itemarrange_apps_p);
        }

        /**
         * item 取消选中时
         */
        @Override
        public void onItemFinish() {
            tvMy.setBackgroundResource(R.drawable.bg_itemarrange_apps);
        }
    }
}
