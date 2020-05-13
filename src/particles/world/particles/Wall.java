package particles.world.particles;

import java.awt.Color;

import particles.world.Particle;
import particles.world.ParticleTypes;
import particles.world.World;

public class Wall extends Particle{
	
	public Wall() {
		type = ParticleTypes.WALL;
		color = Color.WHITE;
	}
	
	@Override
	public void update(World world, int w, int h) {
		
	}

}
