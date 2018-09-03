package timer;

import java.util.concurrent.atomic.AtomicLong;

public class Counter {

	private final AtomicLong atom = new AtomicLong();

	public Counter(final long initial) {
		atom.set(initial);
	}

	public synchronized void increment() {
		atom.incrementAndGet();
	}

	public synchronized void decrement() {
		atom.decrementAndGet();
	}

	public synchronized long value() {
		return atom.get();
	}
}
