package com.demo;

import java.util.List;

public class WorkerRunnable implements Runnable {

	private String command;
	private List<String> list;

	public WorkerRunnable(String s,List<String> list) {
		this.command = s;
		this.list=list;
	}

	@Override
	public void run() {
		list.add(Thread.currentThread().getName() + " Start. Command = " + command);
		processCommand();
		list.add(Thread.currentThread().getName() + " End.");
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
