package threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import threadpools.processor.Processor;

/**
 * In ForkJoinPool, Processes are Daemon Threads, meaning that the
 * main execution thread will not wait for them to complete when the
 * JVM shuts down. More importantly, Processes may steal work from one
 * another, which is nice for Threaded recursion - e.g. in the case of
 * Divide and Conquer Algorithms.
 * 
 * This example makes use of Thread Pools via the Executor Service.
 * 
 * In essence, you have a pool of available Thread Workers of a
 * predefined size (in our case 2 - as it should be a power of 2 for
 * divide and conquer problems), that will spawn off a Thread Process
 * against a Threaded objected. There will be 2 threads running at any
 * point in time as we set the parallelism argument to 2.
 * 
 * When a worker has finished processing, it becomes available again
 * and gets chucked back into the pool to pick up another task.
 * 
 * In this example, we submit 5 tasks. The ExecutorService will
 * essentially work its way through all 5 (2 at a time) until all are
 * complete.
 * 
 * There is a lot of overhead to starting new Threads. The idea here
 * is that we are recycling the Threads in the Thread pool, you avoid
 * that Thread initialisation overhead.
 */
public class ExecutorServiceForkJoinPoolExample {
	public static void main(String[] args) {
		// ExecutorService executor = ForkJoinPool.commonPool();
		// ExecutorService executor =
		// Executors.newWorkStealingPool(2);
		ExecutorService executor = new ForkJoinPool(2);
		for (int i = 0; i < 5; i++) {
			executor.submit(new Processor(i));
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
}
