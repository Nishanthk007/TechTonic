package com.techtonic.springdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Around("@annotation(com.techtonic.springdemo.annotation.TimeBoxAnnotation)")
	public void timeTakenForMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		long startTime = System.currentTimeMillis();
		
		proceedingJoinPoint.proceed();
		
		long timeBox = System.currentTimeMillis() - startTime;
		
		logger.info("Time taken for method to execute :: {} milliseconds", timeBox);
	}
}
