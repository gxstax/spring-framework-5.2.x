package com.ant.anntation.enable;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <p>
 * HelloWorld 模块 {@link ImportBeanDefinitionRegistrar} 实现
 * </p>
 *
 * @author Ant
 * @since 2020/7/4 6:53 下午
 */
public class HelloWorldImportRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		AnnotatedGenericBeanDefinition annotatedBeanDefinition = new AnnotatedGenericBeanDefinition(HelloWorldConfiguration.class);

		BeanDefinitionReaderUtils.registerWithGeneratedName(annotatedBeanDefinition, registry);
	}
}
