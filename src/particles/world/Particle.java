package particles.world;

import java.awt.Color;

public abstract class Particle {

	protected byte type;
	protected Color color;
	protected boolean solid, liquid, gas, logic;
	protected int tempW, tempH;
	
	private long last_time = System.currentTimeMillis();
	protected long update_time = 10;
	
	private long logic_last_time = System.currentTimeMillis();
	protected long logic_update_time = 10;
	
	protected boolean i;	
	public void generateI() {
		i = Math.random() < 0.5;
	}
	
	public boolean shouldUpdate() {
		long current_time = System.currentTimeMillis();
		if(current_time - last_time >= update_time) {
			last_time = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
	public boolean shouldUpdateLogic() {
		long current_time = System.currentTimeMillis();
		if(current_time - logic_last_time >= logic_update_time) {
			logic_last_time = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
	public void update(World world, int w, int h) {
		if(shouldUpdate()) {
			updateWH(w, h);
			if(solid) {
				moveSolid(world, tempW, tempH);
			}
			if(liquid) {
				moveLiquid(world, tempW, tempH);
			}
			if(gas) {
				moveGas(world, tempW, tempH);
			}
			if(shouldUpdateLogic() && logic) {
				logic(world, tempW, tempH);
			}
		}
	}
	
	public void moveSolid(World world, int w, int h) {
		
	}
	
	public void moveLiquid(World world, int w, int h) {
		if(world.spotEmptyAndInBounds(w, h+1)) {
			world.addParticle(w, h, null);
			world.addParticle(w, h+1, this);
			h++;
			generateI();
		}else {
			if(world.getParticle(w, h+1)!=null) {
				if(world.getParticle(w, h+1).getType() != this.type) {
					generateI();
				}
				if(world.spotEmptyAndInBounds(w+1, h+1)) {
					if(i) {
					world.addParticle(w, h, null);
					world.addParticle(w+1, h+1, this);
					w++;
					h++;
					}
				}else if(world.spotEmptyAndInBounds(w+1, h)) {
					if(i) {
					world.addParticle(w, h, null);
					world.addParticle(w+1, h, this);
					w++;
					}
				}else {
						generateI();
				}
				if(world.spotEmptyAndInBounds(w-1, h+1)){
					if(!i) {
					world.addParticle(w, h, null);
					world.addParticle(w-1, h+1, this);
					w--;
					h++;
					}
				}else if(world.spotEmptyAndInBounds(w-1, h)){
					if(!i) {
					world.addParticle(w, h, null);
					world.addParticle(w-1, h, this);
					w--;
					}
				}else {
					generateI();
				}
			}
		}
		
		updateWH(w, h);
	}
	
	public void moveGas(World world, int w, int h) {
		
	}
	
	public void logic(World world, int w, int h) {
		
	}
	
	public void updateWH(int w, int h) {
		tempW = w;
		tempH = h;
	}

	public byte getType() {
		return type;
	}

	public Color getColor() {
		return color;
	}
	
}
