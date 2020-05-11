package threadpools;

/**
 * 
 * 1. Simple fire and forget using lambda within new Thread instance calling a
 * predefined method.
 * 
 * 2. Simple fire and forget example using a combination of lambda, anonymous
 * code blocks and new Thread all in one statement.
 * 
 * 3. ^ same as 3. except wrapped within a for construct. It should be noted
 * however that Thread Pools are a more efficient means of spawning multiple
 * independent Threads.
 * 
 * 4. Similar to 1. except we demonstrate that the lambda becomes nothing more
 * than a type that can be executed. The function itself just becomes a sysout -
 * drops the predefined method call.
 * 
 * 5. Reuses the Runnable type from 4. and calls its run method() direct to
 * prove that this is nothing more than a type. Of course, this will be a non
 * concurrent version of this call and will therefore just be executed in-line.
 * In fact, although Runnable has concurrent thread connotations from previous
 * texts, there is actually no good reason not to use it in this vein.
 * 
 * @author Digilogue on 19/02/17
 *
 */
public class SimpleFireAndForgetExample {

	public static void main(String[] args) throws InterruptedException {

		String input = "some more input.";

		// Example 1.
		new Thread(() -> process("some input...")).start();

		// Example 2.
		new Thread(() -> {
			System.out.println("Working: " + input);
		}).start();

		// Example 3.
		for (int i = 0; i < 5; i++) {
			int j = i;
			new Thread(() -> {
				System.out.println("Working: " + j);
			}).start();
		}

		// Example 4.
		Runnable aRunnable = () -> System.out.println("hi ho, hi ho, it's off to work we go...");
		new Thread(aRunnable).start();

		// Example 5.
		Thread.sleep(2000);
		System.out.println("\nwhen called a second time direct:");
		aRunnable.run();
	}

	public static void process(String input) {
		System.out.println("Working: " + input);
	}
}
