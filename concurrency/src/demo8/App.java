package demo8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// This example makes use of Thread Pools via the Executor Service. In essence, you have a pool of available Thread Workers
// of a pre-defined size (in our case two), that will spawn off a Thread Process against a Threaded objected.
// There will only be two threads running at any point in time. When a worker has finished processing, it becomes
// available again and gets chucked back into the pool to pick up another task. In this example, we submit 5 tasks
// The ExecutorService will essentially work its way through all 5 (2 at a time) until all are complete.
// There is a lot of overhead to starting new Threads. The idea here is that we are recycling the Threads in the 
// Thread pool, you avoid that Thread initialisation overhead.
class Processor implements Runnable {

	private int id;

	public Processor(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Starting: " + id);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Completed: " + id);
	}

}

public class App {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
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
