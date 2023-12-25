package com.infosys.infyride.utilities;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(* com.infosys.infyride..*..*(..))")
    public Object logAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        String logMessage1 = className + "----" + methodName + "----" + "Entering into" + methodName + "with param " +
                Arrays.toString(joinPoint.getArgs());

        logger.info(logMessage1);

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        String logMessage2 = className + "----" + methodName + "----" + "Exiting " + methodName + " with result" +
                result + " ---method execution completed in " + (endTime - startTime) + " ms.";
        logger.info(logMessage2);

        return result;
    }
}
