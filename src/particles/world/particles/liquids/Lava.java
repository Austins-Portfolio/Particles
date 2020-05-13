package particles.world.particles.liquids;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;
import particles.world.particles.gases.Fire;
import particles.world.particles.solids.Rock;

public class Lava extends Particle{
	
	private boolean spawn;
	private long last_spawn = 0;
	private long spawn_time = 300;
	private double spawn_chance = 0.04;
	
	public Lava() {
		type = ParticleTypes.LAVA;
		color = color.ORANGE;
		solid = false;
		liquid = true;
		gas = false;
		logic = true;
		update_time = 50;
		logic_update_time = 1000;
		spawn_time += Math.random()*1000;
	}
	
	private boolean hot = true;
	public void logic(World world, int w, int h) {
		makeRock(world, w, h);
		spawnFire(world, w, h);
	}
	
	public void makeRock(World world, int w, int h) {
		if(world.spotOccupiedAndInBounds(w, h+1) && hot) {
			if(world.getParticle(w, h+1).getType()==ParticleTypes.WATER) {
			 world.addParticle(w, h, null);
			 world.addParticle(w, h+1, new Rock());
			 hot = false;
			}
		}
		if(world.spotOccupiedAndInBounds(w, h-1) && hot) {
			if(world.getParticle(w, h-1).getType()==ParticleTypes.WATER) {
			 world.addParticle(w, h, null);
			 world.addParticle(w, h-1, new Rock());
			 hot = false;
			}
		}
		if(world.spotOccupiedAndInBounds(w+1, h) && hot) {
			if(world.getParticle(w+1, h).getType()==ParticleTypes.WATER) {
			 world.addParticle(w, h, null);
			 world.addParticle(w+1, h, new Rock());
			 hot = false;
			}
		}
		if(world.spotOccupiedAndInBounds(w-1, h) && hot) {
			if(world.getParticle(w-1, h).getType()==ParticleTypes.WATER) {
			 world.addParticle(w, h, null);
			 world.addParticle(w-1, h, new Rock());
			 hot = false;
			}
		}
	}
	
	public boolean shouldSpawnFire() {
		if(System.currentTimeMillis() - last_spawn >= spawn_time) {
			return true;
		}
		return false;
	}
	
	public void randomSpawn() {
		spawn = Math.random() < spawn_chance;
	}
	
	public void spawnFire(World world, int w, int h) {
		randomSpawn();
		if(world.spotEmptyAndInBounds(w, h-1)) {
			if(spawn&&shouldSpawnFire()) {
				world.addParticle(w, h-1, new Fire());
				randomSpawn();
				last_spawn = System.currentTimeMillis();
			}
		}
		if(world.spotEmptyAndInBounds(w-1, h)) {
			if(spawn&&shouldSpawnFire()) {
				world.addParticle(w-1, h, new Fire());
				randomSpawn();
				last_spawn = System.currentTimeMillis();
			}
		}
		if(world.spotEmptyAndInBounds(w+1, h)) {
			if(spawn&&shouldSpawnFire()) {
				world.addParticle(w+1, h, new Fire());
				randomSpawn();
				last_spawn = System.currentTimeMillis();
			}
		}
	}

}
