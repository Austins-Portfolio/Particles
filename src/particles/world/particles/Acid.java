package particles.world.particles;

import particles.world.Particle;
import particles.world.ParticleTypes;

public class Acid extends Particle{
	
	public Acid() {
		type = ParticleTypes.ACID;
		color = color.GREEN;
		solid = false;
		liquid = true;
		gas = false;
		logic = false;
	}

}
