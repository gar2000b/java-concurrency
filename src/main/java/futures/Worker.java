package futures;

import java.util.concurrent.Callable;

public class Worker implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		// Do some work.
		int workerState = 10;
		System.out.println("Worker local result to be returned is: " + workerState
				+ " - going to sleep for 5 secs before returning.");
		Thread.sleep(5000);
		return Integer.valueOf(workerState);
	}
}
