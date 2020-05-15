package particles.world.particles.gases;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;

public class Smoke extends Particle{
	
	public void init() {
		type = ParticleTypes.SMOKE;
		color = color.DARK_GRAY;
		solid = false;
		liquid = false;
		gas = true;
		logic = false;
		lifetime = true;
		update_time = 50;
		logic_update_time = 1000;
		life_time = 2000;
		heat = 30;
		heat_dispersion_rate = 0.75;
	}
	
	public void lifetime(World world, int w, int h) {
		if(lifetimeUp()) {
			world.addParticle(w, h, null);
		}
	}
	
}
