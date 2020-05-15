package particles.world.particles;

import java.awt.Color;

import particles.utils.UniversalTime;
import particles.world.World;

public abstract class Particle{

	protected byte type;
	protected Class<?> particle_class;
	protected Color color;
	protected boolean solid = false, liquid = false, gas = false, non_insulator = false, logic = false, lifetime = false;
	
	protected double heat = 68;
	protected double freezing_point;
	protected double melting_point;
	protected double heat_dispersion_rate = 0.02;
	protected double heat_correction_value = 0.00001;
	
	private long last_time = System.currentTimeMillis();
	protected long update_time = 10;
	
	private long logic_last_time = System.currentTimeMillis();
	protected long logic_update_time = 10;
	
	protected long birth_time = UniversalTime.getTime();
	protected long life_time = 3000;
	
	protected int tempW, tempH;
	
	protected boolean i;	
	
	public Particle() {
		init();
	}
	
	public void init() {
		
	}
	
	public void generateI() {
		i = Math.random() < 0.5;
	}
	
	public boolean shouldUpdate() {
		if(System.currentTimeMillis() - last_time >= update_time) {
			last_time = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
	public boolean shouldUpdateLogic() {
		if(System.currentTimeMillis() - logic_last_time >= logic_update_time) {
			logic_last_time = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
	public boolean lifetimeUp() {
		if(UniversalTime.getTime() - birth_time >= life_time) {
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
			if(non_insulator) {
				disperseHeat(world, tempW, tempH);
			}
			if(shouldUpdateLogic() && logic) {
				logic(world, tempW, tempH);
			}
			if(lifetime) {
				lifetime(world, tempW, tempH);
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
			if(world.spotOccupiedAndInBounds(w, h+1)) {
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
		}if(world.spotOccupiedAndInBounds(w, h+1)) {
			if(world.getParticle(w, h+1).isGas()) {
				world.addParticle(w, h, world.getParticle(w, h+1));
				world.addParticle(w, h+1, this);
				h++;
				generateI();
			}
		}
		
		updateWH(w, h);
	}
	
	public void moveGas(World world, int w, int h) {
		if(world.spotEmptyAndInBounds(w, h-1)&&i) {
			world.addParticle(w, h, null);
			world.addParticle(w, h-1, this);
			h--;
			generateI();
		}else {
				if(world.spotEmptyAndInBounds(w+1, h-1)) {
					if(!i) {
					world.addParticle(w, h, null);
					world.addParticle(w+1, h-1, this);
					w++;
					h--;
					generateI();
					}
				}else if(world.spotEmptyAndInBounds(w+1, h)) {
					if(!i) {
					world.addParticle(w, h, null);
					world.addParticle(w+1, h, this);
					w++;
					generateI();
					}
				}else {
						generateI();
				}
				if(world.spotEmptyAndInBounds(w-1, h-1)){
					if(!i) {
					world.addParticle(w, h, null);
					world.addParticle(w-1, h-1, this);
					w--;
					h--;
					generateI();
					}
				}else if(world.spotEmptyAndInBounds(w-1, h)){
					if(!i) {
					world.addParticle(w, h, null);
					world.addParticle(w-1, h, this);
					w--;
					generateI();
					}
				}else {
					generateI();
				}
			}
		
		updateWH(w, h);
	}
	
	public void disperseHeat(World world, int w, int h) {
		double prefered_spend_amount = (heat * heat_dispersion_rate)/4;
		double total_heat_spent = 0;
		if(world.spotOccupiedAndInBounds(w, h+1)) {
			Particle p = world.getParticle(w, h+1);
			if(p.getHeat()<heat) {
				double heat_difference = heat - p.getHeat();
				if(heat_difference<prefered_spend_amount) {
					p.setHeat(p.getHeat()+heat_difference);
					total_heat_spent+=heat_difference;
				}else {
					p.setHeat(p.getHeat()+prefered_spend_amount);
					total_heat_spent+=prefered_spend_amount;
				}
			}
		}
		if(world.spotOccupiedAndInBounds(w, h-1)) {
			Particle p = world.getParticle(w, h-1);
			if(p.getHeat()<heat) {
				double heat_difference = heat - p.getHeat();
				if(heat_difference<prefered_spend_amount) {
					p.setHeat(p.getHeat()+heat_difference);
					total_heat_spent+=heat_difference;
				}else {
					p.setHeat(p.getHeat()+prefered_spend_amount);
					total_heat_spent+=prefered_spend_amount;
				}
			}
		}
		if(world.spotOccupiedAndInBounds(w+1, h)) {
			Particle p = world.getParticle(w+1, h);
			if(p.getHeat()<heat) {
				double heat_difference = heat - p.getHeat();
				if(heat_difference<prefered_spend_amount) {
					p.setHeat(p.getHeat()+heat_difference);
					total_heat_spent+=heat_difference;
				}else {
					p.setHeat(p.getHeat()+prefered_spend_amount);
					total_heat_spent+=prefered_spend_amount;
				}
			}
		}
		if(world.spotOccupiedAndInBounds(w-1, h)) {
			Particle p = world.getParticle(w-1, h);
			if(p.getHeat()<heat) {
				double heat_difference = heat - p.getHeat();
				if(heat_difference<prefered_spend_amount) {
					p.setHeat(p.getHeat()+heat_difference);
					total_heat_spent+=heat_difference;
				}else {
					p.setHeat(p.getHeat()+prefered_spend_amount);
					total_heat_spent+=prefered_spend_amount;
				}
			}
		}
		heat -= total_heat_spent;
		heatCorrection(world, w, h);
	}
	
	public void heatCorrection(World world, int w, int h) {
		if(heat > 68) {
			heat -= heat_correction_value;
		}else if(heat < 68) {
			heat += heat_correction_value;
		}
	}
	
	public void logic(World world, int w, int h) {
		
	}
	
	public void lifetime(World world, int w, int h) {
		
	}
	
	public void updateWH(int w, int h) {
		tempW = w;
		tempH = h;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public boolean isLiquid() {
		return liquid;
	}

	public void setLiquid(boolean liquid) {
		this.liquid = liquid;
	}

	public boolean isGas() {
		return gas;
	}

	public void setGas(boolean gas) {
		this.gas = gas;
	}

	public boolean isNon_insulator() {
		return non_insulator;
	}

	public void setNon_insulator(boolean non_insulator) {
		this.non_insulator = non_insulator;
	}

	public boolean isLogic() {
		return logic;
	}

	public void setLogic(boolean logic) {
		this.logic = logic;
	}

	public boolean isLifetime() {
		return lifetime;
	}

	public void setLifetime(boolean lifetime) {
		this.lifetime = lifetime;
	}

	public double getHeat() {
		return heat;
	}

	public void setHeat(double heat) {
		this.heat = heat;
	}

	public double getFreezing_point() {
		return freezing_point;
	}

	public void setFreezing_point(double freezing_point) {
		this.freezing_point = freezing_point;
	}

	public double getMelting_point() {
		return melting_point;
	}

	public void setMelting_point(double melting_point) {
		this.melting_point = melting_point;
	}

	public double getHeat_dispersion_rate() {
		return heat_dispersion_rate;
	}

	public void setHeat_dispersion_rate(double heat_dispersion_rate) {
		this.heat_dispersion_rate = heat_dispersion_rate;
	}

	public long getLast_time() {
		return last_time;
	}

	public void setLast_time(long last_time) {
		this.last_time = last_time;
	}

	public long getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(long update_time) {
		this.update_time = update_time;
	}

	public long getLogic_last_time() {
		return logic_last_time;
	}

	public void setLogic_last_time(long logic_last_time) {
		this.logic_last_time = logic_last_time;
	}

	public long getLogic_update_time() {
		return logic_update_time;
	}

	public void setLogic_update_time(long logic_update_time) {
		this.logic_update_time = logic_update_time;
	}

	public long getBirth_time() {
		return birth_time;
	}

	public void setBirth_time(long birth_time) {
		this.birth_time = birth_time;
	}

	public long getLife_time() {
		return life_time;
	}

	public void setLife_time(long life_time) {
		this.life_time = life_time;
	}

	public int getTempW() {
		return tempW;
	}

	public void setTempW(int tempW) {
		this.tempW = tempW;
	}

	public int getTempH() {
		return tempH;
	}

	public void setTempH(int tempH) {
		this.tempH = tempH;
	}

	public boolean isI() {
		return i;
	}

	public void setI(boolean i) {
		this.i = i;
	}
	
	public Particle clone() {
		try {
			return (Particle) Class.forName(particle_class.getName()).getConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
