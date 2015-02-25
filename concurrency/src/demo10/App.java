package demo10;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// Blocking Queues. In a nutshell, this application is spawning up two Threads. One is producing and putting
// a random number on to the Blocking Queue. One is consuming and taking the next value off the queue.
// You'll notice that the queue size is always around 9 or 10 as it is constantly trying to put items on to the
// queue (notice infinite while). Both put() and take() are blocking functions if limits are reached.
public class App {
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(
			10);

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}

	private static void producer() throws InterruptedException {
		Random random = new Random();
		while (true) {
			queue.put(random.nextInt(100));
		}
	}

	private static void consumer() throws InterruptedException {
		Random random = new Random();

		while (true) {
			Thread.sleep(100);
			if (random.nextInt(10) == 0) {
				Integer value = queue.take();
				System.out.println("Taken value: " + value
						+ "; Queue size is: " + queue.size());
			}
		}
	}
}
