package timer;

import function.ThreadFunction;
import org.apache.commons.lang3.Validate;

import java.util.concurrent.TimeUnit;

public class Timer extends Thread {

	private final ThreadFunction function;
	private final long millis;
	private final long loops;

	public Timer(ThreadFunction function, long millis) {
		this.function = function;
		this.millis = millis;
		this.loops = 1;
	}

	public Timer(ThreadFunction function, long millis, long loops) {
		this.function = function;
		this.millis = millis;
		this.loops = loops;
	}

	public Timer(ThreadFunction function, TimeUnit unit, long duration) {
		this.function = function;
		this.millis = unit.toMillis(duration);
		this.loops = 1;
	}

	public Timer(ThreadFunction function, TimeUnit unit, long duration, long loops) {
		this.function = function;
		this.millis = unit.toMillis(duration);
		this.loops = loops;
	}

	private void loop(Thread thread, long millis, long loops) throws InterruptedException {

		Validate.notNull(thread, "Thread cannot be null");
		Validate.isTrue(millis > 0, "Millis cannot be less than or equal to 0");
		Validate.isTrue(loops > 0, "Loops cannot be less than or equal to 0");

		while (loops > 0) {
			sleep(millis);
			function.run(this);
			loops--;
		}
	}

	public void run() {
		try {
			loop(this, millis, loops);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
