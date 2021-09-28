package com.ant.spring.ioc.overview.dependency.repository;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * <p>
 * 用户信息仓库
 * </p>
 *
 * @author Ant gxstax@163.com
 * @since 2020-01-07 08:15
 */
public class UserRepository {
    private Collection<User> users; // 自定义 Bean

    private BeanFactory beanFactory; // 内建的非 Bean 对象（依赖）

    private ObjectFactory<User> userObjectFactory;

	private ObjectFactory<ApplicationContext> objectFactory;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ObjectFactory<User> getUserObjectFactory() {
        return userObjectFactory;
    }

    public void setUserObjectFactory(ObjectFactory<User> userObjectFactory) {
        this.userObjectFactory = userObjectFactory;
    }

	public ObjectFactory<ApplicationContext> getObjectFactory() {
		return objectFactory;
	}

	public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
		this.objectFactory = objectFactory;
	}
}
