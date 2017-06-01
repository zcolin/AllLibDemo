/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-26 下午3:36
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvvm.demo_view.activity;

import android.os.Bundle;

import com.zcolin.outlib.views.richtext.RichEditTextView;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;

public class RichEditTextViewActivity extends BaseActivity {

    private String body = "<p>　　最近，“民国无名女神”在微博上火了。讲真，这位无名女神是比现在那些整容脸、网红脸让人看的赏心悦目。但是，比起那些年轰动的民国美女和才女们，她还是略有逊色。要知道这些美女和才女们，不论是本尊还是是后世扮演她们的演员，真真是极美的。这些人里有你心中女神么？<\\/p><p>　　就是这张相册里的老旧照片让民国时期的美女们再次被大家想起。并不是现在当红的蛇精脸，是肉肉的小圆脸，也没有冲破天际的高鼻梁和比眼睛都要宽的双眼皮，这也叫美女？<\\/p><p>　　你们终于觉得这才是美女了！审美终于回归正常！但是！民国时期的美女还是有很多美过她的，而后来演绎这些美女的人，更是人比花娇。<\\/p>" +
            "<\\/p><p><img title=\"\" src=\"http://img1.cache.netease.com/catchpic/7/7F/7F9C353236E073FA3FD66708AFA58935.png\"width=\"300\" height=\"291\">" +
            "<p>　　<strong>1.侠妓小凤仙<\\/strong><\\/p><p>　　小凤仙是一代奇女子，1900 年 8 月出生在杭州，其父是没落的满八旗武官。正史中并没有关于她的记载，甚至连她的生卒日期都无法明确，但是她却是一位名动公卿的名妓，因为她与共和名将蔡锷有一段传颂至今的至死不渝的爱情。<\\/p>" +
            "<\\/p><p><img title=\"\" src=\"http://img1.cache.netease.com/catchpic/E/E2/E2BF39B9BF83F7F97C1815BE3EB1141B.jpg\"width=\"300\" height=\"291\">" +
            "<p>　　出现作品：<\\/p><p>　　<strong>1.《逃之恋》——刘晓庆<\\/strong><\\/p><p>　　你能看得出来这时的刘晓庆已经 43 岁了么！完全的冰冻美貌，配合着她精湛的演技，这段本就让人震撼的爱情更显伟大。<\\/p><p>　　但是，大眼睛双眼皮的刘晓庆和小眼睛单眼皮的小凤仙在外貌相似度上不是很高，小凤仙更显恬静一些。<\\/p>" +
            "<img src=\"http://img1.cache.netease.com/catchpic/9/9E/9E2326A769BA46AFF6D84B5C7B043004.jpg\" width=\"300\" height=\"291\" title=\"\" style=\"cursor: pointer;\">" +
            "<p>　　<strong>2.《小凤仙的故事》——蒋勤勤<\\/strong><\\/p><p>　　蒋勤勤嫁给陈建斌的时候我的内心是崩溃的，碧波荡漾的双眼太勾引人了！举手投足间满是风情，感觉自己要被掰弯了。<\\/p><p>　　同上，外形和小凤仙也不够贴近，蒋勤勤的眼镜可比小凤仙会说话多了！<\\/p>" +
            "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/A/A3/A399C146A9AB5CE02C19786CFAB23536.jpg\"width=\"300\" height=\"291\">" +
            "<p>　　<strong>3.《蔡锷与小凤仙》——周海媚<\\/strong><\\/p><p>　　当年艳绝峨眉派的周芷若演起小凤仙来并不是很合适，比较现代五官民国风情欠缺啊。<\\/p>" +
            "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/B/B3/B37E70109F28255BA847908985E1C057.jpg\"width=\"300\" height=\"291\">";

    private RichEditTextView mRichText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_richedittextview);
        
        setToolbarTitle("RichEditTextView");
        mRichText = (RichEditTextView) findViewById(R.id.tv_news_detail_body);
        mRichText.setRichText(body);
    }
}