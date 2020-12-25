package com.ant.aop.overview.interceptor;

import java.lang.reflect.Method;

/**
 * <p>
 * 异常拦截器
 * </p>
 *
 * @author Ant
 * @since 2020/12/25 1:00 下午
 */
public interface ExceptionInterceptor {

	/**
	 * <p>
	 * 异常拦截
	 * </p>
	 * 
	  * @param proxy
	 * @param method
	 * @param args
	 * @return {@link Object}
	 */
	void intercept(Object proxy, Method method, Object[] args, Throwable throwable);
	
}
