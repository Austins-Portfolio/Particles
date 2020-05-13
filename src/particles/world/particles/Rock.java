package particles.world.particles;

import java.awt.Color;

import particles.world.Particle;
import particles.world.ParticleTypes;
import particles.world.World;

public class Rock extends Particle{
	
	public Rock() {
		type = ParticleTypes.WALL;
		color = Color.GRAY;
	}
	
	@Override
	public void update(World world, int w, int h) {
		
	}

}
