import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * A Sprite occupies a rectangular region on the screen into which it
 * draws itself. It also has an update method that is called from the
 * timer loop to animate the sprite.
 */
abstract public class Sprite {
	
	protected int centerX;
	protected int centerY;

	//mehthod to set location of the sprite 
	public Sprite(int x, int y) {
		setLocation(x, y);
	}

	public void setLocation(int x, int y) {
		centerX = x;
		centerY = y;
	}

	public int getX() {
		return centerX;
	}

	public int getY() {
		return centerY;
	}
	
	public int lowerY(){
		return centerY+height/2;
	}
	
	public int upperY(){
		return centerY-height/2;
	}
	
	public int eastX(){
		return centerX+width/2;
	}
	
	public int westX(){
		return centerX-width/2;
	}

	protected int width;
	protected int height;

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	//return the rectangle properties of the sprite 
	public Rectangle getRect() {
		return new Rectangle(centerX-width/2, centerY-height/2, width, height);
	}
	
	//return the left bound properties 
	public Rectangle getBoundLeft(){
		return new Rectangle(westX(),upperY(),5, height);
	}
	
	//return the right bound properties 
	public Rectangle getBoundRight(){
		return new Rectangle(eastX()-5,upperY(),5, height);
	}
	
	//return the top bound properties 
	public Rectangle getBoundTop(){
		return new Rectangle(westX(),upperY(),width, 5);
	}
	
	//return the bottom bound properties 
	public Rectangle getBoundBottom(){
		return new Rectangle(westX(),lowerY()-5,width, 5);
	}

	/**
	 * Called from canvas paintComponent method to draw this Sprite.
	 * This method must be provided by subclasses.
	 */
	abstract public void draw(Graphics g);

	/**
	 * Called from the animation loop to update any time-dependent
	 * properties of this Sprite.
	 * This is a no-op for the base Sprite class.
	 */
	public void update() {
	}

}