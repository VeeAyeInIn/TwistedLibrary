package timer;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Persistent Counter that will keep its value through a {@link Thread}.
 *
 * @author GrimlyTwisted
 */
public class Counter {

	private final AtomicLong atom = new AtomicLong();

	/**
	 * Instantiates a new Counter.
	 *
	 * @param initial the initial value of the Counter
	 */
	public Counter(final long initial) {
		atom.set(initial);
	}

	/**
	 * Increment the value by 1.
	 */
	public synchronized void increment() {
		atom.incrementAndGet();
	}

	/**
	 * Decrement the value by 1.
	 */
	public synchronized void decrement() {
		atom.decrementAndGet();
	}

	/**
	 * The value of the Counter at this moment.
	 *
	 * @return the value of the Counter
	 */
	public synchronized long value() {
		return atom.get();
	}
}
