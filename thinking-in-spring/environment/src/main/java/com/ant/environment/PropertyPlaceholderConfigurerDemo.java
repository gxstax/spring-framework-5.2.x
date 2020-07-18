package com.ant.environment;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * <p>
 * {@link PropertyPlaceholderConfigurer} 处理属性占位符示例
 * </p>
 *
 * @author Ant
 * @since 2020/7/12 6:51 下午
 */
@SuppressWarnings("unchecked")
public class PropertyPlaceholderConfigurerDemo {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("META-INF/dependency-setter-injection.xml");


		final User user = context.getBean("user", User.class);
		System.out.println(user.toString());

		// 关闭应用上下文
		context.close();
	}
}
