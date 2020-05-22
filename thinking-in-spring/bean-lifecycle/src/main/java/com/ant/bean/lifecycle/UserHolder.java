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

	private final User user;

	public UserHolder(User user) {
		this.user = user;
	}

}
