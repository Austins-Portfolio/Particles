package particles.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import particles.world.particles.ParticlePallet;

public class Keys implements KeyListener{

	private ParticlePallet particlePallet;
	
	public Keys(ParticlePallet particlePallet) {
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
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
