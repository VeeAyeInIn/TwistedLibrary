package timer;

import org.apache.commons.lang3.Validate;

import java.util.concurrent.TimeUnit;

public class Timer extends Thread {

    private final Thread thread = new Thread(this);
    private final Runnable runnable;
    private final long millis;
    private final long loops;

    public Timer(Runnable runnable, long millis) {
        this.runnable = runnable;
        this.millis = millis;
        this.loops = 1;
    }

    public Timer(Runnable runnable, long millis, long loops) {
        this.runnable = runnable;
        this.millis = millis;
        this.loops = loops;
    }

    public Timer(Runnable runnable, TimeUnit unit, long duration) {
        this.runnable = runnable;
        this.millis = unit.toMillis(duration);
        this.loops = 1;
    }

    public Timer(Runnable runnable, TimeUnit unit, long duration, long loops) {
        this.runnable = runnable;
        this.millis = unit.toMillis(duration);
        this.loops = loops;
    }

    private void loop(Thread thread, long millis, long loops) throws InterruptedException {

        Validate.notNull(thread, "Thread cannot be null");
        Validate.isTrue(millis > 0, "Millis cannot be less than or equal to 0");
        Validate.isTrue(loops > 0, "Loops cannot be less than or equal to 0");

        while (loops > 0) {
            sleep(millis);
            runnable.run();
            loops--;
        }
    }

    public void run() {
        try {
            loop(thread, millis, loops);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
