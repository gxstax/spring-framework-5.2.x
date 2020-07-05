package com.ant.anntation.selfdesign;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/7/4 5:19 下午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyConfiguration {

	/**
	 * 名称属性
	 */
	String name();
}
