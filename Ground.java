import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ground {

	private int x,y,width, height;
	private int vx;

	
	public Ground(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	//drawing ground for the level1 
	public void drawDay(Graphics g){
		
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
		g.setColor(Color.orange);
		g.fillRect(x, y+30, width, height-30);
	}
	
	//drawing ground for the level2 
	public void drawNight(Graphics g){
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		g.setColor(Color.blue);
		g.fillRect(x, y+30, width, height-30);
	}
	
	//return the rectangle properties of the ground 
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
	
	// update the x position of the ground 
	public void update(){
		x-=vx;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getVx() {
		return vx;
	}


	public void setVx(int vx) {
		this.vx = vx;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}



}
