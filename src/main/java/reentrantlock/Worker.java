package reentrantlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * Making use of reentrant locks.
 * 
 * In this example, you can see that we spawn off two Threads. We join
 * both Threads to the main Thread (as we saw before). We use
 * reentrant locks in order to eliminate the interference problem.
 * 
 * So this works fine and it takes around 2 seconds as we are now
 * using two reentrant locks.
 * 
 **/
public class Worker {

	private Random random = new Random();

	private ReentrantLock lock1 = new ReentrantLock();
	private ReentrantLock lock2 = new ReentrantLock();

	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();

	public void stageOne() {

		lock1.lock();
		try {
			Thread.sleep(1);
			list1.add(random.nextInt(100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock1.unlock();
		}
	}

	public void stageTwo() {

		lock2.lock();
		try {
			Thread.sleep(1);
			list2.add(random.nextInt(100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock2.unlock();
		}
	}

	public void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}

	public void main() {
		System.out.println("Starting...");

		long start = System.currentTimeMillis();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		System.out.println("Time take: " + (end - start));
		System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
	}
}
