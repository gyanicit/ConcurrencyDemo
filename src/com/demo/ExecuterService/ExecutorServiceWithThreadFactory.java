package com.demo.ExecuterService;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorServiceWithThreadFactory {
	public static void main(String[] args) {
		ExecutorService ex = Executors.newFixedThreadPool(5, new MyThreadFactory("Test"));
		Future<String> s = ex.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("----->" + Thread.currentThread().getName());
				return "A";
			}
		});
		Future<String> s1 = ex.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("----->" + Thread.currentThread().getName());
				return "B";
			}
		});
	}

}

class MyThreadFactory implements ThreadFactory {

	private final String name;
	private final AtomicInteger integer = new AtomicInteger(1);

	public MyThreadFactory(String name) {
		this.name = name;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, name + integer.getAndIncrement());
		return t;
	}
}