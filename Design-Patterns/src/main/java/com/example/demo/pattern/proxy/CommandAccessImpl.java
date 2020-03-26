package com.example.demo.pattern.proxy;

public class CommandAccessImpl implements CommandAccess {

	@Override
	public Process runService(String cmd) throws Exception {
		Process p = Runtime.getRuntime().exec(cmd);
		System.out.println("'" + cmd + "' command executed.");
		return p;
		
	}

}
