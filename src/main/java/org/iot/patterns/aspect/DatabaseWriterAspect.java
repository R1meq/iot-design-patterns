package org.iot.patterns.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeParseException;

@Log4j2
@Aspect
@Component
public class DatabaseWriterAspect {
    @Around("execution(* *.writeToDatabase(..))")
    public Object handleExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (DataAccessException e) {
            String className = joinPoint.getTarget().getClass().getSimpleName();
            log.error("Database access error in {}: {} - {}", className, e.getClass().getSimpleName(), e.getMessage());
            throw e;
        } catch (DateTimeParseException | IllegalArgumentException e) {
            String className = joinPoint.getTarget().getClass().getSimpleName();
            log.error("Data parsing error in {}: {} - {}", className, e.getClass().getSimpleName(), e.getMessage());
            throw e;
        } catch (Exception e) {
            String className = joinPoint.getTarget().getClass().getSimpleName();
            log.error("Unexpected error in {}: {} - {}", className, e.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }
}