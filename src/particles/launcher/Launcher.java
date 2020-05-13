package particles.launcher;

import java.awt.Color;
import java.awt.Graphics2D;

import particles.graphics.Window;
import particles.input.Keys;
import particles.input.Mouse;
import particles.input.MouseMotion;
import particles.utils.Clock;
import particles.world.ParticlePallet;
import particles.world.World;

public class Launcher {

	public static void main(String[] args) {
		Window window = new Window(1280,600,"Particles");
		World world = new World(window.getWidth(), window.getHeight(),4);
		ParticlePallet pp = new ParticlePallet();
		Mouse mouse = new Mouse(world, pp);
		Keys keys = new Keys(pp);
		window.getFrame().addMouseListener(mouse);
		window.getFrame().addMouseMotionListener(mouse.mouseMotion);
		window.getFrame().addMouseWheelListener(mouse.mouseWheel);
		window.getFrame().addKeyListener(keys);
		
		Clock update_clock = new Clock(1000/240) {
			
			@Override
			public void tick() {
				if(!paused) {
					busy = true;
					world.update();
					busy = false;
				}
			}
			
		};
		
		Clock draw_clock = new Clock(1000/60) {

			int average_amount = 60;
			long averagetime = 0;
			long starttime = System.currentTimeMillis();
			int count = 0;
			
			@Override
			public void tick() {
				starttime = System.currentTimeMillis();
				update_clock.pause();
				update_clock.sleepTillDone(this, 10);
				Graphics2D g2 = window.getGraphics();
				g2.clearRect(0, 0, window.getWidth(), window.getHeight());
				world.draw(g2);
				mouse.drawMouse(g2);
				g2.dispose();
				window.swapBuffer();
				update_clock.unpause();
				long endtime = System.currentTimeMillis();
				averagetime += endtime - starttime;
				count++;
				if(count >= average_amount) {
					averagetime /= count;
					window.getFrame().setTitle(window.getName()+" FPS:"+1000/(averagetime+1)+" Selection:"+pp.getSelectionAsString());
					averagetime = 0;
					count = 0;
				}
			}
			
		};
		
	}

}
