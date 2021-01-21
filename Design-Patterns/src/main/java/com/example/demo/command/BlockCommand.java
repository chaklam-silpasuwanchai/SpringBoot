package com.example.demo.command;

public class BlockCommand implements Command{

	private ActionReceiver actionReceiver;
	
	public BlockCommand(ActionReceiver actionReceiver) {
		super();
		this.actionReceiver = actionReceiver;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.actionReceiver.block();
	}

}
