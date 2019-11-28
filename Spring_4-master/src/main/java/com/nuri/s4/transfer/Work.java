package com.nuri.s4.transfer;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class Work {
	
	@Inject
	private Transfer transfer;

	public void goWork() {
		
		transfer.getBus("Samsung");
		transfer.getSubway("KaKao");
		
		
		
	}
}
