package demo13;

/**
 * 
 * This app demonstrates how local state is impacted when two Threads vie
 * for the same resource. Basically the conclusion is that local variables /
 * local references are not affected, however instance variables / instance 
 * references are.
 * 
 * @author Digilogue
 *
 */
public class App {

	private SharedObject sharedObject = new SharedObject();

	public static void main(String[] args) throws InterruptedException {
		App app = new App();
		app.doWork();
	}

	public void doWork() throws InterruptedException {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Thread 1 going to sleep");
				try {
					sharedObject.setSomeState(6);
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Thread 1 waking up");
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Thread 2 going to sleep");
				try {
					Thread.sleep(3000);
					sharedObject.setSomeState(8);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Thread 2 waking up");
			}
		});

		t1.start();
		t2.start();
	}
}
