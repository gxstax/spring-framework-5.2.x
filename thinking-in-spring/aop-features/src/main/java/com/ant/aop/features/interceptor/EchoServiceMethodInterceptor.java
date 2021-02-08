package com.ant.aop.features.interceptor;

import org.aopalliance.intercept.ConstructorInterceptor;
import org.aopalliance.intercept.ConstructorInvocation;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/1/1 7:31 下午
 */
public class EchoServiceMethodInterceptor implements MethodInterceptor, ConstructorInterceptor {

	Logger logger = Logger.getLogger(EchoServiceMethodInterceptor.class.getName());

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		System.out.println("拦截 EchoService 的方法：" + method);
		return invocation.proceed();
	}

	@Override
	public Object construct(ConstructorInvocation invocation) throws Throwable {
		debug(invocation.getConstructor(), invocation.getThis(), invocation.getArguments());
 		return invocation.proceed();
	}

	void debug(AccessibleObject ao, Object thiss, Object value) {
		System.out.println("AccessibleObject--:" + ao);
		System.out.println("this--:" + thiss);
		System.out.println("getArguments--:" + value);
    }
}
