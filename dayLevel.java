

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class dayLevel {
		Random rand = new Random();
		
		private List<Ground> ground;

		private Flag flag;

		
		// coordinates for forming the ground
		private int x1=0, y1=600, w1=400, h1=800-y1;
		private int x2=400, y2=500, w2=200, h2=800-y2;
		private int x3=600, y3=800, w3=100, h3=800-y3;
		private int x4=700, y4=700, w4=600, h4=800-y4;
		private int x5=1300, y5=600, w5=500, h5=800-y5;
		private int x6=1400, y6=500, w6=400, h6=800-y6;
		private int x7=1800, y7=800, w7=100, h7=800-y7;
		private int x8=1900, y8=400, w8=500, h8=800-y8;
		private int x9=2400, y9=500, w9=100, h9=800-y9;
		private int x10=2500, y10=500, w10=400, h10=800-y10;
		private int x11=2900, y11=800, w11=100, h11=800-y11;
		private int x12=3000, y12=400, w12=300, h12=800-y12;
		private int x13=3300, y13=300, w13=200, h13=800-y13;
		

		private Ground g1,g2,g3,g4,g5,g6,g7,g8,g9,g10,g11,g12,g13;
		
	
		
		public dayLevel(){
			
			//creating and adding round to the array list
			ground = new ArrayList();
			
			g1 = new Ground(x1,y1,w1,h1);
			g2 = new Ground(x2,y2,w2,h2);
			g3 = new Ground(x3,y3,w3,h3);
			g4 = new Ground(x4,y4,w4,h4);
			g5 = new Ground(x5,y5,w5,h5);
			g6 = new Ground(x6,y6,w6,h6);
			g7 = new Ground(x7,y7,w7,h7);
			g8 = new Ground(x8,y8,w8,h8);
			g9 = new Ground(x9,y9,w9,h9);
			g10 = new Ground(x10,y10,w10,h10);
			g11 = new Ground(x11,y11,w11,h11);
			g12 = new Ground(x12,y12,w12,h12);
			g13 = new Ground(x13,y13,w13,h13);
			
			ground.add(g1);
			ground.add(g2);
			ground.add(g3);
			ground.add(g4);
			ground.add(g5);
			ground.add(g6);
			ground.add(g7);
			ground.add(g8);
			ground.add(g9);
			ground.add(g10);
			ground.add(g11);
			ground.add(g12);
			ground.add(g13);
			
	
			
			
			flag = new Flag(3400, 300);
			
			initPowerups();
			initHazards();
			
			//setting speed of the ground , power ups and hazard 
			for(Ground g: ground){
				g.setVx(4);
				
			}
			for (Powerups p : powerups) {
				p.setVx(4);
			}
			
			for (Hazards h : hazard) {
				h.setVx(4);
			}
}
		
		/* 
		 * Creating and checking power ups hit 
		 */

		//array list containing power ups 
		List<Powerups> powerups = new ArrayList<Powerups>();

		//method to initialize power ups 
		public void initPowerups() {
			Powerups p1 = new Powerups(450, 400,1);
			Powerups p2 = new Powerups(900, 500,1);
			Powerups p3 = new Powerups(950, 475,1);
			Powerups p4 = new Powerups(1000, 500,1);
			Powerups p5 = new Powerups(2200, 300,1);
			Powerups p6 = new Powerups(2250, 275,1);
			Powerups p7 = new Powerups(2300, 250,1);
			Powerups p8 = new Powerups(2700, 400,1);
			Powerups p9 = new Powerups(1500, 400,1);
			Powerups p10 = new Powerups(1600, 300,1);
			Powerups p11 = new Powerups(1700, 400,1);
			Powerups p12 = new Powerups(700, 400,2);
			Powerups p13 = new Powerups(3200, 200,2);
			powerups.add(p1);
			powerups.add(p2);
			powerups.add(p3);
			powerups.add(p4);
			powerups.add(p5);
			powerups.add(p6);
			powerups.add(p7);
			powerups.add(p8);
			powerups.add(p9);
			powerups.add(p10);
			powerups.add(p11);
			powerups.add(p12);
			powerups.add(p13);
		}

		//checking power up hit 
		public void checkForPowerupHit(Character Bob) {
			Rectangle characterRect = Bob.getRect();
			ListIterator<Powerups> iterator = powerups.listIterator();
			while (iterator.hasNext()) {
				Powerups p = iterator.next();
				
				if (p.getRect().intersects(characterRect) && p.getType()==1) {
					Bob.setPlayerScore(Bob.getPlayerScore()+20);// when the player hit the star, it scores 10 points. 
					iterator.remove();
				} else if (p.getRect().intersects(characterRect) && p.getType()==2){
					iterator.remove();
					if(Bob.getHealth()!=3){
					Bob.setHealth(Bob.getHealth()+1);} // when the player hit the firtaid box, the player get extra health. 
				}
			}
		}
		

		/* 
		 * Creating and checking hazard hit 
		 */

		List<Hazards> hazard = new ArrayList<Hazards>();

		public void initHazards() {
			Hazards h1 = new Hazards(1000, 700);
			Hazards h2 = new Hazards(2000, 400);
			Hazards h3 = new Hazards(3200, 400);

			hazard.add(h1);
			hazard.add(h2);
			hazard.add(h3);
		}

		//checking hazard hit 
		public void checkForHazardHit(Character Bob) {
			Rectangle characterRect = Bob.getRect();
			ListIterator<Hazards> iterator = hazard.listIterator();
			while (iterator.hasNext()) {
				Hazards h = iterator.next();
				if (h.getBoundLeft().intersects(Bob.getBoundRight()) || h.getBoundTop().intersects(Bob.getBoundBottom())) {
					Bob.setPlayerScore(Bob.getPlayerScore()-5);; // when the player hit the hazard 10 point is detected from the score 
					Bob.setHit(true);
				} else {Bob.setHit(false); }
				
			}
		}
		
		//method to scroll faster
		public void scrollfaster(){
			for(Ground g: ground){
				g.setVx(5);
			}
			
			for (Powerups p : powerups) {
				p.setVx(5);
			}
			
			for (Hazards h : hazard) {
				h.setVx(5);
			}
		}

		//method to draw day level 
		public void draw(Graphics g){
			//drawing sky
			g.setColor(Color.cyan);
			g.fillRect(0, 0, 1000, 800);// 1000 is frame width and 800 is frame height

			//drawing the sun
			g.setColor(Color.yellow);
			g.fillOval(600, 200, 100, 100);
			
			//drawing building 
			g.setColor(Color.GRAY);
			g.fillRect(0, 300, 100, 500);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(200, 200, 300, 600);
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(500, 400, 150, 400);
			
			g.fillRect(700, 500, 900, 300);
			
			//drawing ground 
			for (Ground gr: ground) {
				gr.drawDay(g);
			} 
			
			//drawing power ups
			for (Powerups p: powerups) {
				p.draw(g);
			}
			
			//drawing hazards 
			for (Hazards h: hazard) {
				h.draw(g);
			}
			
			
			flag.draw(g);
		}
		
		
		// updating the position of ground, power up and hazard 
		public void update(Character Bob){
			for (Ground gr : ground) {
				gr.update();
				
			}
			for (Powerups p : powerups) {
				p.update();
			
			}
			checkForPowerupHit(Bob);
			
			for (Hazards h : hazard) {
				h.update();
			
			}
			checkForHazardHit(Bob);
		}
		
		//return the height of the ground at x=150
		public int groundHeight(){
			int groundHeight = 0;
			int x =150;
			for (Ground gr: ground){
				if(x>gr.getX() && x<gr.getX()+gr.getWidth()){
					groundHeight = gr.getY();
				}
			}
			return groundHeight;
		}
		
		//return the last x value of the ground
		public int lastX(){
			int lastX = ground.get(ground.size()-1).getX()+ground.get(ground.size()-1).getWidth();
			return lastX;
		}
		
		//return the last rectangle of ground list 
		public Ground lastGround(){
			Ground g = ground.get(ground.size()-1);
			return g;
			
		}
	
		public int getY1() {
			return y1;
		}

		public void setY1(int y1) {
			this.y1 = y1;
		}

		public int getH1() {
			return h1;
		}

		public void setH1(int h1) {
			this.h1 = h1;
		}


		public List<Ground> getGround() {
			return ground;
		}


		public void setGround(List<Ground> ground) {
			this.ground = ground;
		}
		
		
}
