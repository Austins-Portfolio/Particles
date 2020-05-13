package particles.utils;

public abstract class Clock implements Runnable{

	private long time;
	private boolean running = false;
	protected boolean busy = false;
	protected boolean paused = false;
	private Thread thread;
	
	public Clock(long time) {
		this.time = time;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		running = true;
		while(running) {
			tick();
			try {
				Thread.sleep(time);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sleep(long mills) {
		try {
			Thread.sleep(mills);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract void tick();

	public long getTime() {
		return time;
	}

	public boolean isRunning() {
		return running;
	}

	public Thread getThread() {
		return thread;
	}
	
	public void pause() {
		paused = true;
	}
	
	public void unpause() {
		paused = false;
	}
	
	public void sleepTillDone(Clock clock, long mills) {
		while(busy) {
			clock.sleep(mills);
		}
	}
	
	public void terminate() {
		running = false;
	}
	
}
