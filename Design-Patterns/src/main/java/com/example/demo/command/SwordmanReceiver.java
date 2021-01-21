package com.example.demo.command;

public class SwordmanReceiver implements ActionReceiver {

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		System.out.println("Attack with sword");
	}

	@Override
	public void block() {
		// TODO Auto-generated method stub
		System.out.println("Block with shield");

	}

	@Override
	public void counter() {
		// TODO Auto-generated method stub
		System.out.println("Counter with parry");
	}

}
