package com.nuri.s4.transfer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class Card {
	
	public void cash() {
		System.out.println("Cash");
	}
	
	public Object cardCheck(ProceedingJoinPoint join) {
		System.out.println("Before Card");
		Object obj = null;
		try {
			Object [] args = join.getArgs();
			for(Object object : args) {
				System.out.println(object);
			}
			obj = join.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("After Card");
		
		return obj;
	}
}
