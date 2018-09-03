package timer;

import org.apache.commons.lang3.Validate;

import java.util.concurrent.TimeUnit;

public abstract class Timer extends Thread {

	public Timer(long millis) throws InterruptedException {
		loop(this, millis, 1);
	}

	public Timer(long millis, long loops) throws InterruptedException {
		loop(this, millis, loops);
	}

	public Timer(TimeUnit unit, long duration) throws InterruptedException {
		loop(this, unit.toMillis(duration), 1);
	}

	public Timer(TimeUnit unit, long duration, long loops) throws InterruptedException {
		loop(this, unit.toMillis(duration), loops);
	}

	private static void loop(Thread thread, long millis, long loops) throws InterruptedException {

		Validate.notNull(thread, "Thread cannot be null");
		Validate.isTrue(millis > 0, "Millis cannot be less than or equal to 0");
		Validate.isTrue(loops > 0, "Loops cannot be less than or equal to 0");

		loops--;
		while (loops > 0) {
			sleep(millis);
			thread.run();
			loops--;
		}
		sleep(millis);
	}

	public abstract void run();
}
