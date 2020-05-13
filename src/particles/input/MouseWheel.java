package particles.input;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheel implements MouseWheelListener{

	public Mouse mouse;
	
	public MouseWheel(Mouse mouse) {
		this.mouse = mouse;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		mouse.mouseSizeNotch(e.getWheelRotation());
	}

}
