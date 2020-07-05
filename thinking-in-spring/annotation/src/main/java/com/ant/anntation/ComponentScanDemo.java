package com.ant.anntation;

import com.ant.anntation.selfdesign.MyComponentScan;
import com.ant.anntation.selfdesign.MyComponentScan2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * <p>
 * {@link Component} 扫描示例
 * </p>
 *
 * @author Ant
 * @since 2020/7/3 1:01 下午
 *
 * @see Component
 * @see ComponentScan
 */
// basePackages @AliasFor value()
// value() @AliasFor basePackages()
//@ComponentScan(basePackages = "com.ant")
//@ComponentScan(value = "com.ant")

// 自定义扫描注解
//@MyComponentScan(scanBasePackages = "com.ant")
//@MyComponentScan(scanBasePackagesValue = "com.ant")
@MyComponentScan2(basePackages = "com.ant")
public class ComponentScanDemo {

	private ThreadLocal<String> str = new ThreadLocal<>();
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 当前类为 Configuration class
		context.register(ComponentScanDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		// 依赖查找 DemoClass Bean
		// DemoClass 标注了 @MyComponent2
		// @MyComponent2 <- MyComponent <- Component
		DemoClass demoClass = context.getBean(DemoClass.class);
		System.out.println(demoClass);

		// 关闭 Spring 应用上下文
		context.close();

	}
}
