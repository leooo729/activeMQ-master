package com.example.jms_demo_2.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//定義一切面類
@Aspect
@Component
public class ServiceAop {
    //在不修改源代码的情况下，可以加入並執行自己的程式
    @Before(value = "execution(* com.example.jms_demo_2.service.TransferService.*(..))")
    public void servicePointcut(JoinPoint joinPoint){
        // Method Information
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName =signature.getMethod().getName();
        System.out.println("執行"+methodName);
    }
}

