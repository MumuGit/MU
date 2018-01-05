package com.mu.example.myapplication;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by mu on 2018/1/4.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface  PoetryQualifier {
    String value() default "";
}
