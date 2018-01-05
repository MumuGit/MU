package com.mu.example.myapplication;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by mu on 2018/1/4.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AScope {
}
