package com.ant.aop.overview.interceptor;

import java.lang.reflect.Method;

/**
 * <p>
 * 后置拦截器
 * </p>
 *
 * @author Ant
 * @since 2020/12/25 1:00 下午
 */
public interface AfterInterceptor {

	/**
	 * <p>
	 * 后置执行
	 * </p>
	 * 
	  * @param proxy
	 * @param method
	 * @param args
	 * @return {@link Object}
	 */
	Object after(Object proxy, Method method, Object[] args, Object returnResult);
	
}
