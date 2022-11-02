package com.ant.bean.lifecycle.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;
import javax.annotation.PostConstruct;
import java.util.logging.Logger;

/**
 * <p>
 * 模拟 Spring 生命周期的 Bean
 * </P>
 *
 * @author Ant
 * @since 2022/11/01 4:36 下午
 **/
public class LifecycleBean implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware, InitializingBean{

	Logger log = Logger.getLogger(LifecycleBean.class.getName());

	private String name;
	private String number;
	private String description;


	private String beanName;

	private ClassLoader classLoader;

	private BeanFactory beanFactory;

	private Environment environment;

	@PostConstruct
	public void initPostConstruct() {
		this.description = "LifecycleBean version: V4";
		log.info("添加了 @PostConstruct 注解的 LifecycleBean#postConstruct() 方法调用: initPostConstruct() -> " + description + "\n");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.description = "LifecycleBean version: V5";
		log.info("InitializingBean#afterPropertiesSet 回调: afterPropertiesSet() -> " + description + "\n");
	}

	/**
	 * <p>
	 * 自定义初始化方法
	 * </p>
	 *
	 * @param
	 * @return void
	 */
	public void init() {
		this.description = "LifecycleBean version: V6";
		log.info("自定义初始化方法回调 init() -> " + description + "\n");
	}

	@Override
	public void setBeanName(String name) {
		log.info("BeanNameAware#setBeanName 回调\n");
		this.beanName = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public String getBeanName() {
		return beanName;
	}

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "LifecycleBean{" +
				"name='" + name + '\'' +
				", number='" + number + '\'' +
				", description='" + description + '\'' +
				", beanName='" + beanName + '\'' +
				'}';
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		log.info("BeanClassLoaderAware#setBeanClassLoader 回调" + "\n");
		this.classLoader = classLoader;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		log.info("BeanFactoryAware#setBeanFactory 回调" + "\n");
		this.beanFactory = beanFactory;
	}

	@Override
	public void setEnvironment(Environment environment) {
		log.info("EnvironmentAware#setEnvironment 回调" + "\n");
		this.environment = environment;
	}

	public static class LifecycleInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
		Logger log = Logger.getLogger(LifecycleInstantiationAwareBeanPostProcessor.class.getSimpleName());

		/**
		 * <p>
		 *	spring生命周期-「Spring Bean 实例化前阶段」阶段回调
		 * </p>
		 *
		 * @param beanClass
		 * @param beanName
		 * @return java.lang.Object
		 */
		@Override
		public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("lifecycleBean", beanName) && LifecycleBean.class.equals(beanClass)) {
				log.info("InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation 方法回调\n");
				// 把配置完成的 LifecycleBean 替换掉(如果这里替换掉，那么该bean就是最终的初始化bean，bean的后续生命周期将不会再进行)
//				return new LifecycleBean();

				// 为了走后续的生命周期流程，我们这里返回null
				return null;
			}
			// 保持原来的 spring IOC 初始化的 Bean
			return null;
		}

		/**
		 * <p>
		 * spring生命周期-「Spring Bean 实例化后阶段」阶段回调
		 * </p>
		 *
		 * @param bean:
		 * @param beanName:
		 * @return boolean
		 */
		@Override
		public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("lifecycleBean", beanName) && LifecycleBean.class.equals(bean.getClass())) {
				log.info("InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation 方法回调" + "\n");
				// 手动设置属性之
				LifecycleBean lifecycleBean = (LifecycleBean) bean;
				lifecycleBean.setName("Ant");
				// 不允许属性赋值 (配置元信息 -> 属性值)
//				return false;

				// 为了走后续的生命周期流程，我们这里返回true
				return true;
			}
			return true;
		}

		/**
		 * <p>
		 * spring生命周期-「Spring Bean 属性赋值前阶段」阶段回调
		 * </p>
		 *
		 * @param pvs:
		 * @param bean:
 		 * @param beanName:
		 * @return org.springframework.beans.PropertyValues
		 */
		@Override
		public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
			// 对 "lifecycle" Bean 进行拦截
			if (ObjectUtils.nullSafeEquals("lifecycleBean", beanName) && LifecycleBean.class.equals(bean.getClass())) {
				log.info("InstantiationAwareBeanPostProcessor#postProcessProperties 方法回调" + "\n");
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
					// propertyValue 是不可变的
					mutablePropertyValues.removePropertyValue("description");
					mutablePropertyValues.addPropertyValue("description", "LifecycleBean version: V2");
				} else {
					mutablePropertyValues.addPropertyValue("description", "LifecycleBean version: V2");
				}

				return mutablePropertyValues;
			}
			return null;
		}

		/**
		 * <p>
		 * spring生命周期-「Spring Bean 初始化前阶段」阶段回调
		 * </p>
		 *
		 * @param bean
		 * @param beanName
		 * @return java.lang.Object
		 */
		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("lifecycleBean", beanName) && LifecycleBean.class.equals(bean.getClass()) ) {
				log.info("BeanPostProcessor#postProcessBeforeInitialization 方法回调" + "\n");
				LifecycleBean lifecycleBean = (LifecycleBean) bean;
				lifecycleBean.setDescription("LifecycleBean version: V3");
			}
			return bean;
		}

		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("lifecycleBean", beanName) && LifecycleBean.class.equals(bean.getClass()) ) {
				log.info("BeanPostProcessor#postProcessAfterInitialization 方法回调" + "\n");
				LifecycleBean lifecycleBean = (LifecycleBean) bean;
				lifecycleBean.setDescription("LifecycleBean version: V7");
			}
			return bean;
		}
	}
}

