package com.ant.questions.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>
 * 自定义 {@link Condition}
 * </p>
 *
 * @author Ant
 * @since 2020/8/8 5:33 下午
 */
public class MyConditional implements Condition {
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

		return false;
	}
}
