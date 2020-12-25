package com.ant.aop.overview.interceptor;

import java.lang.reflect.Method;

/**
 * <p>
 * 最终拦截器时间实现类
 * </p>
 *
 * @author Ant
 * @since 2020/12/25 8:23 下午
 */
public class TimeFinallyInterceptor implements FinallyInterceptor {

	private final Long startTime;
	private final Long endTime;

	TimeFinallyInterceptor(Long startTime, Long endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public Object finalize(Object proxy, Method method, Object[] args, Object returnResult) {
		return endTime - startTime;
	}
}
