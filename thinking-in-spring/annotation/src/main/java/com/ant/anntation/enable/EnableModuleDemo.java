package com.ant.anntation.enable;

import com.ant.anntation.ComponentScanDemo;
import com.ant.anntation.DemoClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * Enable 模块驱动示例
 * </p>
 *
 * @author Ant
 * @since 2020/7/4 6:32 下午
 */
@EnableHelloWorld
public class EnableModuleDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 当前类为 Configuration class
		context.register(ComponentScanDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		System.out.println(context.getBean(HelloWorldConfiguration.class));

		// 关闭 Spring 应用上下文
		context.close();

	}
}
