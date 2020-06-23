package com.ant.conversion;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * Spring 自定义 {@link java.beans.PropertyEditor} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/23 8:39 上午
 */
public class SpringCustomizedPropertyEditorDemo {
	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/property-editors-context.xml");


		User user = context.getBean("user", User.class);

		System.out.println(user);

		// 关闭应用上下文
		context.close();
	}
}
