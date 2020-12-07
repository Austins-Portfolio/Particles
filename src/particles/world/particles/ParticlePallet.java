package particles.world.particles;

import particles.world.particles.solids.*;
import particles.world.particles.liquids.*;
import particles.world.World;
import particles.world.particles.gases.*;
import particles.world.particles.special.*;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ParticlePallet {

	private Class<?> particle_class_1;
	private Class<?> particle_class_2;
	private String name_1;
	private String name_2;
	public int selection = 1;
	
	public void buildPallet(JFrame frame, World world) {
		frame.setResizable(false);
		frame.setLayout(new GridLayout());
		
		SelectionItem water = new SelectionItem("Water", new Water());
		SelectionItem watervapor = new SelectionItem("Water Vapor", new WaterVapor());
		SelectionItem lava = new SelectionItem("Lava", new Lava());
		SelectionItem wall = new SelectionItem("Wall", new Wall());
		SelectionItem rock = new SelectionItem("Rock", new Rock());
		SelectionItem acid = new SelectionItem("Acid", new Acid());
		SelectionItem acidvapor = new SelectionItem("Acid Vapor", new AcidVapor());
		SelectionItem glass = new SelectionItem("Glass", new Glass());
		SelectionItem fire = new SelectionItem("Fire", new Fire());
		SelectionItem smoke = new SelectionItem("Smoke", new Smoke());
		SelectionItem plasma = new SelectionItem("Plasma", new Plasma());
		SelectionItem spawnerparticle = new SelectionItem("Spawner", new SpawnerParticle());
		SelectionItem voidparticle = new SelectionItem("Void" , new VoidParticle());
		SelectionItem oil = new SelectionItem("Oil", new Oil());
		SelectionItem gasoline = new SelectionItem("Gasoline", new Gasoline());
		
		SelectionItem[] list = {water,watervapor,lava,wall,rock,acid,acidvapor,glass,fire,smoke,plasma,spawnerparticle,voidparticle,oil,gasoline};
		
		JComboBox<SelectionItem> comboBox = new JComboBox<SelectionItem>(list);
		
		comboBox.setMaximumSize(new Dimension(100,50));
		comboBox.setMinimumSize(new Dimension(100,50));
		comboBox.setVisible(true);
		
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				SelectionItem si = (SelectionItem) e.getItem();
				Particle p = si.getParticle();
				particle_class_1 = p.getParticle_class();
				name_1 = si.getName();
			}
		
		});
		
		JComboBox<SelectionItem> comboBox2 = new JComboBox<SelectionItem>(list);
		
		comboBox2.setMaximumSize(new Dimension(100,50));
		comboBox2.setMinimumSize(new Dimension(100,50));
		comboBox2.setVisible(true);
		
		comboBox2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				SelectionItem si = (SelectionItem) e.getItem();
				Particle p = si.getParticle();
				particle_class_2 = p.getParticle_class();
				name_2 = si.getName();
			}
		
		});
		
		frame.add(comboBox);
		frame.add(comboBox2);
		
		comboBox.setSelectedItem(water);
		comboBox2.setSelectedItem(lava);
		
		particle_class_1 = water.getParticle().getParticle_class();
		name_1 = water.getName();
		particle_class_2 = lava.getParticle().getParticle_class();
		name_2 = lava.getName();
		
		JButton clearButton = new JButton("Clear");
		clearButton.setMaximumSize(new Dimension(100,50));
		clearButton.setMinimumSize(new Dimension(100,50));
		clearButton.setVisible(true);
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				world.clear();
			}
		
		});
		
		JButton voidButton = new JButton("Void Border");
		voidButton.setMaximumSize(new Dimension(100,50));
		voidButton.setMinimumSize(new Dimension(100,50));
		voidButton.setVisible(true);
		voidButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				world.voidBorder();
			}
		
		});
		
		frame.add(clearButton);
		frame.add(voidButton);
		
		frame.validate();
	}
	
	public Particle getRightSelection() {
		return clone(particle_class_1);
	}
	
	public Particle getLeftSelection() {
		return clone(particle_class_2);
	}
	
	public String getSelectionAsString() {
		return name_1+":"+name_2;
	}
	
	public Particle clone(Class<?> particle_class) {
		try {
			return (Particle) Class.forName(particle_class.getName()).getConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
