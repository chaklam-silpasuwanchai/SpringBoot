package com.example.demo.command;

public class Main {
	
	public static void main(String[] args) {
		
		//Creating the receiver object
		ActionReceiver ar = 
				Factory.getCharacterClass("Mage");
		
		//creating command and associating with receiver
		AttackCommand attackCommand = 
				new AttackCommand(ar);
		
		//creating invoker and associating with command
		Invoker action = new Invoker(attackCommand);
		
		//perform action on invoker object
		action.execute();
		
		BlockCommand block = new BlockCommand(ar);
		action = new Invoker(block);
		action.execute();
		
		CounterCommand counter = new CounterCommand(ar);
		action = new Invoker(counter);
		action.execute();
		
	}

}
