package org.lnu.smartphoneservice.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.lnu.smartphoneservice.annotation.TrackExecution;
import org.springframework.context.annotation.Configuration;

@Aspect
@Log
@Configuration
public class TrackingExecutionAspect {
    @Around("@annotation(trackExecution)")
    public Object trackExecutionForSpecifiedMethod(ProceedingJoinPoint joinPoint, TrackExecution trackExecution) throws Throwable {
        return handleTrackingExecution(joinPoint, trackExecution);
    }

    @Around("@within(trackExecution) && !@annotation(org.lnu.smartphoneservice.annotation.TrackExecution)")
    public Object trackExecutionForSpecifiedClass(ProceedingJoinPoint joinPoint, TrackExecution trackExecution) throws Throwable {
        return handleTrackingExecution(joinPoint, trackExecution);
    }

    private Object handleTrackingExecution(ProceedingJoinPoint joinPoint, TrackExecution trackExecution) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed(joinPoint.getArgs());
        long endTime = System.currentTimeMillis();

        if (trackExecution.isEnabled()) {
            long executionTime = endTime - startTime;

            String className = joinPoint.getTarget().getClass().getName();

            Signature signature = joinPoint.getSignature();

            StringBuilder stringBuilder = new StringBuilder("Executed method: " + signature);
            if (trackExecution.isExecutionTimeEnabled()) {
                stringBuilder.append("Execution time: " + executionTime + "ms");
            }

            log.info(stringBuilder.toString());
        }
        return result;
    }
}
