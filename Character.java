import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Character extends Sprite{

	private int health = 3; //player's health 
	private int playerScore = 0;

	private BufferedImage jerry;
	private BufferedImage heart;
	private Boolean death;
	private Boolean hit = false;

	public static int G=7;

	private int vX = 0, vY = 0, aX = 0, aY = 0;
	private int groundHeight;

	public Character(int x, int y){
		super(x,y);
		setLocation(x,y);
		//size of the sprite
		width = 30;
		height = 60;
		death = false;
		aY = G;


		try {
			jerry = ImageIO.read(getClass().getResourceAsStream("/jerry.png"));
		} catch(IOException e){
			e.printStackTrace();
		}

	}


	//method to draw the sprite
	public void draw (Graphics g){

		int x = centerX - width/2;
		int y = centerY - height/2;

//		if(hit==true){
//			System.out.println("hitttt");
//			g.setColor(Color.red);
//			g.fillRect(x, y, width, height);
//		}else {
//			g.setColor(Color.magenta);
//			g.fillRect(x, y, width, height);}

	
		g.drawImage(jerry, x, y, width, height, null); 
	}



	/**
	 * Called from the animation loop to update the position of this
	 * Character.
	 */

	public void update(){
		//update velocity from acceleration
		vY+=aY;
		vX+=aX;
		//update position from velocity 
		centerY+=vY;
		centerX+=vX;

		// Limit checks for sprite motion
		centerX = Math.min(centerX, Game.CANVAS_WIDTH-width/2);
		centerX = Math.max(width/2, centerX);


		if (centerY >= groundHeight) {
			centerY = groundHeight;
			vY = 0;
		}

	}



	public Boolean getHit() {
		return hit;
	}


	public void setHit(Boolean hit) {
		this.hit = hit;
	}


	public int getPlayerScore() {
		return playerScore;
	}


	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}


	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getvX() {
		return vX;
	}
	public void setvX(int vX) {
		this.vX = vX;
	}
	public int getvY() {
		return vY;
	}
	public void setvY(int vY) {
		this.vY = vY;
	}
	public int getaX() {
		return aX;
	}
	public void setaX(int aX) {
		this.aX = aX;
	}
	public int getaY() {
		return aY;
	}
	public void setaY(int aY) {
		this.aY = aY;
	}

	public int getGroundHeight() {
		return groundHeight;
	}

	public void setGroundHeight(int groundHeight) {
		this.groundHeight = groundHeight;
	}


	public Boolean getDeath() {
		return death;
	}


	public void setDeath(Boolean death) {
		this.death = death;
	}









}
