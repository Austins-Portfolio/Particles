package particles.graphics;

import java.awt.Canvas;
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
	private Canvas canvas;
	private BufferStrategy bs;
	private BufferStrategy cbs;
	
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
		
		canvas = new Canvas();
		canvas.setSize(width, height);
		canvas.setIgnoreRepaint(true);
		canvas.setBackground(Color.BLACK);
		frame.add(canvas);
		
		frame.createBufferStrategy(2);
		canvas.createBufferStrategy(2);
		bs = frame.getBufferStrategy();
		cbs = canvas.getBufferStrategy();
		built = true;
	}
	
	public Graphics2D getGraphics() {
		Graphics2D g2 = (Graphics2D) cbs.getDrawGraphics();
		return g2;
	}
	
	public void swapBuffer() {
		cbs.show();
		bs.show();
	}
	
	public void dispose() {
		frame.dispose();
		built = false;
	}

	public int getWidth() {
		return canvas.getWidth();
	}

	public int getHeight() {
		return canvas.getHeight();
	}

	public String getName() {
		return name;
	}

	public boolean isBuilt() {
		return built;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return frame;
	}
	
}
