package demo11;

import java.util.Scanner;

// wait() and notify() example. Again, app spawns two Threads. The first calls produce() which acquires the lock and calls wait().
// This Thread will then be put into the waiting state (releasing the lock) until it is notified by another Thread that acquires 
// the lock and then calls notify(). This is hat happens next, consume is called from the second Thread which of course acquires 
// the lock and calls notify(). Note: wait and notify can only be called from within a synchronised block.
public class Processor {

	public void produce() throws InterruptedException{
		synchronized (this) {
			System.out.println("Producer Thread running...");
			wait();
			System.out.println("Resumed.");
		}
	}
	
	public void consume() throws InterruptedException{
		
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		
		synchronized (this) {
			System.out.println("Waiting for return key.");
			scanner.nextLine();
			System.out.println("Return Key pressed.");
			notify();
		}
	}
}
