package particles.world.particles.gases;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;

public class Smoke extends Particle{
	
	public Smoke() {
		type = ParticleTypes.SMOKE;
		color = color.DARK_GRAY;
		solid = false;
		liquid = false;
		gas = true;
		logic = false;
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
	
	public void lifetime(World world, int w, int h) {
		if(lifetimeUp()) {
			world.addParticle(w, h, null);
		}
	}
	
}
