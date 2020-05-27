package com.ant.configuration.metadata;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
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
//
//		// 创建面线 properties 资源的 BeanDefinitionReader 示例
		BeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
//
		int bdCount = beanDefinitionReader.loadBeanDefinitions("META-INF/user-config.properties");
		System.out.printf("已经加载了 %d 个 BeadDefinition \n", bdCount);
//
		User user = beanFactory.getBean(User.class);
		System.out.println(user);

	}

}
