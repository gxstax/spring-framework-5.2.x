package com.ant.bean.lifecycle;

import com.ant.spring.ioc.overview.domain.User;

/**
 * <p>
 * User Holder 类
 * </p>
 *
 * @author Ant
 * @since 2020/5/22 4:10 下午
 */
public class UserHolder {

	private Integer number;

	private String description;

	private final User user;

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

	@Override
	public String toString() {
		return "UserHolder{" +
				"number=" + number +
				", description='" + description + '\'' +
				", user=" + user +
				'}';
	}
}
