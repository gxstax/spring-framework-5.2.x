package com.ant.bean.lifecycle;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

import java.lang.reflect.Constructor;

/**
 * <p>
 * 自定义 Bean 实例化的构造方法
 * </P>
 *
 * @author Ant
 * @since 2022/10/24 3:46 下午
 **/
public class CustomSmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {
	@Override
	public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
//		return SmartInstantiationAwareBeanPostProcessor.super.determineCandidateConstructors(beanClass, beanName);
		return null;
	}
}
