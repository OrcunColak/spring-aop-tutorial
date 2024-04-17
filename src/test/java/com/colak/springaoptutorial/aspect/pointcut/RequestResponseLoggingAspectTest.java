package com.colak.springaoptutorial.aspect.pointcut;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class RequestResponseLoggingAspectTest {

    // See https://dzone.com/articles/comprehensive-guide-to-unit-testing-spring-aop-asp
    @Test
    void logBefore() {
        RequestResponseLoggingAspect aspect = new RequestResponseLoggingAspect();

        // Creating mock objects
        JoinPoint joinPoint = Mockito.mock(JoinPoint.class);
        Signature signature = Mockito.mock(Signature.class);

        Mockito.when(joinPoint.getSignature()).thenReturn(signature);
        Mockito.when(signature.getName()).thenReturn("methodName");

        aspect.logBefore(joinPoint);

        Mockito.verify(joinPoint, Mockito.times(1)).getSignature();
        Mockito.verify(signature, Mockito.times(1)).getName();
    }
}