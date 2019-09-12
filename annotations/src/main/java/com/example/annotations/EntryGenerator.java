package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@Target(ElementType.TYPE) 此注解代表作用于类上
 *@Retention(RetentionPolicy.SOURCE) 此注解代表在源码上处理的
 *Class<?> entryTemplate();
 *String packageName();
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {
    String packageName();
    Class<?> entryTemplate();
}
