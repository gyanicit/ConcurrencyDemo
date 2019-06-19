package com.demo;

import java.util.List;
import java.util.concurrent.Callable;

public class WorkerCallable implements Callable<List<String>> {
	
	private String command;
	private List<String> list;
	
	public WorkerCallable(String s,List<String> list) {
		this.command = s;
		this.list=list;
	}

	@Override
	public List<String> call() throws Exception {
		list.add(Thread.currentThread().getName() + " Start. Command = " + command);
		processCommand();
		list.add(Thread.currentThread().getName() + " End.");
		return list;
	}

	private void processCommand() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return this.command;
	}
}
