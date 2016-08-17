package demo4;

import java.util.Scanner;

// Making use of the volatile keyword. You have to use this to guarantee that this running member variable is
// properly updated by the main Thread and that the proc1 Thread isn't optimising use of this variable and 
// sees the update straight away.
class Processor extends Thread {

	private volatile boolean running = true;

	public void run() {
		while (running) {
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void shutdown() {
		running = false;
	}
}

public class App {
	public static void main(String[] args) {
		Processor proc1 = new Processor();
		proc1.start();

		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();

		proc1.shutdown();
	}
}
