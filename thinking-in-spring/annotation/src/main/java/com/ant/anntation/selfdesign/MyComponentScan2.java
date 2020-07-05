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
 * 自定义 {@link Component} 层次性扫描
 * </p>
 *
 * @author GaoXin
 * @since 2020/7/4 5:43 下午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponentScan
public @interface MyComponentScan2 {

	@AliasFor(annotation = MyComponentScan.class, attribute = "scanBasePackages") // 隐性别名
			// 多态，子注解提供的属性方法引用"父"（元）注解的中的属性方法
			// 这里的例子就相当于 使用 @MyComponentScan(scanBasePackages ='') 相当于使用了 CompinentScan(basePackages='')
	String[] basePackages() default {};

	// @MyComponentScan2.basePackages -> @MyComponentScan.scanBasePackages ->
	//			@AliasFor @ComponentScan.basePackages -> @AliasFor @ComponentScan.value (显性别名)

	/**
	 * 与元注解 {@link MyComponentScan} 同名注解
	 *
	 * 如果 MyComponentScan 注解中也存在 scanBasePackages 属性
	 * 那么 MyComponentScan 会覆盖掉  MyComponentScan2 中的属性
	 */
	String[] scanBasePackages() default {"#"};

}
