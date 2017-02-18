package threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import threadpools.processor.Processor;

/**
 * This example makes use of Thread Pools via the Executor Service.
 * 
 * In essence, you have a pool of available Thread Workers that will
 * spawn off a Thread Process against a Threaded objected.
 * 
 * The cached thread pool will spawn as many threads as is needed.
 * 
 * When a worker has finished processing, the thread becomes available
 * again and gets chucked back into the pool for another task.
 * However, if threads haven't bee used after 60 secs, they are
 * destroyed and removed from the pool.
 * 
 * In this example, we submit 5 tasks. The ExecutorService will
 * essentially work its way through all 5 until all are complete.
 * 
 * You'll notice in this example that we are not blocking new
 * processes with a fixed upper limit on the number of threads it can
 * have in the pool (unlike the newFixedThreadPool).
 * 
 * There is a lot of overhead to starting new Threads. The idea here
 * is that we are recycling the Threads in the Thread pool, you avoid
 * that Thread initialisation overhead.
 */

public class ExecutorServiceCachedThreadPoolExample {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
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
