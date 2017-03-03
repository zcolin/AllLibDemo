/*
 * **********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-10-8 下午3:55
 * *********************************************************
 */

package cn.sharesdk.util;

import android.content.Context;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.weibo.TencentWeibo;

/**
 * 社会化分享工具类
 */
public class ShareSocial {

    private String platform;
    private boolean isShowContentEdit = true;
    private String  title;
    private String  content;
    private String  targetUrl;
    private String  imgUrl;
    private String  comment;
    private String  imgPath;
    private Context context;

    public ShareSocial(Context context) {
        this.context = context;
    }

    /**
     * 指定直接分享平台名称（一旦设置了平台名称，则九宫格将不会显示）
     */
    public ShareSocial setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    /**
     * 是否显示编辑页
     */
    public ShareSocial setIsShowContentEdit(boolean isShowContentEdit) {
        this.isShowContentEdit = isShowContentEdit;
        return this;
    }

    /**
     * title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
     */
    public ShareSocial setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 分享内容
     */
    public ShareSocial setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 指向链接地址
     */
    public ShareSocial setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
        return this;
    }

    /**
     * 图片Url
     */
    public ShareSocial setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    /**
     * 本地图片路径
     */
    public ShareSocial setImgPath(String imgPath) {
        this.imgPath = imgPath;
        return this;
    }

    /**
     * 我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
     */
    public ShareSocial setComment(String comment) {
        this.comment = comment;
        return this;
    }


    /**
     * 调用执行分享
     */
    public void share() {
        OnekeyShare oks = new OnekeyShare();
        oks.setSilent(!isShowContentEdit);
        if (platform != null) {
            oks.setPlatform(platform);
        }

        //ShareSDK快捷分享提供两个界面第一个是九宫格 CLASSIC  第二个是SKYBLUE
        oks.setTheme(OnekeyShareTheme.CLASSIC);
        // 令编辑页面显示为Dialog模式
        oks.setDialogMode();
        // 在自动授权时可以禁用SSO方式
        oks.disableSSOWhenAuthorize();
        //oks.setAddress("12345678901"); //分享短信的号码和邮件的地址

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        if (title != null) {
            oks.setTitle(title);
        }

        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用  
        if (targetUrl != null) {
            oks.setTitleUrl(targetUrl);
        }

        // text是分享文本，所有平台都需要这个字段  
        if (content != null) {
            oks.setText(content);
        }

        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test-pic.jpg"); 

        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        if (imgUrl != null) {
            oks.setImageUrl(imgUrl);
        }

        if (imgPath != null) {
            oks.setImagePath(imgPath);
        }

        // url仅在微信（包括好友和朋友圈）中使用  
        if (targetUrl != null) {
            oks.setUrl(targetUrl);
        }

        //filePath是待分享应用程序的本地路劲，仅在微信（易信）好友和Dropbox中使用，否则可以不提供
        //oks.setFilePath("/sdcard/test-pic.jpg");  

        //我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
        if (comment != null) {
            oks.setComment(comment);
        }

        // site是分享此内容的网站名称，仅在QQ空间使用
        //oks.setSite(context.getResources()
        //                           .getString(R.string.app_name));

        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        if (targetUrl != null) {
            oks.setSiteUrl(targetUrl);
        }

        // oks.setVenueDescription("This is a beautiful place!");
        // 将快捷分享的操作结果将通过OneKeyShareCallback回调
        //oks.setCallback(new OneKeyShareCallback());
        // 去自定义不同平台的字段内容
        //oks.setShareContentCustomizeCallback(new ShareContentCustomizeDemo());
        // 在九宫格设置自定义的图标
        //        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        //        String label = context.getResources()
        //                              .getString(R.string.app_name);
        //        View.OnClickListener listener = new View.OnClickListener() {
        //            public void onClick(View v) {
        //
        //            }
        //        };
        //        oks.setCustomerLogo(logo, label, listener);

        // 为EditPage设置一个背景的View
        //oks.setEditPageBackground(getPage());
        //  隐藏九宫格中的新浪微博
        oks.addHiddenPlatform(SinaWeibo.NAME);
        oks.addHiddenPlatform(TencentWeibo.NAME);

        // String[] AVATARS = {
        // 		"http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg",
        // 		"http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339485237265.jpg",
        // 		"http://diy.qqjay.com/u/files/2012/0523/f466c38e1c6c99ee2d6cd7746207a97a.jpg",
        // 		"http://diy.qqjay.com/u2/2013/0422/fadc08459b1ef5fc1ea6b5b8d22e44b4.jpg",
        // 		"http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339510584349.jpg",
        // 		"http://diy.qqjay.com/u2/2013/0401/4355c29b30d295b26da6f242a65bcaad.jpg" };
        // oks.setImageArray(AVATARS); //腾讯微博和twitter用此方法分享多张图片，其他平台不可以

        // 启动分享
        oks.show(context);
    }
}
