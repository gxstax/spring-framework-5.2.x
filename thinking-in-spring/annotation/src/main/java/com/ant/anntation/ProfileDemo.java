package com.ant.anntation;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * <p>
 * {@link Profile} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/7/12 2:41 下午
 *
 * @see Profile
 * @see Environment#getActiveProfiles()
 */
@Configuration
public class ProfileDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 当前类为 Configuration class
		context.register(ProfileDemo.class);

		// 获取 Environment 对象（可配置的）
		ConfigurableEnvironment environment = context.getEnvironment();
		// 默认 profiles = ["odd"] (兜底 profiles)
		environment.setDefaultProfiles("odd");

//		environment.setActiveProfiles("even");

		// -Dspring.profiles.active=even

		// 启动 Spring 应用上下文
		context.refresh();

		final Integer number = context.getBean("number", Integer.class);
		System.out.println(number);


		// 关闭 Spring 应用上下文
		context.close();
	}


	@Bean(value = "number")
	@Profile("odd")
	public Integer odd() {
		return 1;
	}

	@Bean(value = "number")
//	@Profile("even")
	@Conditional(EvenConditional.class)
	public Integer even() {
		return 2;
	}
}
