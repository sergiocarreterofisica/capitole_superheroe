package com.scarretero.superhero.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ExecuteMyTimeAnnotation {

	@Around("@annotation(com.scarretero.superhero.annotation.ExecutionIntervalAnnotation)")
	public Object ExecuteMyTimeAnnotation(ProceedingJoinPoint point) throws Throwable {
		
		long initTime = System.currentTimeMillis();
		Object object = point.proceed();
		long endTime = System.currentTimeMillis();
		log.info(point.getSignature().getDeclaringTypeName() +"." + point.getSignature().getName() + " takes for execution " + (endTime - initTime) + " ms");
		
		return object;
	}

}
