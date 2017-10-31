import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Hazards extends Sprite {

	private int x,y,vx;

	private BufferedImage monster1;

	public Hazards(int x, int y){
		super(x,y);
		setLocation(x,y);
		//monster width
		width = 100;
		//monster height
		height = 100;
			
		//importing the monster image
		try {
			monster1 = ImageIO.read(getClass().getResourceAsStream("/monster1.png"));
		} catch(IOException e){
			e.printStackTrace();
		}


	}
	
	//update the position of the monsster
	public void update(){
		centerX-=vx;
	}
//	//updating the x location of monster in level1
//	public void updateDay(){
//		centerX-=4;
//	}
//	
//	//updating the x location of monster in level2 
//	public void updateNight(){
//		centerX-=5;
//	}
	
	//drawing the monster 
	public void draw(Graphics g){
		int x = centerX - 50;
		int y = centerY - 100;
		g.drawImage(monster1, x, y, width, height, null); 
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
	
	

}
