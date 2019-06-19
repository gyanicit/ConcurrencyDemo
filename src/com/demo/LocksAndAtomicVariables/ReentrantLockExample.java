package com.demo.LocksAndAtomicVariables;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		ReentrantLockCounter counter = new ReentrantLockCounter();

		for (int i = 0; i < 1000; i++) {
			executorService.submit(() -> counter.increment());
		}

		executorService.shutdown();
		executorService.awaitTermination(60, TimeUnit.SECONDS);

		System.out.println("Final count is : " + counter.getCount());
	}
}


class ReentrantLockCounter {
    private final ReentrantLock lock = new ReentrantLock();

    private int count = 0;

    // Thread Safe Increment
    public void increment() {
        lock.lock();
        try {
            count = count + 1;
        } finally {
           lock.unlock();
        }
    }
    
    public int getCount() {
		return count;
	}
}