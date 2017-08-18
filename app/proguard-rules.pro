-keep class android.net.http.SslError
-keep class android.webkit.**{*;}
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class com.alipay.share.sdk.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-keep class com.mob.**{*;}

#Mob Share SDK 
-dontwarn com.mob.**
-dontwarn cn.sharesdk.**
-dontwarn **.R$*

#Gson
-keep class com.test.model.response.** {*;}
-keepattributes Signature 
-keep class com.google.gson {*;}
-keep class sun.misc.Unsafe {*;}
-keep class * implements com.zcolin.frame.http.entity {*;}

# OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


#greenDao混淆
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use Rx:
-dontwarn rx.**


#Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep public class * implements java.io.Serializable {*;}

#js调用不被混淆
-keepattributes JavascriptInterface

#排除所有注解类
-keepattributes *Annotation*
-keep class * extends java.lang.annotation.Annotation { *; }
-keep interface * extends java.lang.annotation.Annotation { *; }

#使用ZClick注解的函数不混淆
-keep,allowobfuscation @interface com.fosung.lighthouse.amodule.base.ZClick 
-keepclassmembers class * {  
    @com.fosung.lighthouse.amodule.base.ZClick *;  
}  
