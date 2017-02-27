package reentrantlock;

/**
 * The reentrant lock is an object oriented equivalent of the
 * synchronized locking mechanism.
 * 
 * The synchronised block assumes that you want to lock and unlock
 * objects declaratively not procedurally.
 * 
 * The Lock interface abstracts the notions of locking and unlocking
 * into an instance separate from the actual object we want to operate
 * on.
 * 
 * Look at the Worker class for implementation of reentrant locks.
 * 
 * Alternative to the independentlocks demo which uses the traditional
 * synchronized blocks.
 * 
 * @author Digilogue
 *
 */
public class ReentrantLockDemo {

	public static void main(String[] args) {
		Worker worker = new Worker();
		worker.main();
	}
}
