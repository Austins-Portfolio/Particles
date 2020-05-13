package particles.world.particles.solids;

import java.awt.Color;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;

public class Glass extends Particle{
	
	public Glass() {
		type = ParticleTypes.GLASS;
		color = new Color(0.25f,0.25f,0.25f,1);
	}
	
	@Override
	public void update(World world, int w, int h) {
		
	}

}
