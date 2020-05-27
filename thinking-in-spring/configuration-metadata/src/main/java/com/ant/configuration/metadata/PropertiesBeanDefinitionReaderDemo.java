package com.ant.configuration.metadata;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * <p>
 * {@link PropertiesBeanDefinitionReader} 示例
 * </p>
 *
 * @author GaoXin
 * @since 2020/5/27 9:09 上午
 */
@SuppressWarnings("uncheck")
public class PropertiesBeanDefinitionReaderDemo {

	public static void main(String[] args) {
//		// 创建 IOC 底层容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

//		// 创建面线 properties 资源的 BeanDefinitionReader 示例
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

		ResourceLoader resourceLoader = new DefaultResourceLoader();
		// 通过 classPath 获取 resource 对象
		Resource resource = resourceLoader.getResource("classpath:/META-INF/user-config.properties");
		// 转换为带有字符编码的 EncodedResource 对象
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

		int bdCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);
		System.out.printf("已经加载了 %d 个 BeadDefinition \n", bdCount);
//
		User user = beanFactory.getBean(User.class);
		System.out.println(user);

	}

}
