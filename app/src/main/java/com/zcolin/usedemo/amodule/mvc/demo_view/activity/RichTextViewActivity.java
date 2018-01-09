/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvc.demo_view.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.zcolin.outlib.views.richtext.RichTextView;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;

public class RichTextViewActivity extends BaseActivity {

    private String body = 
            "<p>　　最近，“民国无名女神”在微博上火了。讲真，这位无名女神是比现在那些整容脸、网红脸让人看的赏心悦目。但是，比起那些年轰动的民国美女和才女们，她还是略有逊色。要知道这些美女和才女们，不论是本尊还是是后世扮演她们的演员，真真是极美的。这些人里有你心中女神么？<\\/p><p" +
                    ">　　就是这张相册里的老旧照片让民国时期的美女们再次被大家想起。并不是现在当红的蛇精脸，是肉肉的小圆脸，也没有冲破天际的高鼻梁和比眼睛都要宽的双眼皮，这也叫美女？<\\/p><p" +
                    ">　　你们终于觉得这才是美女了！审美终于回归正常！但是！民国时期的美女还是有很多美过她的，而后来演绎这些美女的人，更是人比花娇。<\\/p>" + "<\\/p><p><img title=\"\" src=\"http://img1.cache.netease" +
                    ".com/catchpic/7/7F/7F9C353236E073FA3FD66708AFA58935.png\"width=\"300\" height=\"291\">" + 
                    "<p>　　<strong>1.侠妓小凤仙<\\/strong><\\/p><p>　　小凤仙是一代奇女子，1900 年 8 " +
                    "月出生在杭州，其父是没落的满八旗武官。正史中并没有关于她的记载，甚至连她的生卒日期都无法明确，但是她却是一位名动公卿的名妓，因为她与共和名将蔡锷有一段传颂至今的至死不渝的爱情。<\\/p>" + "<\\/p><p><img title=\"\" " +
                    "src=\"http://img1.cache.netease.com/catchpic/E/E2/E2BF39B9BF83F7F97C1815BE3EB1141B.jpg\"width=\"300\" height=\"291\">" + 
                    "<p>　　出现作品：<\\/p><p>　　<strong>1.《逃之恋》——刘晓庆<\\/strong><\\/p><p>　　你能看得出来这时的刘晓庆已经 43 " +
                    "岁了么！完全的冰冻美貌，配合着她精湛的演技，这段本就让人震撼的爱情更显伟大。<\\/p><p>　　但是，大眼睛双眼皮的刘晓庆和小眼睛单眼皮的小凤仙在外貌相似度上不是很高，小凤仙更显恬静一些。<\\/p>" + "<img src=\"http://img1.cache" +
                    ".netease.com/catchpic/9/9E/9E2326A769BA46AFF6D84B5C7B043004.jpg\" width=\"300\" height=\"291\" title=\"\" style=\"cursor: pointer;\">" +
                    "<p>　　<strong>2.《小凤仙的故事》——蒋勤勤<\\/strong><\\/p><p>　　蒋勤勤嫁给陈建斌的时候我的内心是崩溃的，碧波荡漾的双眼太勾引人了！举手投足间满是风情，感觉自己要被掰弯了。<\\/p><p" +
                    ">　　同上，外形和小凤仙也不够贴近，蒋勤勤的眼镜可比小凤仙会说话多了！<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease" +
                    ".com/catchpic/A/A3/A399C146A9AB5CE02C19786CFAB23536.jpg\"width=\"300\" height=\"291\">" + 
                    "<p>　　<strong>3.《蔡锷与小凤仙》——周海媚<\\/strong><\\/p><p>　　当年艳绝峨眉派的周芷若演起小凤仙来并不是很合适，比较现代五官民国风情欠缺啊。<\\/p>" + "<img title=\"\" " +
                    "src=\"http://img1.cache.netease.com/catchpic/B/B3/B37E70109F28255BA847908985E1C057.jpg\"width=\"300\" height=\"291\">" + 
                    "<p>　　<strong>4.《建党伟业》——Anglebaby<\\/strong><\\/p><p>　　史上最让人跳戏的小凤仙，Baby 混血儿的立体五官和小凤仙完全不一样好么！高于眉毛的呆萌齐刘海把 Baby 的灵气都挡住了！<\\/p>" + "<img " +
                    "title=\"\" src=\"http://img1.cache.netease.com/catchpic/8/86/8673C6784F60D95CFA87886DC769AC71.jpg\"width=\"300\" height=\"291\">" + 
                    "<p>　　<strong>|校园皇后陆小曼|<\\/strong><\\/p><p>　　作为民国四大才女之一，1903 年 11 月 7 " +
                    "日出生的陆小曼不仅谙昆曲，还能演皮黄，写得一手好文章，有深厚的古文功底和扎实的文字修饰能力。而她和徐志摩不顾一切的相爱在当时也是轰动社会的大事，顶着家人反对和外界批判，他们毅然相爱相守。<\\/p>" + "<img title=\"\" " +
                    "src=\"http://img1.cache.netease.com/catchpic/7/73/7379EC0917F33DE0CC53FFAFAA028E08.jpg\"width=\"300\" height=\"291\">" + 
                    "<p>　　出现作品：<\\/p><p>　　<strong>1.《人间四月天》——伊能静<\\/strong><\\/p><p>　　这时的伊能静一脸胶原蛋白，小女生的娇羞拿捏得恰到好处，陆小曼的一嗔一笑都被她演活了，也难怪她因位入戏太深哭到不能自己。<\\/p>" + 
                    "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/8/8E/8EC06C42A3973A2B0CBE413B23B58885.jpg\"width=\"300\" height=\"291\">" +
                    "<p>　　<strong>2.《建党伟业》——车永莉<\\/strong><\\/p><p>　　和伊能静的女儿家娇羞不同，车永莉比较成熟，可能是造型的问题，让她有种“百乐门头牌”的感觉。完全不符陆小曼的才女形象啊！<\\/p>" + "<img title=\"\" " +
                    "src=\"http://img1.cache.netease.com/catchpic/D/D9/D9D11773FB77829266C417AE85632C48.jpg\"width=\"300\" height=\"291\">" + 
                    "<p>　　<strong>|才气与美貌并重林徽因|<\\/strong><\\/p><p>　　林徽因 1904 年 6 月 10 日出生于杭州，是 " +
                    "中国著名建筑师、诗人、作家，人民英雄纪念碑和中华人民共和国国徽深化方案的设计者、建筑师梁思成的第一任妻子。林徽因梁思成相爱相知、金岳霖对林徽因深爱相守，这三个人在这样微妙的关系下毗邻而居，着实令人佩服。<\\/p>" + "<img title=\"\" " +
                    "src=\"http://img1.cache.netease.com/catchpic/C/C9/C9D3EFBE7F37AEE1ED6B2276E1F0540A.jpg\"width=\"300\" height=\"291\">" + 
                    "<p>　　出现作品：<\\/p><p>　　<strong>《人间四月天》——周迅<\\/strong><\\/p><p" +
                    ">　　讲真，曾几何时林徽因在我心中就是一枚高级“绿茶婊”，嫁了梁思成、吊着金岳霖，想当年对她是无比的不屑。直到我超爱的迅哥演了林徽因，让我这没脑残粉屁颠儿屁颠儿的去看了林徽因的作品，才真的被她的才华所折服。<\\/p><p" +
                    ">　　不论戏里戏外都为爱执着，周迅赋予了林徽因更多的灵气，让人根本讨厌不起来啊！<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease" +
                    ".com/catchpic/3/34/34FA63B934F191BC243D2B02DCA7F6DE.jpeg\"width=\"300\" height=\"291\">" + 
                    "<p>　　<strong>|冬皇孟小冬|<\\/strong><\\/p><p>　　孟小冬 1907 " +
                    "年生于北平宛平（今北京），是早年京剧优秀的女老生。人称“冬皇”，她是京剧著名老生余叔岩的弟子，余派的优秀传人之一。她的扮相威武、神气，唱腔端严厚重，坤生略无雌声。然而天妒英才，孟小冬的人生充满坎坷，她和梅兰芳的相爱而不能相守，和杜月笙经过时间打磨的相伴都是她人生中喜悲各半的经历。<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/6/66/6682FB56F060EAEAE08ED97D7F46D958.jpg\"width=\"300\" height=\"291\">" + "<p>　　出现作品：<\\/p><p>　　<strong>《梅兰芳》——章子怡<\\/strong><\\/p><p>　　章子怡于孟小冬，是极为合适的，正如她自己所说“孟小冬身上有我全部的幻想”，她的敢爱敢恨在那个年代虽然不算独树一帜，但也不是大众所能完全接受的。她和梅兰芳情断后的决绝，对杜月笙相伴到死的情谊都是值得赞赏的。<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/8/89/890F720EA7110CF330E21BE010EE5371.jpg\"width=\"300\" height=\"291\">" + "<p>　　<strong>|默片皇后阮玲玉|<\\/strong><\\/p><p>　　阮玲玉 1910 年 4 月 26 日生于上海，是中国无声电影时期著名影星，民国四大美女之一。<\\/p><p>　　阮玲玉有着精湛的演技和悲惨的人生。父亲早逝，母亲为人帮佣，成名后陷于同张达民和唐季珊的名誉诬陷纠纷案，因不堪舆论诽谤于 1935 年妇女节当日服安眠药自尽。她一生都没能获得过极致的幸福。<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/6/6C/6C9ACC4C1D10D0F8E2DD17A5561601DA.png\"width=\"300\" height=\"291\">" + "<p>　　出现作品：<\\/p><p>　　<strong>《阮玲玉》——张曼玉<\\/strong><\\/p><p>　　张曼玉把阮玲玉骨子里的悲哀完全释放出来，举手投足间满是哀怨。为了错误的爱情搭上自己的名誉和性命，真心替她不值。<\\/p><p>　　真心觉得没有人穿旗袍比张曼玉更美，从《花样年华》到《阮玲玉》，张曼玉上演了一场唯美的旗袍秀。<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/5/57/57B926AF8519A5737DA6F9482C13D1FF.jpg\"width=\"300\" height=\"291\">" + "<p>　　<strong>|文学洛神萧红|<\\/strong><\\/p><p>　　萧红 1911 年 6 月 1 日出生于黑龙江省哈尔滨市呼兰区一个封建地主家庭，她是中国近现代女作家，“民国四大才女”之一，被誉为“ 20 世纪 30 年代的文学洛神”。萧红一生坎坷，颠沛流离，最终因肺结核和恶性气管扩张病逝于香港，年仅 31 岁。这样一个短暂却又传奇的人生让她的作品变得更加珍贵。<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/2/23/237A7A4351856720A6ED83E08E857C28.jpg\"width=\"300\" height=\"291\">" + "<p>　　出现作品：<\\/p><p>　　<strong>1.《萧红》——宋佳<\\/strong><\\/p><p>　　宋佳身上有一种自带的倔强，眉眼间真的蛮有萧红的神韵。但是宋佳的倔强让她的柔情色彩略显失色。<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/E/E3/E31DF4A16D96A4E3D698A0AEC5A1A82D.jpg\"width=\"300\" height=\"291\">" + "<p>　　<strong>2.《黄金时代》——汤唯<\\/strong><\\/p><p>　　有多少人觉得汤唯是一个文艺女神？有人说她把萧红诠释的太过文艺，但是我觉得她的表演恰到好处。一个有着文艺情怀的作家写出的文章可能会更有感情色彩吧。<\\/p><p>　　而且我一定要说：汤唯在《黄金时代》美！爆！了！<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/6/67/67654BCFC88E14D94004EA484BAD2249.jpg\"width=\"300\" height=\"291\">" + "<p>　　<strong>|绝代佳侣赵一荻|<\\/strong><\\/p><p>　　比起赵一荻，似乎你会觉得赵四小姐更为熟悉。1912 年 5 月 28 日出生于香港，是张学良的第二任妻子，也是陪伴张学良最久的女人。赵四小姐就是民国时期的“白富美”，在豆蔻年华遇到了潇洒倜傥的张学良，两人迅速堕入爱河，面对种种阻碍相爱相守，直至百年。<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/2/25/25BA18720A9E295E90479E048AEB8AAF.jpeg\"width=\"300\" height=\"291\">" + "<p>　　出现作品：<\\/p><p>　　<strong>《少帅》——张歆怡<\\/strong><\\/p><p>　　不是只有我一个人觉得她长得像女版佟大为对不对！赵四小姐立体的五官呢？好吧，这孩子太小没长开。<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/C/C8/C856E3C0517B3E07985A6FE51F19E43E.jpg\"width=\"300\" height=\"291\">" + "<p>　　<strong>|金嗓子周璇|<\\/strong><\\/p><p>　　周璇 1920 年 8 月 1 日出生于江苏常州，是知名的中国电影演员、歌唱家。在艺术上颇具天分的她感情生活却十分不顺，有过两段婚姻、三段恋情，但无一善终。<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/7/73/73B6B668BA8696D37A49675197C7D2B4.jpg\"width=\"300\" height=\"291\">" + "<p>　　出现作品：<\\/p><p>　　<strong>《天涯歌女》——张柏芝<\\/strong><\\/p><p>　　很多人都说张柏芝演的周璇美则美矣没有灵魂，周璇曾经的活泼清纯她并没有很好的诠释出来。<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/A/A9/A9871113DCFB2834C5B5E2A6819C25BC.jpg\"width=\"300\" height=\"291\">" + "<p>　　<strong>|小说大家张爱玲<\\/strong><strong>|<\\/strong><\\/p><p>　　张爱玲 1920 年 9 月 30 日出生在上海公共租界西区一幢没落贵族府邸，是中国现代非常有名的作家。我们看到的许多经典电影都是根据她的小说改编而成。也许与她的人生经历有关，她的作品中总是蕴藏着一种悲凉情怀，她非常喜欢用比喻反讽等手法来多描写小人物的悲欢离合。<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/0/0A/0A69F757F40AF1F301AD169DDE00C29F.jpg\"width=\"300\" height=\"291\">" + "<p>　　出现作品：<\\/p><p>　　<strong>《上海往事》——刘若英<\\/strong><\\/p><p>　　没想到刘若英可以为张爱玲赋予这样生动的灵魂，毕竟当时《粉红女郎》里那个恨嫁的方小萍太过深入人心。张爱玲可能会有的一颦一笑都被刘若英展现的淋漓尽致，人家是演活一个角色，她是演痴了一个角色。从外形到举止，你真的快要看不出她只是在演戏。<\\/p>" + "<img title=\"\" src=\"http://img1.cache.netease.com/catchpic/8/8A/8A5B916C89481DA5BE6AAC0740D56B2C.jpg\"width=\"300\" height=\"291\">" + " <img title=\"\" src=\"http://ww4.sinaimg.cn/large/5cfc088ejw1f3jcujb6d6g20ap08mb2c.gif\"width=\"300\" height=\"291\">" + "<p>　　这些民国女神们，不论本尊还是演员，你最喜欢谁？<\\/p>" + "<a href=\"http://3g.163.com/ntes/special/0034073A/article_share.html?docid=BG6CGA9M00264N2N&spst=0&spss=newsapp&spsf=wx&spsw=1\" target=\"_blank\" rel=\"nofollow\">点击这里查看更多哦</a>" + "";

    private RichTextView mRichText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_richtextview);

        setToolbarTitle("RichTextView");
        mRichText = getView(R.id.tv_news_detail_body);
        mRichText.setOnImageClickListener((imageUrls, position) -> Toast.makeText(RichTextViewActivity.this, imageUrls.get(position), Toast.LENGTH_SHORT)
                                                                        .show());

        mRichText.setRichText(body);
    }
}