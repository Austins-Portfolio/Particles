package particles.world.particles.solids;

import java.awt.Color;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;

public class Glass extends Particle{
	
	public void init() {
		type = ParticleTypes.GLASS;
		color = new Color(0.25f,0.25f,0.25f,1);
		non_insulator = true;
		heat_dispersion_rate = 0.25;
	}

}
