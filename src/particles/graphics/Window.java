package particles.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Window {

	private int width, height;
	private String name;
	private boolean built = false;
	
	private JFrame frame;
	private BufferStrategy bs;
	
	public Window(int width, int height, String name) {
		this.width = width;
		this.height = height;
		this.name = name;
		construct();
	}
	
	public void construct() {
		frame = new JFrame();
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setTitle(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame.setBackground(Color.BLACK);
		frame.setVisible(true);
		frame.createBufferStrategy(2);
		bs = frame.getBufferStrategy();
		built = true;
	}
	
	public Graphics2D getGraphics() {
		Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
		return g2;
	}
	
	public void swapBuffer() {
		bs.show();
	}
	
	public void dispose() {
		frame.dispose();
		built = false;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getName() {
		return name;
	}

	public boolean isBuilt() {
		return built;
	}

	public JFrame getFrame() {
		return frame;
	}
	
}
