package particles.world.particles;

import java.awt.Color;

import particles.world.Particle;
import particles.world.ParticleTypes;
import particles.world.World;

public class Glass extends Particle{
	
	public Glass() {
		type = ParticleTypes.GLASS;
		color = new Color(0.25f,0.25f,0.25f,1);
	}
	
	@Override
	public void update(World world, int w, int h) {
		
	}

}
