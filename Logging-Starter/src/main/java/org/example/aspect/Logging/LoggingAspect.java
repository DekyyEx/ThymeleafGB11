package org.example.aspect.Logging;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LoggingAspect {

    private final LoggingProperties loggingProperties;

    @Pointcut("@annotation(com.example.aspect.Logging.Logging)")
    public void loggingMethodsPointcut() {

    }

    @Pointcut("@within(com.example.aspect.Logging.Logging)")
    public void loggingTypePointcut() {

    }

    @Around(value = "loggingMethodsPointcut() || loggingTypePointcut()")
    public Object loggingMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.atLevel(loggingProperties.getLoggingLevel()).log("Before method execution", methodName);
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("Exception in method execution", methodName);
            throw e;
        } finally {
            log.atLevel(loggingProperties.getLoggingLevel()).log("After method execution", methodName);
        }
    }

    @Before("loggingMethodsPointcut()")
    public void before(JoinPoint joinPoint) {
        logArguments(joinPoint);
    }

    @After(value = "loggingMethodsPointcut()")
    public void after(JoinPoint joinPoint) {
        logArguments(joinPoint);
        log.info("After method: {} ", joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "loggingMethodsPointcut()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        logArguments(joinPoint);
        log.info("AfterThrowing method: {} , Exception : {}", joinPoint.getSignature().getName(),
                exception.getClass().getName());
    }

    @AfterReturning(value = "loggingMethodsPointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        logArguments(joinPoint);
        log.info("AfterReturning method: {} ", joinPoint.getSignature().getName());
    }

    private void logArguments(JoinPoint joinPoint) {
        if (!loggingProperties.isPrintArgs()) {
            return;
        }
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder(methodName).append("(");
        for (int i = 0; i < args.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(args[i].getClass().getSimpleName()).append(" = ").append(args[i]);
        }
        sb.append(")");
        log.info(sb.toString());
    }
}
