package com.ant.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Random;

/**
 * <p>
 * Aspect XML 配置
 * </p>
 *
 * @author Ant
 * @since 2021/1/1 4:24 下午
 */
public class AspectXmlConfiguration {

	public void beforeAnyPublicMethods() {
		System.out.println("@Before XML any public method...");
	}

	public void aroundAnyPublicMethods(ProceedingJoinPoint pjp) throws Throwable {
		Random random = new Random();
		if (random.nextBoolean()) {
			throw new RuntimeException("On Purpose from XML configuration!");
		}
		System.out.println("@Around XML any public method...");
		pjp.proceed();
	}

	public void afterAnyPublicMethods() {
		System.out.println("@After any public method...");
	}

	public void afterReturningAnyPublicMethods() {
		System.out.println("@AfterReturning any public method...");
	}

	public void afterThrowingAnyPublicMethods() {
		System.out.println("@AfterThrowing any public method...");
	}
}
