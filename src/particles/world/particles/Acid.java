package particles.world.particles;

import particles.world.Particle;
import particles.world.ParticleTypes;
import particles.world.World;

public class Acid extends Particle{
	
	public Acid() {
		type = ParticleTypes.ACID;
		color = color.GREEN;
		solid = false;
		liquid = true;
		gas = false;
		logic = true;
		logic_update_time = 100;
	}

	public void logic(World world, int w, int h) {
		if(world.spotOccupiedAndInBounds(w, h+1)) {
			if(canDisolve(world.getParticle(w, h+1).getType())) {
			 world.addParticle(w, h, null);
			 world.addParticle(w, h+1, null);
			}
		}
		if(world.spotOccupiedAndInBounds(w, h-1)) {
			if(canDisolve(world.getParticle(w, h-1).getType())) {
			 world.addParticle(w, h, null);
			 world.addParticle(w, h-1, null);
			}
		}
		if(world.spotOccupiedAndInBounds(w+1, h)) {
			if(canDisolve(world.getParticle(w+1, h).getType())) {
			 world.addParticle(w, h, null);
			 world.addParticle(w+1, h, null);
			}
		}
		if(world.spotOccupiedAndInBounds(w-1, h)) {
			if(canDisolve(world.getParticle(w-1, h).getType())) {
			 world.addParticle(w, h, null);
			 world.addParticle(w-1, h, null);
			}
		}
	}
	
	public boolean canDisolve(byte type) {
		if(type!=ParticleTypes.ACID && type!=ParticleTypes.GLASS) {
			return true;
		}
		return false;
	}
	
}
