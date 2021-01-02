package com.ant.aop.features;

import com.ant.aop.features.aspect.AspectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>
 * Pointcut 示例
 * </p>
 *
 * @author Ant
 * @since 2021/1/1 4:10 下午
 */
@Configuration // Configuration class
@EnableAspectJAutoProxy // 激活 Aspect 注解自动代理
public class AspectJAnnotationPointCutDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(AspectJAnnotationPointCutDemo.class, AspectConfiguration.class);

		// 启动 Spring 容器
		context.refresh();

		AspectJAnnotationPointCutDemo aspectJAnnotationDemo = context.getBean(AspectJAnnotationPointCutDemo.class);

		aspectJAnnotationDemo.execute();

		// 关闭 Spring 容器
		context.close();
	}

	public void execute() {
		System.out.println("execute()....");
	}

}
