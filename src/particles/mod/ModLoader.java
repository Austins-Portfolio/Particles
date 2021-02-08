package particles.mod;

import java.util.ArrayList;

import particles.launcher.Launcher;
import particles.world.particles.Particle;
import particles.world.particles.SelectionItem;

public class ModLoader {

	public static ArrayList<SelectionItem> items = new ArrayList<SelectionItem>();
	
	public static void InjectParticle(String name, Particle p) {
		SelectionItem i = new SelectionItem(name, p);
		items.add(i);
	}
	
	public static void launchModdedWorld() {
		Launcher.launch();
	}
	
}
