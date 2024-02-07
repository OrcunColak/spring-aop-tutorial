package com.colak.springaoptutorial.aspect;

import com.colak.springaoptutorial.annotation.SpecificParameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class SpecificParameterBeforeAspect {

    /**
     * The @Before aspect for @SpecificParameter
     */
    @Before("@annotation(specificParameter)")
    public void beforeAnnotatedMethod(JoinPoint joinPoint, SpecificParameter specificParameter) {
        // Get the value from annotation example
        String methodParameterName = specificParameter.value();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String parameterValue = request.getParameter(methodParameterName);

        if (parameterValue != null) {
            log.info("The parameter '" + methodParameterName + "' was detected in the request with the value: " + parameterValue);
        } else {
            log.info("The parameter '" + methodParameterName + "' was not detected in the request.");
        }
    }
}