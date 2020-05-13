package com.ant.ioc.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * <p>
 * "byName" AutoWiring 依赖 Setter 方法注入示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/7 9:23 上午
 */
public class AutoWiringByNameDependencySetterInjectionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String xmlResourcePath = "classpath:/META-INF/autowiring-dependency-setter-injection.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        reader.loadBeanDefinitions(xmlResourcePath);

        // 依赖查找并且自动创建Bean
        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);
    }
}
