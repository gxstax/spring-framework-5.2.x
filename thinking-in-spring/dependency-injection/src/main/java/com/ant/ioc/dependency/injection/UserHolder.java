package com.ant.ioc.dependency.injection;

import com.ant.spring.ioc.overview.domain.User;

/**
 * <p>
 * {@link User} 的Holder对象
 * </p>
 *
 * @author Ant
 * @since 2020/5/7 8:55 上午
 */
public class UserHolder {

    private User user;

    public UserHolder() {}

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
