import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Health extends Sprite{
	private int x,y;

	private BufferedImage heart;
	private int health;


	public Health(int x, int y){
		super(x,y);
		setLocation(x,y);
		//heart width
		width = 50;
		//heart height
		height = 50;
		
		//importing the heart image
		try {
			heart = ImageIO.read(getClass().getResourceAsStream("/heart.png"));
		} catch(IOException e){
			e.printStackTrace();
		}


	}
	
	public void update(){
		
	}
	

	@Override
	public void draw(Graphics g) {
		int x = centerX - 50;
		int y = centerY - 50;
		g.drawImage(heart, x, y, width, height, null); 
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


	

}


