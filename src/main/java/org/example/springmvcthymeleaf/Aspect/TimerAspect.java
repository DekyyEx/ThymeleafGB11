package org.example.springmvcthymeleaf.Aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Slf4j
@Aspect
@Component
public class TimerAspect {

    @Pointcut("@annotation(org.example.springmvcthymeleaf.Aspect.Timer)")
    public void timesheetServiceMethodsPointcut() {
    }

    @Pointcut("@within(org.example.springmvcthymeleaf.Aspect.Timer)")
    public void timesheetServiceTypePointcut() {
    }

    @Around(value = "timesheetServiceMethodsPointcut() || timesheetServiceTypePointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Object target = proceedingJoinPoint.getTarget();
        Method method = methodSignature.getMethod();

        Timer timer = null;
        if (method.isAnnotationPresent(Timer.class)) {
            timer = method.getAnnotation(Timer.class);
        } else if (target.getClass().isAnnotationPresent(Timer.class)) {
            timer = target.getClass().getAnnotation(Timer.class);
        }

        if (timer == null || !timer.enabled()) {
            return proceedingJoinPoint.proceed();
        }

        org.slf4j.event.Level level = timer.level();
        long startTime = System.currentTimeMillis();
        try {
            return proceedingJoinPoint.proceed();
        } finally {
            long timeTaken = System.currentTimeMillis() - startTime;
            String logMessage = String.format("Around method: %s, Time taken: %d ms", proceedingJoinPoint.getSignature().getName(), timeTaken);
            logAtLevel(level, logMessage);
        }
    }

    private void logAtLevel(org.slf4j.event.Level level, String message) {
        switch (level) {
            case TRACE:
                log.trace(message);
                break;
            case DEBUG:
                log.debug(message);
                break;
            case WARN:
                log.warn(message);
                break;
            case ERROR:
                log.error(message);
                break;
            case INFO:
            default:
                log.info(message);
                break;
        }
    }
}
