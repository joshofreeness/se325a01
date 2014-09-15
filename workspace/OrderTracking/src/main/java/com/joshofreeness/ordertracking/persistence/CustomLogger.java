package com.joshofreeness.ordertracking.persistence;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;



public class CustomLogger implements MethodInterceptor{
	
	private final Logger log = Logger.getLogger(CustomLogger.class);

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		Object target = method.getThis().getClass();
		
		log.info(String.format("test BEFORE %s", target.toString()));
		Object result = method.proceed();
		log.info("testAFTER");
		return result;
	}

}
