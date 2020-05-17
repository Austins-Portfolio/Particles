package particles.world.particles.solids;

import java.awt.Color;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;
import particles.world.particles.liquids.Lava;

public class Rock extends Particle{
	
	public void init() {
		type = ParticleTypes.WALL;
		color = Color.GRAY;
		logic = true;
		melting_point = 1300;
		update_time = 100;
		logic_update_time = 100;
		non_insulator = true;
		heat_dispersion_rate = 0.00001;
		particle_class = this.getClass();
	}
	
	public void logic(World world, int w, int h) {
		melt(world, w, h);
	}
	
	public void melt(World world, int w, int h) {
		if(heat >= melting_point) {
			Lava lava = new Lava();
			lava.setHeat(heat);
			world.addParticle(w, h, lava);
		}
	}

}
