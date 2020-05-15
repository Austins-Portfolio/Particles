package particles.world.particles.gases;

import java.awt.Color;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;

public class Fire extends Particle{
	
	private boolean spawn;
	private long last_spawn = 0;
	private long spawn_time = 100;
	private double spawn_chance = 0.5;
	
	public void init() {
		type = ParticleTypes.FIRE;
		color = new Color(240,89,56);
		solid = false;
		liquid = false;
		gas = true;
		logic = true;
		non_insulator = true;
		lifetime = true;
		update_time = 50;
		logic_update_time = 100;
		life_time = 2000;
		heat = 400;
		heat_dispersion_rate = 1;
		particle_class = this.getClass();
	}
	
	public boolean shouldSpawnSmoke() {
		if(System.currentTimeMillis() - last_spawn >= spawn_time) {
			if(heat>=60) {
				return true;
			}
		}
		return false;
	}
	
	public void randomSpawn() {
		if(heat >= 300) {
			spawn = Math.random() < spawn_chance;
		}else {
			spawn = false;
		}
	}
	
	public void logic(World world, int w, int h) {
		spawnSmoke(world, w, h);
	}
	
	public void spawnSmoke(World world, int w, int h) {
		randomSpawn();
		if(world.spotEmptyAndInBounds(w, h-1)) {
			if(spawn&&shouldSpawnSmoke()) {
				world.addParticle(w, h-1, new Smoke());
				randomSpawn();
				last_spawn = System.currentTimeMillis();
				heat -= 30;
			}
		}
		if(world.spotEmptyAndInBounds(w, h+1)) {
			if(spawn&&shouldSpawnSmoke()) {
				world.addParticle(w, h+1, new Smoke());
				randomSpawn();
				last_spawn = System.currentTimeMillis();
				heat -= 30;
			}
		}
		if(world.spotEmptyAndInBounds(w-1, h)) {
			if(spawn&&shouldSpawnSmoke()) {
				world.addParticle(w-1, h, new Smoke());
				randomSpawn();
				last_spawn = System.currentTimeMillis();
				heat -= 30;
			}
		}
		if(world.spotEmptyAndInBounds(w+1, h)) {
			if(spawn&&shouldSpawnSmoke()) {
				world.addParticle(w+1, h, new Smoke());
				randomSpawn();
				last_spawn = System.currentTimeMillis();
				heat -= 30;
			}
		}
	}
	
	public void lifetime(World world, int w, int h) {
		if(lifetimeUp()) {
			world.addParticle(w, h, null);
		}
	}

}
