package com.example.demo.cor;

public class Baht5Dispenser implements DispenseChain {

	private DispenseChain chain;

	@Override
	public void setNextChain(DispenseChain nextChain) {
		// TODO Auto-generated method stub
		this.chain = nextChain;

	}

	@Override
	public void dispense(Currency cur) {
		// TODO Auto-generated method stub
		if (cur.getAmount() >= 5) {
			int num = cur.getAmount() / 5;
			int remainder = cur.getAmount() % 5;
			System.out.println("Dispensing " + num + " 5 baht coin(s)");
			if (remainder != 0) {
				this.chain.dispense(new Currency(remainder));
			}
		} else {
			// go to next chain
			this.chain.dispense(cur);
		}
	}

}
