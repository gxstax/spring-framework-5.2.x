package com.ant.anntation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.function.Predicate;

/**
 * <p>
 * 偶数 profile 条件
 * </p>
 *
 * @author Ant
 * @since 2020/7/12 2:53 下午
 */
public class EvenConditional implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// 条件上下文
		final Environment environment = context.getEnvironment();

		Profiles profiles = new Profiles() {
			@Override
			public boolean matches(Predicate<String> activeProfiles) {
				return activeProfiles.test("even");
			}
		};
		return environment.acceptsProfiles(profiles);
	}
}
