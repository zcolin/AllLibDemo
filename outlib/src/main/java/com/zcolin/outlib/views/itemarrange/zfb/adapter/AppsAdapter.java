/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-3-28 上午10:26
 * ********************************************************
 */

package com.zcolin.outlib.views.itemarrange.zfb.adapter;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcolin.outlib.R;
import com.zcolin.outlib.views.itemarrange.AppsItemEntity;
import com.zcolin.outlib.views.itemarrange.helper.ItemDragHelperCallback;
import com.zcolin.outlib.views.itemarrange.helper.OnDragVHListener;
import com.zcolin.outlib.views.itemarrange.helper.OnItemMoveListener;

import java.util.ArrayList;

/**
 * 拖拽排序 + 增删
 */
public class AppsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemMoveListener {
    public static final int TYPE_MY_APPS_HEADER  = 0;//我的应用的标题部分
    public static final int TYPE_MY_APPS         = 1;//我的应用
    public static final int TYPE_ALL_APPS        = 2;//全部应用
    public static final int TYPE_ALL_APPS_HEADER = 3;//其他应用的标题部分

    private static final int COUNT_PRE_MY_HEADER  = 1;//我的应用之前的header数量:即标题部分为 1
    private static final int COUNT_PRE_ALL_HEADER = COUNT_PRE_MY_HEADER + 1;//其他应用之前的header数量:即标题部分为 COUNT_PRE_MY_HEADER + 1

    private static final long SPACE_TIME = 100; //touch间隔时间,用于分辨是否是 "点击"

    private long                      startTime; //touch点击开始时间
    private boolean                   isEditMode;   //是否为编辑模式
    private OnAppsItemClickListener   mAppsItemClickListener; // 点击事件
    private OnItemEditListener        onItemEditListener;
    private ArrayList<AppsItemEntity> listMyAppsItems;  //我的应用列表
    private ArrayList<AppsItemEntity> listAllAppsItems;//全部应用列表
    private ItemTouchHelper           mItemTouchHelper;
    private RecyclerView              recyclerView;

