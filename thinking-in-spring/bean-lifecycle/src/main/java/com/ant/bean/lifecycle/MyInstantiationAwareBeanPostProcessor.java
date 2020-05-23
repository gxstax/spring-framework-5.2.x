package com.ant.bean.lifecycle;

import com.ant.spring.ioc.overview.domain.SuperUser;
import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2020/5/23 9:35 下午
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
	/**
	 * <实例化>前
	 */
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
			// 把配置完成的 superUser 替换掉
			return new SuperUser();
		}
		// 保持原来的 spring IOC 初始化的 Bean
		return null;
	}

	/**
	 * <实例化>后
	 */
	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
			// 手动设置属性之
			User user = (User) bean;
			user.setId(2L);
			user.setName("Ant");
			// 不允许属性赋值 (配置元信息 -> 属性值)
			return false;
		}
		return true;
	}

	/**
	 * <实例化>过程属性操作
	 * 这里需要注意的是，如果 postProcessAfterInstantiation() 方法返回 false 的话，这个方法将不会被执行
	 * User：      是跳过了属性填充过程的
	 * SuperUser： 是完全跳过 Bean 的实例化，所以也不会走这个方法
	 * userHolder: 自定义属性填充
	 */
	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		// 对 "userHolder" Bean 进行拦截
		if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
			// 假设我们的 Bean 配置有这么一条 <property name="number" value="1">
			// 那么 PropertyValues 就包含了 PropertyValue(number=1) 这个元素
			final MutablePropertyValues mutablePropertyValues;

			if (pvs instanceof MutablePropertyValues) {
				mutablePropertyValues = (MutablePropertyValues) pvs;
			} else {
				mutablePropertyValues = new MutablePropertyValues();
			}

			// 等价于 <property name="number" value="1" /> 这个代码
			mutablePropertyValues.addPropertyValue("number", "1");

			// 原始配置 <property name="description" value="The UserHolder" />
			if (pvs.contains("description")) {
				mutablePropertyValues.removePropertyValue("description");
				mutablePropertyValues.addPropertyValue("description", "The UserHolder V2");
			}

			return mutablePropertyValues;
		}
		return null;
	}

	/**
	 * <初始化>前
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
			UserHolder userHolder = (UserHolder) bean;
			userHolder.setDescription("The UserHolder V3");
		}
		return bean;
	}

	/**
	 * <初始化>后
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
			UserHolder userHolder = (UserHolder) bean;
			userHolder.setDescription("The UserHolder V7");
		}
		return bean;
	}
}
