package localvobjectstate;

/**
 * 
 * This app demonstrates how local v object state is impacted when two
 * Threads vie for the same resource.
 * 
 * Local state = variables and references declared within an instance
 * method.
 * 
 * Object state = instance variables and references declared within
 * the object or class.
 * 
 * Basically the conclusion is that local variables / local references
 * are not affected, however instance variables / instance references
 * are.
 * 
 * In other words: Object state (not Local state) is impacted by vying
 * threads and it is this state we need to protect with synchronized
 * blocks and methods etc.
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
