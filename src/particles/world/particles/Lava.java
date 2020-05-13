package particles.world.particles;

import particles.world.Particle;
import particles.world.ParticleTypes;

public class Lava extends Particle{
	
	public Lava() {
		type = ParticleTypes.LAVA;
		color = color.ORANGE;
		solid = false;
		liquid = true;
		gas = false;
		logic = false;
		updatetime = 50;
	}

}
