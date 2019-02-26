package org.terence.backend.common.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author terence
 * @since 2019/2/26 11:36
 */

@Aspect
@Component
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* org.terence.backend.web.controller..*.*(..)) ||"
            + "execution(* org.terence.backend.service.service..*.*(..)) ||"
            + "execution(* org.terence.backend.dao.repository..*.*(..))")
    public void executionService() {

    }

    @Before("executionService()")
    public void logBefore(JoinPoint joinPoint) {
        String logTmp = "Beginning: %s; Arguments: %s; At: %d";
        logger.info(String.format(logTmp, joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()), System.currentTimeMillis()));
    }

    @After("executionService()")
    public void logAfter(JoinPoint joinPoint) {
        String logTmp = "Ending: %s; At: %d";
        logger.info(String.format(logTmp, joinPoint.getSignature().getName(), System.currentTimeMillis()));
    }

    @AfterThrowing(pointcut = "executionService()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

        logger.error("Beginning method: " + joinPoint.getSignature().getName());
        logger.error("Method arguments: " + Arrays.toString(joinPoint.getArgs()));
        logger.error("Exception in method: " + joinPoint.getSignature().getName());
        logger.error("Exception is: " + error);
    }
}
