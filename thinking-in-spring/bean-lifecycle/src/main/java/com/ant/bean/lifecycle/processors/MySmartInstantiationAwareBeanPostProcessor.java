package com.ant.bean.lifecycle.processors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.Primary;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href="mailto:gxstaxant@gmail.com">Ant</a>
 * @since 2025/9/3 19:51
 */

public class MySmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {
	/**
	 * Determine the candidate constructors to use for the given bean.
	 * <p>The default implementation returns {@code null}.
	 *
	 * @param beanClass the raw class of the bean (never {@code null})
	 * @param beanName  the name of the bean
	 * @return the candidate constructors, or {@code null} if none specified
	 * @throws BeansException in case of errors
	 *                        在创建 Bean 的过程当中我们可以指定 Constructor 如果指定构造器，那么将会使用我们指定的构造器去创建
	 */
	@Override
	public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
		// 示例：如果 Bean 名称包含 "Special"
		if (beanName.contains("Special")) {
			Constructor<?>[] constructors = beanClass.getConstructors();
			// 找到你想要的构造函数，比如参数最多的
			return Arrays.stream(constructors)
					.sorted((c1, c2) -> Integer.compare(c2.getParameterCount(), c1.getParameterCount()))
					.limit(1)
					.toArray(Constructor[]::new);
		}

		// 示例：如果类上有 @Primary 注解
		if (beanClass.isAnnotationPresent(Primary.class)) {
			try {
				Constructor<?> ctor = beanClass.getConstructor(String.class, int.class);
				return new Constructor[]{ctor};
			} catch (NoSuchMethodException e) {
				throw new BeansException("Custom constructor not found", e) {};
			}
		}

		return SmartInstantiationAwareBeanPostProcessor.super.determineCandidateConstructors(beanClass, beanName);
	}
}
