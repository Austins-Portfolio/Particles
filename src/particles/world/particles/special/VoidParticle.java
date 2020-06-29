package particles.world.particles.special;

import java.awt.Color;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;

public class VoidParticle extends Particle{
	
	public VoidParticle() {
		type = ParticleTypes.VOID;
		color = Color.BLACK.brighter().brighter().brighter();
		update_time = 100;
		logic_update_time = 100;
		logic = true;
		particle_class = this.getClass();
	}
	
	public void logic(World world, int w, int h) {
		if(world.spotOccupiedAndInBounds(w, h+1)) {
			if(world.getParticle(w, h+1).getType()!=type) {
				world.addParticle(w, h+1, null);
			}
		}
		if(world.spotOccupiedAndInBounds(w, h-1)) {
			if(world.getParticle(w, h-1).getType()!=type) {
				world.addParticle(w, h-1, null);
			}
		}
		if(world.spotOccupiedAndInBounds(w-1, h)) {
			if(world.getParticle(w-1, h).getType()!=type) {
				world.addParticle(w-1, h, null);
			}
		}
		if(world.spotOccupiedAndInBounds(w-1, h-1)) {
			if(world.getParticle(w-1, h-1).getType()!=type) {
				world.addParticle(w-1, h-1, null);
			}
		}
		if(world.spotOccupiedAndInBounds(w-1, h+1)) {
			if(world.getParticle(w-1, h+1).getType()!=type) {
				world.addParticle(w-1, h+1, null);
			}
		}
		if(world.spotOccupiedAndInBounds(w+1, h)) {
			if(world.getParticle(w+1, h).getType()!=type) {
				world.addParticle(w+1, h, null);
			}
		}
		if(world.spotOccupiedAndInBounds(w+1, h-1)) {
			if(world.getParticle(w+1, h-1).getType()!=type) {
				world.addParticle(w+1, h-1, null);
			}
		}
		if(world.spotOccupiedAndInBounds(w+1, h+1)) {
			if(world.getParticle(w+1, h+1).getType()!=type) {
				world.addParticle(w+1, h+1, null);
			}
		}
	}

}
