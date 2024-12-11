package com.hanson.eventlogging.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName LogAspect
 * @Description 日志切面
 * @date 2024/12/9 14:30
 **/
@Aspect
@Component
public class LogAspect {

    @Before("execution(* com.hanson.eventlogging.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        // 获取接口路径
        String interfaceName = getRequestMappingPath(joinPoint);

        LogContext.setInterfaceName(interfaceName);
    }

    private String getRequestMappingPath(JoinPoint joinPoint) {
        // 获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();

        // 获取方法参数类型
        Class<?>[] parameterTypes = getParameterTypes(joinPoint);

        try {
            // 使用正确的参数类型获取方法对象
            java.lang.reflect.Method method = targetClass.getMethod(methodName, parameterTypes);
            Annotation[] annotations = method.getAnnotations();

            // 遍历注解，查找请求映射注解
            for (Annotation annotation : annotations) {
                if (annotation instanceof RequestMapping) {
                    RequestMapping requestMapping = (RequestMapping) annotation;
                    return requestMapping.value()[0];  // 返回接口路径
                } else if (annotation instanceof GetMapping) {
                    GetMapping getMapping = (GetMapping) annotation;
                    return getMapping.value()[0];
                } else if (annotation instanceof PostMapping) {
                    PostMapping postMapping = (PostMapping) annotation;
                    return postMapping.value()[0];
                } else if (annotation instanceof PutMapping) {
                    PutMapping putMapping = (PutMapping) annotation;
                    return putMapping.value()[0];
                } else if (annotation instanceof DeleteMapping) {
                    DeleteMapping deleteMapping = (DeleteMapping) annotation;
                    return deleteMapping.value()[0];
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // 如果没有找到路径信息，返回方法名
        return targetClass.getSimpleName() + "." + methodName;
    }

    // 获取方法参数类型
    private Class<?>[] getParameterTypes(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Class<?>[] parameterTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return parameterTypes;
    }

}