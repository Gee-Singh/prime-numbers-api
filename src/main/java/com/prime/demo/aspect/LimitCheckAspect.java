package com.prime.demo.aspect;

import com.prime.demo.exceptions.InvalidNumberException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * This class is an aspect logic feature where provided upper limit in the URL is verified
 * throws exception if number is <=0 , can be changed depending on the requirements.
 *
 * @See LimitCheck interface and PrimeNumberController
 */

@Aspect
@Component
@EnableAspectJAutoProxy
public class LimitCheckAspect {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(LimitCheck) && args(limit)")
    public Object verifyLimitNumber(ProceedingJoinPoint joinPoint, Integer limit) throws Throwable {
        LOG.info("verifying limit number provided in the URL [{}]", limit);

        if (limit <= 0) {
            throw new InvalidNumberException("given limit number is invalid , please provide a valid limit number and try again");
        }

        LOG.info("successfully verified limit number [{}]", limit);

        return joinPoint.proceed();
    }

}
