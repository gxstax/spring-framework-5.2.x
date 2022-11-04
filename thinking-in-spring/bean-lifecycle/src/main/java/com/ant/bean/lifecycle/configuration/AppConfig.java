package com.ant.bean.lifecycle.configuration;


import com.ant.bean.lifecycle.bean.ConfigurationBean1;
import com.ant.bean.lifecycle.bean.ConfigurationBean2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * <p>
 * TODO
 * </P>
 *
 * @author Ant
 * @since 2022/11/04 8:46 下午
 **/
@Configuration(proxyBeanMethods = false)
public class AppConfig {

	@Bean
	public ConfigurationBean1 configurationBean1() {
		return new ConfigurationBean1();
	}

	/**
	 * <p>
	 * 这里如果把（proxyBeanMethods = true） 设置为true
	 * 那么 ConfigurationBean2 实例化的时候是依赖的容器中已经初始化完成的Bean
	 *
	 * 如果 proxyBeanMethods = false，那么在执行ConfigurationBean2 的实例化的时候，需要重新
	 * 执行一遍 ConfigurationBean1 的整个完整生命周期，重新初始化供 ConfigurationBean2 依赖
	 *
	 * 所以  proxyBeanMethods = false 适合做不强依赖性的业务场景
	 * </p>
	 *
	 * @param
	 * @return com.ant.bean.lifecycle.bean.ConfigurationBean2
	 */
	@Bean
	public ConfigurationBean2 configurationBean2() {
		return new ConfigurationBean2(configurationBean1());
	}

}
