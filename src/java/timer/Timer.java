package timer;

import java.util.concurrent.*;

/**
 * Timer is an object that can utilize a runnable to run a several times, at certain intervals.
 * There are two ways to run methods, {@link #runSynchronously()}, and {@link #runAsynchronously()}.
 * <p>
 * {@link #runSynchronously()} Executes the runnable on the main thread, so actions after starting
 * the timer will not happen until the timer ends. Seeing as the method runs on the main thread,
 * this can cause problems to arise. Due to the synchronous method, other tasks cannot be preformed,
 * unless they are already running as an asynchronous task.
 * <p>
 * {@link #runAsynchronously()} Executes the runnable on a different thread, so actions will be
 * preformed throughout the code. This will allow for the possibility of multiple functions
 * running independently, as opposed to a normal method, reading from top to bottom.
 *
 * @author GrimlyTwisted
 */
public class Timer {

	private final ScheduledExecutorService scheduler;
	private final ExecutorService service;
	private final Runnable runnable;

	private final long milliseconds;
	private final long loops;

	/**
	 * Instantiates a new Timer.
	 *
	 * @param runnable     the runnable, better to instantiate in the parameter slot itself
	 * @param milliseconds the milliseconds between running an interval
	 * @param loops        the amount of times to run the function
	 */
	public Timer(Runnable runnable, long milliseconds, long loops) {
		this.runnable = runnable;
		this.milliseconds = milliseconds;
		this.loops = loops;

		service = Executors.newFixedThreadPool(1);
		scheduler = Executors.newScheduledThreadPool(1);
	}

	/**
	 * Executes the runnable synchronously, with the main thread.
	 *
	 * @return instance of {@link Future}, returned from executing the {@link ExecutorService}
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	public Future<?> runSynchronously() throws InterruptedException {
		synchronized (service) {
			Future<?> future = null;
			for (int i = 0; i < loops; i++) {
				service.wait(milliseconds);
				future = service.submit(runnable);
			}
			return future;
		}
	}

	/**
	 * Executes the runnable asynchronously, in a different thread.
	 *
	 * @return instance of {@link ScheduledFuture}, returned from executing the {@link ScheduledExecutorService}
	 */
	public ScheduledFuture<?> runAsynchronously() {
		ScheduledFuture<?> future = null;
		for (int i = 0; i < loops; i++) {
			future = scheduler.schedule(runnable, milliseconds * (i + 1), TimeUnit.MILLISECONDS);
		}
		return future;
	}
}