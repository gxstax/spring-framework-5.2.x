package com.ant.spring.ioc.overview.domain;

/**
 * <p>
 * 公司类
 * </p>
 *
 * @author Ant
 * @since 2020/6/21 11:33 下午
 */
public class Company {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company{" +
				"name='" + name + '\'' +
				'}';
	}
}
