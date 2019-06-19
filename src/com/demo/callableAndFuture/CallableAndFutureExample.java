package com.demo.callableAndFuture;

import java.util.concurrent.*;

public class CallableAndFutureExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		Callable<String> callable = () -> {
			// Perform some computation
			System.out.println("Entered Callable");
			Thread.sleep(5000);
			return "Hello from Callable";
		};

		System.out.println("Submitting Callable");
		Future<String> future = executorService.submit(callable);

		// This line executes immediately
		System.out.println("Do something else while callable is getting executed");

		System.out.println("Retrieve the result of the future -->> "+future.isDone());
		// Future.get() blocks until the result is available
		String result = future.get();
		System.out.println(result);
		System.out.println("Execution End-->>"+future.isDone());
		executorService.shutdown();
	}
}