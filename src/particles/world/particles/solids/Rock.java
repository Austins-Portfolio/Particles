package particles.world.particles.solids;

import java.awt.Color;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;

public class Rock extends Particle{
	
	public Rock() {
		type = ParticleTypes.WALL;
		color = Color.GRAY;
	}
	
	@Override
	public void update(World world, int w, int h) {
		
	}

}
