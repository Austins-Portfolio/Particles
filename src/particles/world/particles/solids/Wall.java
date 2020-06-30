package particles.world.particles.solids;

import java.awt.Color;

import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;

public class Wall extends Particle{
	
	public Wall() {
		type = ParticleTypes.WALL;
		color = Color.WHITE;
		particle_class = this.getClass();
	}

}
