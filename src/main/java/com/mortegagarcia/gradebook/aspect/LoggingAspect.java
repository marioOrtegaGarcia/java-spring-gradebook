package com.mortegagarcia.gradebook.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
/*
 * excecution( modifiers-pattern? return-type-pattern declaring-type-pattern?
 * method-name-pattern(param-pattern) throws-pattern? )
 */
public class LoggingAspect {
  private Logger myLogger = Logger.getLogger(getClass().getName());

  // Pointcut Declarations

  @Pointcut("execution(* com.mortegagarcia.gradebook.controller.*.*(..))")
  private void forControllerPackage() {
    // Pointcut Expression used to log the controller package classes
  }

  @Pointcut("execution(* com.mortegagarcia.gradebook.repository.*.*(..))")
  private void forRepositoryPackage() {
    // Pointcut Expression used to log the repository package classes
  }

  @Pointcut("execution(* com.mortegagarcia.gradebook.dto.*.*(..))")
  private void forDtoPackage() {
    // Pointcut Expression used to log the dto package classes
  }

  @Pointcut("forControllerPackage() || forRepositoryPackage() || forDtoPackage()")
  private void forAppFlow() {
    // Pointcut Expression used to log application flow
  }

  @Before("forAppFlow()")
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

  @AfterReturning(pointcut = "forAppFlow()", returning = "returnResult")
  public void afterReturning(JoinPoint joinPoint, Object returnResult) {

    // display method we are returning from
    String calledMethodString = "=====>> @AfterReturning: from method: " + joinPoint.getSignature().toShortString();
    myLogger.info(calledMethodString);

    // display data returned
    String returnedDataString = "=====>> result: " + returnResult;
    myLogger.info(returnedDataString);
  }

}
