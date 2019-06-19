package com.demo.LocksAndAtomicVariables;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		ReadWriteCounter counter = new ReadWriteCounter();

		for (int i = 0; i < 1000; i++) {
			executorService.submit(() -> counter.incrementAndGetCount());
		}

		executorService.shutdown();
		executorService.awaitTermination(60, TimeUnit.SECONDS);

		System.out.println("Final count is : " + counter.getCount());
	}

}

class ReadWriteCounter {
	ReadWriteLock lock = new ReentrantReadWriteLock();

	private int count = 0;

	public int incrementAndGetCount() {
		lock.writeLock().lock();
		try {
			count = count + 1;
			return count;
		} finally {
			lock.writeLock().unlock();
		}
	}

	public int getCount() {
		lock.readLock().lock();
		try {
			return count;
		} finally {
			lock.readLock().unlock();
		}
	}
}
