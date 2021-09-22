package com.ant.ioc.java.beans;

/**
 * <p>
 * 描述人的 POJO 类
 * Setter / Getter 方法
 * 可写方法/ 可读方法
 * </p>
 *
 * @author GaoXin
 * @since 2021/9/20 4:35 下午
 */
public class Person {
	String name;

	Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
