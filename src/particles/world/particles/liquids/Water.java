package particles.world.particles.liquids;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;
import particles.world.particles.gases.WaterVapor;

public class Water extends Particle{
	
	public Water() {
		type = ParticleTypes.WATER;
		color = color.BLUE;
		solid = false;
		liquid = true;
		gas = false;
		non_insulator = true;
		melting_point = 212;
		freezing_point = 32;
		heat_dispersion_rate = 0.5;
		update_time = 10;
		logic = true;
	}
	
	public void logic(World world, int w, int h) {
		vaporize(world, w, h);
	}
	
	public void freeze(World world, int w, int h) {
		
	}
	
	public void vaporize(World world, int w, int h) {
		if(heat >= melting_point) {
			WaterVapor water_vapor = new WaterVapor();
			water_vapor.setHeat(heat);
			world.addParticle(w, h, water_vapor);
		}
	}

}
