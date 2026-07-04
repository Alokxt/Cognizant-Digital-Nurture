package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;

@Aspect
public class LoggingAspect {
    @Around("execution(* org.example.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint jp) throws Throwable{
        long st = System.currentTimeMillis();
        Object res =jp.proceed();
        long en = System.currentTimeMillis();
        long tt = en-st;
        System.out.println("Completed in "+tt+" milisecond");
        return res;
    }
}
