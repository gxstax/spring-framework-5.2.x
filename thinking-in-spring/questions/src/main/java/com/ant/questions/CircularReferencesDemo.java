package com.ant.questions;

import com.ant.questions.referenceclass.ClassRoom;
import com.ant.questions.referenceclass.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 * 循环引用（依赖）示例
 * </p>
 *
 * @author Ant
 * @since 2020/8/8 5:41 下午
 */
public class CircularReferencesDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configuration class
		context.register(CircularReferencesDemo.class);

		// 设置是否允许循环引用
		// 默认为true 如果设置为 false，则抛出异常信息如：currently in creation: Is there an unresolvable circular reference?
		context.setAllowCircularReferences(true);

		// 启动 Spring 应用上下文
		context.refresh();

		System.out.println("Student : " + context.getBean(Student.class));
		System.out.println("ClassRoom : " + context.getBean(ClassRoom.class));

		// 关闭 Spring 应用上下文
		context.close();
	}


	@Bean
	public static Student student() {
		Student student = new Student();
		student.setId(1L);
		student.setName("马以");
		return student;
	}

	@Bean
	public static ClassRoom classRoom() {
		ClassRoom classRoom = new ClassRoom();
		classRoom.setName("A01");
		return classRoom;
	}

}
