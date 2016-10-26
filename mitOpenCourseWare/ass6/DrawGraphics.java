import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DrawGraphics {
	/*let us comment the version 1, before unifying the classes
	  
	 
    ArrayList<Bouncer> movingSprite = new ArrayList<Bouncer>();
    ArrayList<StraightMover> straightSprite = new ArrayList<StraightMover>();

    // Initializes this class for drawing. 
    public DrawGraphics() {
    
    
    	//notes in here
    	//both Rectangle and Oval classes implements the Sprite Interface
    	//on the other hand the Bouncer class constructor takes in a Sprite as its input
    	//via polymorphism, it seems that anywhere you can use Sprite, I can use Rectangle/Oval as they both implement Sprite but only the interface will be accessible
    	//so in here, both Oval and Rectangle are used in the constructor for Bouncer
    	
        Rectangle box = new Rectangle(15, 20, Color.RED);
        Oval box3 = new Oval(25, 25, Color.GREEN);
        
        movingSprite.add(new Bouncer(100, 170, box));
        movingSprite.add(new Bouncer(100, 170, box3));
        
        movingSprite.get(0).setMovementVector(3, 1);
        movingSprite.get(1).setMovementVector(-1, -2);
        
        
        //now, i implemented another class straight mover, which does not bounce
        //see that the methods were duplicated for bouncer and straight mover
        //wan to create a new interface for this
        
        straightSprite.add(new StraightMover(50,100, box));
        straightSprite.add(new StraightMover(50,100, box3));
        
        straightSprite.get(0).setMovementVector(3, 1);
        straightSprite.get(1).setMovementVector(-1, -2);

    }

    // Draw the contents of the window on surface. 
    public void draw(Graphics surface) {
        movingSprite.get(0).draw(surface);
        movingSprite.get(1).draw(surface);
        straightSprite.get(0).draw(surface);
        straightSprite.get(1).draw(surface);
        //movingSprite.get(2).draw(surface);
    }
    
    */
	
	
	//version 2, unify bouncer and straight mover 
	//have created anew interface mover, and is implemented by bouncer and stmover
	//anywhere we can use bouncer and stmover, we can use mover through polymorphism
	//see that both bouncer and stmover had been instantiated on a single movingsprite array
	
	ArrayList<Mover> movingSprite = new ArrayList<Mover>();
	
	public DrawGraphics() {
		 Rectangle box = new Rectangle(15, 20, Color.RED);
	     Oval box3 = new Oval(25, 25, Color.GREEN);
	     
	     movingSprite.add(new Bouncer(100, 170, box));
	     movingSprite.add(new Bouncer(100, 170, box3));
	     movingSprite.add(new StraightMover(80, 160, box));
	     movingSprite.add(new StraightMover(90, 160, box3));
		
	     movingSprite.get(0).setMovementVector(3, 1);
	     movingSprite.get(1).setMovementVector(-1, -2);
	     movingSprite.get(2).setMovementVector(3, 1);
	     movingSprite.get(3).setMovementVector(-1, -2);
	}
	
	  public void draw(Graphics surface) {
		  
		  movingSprite.get(0).draw(surface);
	      movingSprite.get(1).draw(surface);
	      movingSprite.get(2).draw(surface);
	      movingSprite.get(3).draw(surface);
	  }
}
