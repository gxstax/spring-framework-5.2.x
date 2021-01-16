package com.ant.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * <p>
 * Aspect 配置
 * </p>
 *
 * @author Ant
 * @since 2021/1/1 4:24 下午
 */
@Aspect
public class AspectConfiguration2 implements Ordered {

	@Pointcut("execution(public * *(..))")
	public void anyPublicMethods() {
		System.out.println("@Pointcut anyPublicMethods");
	}

	@Before("anyPublicMethods()")
	public void beforeAnyPublicMethods2() {
		System.out.println("@Before any public method.(2)");
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
