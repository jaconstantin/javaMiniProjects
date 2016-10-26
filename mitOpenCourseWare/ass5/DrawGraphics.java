import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DrawGraphics {
	ArrayList<BouncingBox> boxes = new ArrayList<BouncingBox>();
	/** Initializes this class for drawing. */
	
	public DrawGraphics() {
		
		
		boxes.add(new BouncingBox(200, 50, Color.RED));
		boxes.add(new BouncingBox(100, 60, Color.BLUE));
		
		//note here, operator [] can only be used for arrays
		//but then, it is allowed to use a function that returns the element, and directly access its method
		
		boxes.get(0).setMovementVector(1,0);
		boxes.get(1).setMovementVector(0,-2);

	}
	
	/** Draw the contents of the window on surface. Called 20 times per second. */
	public void draw(Graphics surface){
		surface.drawLine(50, 50, 250, 250);
		
		//same goes here, can iterate the loop using the advanced for loop...
		
		boxes.get(0).draw(surface);
		boxes.get(1).draw(surface);
		
		//for (BouncingBox box : boxes){
		//	box.draw(surface);
		//	}
	}
}