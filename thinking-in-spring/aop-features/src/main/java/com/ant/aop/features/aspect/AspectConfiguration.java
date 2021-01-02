package com.ant.aop.features.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>
 * Aspect 配置
 * </p>
 *
 * @author Ant
 * @since 2021/1/1 4:24 下午
 */
@Aspect
public class AspectConfiguration {

	@Pointcut("execution(public * *(..))")
	public void anyPublicMethods() {
		System.out.println("@Pointcut anyPublicMethods");
	}

	@Before("anyPublicMethods()")
	public void beforeAnyPublicMethods() {
		System.out.println("@Before any public method...");
	}

}
