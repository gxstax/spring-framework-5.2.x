package com.ant.bean.lifecycle.processors;

import com.ant.bean.lifecycle.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * {@link DestructionAwareBeanPostProcessor} 实现
 * </p>
 *
 * @author Ant
 * @since 2020/5/24 4:45 下午
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor{

	@Override
	public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
			UserHolder userHolder = (UserHolder) bean;
			// afterSingletonsInstantiated V8 -> postProcessBeforeDestruction V9
			userHolder.setDescription("The UserHolder V9");
			System.out.println("postProcessBeforeDestruction() = " + userHolder.getDescription());
		}
	}
}
