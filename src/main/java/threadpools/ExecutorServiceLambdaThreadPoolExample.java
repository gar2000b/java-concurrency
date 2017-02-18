package threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Offers similar functionality to the
 * ExecutorServiceCachedThreadPoolExample.
 * 
 * This time, we don't use the Runnable processor but a simple method
 * testLamba() to act as our process.
 * 
 * The Lambda expression in the constructor basically provides a
 * syntactic reduction with an anonymous implementation of the
 * Runnable Interface for testLambda().
 * 
 * In other words, it turns testLambda() into a Runnable type under
 * the hood. Extremely powerful.
 * 
 * @author Digilogue on 18/02/17
 *
 */
public class ExecutorServiceLambdaThreadPoolExample {

	public ExecutorServiceLambdaThreadPoolExample() {

		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 0; i < 5; i++) {
			System.out.println("i is: " + i);
			int j = i;
			executor.submit(() -> testLambda(j));
		}

		executor.shutdown();
		System.out.println("All tasks submitted");

		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("All tasks completed.");
	}

	public static void main(String[] args) {
		new ExecutorServiceLambdaThreadPoolExample();
	}

	public static void testLambda(int id) {
		System.out.println("Testing out Lambda expression from executor with id: " + id);
	}
}
