package com.ant.configuration.metadata;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * <p>
 * Spring XML 元素扩展示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/29 1:06 上午
 */
public class ExtensibleXmlAuthoringDemo {
	public static void main(String[] args) {
		// 创建 IOC 底层容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 创建 XML 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		// 加载 XML 资源
		beanDefinitionReader.loadBeanDefinitions("META-INF/user-context.xml");

		User bean = beanFactory.getBean(User.class);

		System.out.println(bean);
	}
}
