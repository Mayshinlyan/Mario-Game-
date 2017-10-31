import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JFrame{
	public  static final int TIMER_DELAY = 20; // ms
	public  static final int CANVAS_WIDTH = 1000;
	public  static final int CANVAS_HEIGHT = 800;
	public  static final int CANVAS_BASELINE = 770;
	private MyCanvas canvas;
	private dayLevel dayLevel;
	private nightLevel nightLevel;
	private Timer timer;
	private JLabel time;
	private JLabel score;

	Character Bob;

	private Boolean wonLevel2 = false;
	private Boolean wonLevel1 = false;

	/*
	 * initializing the core components of the game
	 */

	public Game(){

		initGraphics();
		initLevel1();
		initLevel2();
		initTimer();
		initScore();
		initHandlers();
		initCharacter();
		initHeart();

	}

	/*
	 * creating frame and canvas
	 */
	public void initGraphics(){
		setTitle("Mario");
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		canvas = new MyCanvas();
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		add(canvas, BorderLayout.NORTH);
		pack();
	}

	/*
	 * creating score label 
	 */

	public void initScore() {
		score = new JLabel();
		score.setSize(500, 500);
		score.setFont(new Font("Times New Roman", 1, 30));
		score.setForeground(Color.white);
		canvas.add(score);
	}

	/*
	 * adding Timer and the label
	 */
	public void initTimer() {
		timer = new Timer(TIMER_DELAY, new TimerHandler());
		time = new JLabel();
		time.setSize(500, 500);
		time.setFont(new Font("Times New Roman", 1, 30));
		time.setForeground(Color.white);
		canvas.add(time);

	}

	/*
	 * Start the game by making the frame visible and starting the
	 * animation timer.
	 */
	public void start() {
		setVisible(true);
		timer.start();
	}

	/*
	 * initializing level
	 */

	public void initLevel1() {
		dayLevel = new dayLevel();
	}

	public void initLevel2() {
		nightLevel = new nightLevel();
	}


	/*
	 * Creating the Character
	 */

	public void initCharacter() {
		Bob = new Character(0, 0);
		int x = 150;
		int y = 0;

		if(x<= dayLevel.lastX()){
			y = dayLevel.groundHeight()-30;
		}
		else{
			y = nightLevel.groundHeight()-30;

		}

		Bob.setLocation(x, y);

	}

	/*
	 * initializing, updating and drawing hearts
	 */

	List<Health> heart = new ArrayList<Health>();
	public void initHeart() {
		Health h1 = new Health(750, 50);
		Health h2 = new Health(800, 50);
		Health h3 = new Health(850, 50);

		heart.add(h1);
		heart.add(h2);
		heart.add(h3);
	}

	public void updateHeart() {
		for (Health h : heart) {
			h.update();
		}
	}

	public void drawHeart(Graphics g) {
		for(int i=0; i<Bob.getHealth();i++){
			heart.get(i).draw(g);
		}
	}


	/*
	 * method check whether the player hit the wall or not
	 */
	public void groundHit(){

		if(Bob.getX()<=dayLevel.lastX()){
			ListIterator<Ground> iterator = dayLevel.getGround().listIterator();
			while(iterator.hasNext()){
				Ground g = iterator.next();
				if(Bob.getX()+Bob.getWidth()==g.getX() && Bob.lowerY()>g.getY()){
					Bob.setPlayerScore(Bob.getPlayerScore()-10);
				}
			}

		}else {
			ListIterator<Ground> iterator = nightLevel.getGround().listIterator();
			while(iterator.hasNext()){
				Ground g = iterator.next();
				if(Bob.getX()+Bob.getWidth()==g.getX() && Bob.lowerY()>g.getY()){
					Bob.setPlayerScore(Bob.getPlayerScore()-10);
				}
			}

		}

		// restarting the level and deducting heart when fall into the pit 
		if (Bob.lowerY()>=CANVAS_BASELINE){
			Bob.setHealth(Bob.getHealth()-1);
			if(Bob.getX()<=dayLevel.lastX()){
				initLevel1();
			} else{
				initLevel2();
			}
		}
	}

	/*
	 * method checking player is death or not 
	 */
	public void checkDeath(){
		if(Bob.getHealth()==0){
			Bob.setDeath(true);
		} 
	}

	public void initHandlers() {
		canvas.addKeyListener(new KeyHandler());
		canvas.addMouseListener(new KeyHandler());
	}

	/*
	 * creating canvas class 
	 */

	public class MyCanvas extends JPanel{

		@Override
		public void paintComponent(Graphics g) {

			//drawing level1
			if(Bob.getX()<=dayLevel.lastX()){
				dayLevel.draw(g);
//				dayLevel.drawPowerups(g);
//				dayLevel.drawHazard(g);
			}

			//drawing level2
			else{
				nightLevel.draw(g);
				//nightLevel.drawPowerups(g);
				//nightLevel.drawHazard(g);
			}

			//displaying game over when the player loses all the health
			if(Bob.getDeath()==true){
				timer.stop();
				g.setColor(Color.red);
				g.setFont(new Font("Courier", Font.BOLD, 100));
				g.drawString("Game Over", 200, 400);
				g.drawString("Score: " + Bob.getPlayerScore(), 210,500);
			}

			//displaying the level1 won statement 
			if(wonLevel1 == true){
				g.setColor(Color.red);
				g.setFont(new Font("Courier", Font.BOLD, 100));
				g.drawString("Level 1 WON!", 150, 400);
				g.drawString("Level 2 Start!", 100, 500);
			}

			//displaying the level2 won statement 
			if(wonLevel2 == true){
				g.setColor(Color.red);
				g.setFont(new Font("Courier", Font.BOLD, 100));
				g.drawString("Level 2 WON!", 150, 400);
				g.drawString("Score: "+ Bob.getPlayerScore(), 200, 500);
				timer.stop();
			}

			Bob.draw(g);
			drawHeart(g);
			repaint();
		}

		@Override
		public boolean isFocusable() {
			return true;
		}
	}

	/**
	 * Timer callback handler. Updates everything and schedules
	 * a repaint.
	 */

	private int count;

	public class TimerHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			//adjusting the time
			count++;
			System.out.println(count);
			time.setText("Time:" + count/60 + ":" + count%60);

			//updating level1
			if(Bob.getX()<=dayLevel.lastX()){
				dayLevel.update(Bob);
				//dayLevel.updatePowerups(Bob);
				Bob.setGroundHeight(dayLevel.groundHeight()-30);
				//dayLevel.updateHazards(Bob);

				//checking whether the player won level1 or not
				if (Bob.getDeath()==false){
					if(Bob.getX()>dayLevel.lastX()-dayLevel.lastGround().getWidth()){
						wonLevel1 = true;

					}
				}

				//updating level2
			} else if (Bob.getDeath()==false){
				wonLevel1 = false;
				nightLevel.update(Bob);
				//nightLevel.updatePowerups(Bob);
				Bob.setGroundHeight(nightLevel.groundHeight()-30);
			//	nightLevel.updateHazards(Bob);
				//checking whether the player won level2 or not
				if(Bob.getX()>nightLevel.lastX()-nightLevel.lastGround().getWidth()){
					wonLevel2 = true;

					//calculating the final score 
					if(count<1500){
						Bob.setPlayerScore(Bob.getPlayerScore()+100);
					} else if (count>=1500 && count<=2000) {
						Bob.setPlayerScore(Bob.getPlayerScore()+50);	
					}
					if(Bob.getHealth()==3){
						Bob.setPlayerScore(Bob.getPlayerScore()+100);
					} else if (Bob.getHealth()==2){
						Bob.setPlayerScore(Bob.getPlayerScore()+50);
					} 
				}
			}

			Bob.update();
			updateHeart();
			groundHit();
			checkDeath();
			score.setText("     Score: " + Bob.getPlayerScore());
			repaint();


		}

	}

	/**
	 * KeyListener handles key presses in game.
	 */
	public class KeyHandler implements KeyListener, MouseListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		boolean touch = false;
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:{
				//making sure the character jump only two time maximum straight

				if(Bob.lowerY()==dayLevel.groundHeight()||Bob.lowerY()==nightLevel.groundHeight()){
					startJumping();
					touch = true;
				} else {
					if (touch==true){
						startJumping();
						touch = false;
					}

				}
				break;}

			case KeyEvent.VK_RIGHT:{
				if(wonLevel1 == false){
					dayLevel.scrollfaster();
					System.out.println("scrolling fasterrrrrrr");
				} else {
					nightLevel.scrollfaster();
				}
			}
			}

		}
		@Override
		public void keyReleased(KeyEvent e) {
			//the character stops jumping when the up keep is pressed 
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:{
				stopJumping();
				break;}
				
			case KeyEvent.VK_RIGHT:{
				stopScrollFast();
				}
			}
			}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// jumping method for mouse click
			if(Bob.lowerY()==dayLevel.groundHeight()||Bob.lowerY()==nightLevel.groundHeight()){
				startJumping();
				touch = true;
			} else {
				if (touch==true){
					startJumping();
					touch = false;
				}

			}

		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void mouseReleased(MouseEvent e) {
			stopJumping();

		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	//setting the vertical speed of the character to be -70
	public void startJumping() {
		Bob.setvY(-70);
	}

	public void stopJumping() {
	}

	public void stopScrollFast(){
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();


	}



}
