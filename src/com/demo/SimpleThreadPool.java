package com.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;	

public class SimpleThreadPool {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		String status=execute(list);
		System.out.println("---status-->"+status);
		for(String l:list) {
			System.out.println("---->"+l);
		}
	}

	public static String execute(List<String> list) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            //Runnable worker = new WorkerRunnable("" + i,list);
            //executor.execute(worker);
        	
        	WorkerCallable worker=new WorkerCallable("" + i, list);
            Future<List<String>> s=executor.submit(worker);
          }
        executor.shutdown();
        
        while (!executor.isTerminated()) {
        }
        //System.out.println("Finished all threads");
		return "Finished all threads";
	}

}
