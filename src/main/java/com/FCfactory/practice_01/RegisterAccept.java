package com.FCfactory.practice_01;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class RegisterAccept {

    @Pointcut("execution(public void com.FCfactory.practice_01.Register2.registe())")
    private void pt(){

    }

    @Around("pt()")
    public Object method(ProceedingJoinPoint pjp) throws Throwable {
        Object proceed = pjp.proceed();
        log.info("发送短信");
        return proceed;
    }
}
