package com.nuri.s4.transfer;

import org.springframework.stereotype.Component;

@Component
public class Transfer {
	
	public void car() {
		System.out.println("===============car================");
		System.out.println("drive");
		System.out.println("===============car================");
	}
	
	public void taxi() {
		System.out.println("===============taxi================");
		System.out.println("sleep");
		System.out.println("===============taxi================");		
	}
	
	public void getSubway(String cardName) {
		System.out.println("===============subway================");
		System.out.println("smartphone");
		System.out.println("===============subway================");
	}
	
	public void getBus(String cardName) {
		System.out.println("===============bus================");
		System.out.println("Music");
		System.out.println("===============bus================");
	}
	
}
