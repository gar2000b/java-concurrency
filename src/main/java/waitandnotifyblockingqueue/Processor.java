package waitandnotifyblockingqueue;

import java.util.LinkedList;

/**
 * This is a lower level version of our blocking queue example.
 * 
 * It achieves all this with conditioning and out the box wait() and
 * notify().
 * 
 * Note: wait() and notify() are called against the intrinsic lock
 * (lock) specified in this Processor.
 **/
public class Processor {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();
	private int value = 0;

	public void produce() throws InterruptedException {

		synchronized (lock) {
			while (list.size() == LIMIT) {
				lock.wait();
			}

			list.add(value++);
			lock.notify();
		}

	}

	public void consume() throws InterruptedException {

		synchronized (lock) {
			while (list.size() == 0) {
				lock.wait();
			}

			System.out.println("List size is: " + list.size());
			int value = list.removeFirst();
			System.out.println("; value is " + value);
			lock.notify();
		}
		Thread.sleep(1000);
	}

}
