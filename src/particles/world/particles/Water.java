package particles.world.particles;

import particles.world.Particle;
import particles.world.ParticleTypes;

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
