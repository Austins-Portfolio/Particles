package particles.world.particles.liquids;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;
import particles.world.particles.gases.AcidVapor;

public class Acid extends Particle{
	
	public void init() {
		particle_class = this.getClass();
		type = ParticleTypes.ACID;
		color = color.GREEN;
		solid = false;
		liquid = true;
		gas = false;
		logic = true;
		density = 1.25;
		logic_update_time = 100;
		particle_class = this.getClass();
	}

	public void logic(World world, int w, int h) {
		if(world.spotOccupiedAndInBounds(w, h+1)) {
			if(canDisolve(world.getParticle(w, h+1).getType())) {
			 world.addParticle(w, h, null);
			 world.addParticle(w, h+1, null);
			}else if(world.getParticle(w, h+1).getType() == ParticleTypes.WATER) {
				 world.addParticle(w, h, new AcidVapor());
				 world.addParticle(w, h+1, null);
			}
		}
		if(world.spotOccupiedAndInBounds(w, h-1)) {
			if(canDisolve(world.getParticle(w, h-1).getType())) {
			 world.addParticle(w, h, null);
			 world.addParticle(w, h-1, null);
			}else if(world.getParticle(w, h-1).getType() == ParticleTypes.WATER) {
				world.addParticle(w, h, new AcidVapor());
				world.addParticle(w, h-1, null);
			}
		}
		if(world.spotOccupiedAndInBounds(w+1, h)) {
			if(canDisolve(world.getParticle(w+1, h).getType())) {
			 world.addParticle(w, h, null);
			 world.addParticle(w+1, h, null);
			}else if(world.getParticle(w+1, h).getType() == ParticleTypes.WATER) {
				world.addParticle(w, h, new AcidVapor());
				world.addParticle(w+1, h, null);
			}
		}
		if(world.spotOccupiedAndInBounds(w-1, h)) {
			if(canDisolve(world.getParticle(w-1, h).getType())) {
			 world.addParticle(w, h, null);
			 world.addParticle(w-1, h, null);
			}else if(world.getParticle(w-1, h).getType() == ParticleTypes.WATER) {
				world.addParticle(w, h, new AcidVapor());
				world.addParticle(w-1, h, null);
			}
		}
	}
	
	public boolean canDisolve(byte type) {
		if(type!=ParticleTypes.ACID && type!=ParticleTypes.ACIDVAPOR && type!=ParticleTypes.GLASS && type!=ParticleTypes.VOID && type!=ParticleTypes.SPAWNER && type != ParticleTypes.WATER) {
			return true;
		}
		return false;
	}
	
}
