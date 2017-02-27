package executorcompletionservice;

/**
 * 
 * TODO: Implement an example of the ExecutorCompletionService.
 * 
 * The advantage of using this over a regular ExecutorService with
 * Futures is that you block against the service itself not the
 * future.get().
 * 
 * The ExecutorService has the downside of waiting on the tasks to
 * complete in the order that they were fired off.
 * 
 * The ExecutorCompletionService is an improvement (maintains a queue
 * internally) as futures are drawn in the order that they were
 * finished asynchronously. Results in a reduction of real-time
 * execution.
 * 
 * @author Digilogue
 *
 */
public class ExecutorCompletionServiceExample {

	public static void main(String[] args) {
		// TODO
	}
}
