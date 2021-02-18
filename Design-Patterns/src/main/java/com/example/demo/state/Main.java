package com.example.demo.state;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Swordman sm = new Swordman();
		sm.increaseAttack(4);
		sm.speedUp(3);
		sm.increaseDefense(1);
		sm.speedUp(2);

		System.out.println("Character present state: ");
		sm.printStates();
	}

}
