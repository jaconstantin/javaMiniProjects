import java.awt.Graphics;

//here we implement the methods needed by those that will be using the interface
	
//note that only method prototypes are in here
//in a sense, interface allows us to do polymorphism without the need for inherited classes
	//they said that is is useful in cases wherein multiple inheritance might be required


public interface Mover {
	  
	public void setMovementVector(int xIncrement, int yIncrement);
	public void draw(Graphics surface);
	      
}
