package particles.world;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Particle {

	public byte type;
	public Color color;
	
	public abstract void update(World world, int w, int h);
	
	
}
