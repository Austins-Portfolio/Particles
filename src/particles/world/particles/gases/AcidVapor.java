package particles.world.particles.gases;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;

public class AcidVapor extends Particle{
	
	public void init() {
		particle_class = this.getClass();
		type = ParticleTypes.ACIDVAPOR;
		color = color.GREEN.brighter();
		solid = false;
		liquid = false;
		gas = true;
		logic = true;
		logic_update_time = 100;
		update_time = 50;
		density = 1;
		particle_class = this.getClass();
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
		if(type!=ParticleTypes.ACID && type!=ParticleTypes.ACIDVAPOR && type!=ParticleTypes.GLASS && type!=ParticleTypes.VOID && type!=ParticleTypes.SPAWNER) {
			return true;
		}
		return false;
	}
	
}
