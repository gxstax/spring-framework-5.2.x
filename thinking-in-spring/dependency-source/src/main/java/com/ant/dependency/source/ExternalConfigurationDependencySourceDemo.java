package com.ant.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * <p>
 * 外部化配置作为依赖来源
 * </p>
 *
 * @author Ant
 * @since 2020/5/17 2:25 下午
 */
@Configuration
@PropertySource(value = "META-INF/default.properties", encoding = "UTF-8")
public class ExternalConfigurationDependencySourceDemo {

	@Value("${user.id:-1}")
	private Long id;

	// 如果我们这里写 user.name 它优先会去找java 环境变量 所以我们这里改一个名称
	// 当然我们这里写中文还会存在一个乱码的问题, 我们可以设置 @PropertySource 的编码格式
	@Value("${usr.name}")
	private String name;

	@Value("${user.resource:classpath://META-INF/default.properties}")
	private Resource resource;

	public static void main(String[] args) {
		// 初始化Spring上下文环境
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册Configuration Class(配置类) ->Spring Bean(配置类也是spirng的bean)
		context.register(ExternalConfigurationDependencySourceDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		// 依赖查找 ExternalConfigurationDependencySourceDemo Bean
		ExternalConfigurationDependencySourceDemo demo = context.getBean(ExternalConfigurationDependencySourceDemo.class);

		System.out.println("demo.id " + demo.id);

		System.out.println("demo.name " + demo.name);

		System.out.println("demo.resource " + demo.resource);

		// 显式的关闭Spring应用上下文
		context.close();
	}
}
