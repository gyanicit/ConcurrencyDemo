package com.demo.ConcurrencyIssuesAndThreadSynchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RaceConditionResolveBySynchronized {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		//SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
		SynchronizedBlockCounter synchronizedCounter=new SynchronizedBlockCounter();

		for (int i = 0; i < 1000; i++) {
			executorService.submit(() -> synchronizedCounter.increment());
		}

		executorService.shutdown();
		executorService.awaitTermination(60, TimeUnit.SECONDS);

		System.out.println("Final count is : " + synchronizedCounter.getCount());
	}
}

class SynchronizedCounter {
	private int count = 0;

	// Synchronized Method
	public synchronized void increment() {
		count = count + 1;
	}

	public int getCount() {
		return count;
	}
}
class SynchronizedBlockCounter {
	private int count = 0;

	// Synchronized Method
	public void increment() {
		// Synchronized Block - 

	    // Acquire Lock
	    synchronized (this) { 
	        count = count + 1;
	    }   
	    // Release Lock
	}

	public int getCount() {
		return count;
	}
}
