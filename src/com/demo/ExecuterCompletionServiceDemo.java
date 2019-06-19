package com.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecuterCompletionServiceDemo {
	private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);
	private static final int numberOfTask = 10;

	public static void main(String args[]) throws InterruptedException, ExecutionException {

		CompletionService<String> completionService = new ExecutorCompletionService<String>(threadpool);

		
		System.out.println("Submitting Task ...");

		for (int i = 1; i <= numberOfTask; i++) {
			FactorialCalculator task = new FactorialCalculator(i);
			completionService.submit(task);
		}

		System.out.println("Task is submitted by- " + Thread.currentThread().getName().toUpperCase()+" Thread");

		int received = 1;
		boolean errors = false;

		while (received <= numberOfTask && !errors) {
			Future<String> resultFuture = completionService.take(); // blocks if none available
			try {
				String result = resultFuture.get();
				System.out.println(result);
				received++;
			} catch (Exception e) {
				errors = true;
			}
		}

		threadpool.shutdown();
		System.out.println("Finished all execution");
	}
}

class FactorialCalculator implements Callable<String> {

	private final int number;

	public FactorialCalculator(int number) {
		this.number = number;
	}

	@Override
	public String call() {
		String output = null;
		try {
			if(number==5) {
				Thread.sleep(2000);
			}
			output = "Factorial of "+number+" "+factorial(number);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		return output;
	}

	private long factorial(int number) throws InterruptedException {

		if (number < 0) {
			throw new IllegalArgumentException("Number must be greater than zero");
		}
		long result = 1;
		while (number > 0) {
			Thread.sleep(1); // adding delay for example
			result = result * number;
			number--;
		}
		return result;
	}
}
