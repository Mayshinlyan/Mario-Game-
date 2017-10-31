import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Powerups extends Sprite {
	private int x,y,vx;
	private BufferedImage star;
	private BufferedImage firstaid;
	private int type; //integer to track the type of powerups. 

	public Powerups(int x, int y, int type){
		super(x,y);
		setLocation(x,y);
		width = 32;
		height = 32;
		this.type = type;
		switch(type){

		case 1: {

			//importing the image of the star 
			try {
				star = ImageIO.read(getClass().getResourceAsStream("/star.png"));
			} catch(IOException e){
				e.printStackTrace();
			} break;
		}

		case 2: {
			System.out.println("starrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
			//importing the image of the health  
			try {
				firstaid = ImageIO.read(getClass().getResourceAsStream("/firstaid.png"));
			} catch(IOException e){
				e.printStackTrace();
			} break;
		}

		}



	}

	//update the position of the powerUp
	public void update(){
		centerX-=vx;
	}


	//drawing powerup 
	public void draw (Graphics g){
		int x = centerX - 16;
		int y = centerY - 16;
		g.drawImage(star, x, y,width, height, null);  // 1/3 away from the right frame
		g.drawImage(firstaid, x, y,width, height, null);
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}






}
