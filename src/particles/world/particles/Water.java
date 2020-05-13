package particles.world.particles;

import java.util.Random;

import particles.world.Particle;
import particles.world.ParticleTypes;
import particles.world.World;

public class Water extends Particle{

	private long lasttime = System.currentTimeMillis();
	private long updatetime = 10;
	private Random random = new Random();
	
	public Water() {
		type = ParticleTypes.WATER;
		color = color.BLUE;
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
			if(world.spotEmpty(w, h+1)&&world.inBounds(w, h+1)) {
				world.addParticle(w, h, null);
				world.addParticle(w, h+1, this);
				generateI();
			}else {
				if(world.getParticle(w, h+1)!=null) {
					if(world.getParticle(w, h+1).type != this.type) {
						generateI();
					}
				}
				if(i < 25) {
					if(world.spotEmpty(w+1, h)&&world.inBounds(w+1, h)) {
						world.addParticle(w, h, null);
						world.addParticle(w+1, h, this);
					}else {
						generateI();
					}
				}else if(i > 25) {
					if(world.spotEmpty(w-1, h)&&world.inBounds(w-1, h)){
						world.addParticle(w, h, null);
						world.addParticle(w-1, h, this);
					}else {
						generateI();
					}
				}
			}
			lasttime = System.currentTimeMillis();
		}
	}

}
