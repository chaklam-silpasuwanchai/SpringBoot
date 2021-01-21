package com.example.demo.command;

public class CounterCommand implements Command{

	private ActionReceiver actionReceiver;
	
	public CounterCommand(ActionReceiver actionReceiver) {
		super();
		this.actionReceiver = actionReceiver;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.actionReceiver.counter();
	}

}
