package particles.world.particles;

public class SelectionItem {

	private String name;
	private Particle p;
	
	public SelectionItem(String name, Particle p) {
		this.name = name;
		this.p = p;
	}
	
	public String getName() {
		return name;
	}
	
	public Particle getParticle() {
		return p;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
