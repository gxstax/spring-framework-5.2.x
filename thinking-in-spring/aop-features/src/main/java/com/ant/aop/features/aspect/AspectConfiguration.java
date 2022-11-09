package com.ant.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;

import java.util.Random;

/**
 * <p>
 * Aspect 配置类
 * </p>
 *
 * @author Ant
 * @since 2021/1/1 4:24 下午
 */
@Aspect
@Order
public class AspectConfiguration {

	@Pointcut("execution(public * *(..))")
	public void anyPublicMethods() {
		System.out.println("@Pointcut anyPublicMethods");
	}

	@Before("anyPublicMethods()")
	public void beforeAnyPublicMethods() {

		Random random = new Random();
		if (random.nextBoolean()) {
			throw new RuntimeException("On Purpose!");
		}
		System.out.println("@Before any public method...");
	}

	@Around("anyPublicMethods()")
	public void aroundAnyPublicMethods(ProceedingJoinPoint p) throws Throwable {
		System.out.println("@Around any public method...");
		p.proceed();
	}

	@After("anyPublicMethods()")
	public void afterAnyPublicMethods() {
		System.out.println("@After any public method...");
	}

	@AfterReturning("anyPublicMethods()")
	public void afterReturningAnyPublicMethods() {
		System.out.println("@AfterReturning any public method...");
	}

	@AfterThrowing("anyPublicMethods()")
	public void finalized() {
		System.out.println("@AfterThrowing any public method...");
	}

}
