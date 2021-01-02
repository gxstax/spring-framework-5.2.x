package com.ant.aop.features.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * <p>
 * Aspect XML 配置
 * </p>
 *
 * @author Ant
 * @since 2021/1/1 4:24 下午
 */
public class AspectXmlConfiguration {

	@Before("anyPublicMethods()")
	public void beforeAnyPublicMethods() {
		System.out.println("@Before XML any public method...");
	}

}
