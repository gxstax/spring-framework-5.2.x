package com.ant.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/1/1 4:10 下午
 */
@Aspect
@Configuration
public class AspectJAnnotationDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AspectJAnnotationDemo.class);
		// 启动 Spring 容器
		context.refresh();

		AspectJAnnotationDemo aspectJAnnotationDemo = context.getBean(AspectJAnnotationDemo.class);

		// 关闭 Spring 容器
		context.close();
	}

}
