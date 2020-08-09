package com.ant.questions;


import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * <p>
 * {@link ObjectFactory} 延迟依赖查找示例
 * </p>
 *
 * @author Ant
 * @since 2020/8/8 10:40 上午
 *
 *
 */
public class ObjectFactoryLazyLookupDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configuration class
		context.register(ObjectFactoryLazyLookupDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		ObjectFactoryLazyLookupDemo objectFactoryLazyLookupDemo = context.getBean(ObjectFactoryLazyLookupDemo.class);

		ObjectFactory<User> userObjectFactory = objectFactoryLazyLookupDemo.userObjectFactory;
		ObjectProvider<User> userObjectProvider = objectFactoryLazyLookupDemo.userObjectProvider;

		System.out.println("userObjectFactory == userObjectProvider :" +
				(userObjectFactory == userObjectProvider));

		System.out.println("userObjectFactory.getClass() == userObjectProvider.getClass() : " +
				(userObjectFactory.getClass() == userObjectProvider.getClass()));


		System.out.println("user = " + userObjectFactory.getObject());
		System.out.println("user = " + userObjectProvider.getObject());
		System.out.println("user = " + context.getBean(User.class));


		// 关闭 Spring 应用上下文
		context.close();
	}

	@Autowired
	public ObjectFactory<User> userObjectFactory;

	@Autowired
	private ObjectProvider<User> userObjectProvider;

	@Bean
	@Lazy
	public static User user() {
		User user = new User();
		user.setId(1L);
		user.setName("马以");
		return user;
	}
}
