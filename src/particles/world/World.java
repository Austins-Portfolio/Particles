package particles.world;

import java.awt.Color;
import java.awt.Graphics2D;

import particles.utils.UniversalTime;
import particles.world.particles.Particle;

public class World {

	private int width, height, world_scale;
	public Particle[][] particles;
	private int spacing = 10;
	private int update_steps = 1;
	private boolean should_update = true;
	public int particle_count = 0;
	
	public World(int width, int height, int world_scale) {
		this.width = width/world_scale;
		this.height = height/world_scale;
		this.world_scale = world_scale;
		particles = new Particle[width][height];
		for(int w = 0; w < width; w++) {
			for(int h = 0; h < height; h++) {
				particles[w][h] = null;
			}
		}
	}
	
	public void addParticle(int w, int h, Particle particle) {
		if(w < width-spacing && h < height-spacing) {
			if(w >= 0+spacing && h >= 0+spacing) {
				particles[w][h] = particle;
			}
		}
	}
	
	public void addParticleMouse(int w, int h, Particle particle) {
		if(w/world_scale < width-spacing && h/world_scale < height-spacing) {
			if(w/world_scale >= 0+spacing && h/world_scale >= 0+spacing) {
				if(spotEmpty(w/world_scale, h/world_scale)) {
					particles[w/world_scale][h/world_scale] = particle;
				}
			}
		}
	}
	
	public void addParticleMouseNoSafety(int w, int h, Particle particle) {
		if(w/world_scale < width-spacing && h/world_scale < height-spacing) {
			if(w/world_scale >= 0+spacing && h/world_scale >= 0+spacing) {
					particles[w/world_scale][h/world_scale] = particle;
			}
		}
	}
	
	public boolean inBounds(int w, int h) {
		if(w < width-spacing && h < height-spacing) {
			if(w >= 0+spacing && h >= 0+spacing) {
				return true;
			}
		}
		return false;
	}
	
	public boolean spotEmpty(int w, int h) {
		if(particles[w][h] == null) {
				return true;
		}
		return false;
	}
	
	public boolean spotEmptyAndInBounds(int w, int h) {
		if(inBounds(w, h)) {
			if(spotEmpty(w, h)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean spotOccupiedAndInBounds(int w, int h) {
		if(inBounds(w, h)) {
			if(!spotEmpty(w, h)) {
				return true;
			}
		}
		return false;
	}
	
	public void update() {
		if(should_update) {
		int count = 0;
		for(int i = 0; i < update_steps; i++) {
			for(int h = height-1; h > 0; h--) {
				for(int w = 0; w < width; w++) {
					if(spotOccupiedAndInBounds(w,h)) {
						particles[w][h].update(this, w, h);
						count++;
					}
				}
			}
		}
		particle_count=count;
		}
		UniversalTime.tickTime();
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.drawRect(spacing*world_scale, spacing*world_scale, ((width-spacing)*world_scale)-(spacing*world_scale), ((height-spacing)*world_scale)-(spacing*world_scale));
		for(int w = 0; w < width; w++) {
			for(int h = 0; h < height; h++) {
				if(spotOccupiedAndInBounds(w,h)) {
					g2.setColor(particles[w][h].getColor());
					g2.fillRect(w*world_scale, h*world_scale, world_scale+1, world_scale+1);
				}
			}
		}
	}
	
	public Particle getParticle(int w, int h) {
		if(!spotEmpty(w,h)) {
			return particles[w][h];
		}
		return null;
	}

	public int getWorld_scale() {
		return world_scale;
	}
	
	public void flipShould_Update() {
		should_update = !should_update;
		if(should_update) {
			UniversalTime.unpause();
		}else {
			UniversalTime.pause();
		}
	}
	
}
