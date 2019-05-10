package com.kotlin.demo.di.qualifier;

import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/19
 * Desc :
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface MainUrl {
}
