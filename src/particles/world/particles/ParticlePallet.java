package particles.world.particles;

import particles.world.particles.solids.*;
import particles.world.particles.liquids.*;
import particles.world.particles.gases.*;

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
			case 4:{
				return new Fire();
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
		case 4:{
			return new WaterVapor();
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
			case 4:{
				return "[Fire,WaterVapor]";
			}
		};
		return "";
	}
	
}
