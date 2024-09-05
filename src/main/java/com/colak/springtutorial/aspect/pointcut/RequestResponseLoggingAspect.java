package com.colak.springtutorial.aspect.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RequestResponseLoggingAspect {

    // Define the pointcut for LogRequestResponse annotation
    @Pointcut("@annotation(com.colak.springtutorial.annotation.LogRequestResponse)")
    public void logAnnotationPointcut() {
    }

    // Before advice
    @Before("logAnnotationPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        log.info("Before " + signature.getName());
    }


    // Normal after advice
    @AfterReturning(pointcut = "logAnnotationPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        // Logging the response
        Signature signature = joinPoint.getSignature();
        log.info("After " + signature.getName() + "() returned value " + result);

        // Logging the response status
        if (result instanceof ResponseEntity<?> responseEntity) {
            log.info("Response status: " + responseEntity.getStatusCode());
        }
    }

}
