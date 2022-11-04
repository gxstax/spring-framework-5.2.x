package com.ant.bean.lifecycle.bean;


/**
 * <p>
 * TODO
 * </P>
 *
 * @author Ant
 * @since 2022/11/04 8:52 下午
 **/
public class ConfigurationBean2 {

	private ConfigurationBean1 configurationBean1;

	public ConfigurationBean2(ConfigurationBean1 configurationBean1) {
		System.out.println("ConfigurationBean2 construct... " + this.hashCode());
		this.configurationBean1 = configurationBean1;
	}

	public void sayHello() {
		System.out.println("ConfigurationBean2 say Hello...");
	}
}
