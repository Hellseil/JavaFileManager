package com.filemanager.inifile;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// アノテーションの定義
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface  KeyNameAnnotation {
    String value();
}
