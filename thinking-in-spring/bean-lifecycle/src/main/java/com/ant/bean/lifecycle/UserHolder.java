package com.ant.bean.lifecycle;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <p>
 * User Holder 类
 * </p>
 *
 * @author Ant
 * @since 2020/5/22 4:10 下午
 */
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
		EnvironmentAware, InitializingBean, SmartInitializingSingleton, DisposableBean {

	private Integer number;

	private String description;

	private final User user;

	private String beanName;

	private BeanFactory beanFactory;

	private ClassLoader classLoader;

	private Environment environment;

	public UserHolder(User user) {
		this.user = user;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@PostConstruct
	public void initPostConstruct() {
		// postProcessorBeforeInitialization V3 -> initPostConstruct V4
		this.description = "The userHolder V4";
		System.out.println("initPostConstruct() = " + description);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// initPostConstruct V4 -> afterPropertiesSet V5
		this.description = "The userHolder V5";
		System.out.println("afterPropertiesSet() = " + description);
	}

	public void init() {
		// afterPropertiesSet V5 -> init V6
		this.description = "The userHolder V6";
		System.out.println("init() = " + description);
	}

	@PreDestroy
	public void preDestroy() {
		// postProcessBeforeDestruction() V9 -> preDestroy V10
		this.description = "The userHolder V10";
		System.out.println("preDestroy() = " + description);
	}

	@Override
	public void destroy() throws Exception {
		// preDestroy() V10 -> destroy V11
		this.description = "The userHolder V11";
		System.out.println("preDestroy() = " + description);
	}

	public void doDestroy() {
		// destroy() V11 -> doDestroy V12
		this.description = "The userHolder V12";
		System.out.println("preDestroy() = " + description);
	}

	@Override
	public String toString() {
		return "UserHolder{" +
				"number=" + number +
				", description='" + description + '\'' +
				", user=" + user +
				", beanName='" + beanName + '\'' +
				", environment=" + environment +
				'}';
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = beanName;
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}


	@Override
	public void afterSingletonsInstantiated() {
		// postProcessorAfterInitialization V7 -> afterSingletonsInstantiated V8
		this.description = "The userHolder V8";
		System.out.println("afterSingletonsInstantiated() = " + description);
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("The UserHolder is finalized...");
	}

}
