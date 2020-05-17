package particles.world.particles.gases;

import java.awt.Color;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;

public class Plasma extends Particle{
	
	public void init() {
		type = ParticleTypes.FIRE;
		color = Color.MAGENTA.darker();
		solid = false;
		liquid = false;
		gas = true;
		logic = true;
		non_insulator = true;
		lifetime = true;
		update_time = 50;
		logic_update_time = 100;
		life_time = 2000;
		heat = 40000;
		heat_dispersion_rate = 1;
		particle_class = this.getClass();
	}
	
	public void lifetime(World world, int w, int h) {
		if(lifetimeUp()) {
			world.addParticle(w, h, null);
		}
	}

}
