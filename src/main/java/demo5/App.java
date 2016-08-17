package demo5;

// Making use of synchronised - means that only one Thread can acquire the intrinsic lock of this object at a time.
// also making use of join() method against our Threads. Join basically halts the current Thread (in our case main)
// until the Threads (which execute the join method) have completed. Basically a stopping function.
public class App {

	private int count = 0;

	public synchronized void increment() {
		count++;
	}

	public static void main(String[] args) throws InterruptedException {
		App app = new App();
		app.doWork();
	}

	public void doWork() throws InterruptedException {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("Count is: " + count);
	}
}
