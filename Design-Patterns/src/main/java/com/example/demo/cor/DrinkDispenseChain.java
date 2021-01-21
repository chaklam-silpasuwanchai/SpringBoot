package com.example.demo.cor;

import java.util.Scanner;

public class DrinkDispenseChain {
	
	private DispenseChain c1;
	
	public DrinkDispenseChain() {
		//initialize the chain
		this.c1 = new Baht10Dispenser();
		DispenseChain c2 = new Baht5Dispenser();
		DispenseChain c3 = new Baht2Dispenser();
		DispenseChain c4 = new Baht1Dispenser();
		
		//set the chain of responsibility
		c1.setNextChain(c2);
		c2.setNextChain(c3);
		c3.setNextChain(c4);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrinkDispenseChain dispenser = new DrinkDispenseChain();
		while (true) {
			int amount = 0;
			System.out.println("Enter amount to dispense");
			Scanner input = new Scanner(System.in);
			amount = input.nextInt();
			dispenser.c1.dispense(new Currency(amount));
		}
	}

}
