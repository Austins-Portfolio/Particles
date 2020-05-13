package particles.world.particles.gases;

import java.awt.Color;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;
import particles.world.particles.liquids.Water;

public class WaterVapor extends Particle{
	
	public WaterVapor() {
		type = ParticleTypes.WATER;
		color = new Color(56, 196, 240);
		solid = false;
		liquid = false;
		gas = true;
		non_insulator = true;
		freezing_point = 212;
		heat_dispersion_rate = 0.5;
		heat_correction_value = 0.0000000001;
		logic = true;
	}
	
	public void logic(World world, int w, int h) {
		freeze(world, w, h);
	}
	
	public void freeze(World world, int w, int h) {
		if(heat <= freezing_point) {
			Water water = new Water();
			water.setHeat(heat);
			world.addParticle(w, h, water);
		}
	}

}
