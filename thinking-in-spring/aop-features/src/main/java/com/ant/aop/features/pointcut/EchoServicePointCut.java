package com.ant.aop.features.pointcut;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <p>
 * 基于 API PointCut 示例
 * </p>
 *
 * @author Ant
 * @since 2021/1/11 1:04 下午
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class EchoServicePointCut extends StaticMethodMatcherPointcut {

	private String methodName;

	private Class targetClass;

	public EchoServicePointCut() {}

	public EchoServicePointCut(String methodName, Class targetClass) {
		this.methodName = methodName;
		this.targetClass = targetClass;
	}

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return Objects.equals(method.getName(), this.methodName)
				&& this.targetClass.isAssignableFrom(targetClass);
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(Class targetClass) {
		this.targetClass = targetClass;
	}

//	public String echo() {
//		return "EchoServicePointCut...";
//	}

}
