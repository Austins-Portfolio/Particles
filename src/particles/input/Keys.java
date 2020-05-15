package particles.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import particles.world.World;
import particles.world.particles.ParticlePallet;

public class Keys implements KeyListener{

	private World world;
	private ParticlePallet particlePallet;
	
	public Keys(World world, ParticlePallet particlePallet) {
		this.world = world;
		this.particlePallet = particlePallet;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_1) {
			particlePallet.setSelection(1);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_2) {
			particlePallet.setSelection(2);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_3) {
			particlePallet.setSelection(3);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_4) {
			particlePallet.setSelection(4);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_5) {
			particlePallet.setSelection(5);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			world.flipShould_Update();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
