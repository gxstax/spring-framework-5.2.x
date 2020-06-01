package com.ant.configuration.metadata;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * <p>
 * 基于 XML 的 YAML 外部实例化配置
 * </p>
 *
 * @author Ant
 * @since 2020/5/29 6:38 下午
 */
@SuppressWarnings("unchecked")
public class XmlBasedYamlPropertySourceDemo {
	public static void main(String[] args) {
		// 创建 IOC 底层容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 创建 XML 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		// 加载 XML 资源
		beanDefinitionReader.loadBeanDefinitions("META-INF/yaml-property-source-context.xml");

		Map<String, Object> bean = beanFactory.getBean("yamlMap", Map.class);

		System.out.println(bean);
	}
}
