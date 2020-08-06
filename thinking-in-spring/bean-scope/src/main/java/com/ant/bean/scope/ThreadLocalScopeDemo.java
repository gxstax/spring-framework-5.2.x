package com.ant.bean.scope;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * <p>
 *	自定义 Scope {@link ThreadLocalScope} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/8/5 9:34 上午
 */
public class ThreadLocalScopeDemo {

	@Bean
	@Scope(ThreadLocalScope.SCOPE_NAME)
	public User user() {
		return createUser();
	}

	public static User createUser() {
		User user = new User();
		user.setId(System.nanoTime());
		return user;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(ThreadLocalScopeDemo.class);

		context.addBeanFactoryPostProcessor(beanFactory -> {
			// 注册自定义 Scope
			beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
		});

		// 启动 Spring 应用上下文
		context.refresh();

		scopeBeansByLookUp(context);

		// 关闭 Spring 应用上下文
		context.close();
	}

	public static void scopeBeansByLookUp(AnnotationConfigApplicationContext context) {
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(() -> {
				// user 是共享 Bean 对象
				User user = context.getBean("user", User.class);
				System.out.println("user-->" + user);
			});

			thread.start();

		}
	}

	private static void scopeBeansByInjection(AnnotationConfigApplicationContext context) {

	}
}
