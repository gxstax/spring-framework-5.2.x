package com.ant.bean.lifecycle;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * <p>
 * BeanDefinition 合并示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/19 9:03 上午
 */
public class MergedBeanDefinitionDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 基于 XML  资源的 BeanDefinitionReader 实现
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		String location = "META-INF/dependency-lookup-context.xml";

		// 定义字符编码为 UTF-8
		Resource resource = new ClassPathResource(location);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
		int num = beanDefinitionReader.loadBeanDefinitions(encodedResource);

		System.out.printf("已加载了 %d 条 BeanDefinition %n", num);

		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

		User superUser = beanFactory.getBean("superUser", User.class);
		System.out.println(superUser);

	}
}
