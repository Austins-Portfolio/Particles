package particles.world;

import particles.world.particles.*;

public class ParticlePallet {

	public int selection = 1;
	
	public void setSelection(int new_selection) {
		selection = new_selection;
	}
	
	public Particle getLeftSelection() {
		switch(selection) {
			case 1:{
				return new Water();
			}
			case 2:{
				return new Wall();
			}
			case 3:{
				return new Acid();
			}
		};
		
		return null;
	}
	
	public Particle getRightSelection() {
		switch(selection) {
		case 1:{
			return new Lava();
		}
		case 2:{
			return new Rock();
		}
		case 3:{
			return new Glass();
		}
	};
		
		return null;
	}
	
	public String getSelectionAsString() {
		switch(selection) {
			case 1:{
				return "[Water,Lava]";
			}
			case 2:{
				return "[Wall,Rock]";
			}
			case 3:{
				return "[Acid,Glass]";
			}
		};
		return "";
	}
	
}
