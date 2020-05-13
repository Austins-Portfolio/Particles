package particles.world.particles.liquids;

import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;

public class Water extends Particle{
	
	public Water() {
		type = ParticleTypes.WATER;
		color = color.BLUE;
		solid = false;
		liquid = true;
		gas = false;
		logic = false;
	}

}
