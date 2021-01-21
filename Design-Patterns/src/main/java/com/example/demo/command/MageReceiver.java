package com.example.demo.command;

public class MageReceiver implements ActionReceiver {

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		System.out.println("Attack with magic");
	}

	@Override
	public void block() {
		// TODO Auto-generated method stub
		System.out.println("Block with magic barrier");

	}

	@Override
	public void counter() {
		// TODO Auto-generated method stub
		System.out.println("Counter with firewall");
	}

}
