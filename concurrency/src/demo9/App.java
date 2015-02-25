package demo9;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Count down latch example. Blocks a thread until latch has reached 0.
// You block the Thread with latch.await(); The following three lines make this sequence clearer:
// CountDownLatch latch = new CountDownLatch(3);
// latch.countDown(); - Threads spawn off, have a handle on the latch and counts it down.
// latch.await(); - acts like a blocking call until the latch reaches 0.
class Processor implements Runnable {

	private CountDownLatch latch;
	
	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}
	
	@Override
	public void run() {
		System.out.println("Processor Running...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		latch.countDown();
	}
	
}

public class App {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);
		
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		for(int i = 0; i < 3; i++) {
			executorService.submit(new Processor(latch));
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Completed!!!");
	}
}
