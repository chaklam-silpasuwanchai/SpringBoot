package com.example.demo.command;

public class AttackCommand implements Command{

	private ActionReceiver actionReceiver;
	
	public AttackCommand(ActionReceiver actionReceiver) {
		super();
		this.actionReceiver = actionReceiver;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.actionReceiver.attack();
	}

}
