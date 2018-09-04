package timer;

import java.util.concurrent.*;

public class Timer extends Thread {

	private final ScheduledExecutorService scheduler;
	private final ExecutorService service;
	private final Runnable runnable;

	private final long milliseconds;
	private final long loops;

	public Timer(Runnable runnable, long milliseconds, long loops) {
		this.runnable = runnable;
		this.milliseconds = milliseconds;
		this.loops = loops;

		service = Executors.newFixedThreadPool(1);
		scheduler = Executors.newScheduledThreadPool(1);
	}

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

	public ScheduledFuture<?> runAsynchronously() {
		ScheduledFuture<?> future = null;
		for (int i = 0; i < loops; i++) {
			future = scheduler.schedule(runnable, milliseconds * (i + 1), TimeUnit.MILLISECONDS);
		}
		return future;
	}
}
