package com.ant;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/5/12 6:24 下午
 */
public class ApplicationTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ApplicationTest.class);
		context.refresh();



		context.close();
	}
}
