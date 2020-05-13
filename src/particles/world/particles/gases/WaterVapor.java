package particles.world.particles.gases;

import java.awt.Color;

import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;

public class WaterVapor extends Particle{
	
	public WaterVapor() {
		type = ParticleTypes.WATER;
		color = new Color(56, 196, 240);
		solid = false;
		liquid = false;
		gas = true;
		logic = false;
	}

}
