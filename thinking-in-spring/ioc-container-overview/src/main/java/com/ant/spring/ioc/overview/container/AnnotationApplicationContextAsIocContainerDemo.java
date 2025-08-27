package com.ant.spring.ioc.overview.container;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * <p>
 * {@link ApplicationContext} 作为 IOC 容器示例
 * </p>
 *
 * @author <a href="mailto:gxstaxant@gmail.com">Ant</a>
 * @since 2025/8/27 12:46
 */
public class AnnotationApplicationContextAsIocContainerDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.register(AnnotationApplicationContextAsIocContainerDemo.class);
		// 启动应用上下文
		applicationContext.refresh();

		lookupByCollectionType(applicationContext);
	}

	@Bean
	public User user() {
		User user = new User();
		user.setName("Ant");
		return user;
	}

	private static void lookupByCollectionType(BeanFactory beanFactory) {
		if(beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
			System.out.println("查找到的所有的 User 集合对象：" + beansOfType);
		}
	}
}
