package com.ant.bean.lifecycle.bean;


/**
 * <p>
 * TODO
 * </P>
 *
 * @author Ant
 * @since 2022/11/04 8:52 下午
 **/
public class ConfigurationBean1 {

	public ConfigurationBean1() {
		System.out.println("ConfigurationBean1 construct... " + this.hashCode());
	}

	public void syHello() {
		System.out.println("ConfigurationBean1 say hello...");
	}
}
