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
	
	public Fire() {
		type = ParticleTypes.FIRE;
		color = new Color(240,89,56);
		solid = false;
		liquid = false;
		gas = true;
		logic = true;
		lifetime = true;
		update_time = 50;
		logic_update_time = 1000;
		life_time = 2000;
	}

	public void moveGas(World world, int w, int h) {
		if(world.spotEmptyAndInBounds(w, h-1)) {
			if(i) {
			world.addParticle(w, h, null);
			world.addParticle(w, h-1, this);
			h--;
			}
		}
		if(world.getParticle(w, h-1)!=null&&!i) {
			if(world.spotEmptyAndInBounds(w+1, h-1)) {
				world.addParticle(w, h, null);
				world.addParticle(w+1, h-1, this);
				w++;
				h--;
			}else if(world.spotEmptyAndInBounds(w+1, h)) {
				world.addParticle(w, h, null);
				world.addParticle(w+1, h, this);
				w++;
			}
			if(world.spotEmptyAndInBounds(w-1, h-1)){
				world.addParticle(w, h, null);
				world.addParticle(w-1, h-1, this);
				w--;
				h--;
			}else if(world.spotEmptyAndInBounds(w-1, h)){
				world.addParticle(w, h, null);
				world.addParticle(w-1, h, this);
				w--;
			}
		}
		generateI();
		updateWH(w, h);
	}
	
	public boolean shouldSpawnSmoke() {
		if(System.currentTimeMillis() - last_spawn >= spawn_time) {
			return true;
		}
		return false;
	}
	
	public void randomSpawn() {
		spawn = Math.random() < spawn_chance;
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
			}
		}
		if(world.spotEmptyAndInBounds(w, h+1)) {
			if(spawn&&shouldSpawnSmoke()) {
				world.addParticle(w, h+1, new Smoke());
				randomSpawn();
				last_spawn = System.currentTimeMillis();
			}
		}
		if(world.spotEmptyAndInBounds(w-1, h)) {
			if(spawn&&shouldSpawnSmoke()) {
				world.addParticle(w-1, h, new Smoke());
				randomSpawn();
				last_spawn = System.currentTimeMillis();
			}
		}
		if(world.spotEmptyAndInBounds(w+1, h)) {
			if(spawn&&shouldSpawnSmoke()) {
				world.addParticle(w+1, h, new Smoke());
				randomSpawn();
				last_spawn = System.currentTimeMillis();
			}
		}
	}
	
	public void lifetime(World world, int w, int h) {
		if(lifetimeUp()) {
			world.addParticle(w, h, null);
		}
	}

}
