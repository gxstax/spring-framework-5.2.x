package com.ant.bean.lifecycle;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * <p>
 * 注解 {@link BeanDefinition} 解析示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/18 11:41 上午
 */
public class AnnotationBeanDefinitionParsingDemo {
	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 基于 java 注解的 BeanDefinitionReader 实现
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
		int beforeCount = beanFactory.getBeanDefinitionCount();

		// 注册当前 Bean (非 @Component 注解的类)
		reader.register(AnnotationBeanDefinitionParsingDemo.class);
		int afterCount = beanFactory.getBeanDefinitionCount();

		int beanDefinitonCount = afterCount - beforeCount;
		System.out.printf("已加载了 %d 个 BeanDefinition %n", beanDefinitonCount);

		// 普通的 Class 作为 Component 注册到 Spring IOC 容器中后，通常 Bean 的名字为 首字母小写的类名 annotationBeanDefinitionParsingDemo
		// Bean 的名称生成来自于 BeanNameGenerator 类; 注解的生成是来自于 AnnotationBeanNameGenerator
		// 同样的我们还可以通过 AnnotatedBeanDefinitionReader#setBeanNameGenerator()来设置我们自己定义命名规则
		AnnotationBeanDefinitionParsingDemo demo = beanFactory.getBean("annotationBeanDefinitionParsingDemo",
						AnnotationBeanDefinitionParsingDemo.class);
		System.out.println(demo);

	}
}
