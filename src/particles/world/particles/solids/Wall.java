package particles.world.particles.solids;

import java.awt.Color;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;

public class Wall extends Particle{
	
	public Wall() {
		type = ParticleTypes.WALL;
		color = Color.WHITE;
	}
	
	@Override
	public void update(World world, int w, int h) {
		
	}

}
