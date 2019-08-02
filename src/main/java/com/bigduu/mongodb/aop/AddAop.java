package com.bigduu.mongodb.aop;


import com.bigduu.mongodb.domain.User;
import com.bigduu.mongodb.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class AddAop {

    @Autowired
    private UserRepository repository;

    @Pointcut("execution(* com.bigduu.mongodb.controller.UserController.addUser(..))")
    public void cutPoint(){
    }



    @Before("cutPoint()")
    public void doBefore(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info(name+"执行了-----这是before");
    }

    @Around("cutPoint()")
    public Object deAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("这里是循环start------");
        long count = repository.count();
        if (count != 0){
            User user = repository.findTopByOrderByIdDesc();
            count = user.getId()+1L;
        }
        Object[] args = proceedingJoinPoint.getArgs();
        long finalCount = count;
        Arrays.stream(args).forEach(arg->{
            Class<?> aClass = arg.getClass();
            try {
                Field id = aClass.getDeclaredField("id");
                id.setAccessible(true);
                id.set(arg, finalCount);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        Object proceed = proceedingJoinPoint.proceed();
        log.info("这里是循环end------");
        return proceed;
    }

    @After("cutPoint()")
    public void doAfter(JoinPoint joinPoint){
      log.info("这里是执行后-----这是after");
    }
}
