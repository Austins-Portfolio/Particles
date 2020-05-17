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
	
	public void init() {
		type = ParticleTypes.LAVA;
		color = color.ORANGE;
		solid = false;
		liquid = true;
		gas = false;
		non_insulator = true;
		heat = 2400;
		heat_dispersion_rate = 0.1f;
		heat_correction_value = 0;
		freezing_point = 1300;
		logic = true;
		update_time = 50;
		logic_update_time = 1000;
		spawn_time += Math.random()*1000;
		density = 2;
		particle_class = this.getClass();
	}
	
	private boolean hot = true;
	public void logic(World world, int w, int h) {
		spawnFire(world, w, h);
		freeze(world, w, h);
	}
	
	public boolean shouldSpawnFire() {
		if(System.currentTimeMillis() - last_spawn >= spawn_time) {
			if(heat>=2100) {
				return true;
			}
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
				heat-=400;
			}
		}
		if(world.spotEmptyAndInBounds(w-1, h)) {
			if(spawn&&shouldSpawnFire()) {
				world.addParticle(w-1, h, new Fire());
				randomSpawn();
				last_spawn = System.currentTimeMillis();
				heat-=400;
			}
		}
		if(world.spotEmptyAndInBounds(w+1, h)) {
			if(spawn&&shouldSpawnFire()) {
				world.addParticle(w+1, h, new Fire());
				randomSpawn();
				last_spawn = System.currentTimeMillis();
				heat-=400;
			}
		}
	}

	public void freeze(World world, int w, int h) {
		if(heat <= freezing_point) {
			Rock rock = new Rock();
			rock.setHeat(heat);
			world.addParticle(w, h, rock);
		}
	}
	
}
