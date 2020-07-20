package com.ant.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * {@link org.springframework.beans.factory.annotation.Value} 示例
 * </p>
 *
 * @author GaoXin
 * @since 2020/7/18 5:15 下午
 */
public class ValueAnnotationDemo {

	@Value("${user.name}")
	private String userName;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册 Configuration class
		context.register(ValueAnnotationDemo.class);

		// 开启 Spring 应用上下文
		context.refresh();

		ValueAnnotationDemo valueAnnotationDemo = context.getBean(ValueAnnotationDemo.class);

		System.out.println(valueAnnotationDemo.userName);

		// 关闭 Spring 应用上下文
		context.close();
	}
}
