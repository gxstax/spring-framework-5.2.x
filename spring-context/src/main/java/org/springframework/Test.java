package org.springframework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2020/5/12 5:56 下午
 */
public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Test.class);
		context.refresh();

		Test bean = context.getBean(Test.class);
		System.out.println(bean);
		context.close();
	}
}
