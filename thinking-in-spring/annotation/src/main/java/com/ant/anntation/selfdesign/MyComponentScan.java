package com.ant.anntation.selfdesign;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 自定义 {@link Component} 扫描
 * </p>
 *
 * @author GaoXin
 * @since 2020/7/4 5:43 下午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan
public @interface MyComponentScan {

	@AliasFor(annotation = ComponentScan.class, attribute = "basePackages") // 隐性别名
			// 多态，子注解提供的属性方法引用"父"（元）注解的中的属性方法
			// 这里的例子就相当于 使用 @MyComponentScan(scanBasePackages ='') 相当于使用了 CompinentScan(basePackages='')
	String[] scanBasePackages() default {};

	// scanBasePackages ->
	//			@AliasFor @ComponentScan.basePackages ->
	// 				 @AliasFor @ComponentScan.value (显性别名)

	@AliasFor(annotation = ComponentScan.class, attribute = "value") // 隐性别名
	String[] scanBasePackagesValue() default {};
}
