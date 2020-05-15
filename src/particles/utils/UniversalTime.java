package particles.utils;

public class UniversalTime {

	private static long time = System.currentTimeMillis();
	private static long paused_time = 0;
	private static boolean paused = false;
	private static long pause_time_start = System.currentTimeMillis();
	private static long pause_time_end = System.currentTimeMillis();
	
	public static void tickTime() {
		time=System.currentTimeMillis();
		if(paused) {
			pause_time_end = System.currentTimeMillis();
		}else {
			pause_time_start = System.currentTimeMillis();
		}
	}
	
	public static long getTime() {
		return time-paused_time;
	}
	
	public static void pause() {
		paused = true;
	}
	
	public static void unpause() {
		if((pause_time_end-pause_time_start)>0) {
			paused_time+=(pause_time_end-pause_time_start);
		}
		paused = false;
	}
	
}
