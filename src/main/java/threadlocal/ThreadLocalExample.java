package threadlocal;

/**
 * ThreadLocal example
 * 
 * This example makes use of the shared ThreadLocal type among several Threads.
 * The Threads are controlled to sleep for a duration, then awake rendering the
 * Thread Local variable value (it had previously set) out to the screen with
 * sysout.
 * 
 * All the threads are set to wake up 200ms apart from each other in a
 * controlled sequential fashion to help prove that ThreadLocal instances are
 * behaving as they are expected and retain only their copy of their respective
 * variable type for their own particular thread.
 * 
 * ThreadLocals are great, they promote memory efficiency, thread safety, it
 * provides a thread context for multiple classes per Thread flow and helps to
 * boost performance as all classes in that flow can reference the same type
 * without passing everything by reference at each hop.
 * 
 * In a nutshell, transaction state can be preserved, accessed and modified much
 * easier (per thread of control) for the duration of its potentially short
 * life-cycle.
 * 
 * @author gar20
 *
 */
public class ThreadLocalExample {
	static ThreadLocal<String> tl = new ThreadLocal<>();

	public static void main(String[] args) throws InterruptedException {
		tl.set("Gary's Thread");

		new Thread(() -> {
			tl.set("Derek's Thread");
			try {
				Thread.sleep(2000);
				System.out.println(tl.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			tl.set("Gill's Thread");
			try {
				Thread.sleep(1800);
				System.out.println(tl.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			tl.set("Jannice's Thread");
			try {
				Thread.sleep(1600);
				System.out.println(tl.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			tl.set("Andy's Thread");
			try {
				Thread.sleep(1400);
				System.out.println(tl.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		Thread.sleep(1200);
		System.out.println(tl.get());
	}
}
