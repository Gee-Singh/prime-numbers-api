package com.prime.demo.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to be used on in REST API controller method, triggers LimitCheckAspect logic
 *
 * @See LimitCheckAspect for verification logic
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitCheck {
}
