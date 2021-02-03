package com.mortegagarcia.gradebook.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class SecurityAspect {

	private final Logger myLogger = Logger.getLogger(getClass().getName());

	@Pointcut("execution(* com.mortegagarcia.gradebook.config.UserSecurity.*(..))")
	private void forUserSecurityClass() {
		// Pointcut Expression used to log the controller package classes
	}

	@Pointcut("forUserSecurityClass()")
	private void forSecurityFlow() {
		// Pointcut Expression used to log application flow
	}


	@Before("forSecurityFlow()")
	public void before(JoinPoint joinPoint) {
		String calledMethodString = "=====>> @Before: calling method: " + joinPoint.getSignature().toShortString();
		myLogger.info(calledMethodString);

		// display the arguments to the method
		// get the arguments
		Object[] args = joinPoint.getArgs();

		// loop thru and display args
		for (Object tempArg : args) {
			String argumentString = "=====>> argument value: " + tempArg;
			myLogger.info(argumentString);
		}
	}

	@AfterReturning(pointcut = "forSecurityFlow()", returning = "returnResult")
	public void afterReturning(JoinPoint joinPoint, Object returnResult) {

		// display method we are returning from
		String calledMethodString = "=====>> @AfterReturning: from method: " + joinPoint.getSignature().toShortString();
		myLogger.info(calledMethodString);

		// display data returned
		String returnedDataString = "=====>> result: " + returnResult;
		myLogger.info(returnedDataString);
	}

}
