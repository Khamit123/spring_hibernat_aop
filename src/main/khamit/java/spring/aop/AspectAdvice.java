package spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectAdvice {
    @Around("execution(* spring.dao.*.*(..))")
    public Object AdviceArounDAOmethods(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        MethodSignature methodSignature= (MethodSignature) proceedingJoinPoint.getSignature();
        String metname=methodSignature.getName();
        System.out.println("Начало работы метода "+ metname);
        Object result =proceedingJoinPoint.proceed();
        System.out.println("Конец работы метода "+ metname);
        return result;
    }
}
