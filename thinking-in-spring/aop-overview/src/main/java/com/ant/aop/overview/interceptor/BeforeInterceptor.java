package com.ant.aop.overview.interceptor;

import java.lang.reflect.Method;

/**
 * <p>
 * 前置拦截器
 * </p>
 *
 * @author Ant
 * @since 2020/12/25 1:00 下午
 */
public interface BeforeInterceptor {

	/**
	 * <p>
	 * 前置执行
	 * </p>
	 * 
	  * @param proxy
	 * @param method
	 * @param args
	 * @return {@link Object}
	 */
	Object before(Object proxy, Method method, Object[] args);
	
}
