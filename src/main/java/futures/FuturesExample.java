package futures;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * "I promise to give you the result at some point in the future when
 * I have it"
 * 
 * This example creates a Callable Worker which implements a call()
 * which gets invoked from the ExecutorService.
 * 
 * The result is a Future of the call() return type <Integer> which of
 * course represents the result that will eventually be returned once
 * the worker has returned its result from call().
 * 
 * We simulate work being done by the worker by sending it to sleep
 * for 5 secs before returning the result.
 * 
 * We get the result by invoking get() against the future (at any
 * point in any position in the code) which essentially acts as a
 * blocking call until the worker is finished processing and its
 * result returned.
 * 
 * @author Digilogue
 *
 */
public class FuturesExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Integer> future = executor.submit(new Worker());

		System.out.println("The result of the future is: " + future.get());

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
