package com.colak.springtutorial.aspect.around;

import com.colak.springtutorial.annotation.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RedisLockAroundAspect {

    /**
     * The @Around aspect for @RedisLock
     */
    @Around("@annotation(redisLock)")
    public Object aroundMethod(ProceedingJoinPoint joinPoint, RedisLock redisLock) throws Throwable {
        // Get the values from ProceedingJoinPoint  example
        try {
            Object[] args = joinPoint.getArgs();
            Object targetObject = joinPoint.getThis();
            log.info("TargetObject : {} args : {}", targetObject, args);
            log.info("RedisLock key: {}", redisLock.key());
            return joinPoint.proceed();
        } finally {
            log.info("Around aspect finished");
        }
    }
}