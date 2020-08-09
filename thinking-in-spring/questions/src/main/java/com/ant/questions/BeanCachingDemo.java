package com.ant.questions;

import com.ant.questions.conditional.MyConditional;
import com.ant.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * <p>
 * Bean 是否缓存示例
 * </p>
 *
 * @author Ant
 * @since 2020/8/8 11:59 上午
 */
public class BeanCachingDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configuration class
		context.register(BeanCachingDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		User user = context.getBean(User.class);

		for (int i = 0; i < 9; i++) {
			// Singleton Scope Bean 是否缓存 （结果为 true 说明单例Bean 是会缓存的）
			System.out.println(user == context.getBean(User.class));
		}

		// 关闭 Spring 应用上下文
		context.close();
	}


	@Bean
	@Conditional(value = MyConditional.class)
//	@Scope("prototype")
	public static User user() {
		User user = new User();
		user.setId(1L);
		user.setName("马以");
		return user;
	}
}
