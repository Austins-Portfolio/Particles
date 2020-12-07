package particles.world.particles.liquids;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;
import particles.world.particles.gases.Fire;

public class Gasoline extends Particle{
	
	public void init() {
		type = ParticleTypes.GASOLINE;
		color = color.RED.darker().darker().darker();
		solid = false;
		liquid = true;
		gas = false;
		non_insulator = true;
		melting_point = 200;
		freezing_point = 32;
		heat_dispersion_rate = 1;
		density = 0.25;
		update_time = 10;
		logic = true;
		particle_class = this.getClass();
	}
	
	public void logic(World world, int w, int h) {
		vaporize(world, w, h);
	}
	
	public void freeze(World world, int w, int h) {
		
	}
	
	public void vaporize(World world, int w, int h) {
		if(heat >= melting_point) {
			Fire fire = new Fire();
			fire.setHeat(heat+2200);
			world.addParticle(w, h, fire);
		}
	}

}