    public AppsAdapter(RecyclerView recyclerView, ArrayList<AppsItemEntity> listMyAppsItems, ArrayList<AppsItemEntity> listAllAppsItems) {
        this.listMyAppsItems = listMyAppsItems;
        this.listAllAppsItems = listAllAppsItems;
        this.recyclerView = recyclerView;

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        // 我的应用  标题 + 我的应用.size + 其他应用 标题 + 其他应用.size
        return listMyAppsItems.size() + listAllAppsItems.size() + COUNT_PRE_ALL_HEADER;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_MY_APPS_HEADER;
        } else if (position == listMyAppsItems.size() + 1) {
            return TYPE_ALL_APPS_HEADER;
        } else if (position > 0 && position < listMyAppsItems.size() + 1) {
            return TYPE_MY_APPS;
        } else {
            return TYPE_ALL_APPS;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_MY_APPS_HEADER:
                return new RecyclerView.ViewHolder(inflater.inflate(R.layout.itemarrange_my_apps_header, parent, false)) {
                };
            case TYPE_ALL_APPS_HEADER:
                return new RecyclerView.ViewHolder(inflater.inflate(R.layout.itemarrange_all_apps_header, parent, false)) {
                };
            case TYPE_MY_APPS:
                final AppsViewHolder myHolder = new AppsViewHolder(inflater.inflate(R.layout.itemarrange_apps, parent, false));
                myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        if (isEditMode) {
                            removeMyApps(myHolder.getAdapterPosition());
                        } else if (mAppsItemClickListener != null) {
                            mAppsItemClickListener.onItemClick(v, myHolder.getAdapterPosition() - COUNT_PRE_MY_HEADER);
                        }
                    }
                });
                myHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (!isEditMode) {
                            edit();
                        }
                        return true;
                    }
                });
                myHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
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
                    }
                });
                return myHolder;
            case TYPE_ALL_APPS:
                final AppsViewHolder allHolder = new AppsViewHolder(inflater.inflate(R.layout.itemarrange_apps, parent, false));
                allHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isEditMode) {
                            int pos = allHolder.getAdapterPosition();
                            AppsItemEntity entity = listAllAppsItems.get(pos - listMyAppsItems.size() - COUNT_PRE_ALL_HEADER);
                            if (!entity.isAdded) {
                                addAppToMy(pos);
                            }
                        } else if (mAppsItemClickListener != null) {
                            mAppsItemClickListener.onItemClick(v, allHolder.getAdapterPosition() - COUNT_PRE_ALL_HEADER - listMyAppsItems.size());
                        }
                    }
                });
                allHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (!isEditMode) {
                            edit();
                        }
                        return false;
                    }
                });
                return allHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_MY_APPS) {
            AppsViewHolder myHolder = (AppsViewHolder) holder;
            AppsItemEntity entity = listMyAppsItems.get(position - COUNT_PRE_MY_HEADER);
            myHolder.tvName.setText(entity.name);
            myHolder.tvName.setCompoundDrawablesWithIntrinsicBounds(0, entity.iconDrawable, 0, 0);

            myHolder.ivEditFlag.setImageResource(R.drawable.apps_reduce_icon);
            if (isEditMode) {
                myHolder.ivEditFlag.setVisibility(View.VISIBLE);
            } else {
                myHolder.ivEditFlag.setVisibility(View.GONE);
            }
        } else if (getItemViewType(position) == TYPE_ALL_APPS) {
            final AppsViewHolder allHolder = (AppsViewHolder) holder;
            AppsItemEntity entity = listAllAppsItems.get(position - listMyAppsItems.size() - COUNT_PRE_ALL_HEADER);
            allHolder.tvName.setText(entity.name);
            allHolder.tvName.setCompoundDrawablesWithIntrinsicBounds(0, entity.iconDrawable, 0, 0);


            if (entity.isAdded) {
                allHolder.ivEditFlag.setImageResource(R.drawable.apps_ok_icon);
            } else {
                allHolder.ivEditFlag.setImageResource(R.drawable.apps_increase_icon);
            }
            if (isEditMode) {
                allHolder.ivEditFlag.setVisibility(View.VISIBLE);
            } else {
                allHolder.ivEditFlag.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        AppsItemEntity item = listMyAppsItems.get(fromPosition - COUNT_PRE_MY_HEADER);
        listMyAppsItems.remove(fromPosition - COUNT_PRE_MY_HEADER);
        listMyAppsItems.add(toPosition - COUNT_PRE_MY_HEADER, item);
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * 我的应用 移动到 其他应用
     */
    private void removeMyApps(int position) {
        int startPosition = position - COUNT_PRE_MY_HEADER;
        if (startPosition > listMyAppsItems.size() - 1) {
            return;
        }
        AppsItemEntity item = listMyAppsItems.get(startPosition);
        item.isAdded = false;
        listMyAppsItems.remove(item);
        notifyDataSetChanged();
    }

    /**
     * 其他应用 移动到 我的应用
     */
    private void addAppToMy(int position) {
        int startPosition = position - listMyAppsItems.size() - COUNT_PRE_ALL_HEADER;
        if (startPosition <= listAllAppsItems.size() - 1) {
            AppsItemEntity item = listAllAppsItems.get(startPosition);
            item.isAdded = true;
            listMyAppsItems.add(item);
            notifyDataSetChanged();
        }
    }

    public ArrayList<AppsItemEntity> getListMyAppsItems() {
        return listMyAppsItems;
    }

    public ArrayList<AppsItemEntity> getListAllAppsItems() {
        return listAllAppsItems;
    }

    public void setOnMyAppsItemClickListener(OnAppsItemClickListener listener) {
        this.mAppsItemClickListener = listener;
    }

    public void setOnItemEditListener(OnItemEditListener listener) {
        this.onItemEditListener = listener;
    }

    /**
     * 是否为编辑模式
     */
    public boolean isEditMode() {
        return isEditMode;
    }

    /**
     * 完成编辑
     */
    public void completeEdit() {
        isEditMode = false;
        notifyDataSetChanged();
        if (onItemEditListener != null) {
            onItemEditListener.onComplete();
        }
    }

    /**
     * 取消编辑模式
     */
    public void cancelEdit() {
        isEditMode = false;
        notifyDataSetChanged();
        if (onItemEditListener != null) {
            onItemEditListener.onCancel();
        }
    }

    /**
     * 进入编辑模式
     */
    public void edit() {
        isEditMode = true;
        int visibleChildCount = recyclerView.getChildCount();
        for (int i = 0; i < visibleChildCount; i++) {
            View view = recyclerView.getChildAt(i);
            ImageView ivMyReduceSign = (ImageView) view.findViewById(R.id.iv_editflag);
            if (ivMyReduceSign != null) {
                ivMyReduceSign.setVisibility(View.VISIBLE);
            }
        }

        if (onItemEditListener != null) {
            onItemEditListener.onEdit();
        }
    }

    private class AppsViewHolder extends RecyclerView.ViewHolder implements OnDragVHListener {
        private TextView  tvName;
        private ImageView ivEditFlag;

        AppsViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            ivEditFlag = (ImageView) itemView.findViewById(R.id.iv_editflag);
        }

        @Override
        public void onItemSelected() {
            tvName.setBackgroundResource(R.drawable.bg_itemarrange_apps_p);
        }

        @Override
        public void onItemFinish() {
            tvName.setBackgroundResource(R.drawable.bg_itemarrange_apps);
        }
    }

    public interface OnAppsItemClickListener {
        void onItemClick(View v, int position);
    }

    public interface OnItemEditListener {
        void onComplete();

        void onCancel();

        void onEdit();
    }
}
