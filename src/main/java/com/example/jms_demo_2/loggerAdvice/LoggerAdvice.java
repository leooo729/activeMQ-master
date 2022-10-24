//package com.example.jms_demo_2.loggerAdvice;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
////定義一日誌切麵類
//@Aspect
//@Component
//public class LoggerAdvice {
//    private Logger logger= LoggerFactory.getLogger(LoggerAdvice.class);
//
//    @Pointcut(value = "execution(* com.example.jms_demo_2.controller.*(..))")//com.example.jms_demo_2.*
//    public void myPointcut(){}//* com.savage.aop.MessageSender.*(..)
//
//    @Around("myPointcut()")
//    public Object applicationLogger(ProceedingJoinPoint pjp)
//
//
//}
//
