package com.ant.bean.lifecycle;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * <p>
 * Bean 元信息配置示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/18 10:23 上午
 */
public class BeanMetadataConfigurationDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 实例化基于 Properties 资源的 BeanDefinitionReader
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

		String location = "META-INF/user.properties";

		// 定义字符编码为 UTF-8
		Resource resource = new ClassPathResource(location);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
		int num = beanDefinitionReader.loadBeanDefinitions(encodedResource);

		System.out.printf("共加载了 %d 条 BeanDefinition %n", num);

		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

	}
}
