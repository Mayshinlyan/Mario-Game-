import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Flag extends Sprite {
	BufferedImage flag;

	public Flag(int x, int y){
		super(x,y);
		
		//monster width
		width = 50;
		//monster height
		height = 100;
		//generating random appearance of the hazard 
	//	x = rand.nextInt(1000-width);
		
		//importing the monster image
		try {
			flag = ImageIO.read(getClass().getResourceAsStream("/flag.png"));
		} catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void update(){
		centerX-=4;
	}

	@Override
	public void draw(Graphics g) {
		int x = centerX - 50;
		int y = centerY - 100;
		g.drawImage(flag, x, y, width, height, null); 	
	
}
	
}
