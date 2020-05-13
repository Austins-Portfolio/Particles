package particles.input;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import particles.utils.Clock;
import particles.world.World;
import particles.world.particles.ParticlePallet;

public class Mouse implements MouseListener{
	
	public World world;
	public MouseMotion mouseMotion;
	public MouseWheel mouseWheel;
	private boolean leftdown = false,rightdown = false, middledown = false;
	private int mouseSize = 10;
	private ParticlePallet particlePallet;
	
	public Mouse(World world, ParticlePallet particlePallet) {
		this.world = world;
		this.mouseMotion = new MouseMotion();
		this.mouseWheel = new MouseWheel(this);
		this.particlePallet = particlePallet;
		
		Clock clock = new Clock(1000/240) {

			@Override
			public void tick() {
				if(leftdown) {
					for(int x = mouseMotion.x-mouseSize/2; x < mouseMotion.x+mouseSize/2;x++) {
						for(int y = mouseMotion.y-mouseSize/2; y < mouseMotion.y+mouseSize/2;y++) {
							world.addParticleMouse(x, y, particlePallet.getLeftSelection());
						}
					}
				}
				if(rightdown) {
					for(int x = mouseMotion.x-mouseSize/2; x < mouseMotion.x+mouseSize/2;x++) {
						for(int y = mouseMotion.y-mouseSize/2; y < mouseMotion.y+mouseSize/2;y++) {
							world.addParticleMouse(x, y, particlePallet.getRightSelection());
						}
					}
				}
				if(middledown) {
					for(int x = mouseMotion.x-mouseSize/2; x < mouseMotion.x+mouseSize/2;x++) {
						for(int y = mouseMotion.y-mouseSize/2; y < mouseMotion.y+mouseSize/2;y++) {
							world.addParticleMouseNoSafety(x, y, null);
						}
					}
				}
			}
			
		};
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			leftdown = true;
		}
		if(e.getButton() == MouseEvent.BUTTON2) {
			middledown = true;
		}
		if(e.getButton() == MouseEvent.BUTTON3) {
			rightdown = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			leftdown = false;
		}
		if(e.getButton() == MouseEvent.BUTTON2) {
			middledown = false;
		}
		if(e.getButton() == MouseEvent.BUTTON3) {
			rightdown = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mouseSizeNotch(int notch) {
		if(notch > 0) {
			mouseSize*=(notch*2);
		}else if(notch < 0) {
			mouseSize = mouseSize/(notch*-2);
		}
		if(mouseSize < world.getWorld_scale()) {
			mouseSize = world.getWorld_scale();
		}
		if(mouseSize > world.getWorld_scale()*30) {
			mouseSize = world.getWorld_scale()*30;
		}
	}
	
	public void drawMouse(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.drawRect(mouseMotion.x-mouseSize/2, mouseMotion.y-mouseSize/2, mouseSize, mouseSize);
	}

}
