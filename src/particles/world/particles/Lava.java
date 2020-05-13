package particles.world.particles;

import java.util.Random;

import particles.world.Particle;
import particles.world.ParticleTypes;
import particles.world.World;

public class Lava extends Particle{

	private long lasttime = System.currentTimeMillis();
	private long updatetime = 50;
	private Random random = new Random();
	private boolean hot = true;
	
	public Lava() {
		type = ParticleTypes.LAVA;
		color = color.ORANGE;
	}
	
	int i;	
	public void generateI() {
		i = random.nextInt(50);
		if(i == 25) {
			generateI();
		}
	}
	
	@Override
	public void update(World world, int w, int h) {
		long currenttime = System.currentTimeMillis();
		if(currenttime - lasttime >= updatetime) {
			if(hot&&(world.spotEmpty(w, h+1)&&world.inBounds(w, h+1))) {
				world.addParticle(w, h, null);
				world.addParticle(w, h+1, this);
				generateI();
			}else {
				if(hot&&world.getParticle(w, h+1)!=null) {
					if(world.getParticle(w, h+1).type != this.type) {
						generateI();
					}
				}
				if(i < 25) {
					if(hot&&world.spotEmpty(w+1, h)&&world.inBounds(w+1, h)) {
						world.addParticle(w, h, null);
						world.addParticle(w+1, h, this);
					}else {
						generateI();
					}
				}else if(i > 25) {
					if(hot&&world.spotEmpty(w-1, h)&&world.inBounds(w-1, h)){
						world.addParticle(w, h, null);
						world.addParticle(w-1, h, this);
					}else {
						generateI();
					}
				}
			}
			if((!world.spotEmpty(w, h+1)&&world.inBounds(w, h+1))) {
				if(world.getParticle(w, h+1).type==ParticleTypes.WATER) {
				 world.addParticle(w, h, null);
				 world.addParticle(w, h+1, new Rock());
				 hot = false;
				}
			}
			if((!world.spotEmpty(w, h-1)&&world.inBounds(w, h-1))) {
				if(world.getParticle(w, h-1).type==ParticleTypes.WATER) {
				 world.addParticle(w, h, null);
				 world.addParticle(w, h-1, new Rock());
				 hot = false;
				}
			}
			if((!world.spotEmpty(w+1, h)&&world.inBounds(w+1, h))) {
				if(world.getParticle(w+1, h).type==ParticleTypes.WATER) {
				 world.addParticle(w, h, null);
				 world.addParticle(w+1, h, new Rock());
				 hot = false;
				}
			}
			if((!world.spotEmpty(w-1, h)&&world.inBounds(w-1, h))) {
				if(world.getParticle(w-1, h).type==ParticleTypes.WATER) {
				 world.addParticle(w, h, null);
				 world.addParticle(w-1, h, new Rock());
				 hot = false;
				}
			}
			lasttime = System.currentTimeMillis();
		}
	}

}
