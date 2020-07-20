package com.ant.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * <p>
 * 依赖注入 {@link Environment} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/7/18 3:37 下午
 *
 * @see Environment
 */
public class LookupEnvironmentDemo implements EnvironmentAware {

	private Environment environment;


	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册 Configuration class
		context.register(LookupEnvironmentDemo.class);

		// 开启 Spring 应用上下文
		context.refresh();


		LookupEnvironmentDemo demo = context.getBean(LookupEnvironmentDemo.class);

		Environment environment = context.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);

		System.out.println(demo.environment == environment);


		// 关闭 Spring 应用上下文
		context.close();
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
