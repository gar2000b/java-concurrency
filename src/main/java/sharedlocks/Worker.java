package sharedlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Threads using shared locks via synchronized:
 * 
 * There is only one intrinsic lock for the worker object.
 * 
 * In this example, you can see that we spawn off two Threads. We join
 * both Threads to the main Thread (as we saw before). We had to make
 * our target methods synchronized in order to eliminate the
 * interference problem.
 * 
 * So this works fine but it takes around 4 seconds. The issue is (as
 * mentioned above) that there is only one intrinsic lock per object.
 * 
 * So we have two Threads vying for the one locked resource. demo 7
 * will demonstrate how to create multiple locks to speed up this
 * problem.
 **/
public class Worker {

	private Random random = new Random();

	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();

	public synchronized void stageOne() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		list1.add(random.nextInt(100));
	}

	public synchronized void stageTwo() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		list2.add(random.nextInt(100));
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
			System.out.println("Thread 1 complete.");
			t2.join();
			System.out.println("Thread 2 complete.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		System.out.println("Time take: " + (end - start));
		System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
	}
}
