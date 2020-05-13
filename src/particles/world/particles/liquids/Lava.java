package particles.world.particles.liquids;

import particles.world.World;
import particles.world.particles.Particle;
import particles.world.particles.ParticleTypes;
import particles.world.particles.solids.Rock;

public class Lava extends Particle{
	
	public Lava() {
		type = ParticleTypes.LAVA;
		color = color.ORANGE;
		solid = false;
		liquid = true;
		gas = false;
		logic = true;
		update_time = 50;
		logic_update_time = 1000;
	}
	
	private boolean hot = true;
	public void logic(World world, int w, int h) {
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

}
