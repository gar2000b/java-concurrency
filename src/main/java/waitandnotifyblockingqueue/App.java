package waitandnotifyblockingqueue;

/**
 * Low level wait and notify -> explained in Processor class.
 * 
 * This class has a main that simple spins up x2 threads that
 * endlessly call produce() and consume() against the processor
 * instance.
 * 
 * @author Digilogue
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {

		final Processor processor = new Processor();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true)
						processor.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true)
						processor.consume();
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
}
