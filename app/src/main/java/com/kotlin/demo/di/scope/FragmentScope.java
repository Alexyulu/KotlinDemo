package com.kotlin.demo.di.scope;

import javax.inject.Scope;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */

@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
