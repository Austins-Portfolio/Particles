package particles.world.particles.special;

import java.awt.Color;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;

public class SpawnerParticle extends Particle{
	
	private boolean needs_particle = true;
	private Class particle_class_to_spawn;
	private Particle particle_to_spawn = null;
	
	public SpawnerParticle() {
		type = ParticleTypes.SPAWNER;
		color = Color.MAGENTA;
		update_time = 10;
		logic_update_time = 10;
		logic = true;
		non_insulator = true;
	}
	
	public void logic(World world, int w, int h) {
		if(needs_particle) {
			findParticle(world, w, h);
		}else {
			spawnParticle(world, w, h);
		}
	}
	
	public void findParticle(World world, int w, int h) {
		if(needs_particle&&world.spotOccupiedAndInBounds(w, h+1)) {
			if(world.getParticle(w, h+1).getType()!=type) {
				particle_to_spawn = world.getParticle(w, h+1);
				needs_particle = false;
			}
		}
		if(needs_particle&&world.spotOccupiedAndInBounds(w, h-1)) {
			if(world.getParticle(w, h-1).getType()!=type) {
				particle_to_spawn = world.getParticle(w, h-1);
				needs_particle = false;
			}
		}
		if(needs_particle&&world.spotOccupiedAndInBounds(w-1, h)) {
			if(world.getParticle(w-1, h).getType()!=type) {
				particle_to_spawn = world.getParticle(w-1, h);
				needs_particle = false;
			}
		}
		if(needs_particle&&world.spotOccupiedAndInBounds(w-1, h+1)) {
			if(world.getParticle(w-1, h+1).getType()!=type) {
				particle_to_spawn = world.getParticle(w-1, h+1);
				needs_particle = false;
			}
		}
		if(needs_particle&&world.spotOccupiedAndInBounds(w-1, h-1)) {
			if(world.getParticle(w-1, h-1).getType()!=type) {
				particle_to_spawn = world.getParticle(w-1, h-1);
				needs_particle = false;
			}
		}
		if(needs_particle&&world.spotOccupiedAndInBounds(w+1, h)) {
			if(world.getParticle(w+1, h).getType()!=type) {
				particle_to_spawn = world.getParticle(w+1, h);
				needs_particle = false;
			}
		}
		if(needs_particle&&world.spotOccupiedAndInBounds(w+1, h+1)) {
			if(world.getParticle(w+1, h+1).getType()!=type) {
				particle_to_spawn = world.getParticle(w+1, h+1);
				needs_particle = false;
			}
		}
		if(needs_particle&&world.spotOccupiedAndInBounds(w+1, h-1)) {
			if(world.getParticle(w+1, h-1).getType()!=type) {
				particle_to_spawn = world.getParticle(w+1, h-1);
				needs_particle = false;
			}
		}
	}
	
	public void spawnParticle(World world, int w, int h) {
		if(world.spotEmptyAndInBounds(w, h+1)) {
			world.addParticle(w, h+1, particle_to_spawn.clone());
		}
		if(world.spotEmptyAndInBounds(w, h-1)) {
			world.addParticle(w, h-1, particle_to_spawn.clone());
		}
		if(world.spotEmptyAndInBounds(w-1, h)) {
			world.addParticle(w-1, h, particle_to_spawn.clone());
		}
		if(world.spotEmptyAndInBounds(w-1, h+1)) {
			world.addParticle(w-1, h+1, particle_to_spawn.clone());
		}
		if(world.spotEmptyAndInBounds(w-1, h-1)) {
			world.addParticle(w-1, h-1, particle_to_spawn.clone());
		}
		if(world.spotEmptyAndInBounds(w+1, h)) {
			world.addParticle(w+1, h, particle_to_spawn.clone());
		}
		if(world.spotEmptyAndInBounds(w+1, h+1)) {
			world.addParticle(w+1, h+1, particle_to_spawn.clone());
		}
		if(world.spotEmptyAndInBounds(w+1, h-1)) {
			world.addParticle(w+1, h-1, particle_to_spawn.clone());
		}
	}

}
