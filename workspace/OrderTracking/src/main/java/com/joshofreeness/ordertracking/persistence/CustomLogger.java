package com.joshofreeness.ordertracking.persistence;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;



public class CustomLogger implements MethodInterceptor{
	


	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		System.out.println("BEFORE CGFHVJGFCNH");
		Object result = method.proceed();
		System.out.println("AFTER CGFHVJGFCNH");
		return result;
	}

}
