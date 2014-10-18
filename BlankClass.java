
/*
 * File: BreakoutExtensions.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class BlankClass extends GraphicsProgram {

/** Width and height of application window in pixels.  On some platforms 
  * these may NOT actually be the dimensions of the graphics canvas. */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board.  On some platforms these may NOT actually
  * be the dimensions of the graphics canvas. */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setUpGame();
		addMouseListeners();
		playTheGame();
	}	

	/*
	 * setUpGame allows the BREAKOUT game window to be setup so that
	 * play can take place.
	 */
	
	private void setUpGame(){
		makeBall();
		makePaddle();
		placeBricks();
	}
	
	/*
	 * playTheGame is the method that is executed so that the game runs.
	 */
	private void playTheGame(){
		while(ball.getY()<APPLICATION_HEIGHT - PADDLE_Y_OFFSET){
			ball.move(vx,vy);
			pause(10);
			makeChecks();
		}
	}
	
	/*
	 * makeChecks allows the ball to consider collisions with bricks,the sides
	 * of the game window and the paddle.
	 */
	private void makeChecks(){
		checkForPaddle();
		checkForBrick();
		checkForSide();
	}
	
	/*
	 * This method allows the ball to check whether or not it has collided with
	 * the paddle so that it may ricochet off the paddle, allowing the game to 
	 * continue.
	 */
	
	private void checkForPaddle(){
		double xPositionOfBall = ball.getX() + 0.5*BALL_RADIUS;
		double yPositionOfBall = ball.getY() + BALL_RADIUS;
		if (getElementAt(xPositionOfBall,yPositionOfBall)==paddle){
			vy = - vy;
			vx = rgen.nextDouble(1.0,3);
			if (rgen.nextBoolean(0.5)){
				vx = -vx;
			}
		}
	}
	
	/*
	 * This method allows the ball to check whether or not it has collided with
	 * a brick and then result in the removal of this brick if a collision 
	 * occurred.		
	 */
	
	private void checkForBrick(){
		for (int i = 0; i<NBRICK_ROWS ; i++){
			if (getElementAt(ball.getX()+0.5*BALL_RADIUS, ball.getY()) == arr[i]) {
				remove(arr[i]);
				vy = -vy;
			}else if(getElementAt(ball.getX()+0.5*BALL_RADIUS, ball.getY()) == arr1[i]){
				remove(arr1[i]);
				vy = -vy;
			}else if (getElementAt(ball.getX()+0.5*BALL_RADIUS, ball.getY()) == arrG[i]){
				remove(arrG[i]);
				vy = -vy;
			}else if (getElementAt(ball.getX()+0.5*BALL_RADIUS, ball.getY()) == arrG1[i]){
				remove(arrG1[i]);
				vy = -vy;
			}else if (getElementAt(ball.getX()+0.5*BALL_RADIUS, ball.getY()) == arrY[i]){
				remove(arrY[i]);
				vy = -vy;
			}else if(getElementAt(ball.getX()+0.5*BALL_RADIUS, ball.getY()) == arrY1[i]){
				remove(arrY1[i]);
				vy = -vy;
			}else if (getElementAt(ball.getX()+0.5*BALL_RADIUS, ball.getY()) == arrO[i]){
				remove(arrO[i]);
				vy = -vy;
			}else if (getElementAt(ball.getX()+0.5*BALL_RADIUS, ball.getY()) == arrO1[i]){
				remove(arrO1[i]);
				vy = -vy;
			}else if (getElementAt(ball.getX()+0.5*BALL_RADIUS, ball.getY()) == arrR[i]){
				remove(arrR[i]);
				vy = -vy;
			} else if (getElementAt(ball.getX()+0.5*BALL_RADIUS, ball.getY()) == arrR1[i]){
				remove(arrR1[i]);
				vy = -vy;
			}
			
			
		}
	}
	
	
	/*
	 * This is needed to keep the ball within the dimensions of the screen.
	 * More specifically, it allows the ball to ricochet off the walls of the
	 * application.
	 */
	
	
	private void checkForSide(){
		if (ball.getX()+ 2*BALL_RADIUS > APPLICATION_WIDTH ){
			vx = -vx;
		}
		if (ball.getX() < 0){
			vx = -vx;
		}
		if(ball.getY() <= 0){
			vy = -vy;
		}
	}
	
	
	public void mouseMoved(MouseEvent e) {
		 
		 	 double adjustedMovement = e.getX() - 0.5*PADDLE_WIDTH;
		 	 
		 	 if (e.getX() + 0.5* PADDLE_WIDTH <= APPLICATION_WIDTH && e.getX()> 0.5*PADDLE_WIDTH){
		 		 paddle.move((adjustedMovement-lastX), 0);
		 		 lastX = adjustedMovement;	
		 	 }	 
	 }

	private void makeBall(){
		ball = new GOval(getWidth()/2, getHeight()/2,2*BALL_RADIUS , 2*BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
		
	}
	
	private double vx = 0;
	
	private double vy = 3;
	
	private double lastX;
	
	private GOval ball;		// This is the ball that is bounding around the screen.
	
	private GRect paddle;	// This is the paddle that is moving at the bottom of the screen.
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	
	private GRect [] arr, arr1;
	private GRect [] arrG, arrG1;
	private GRect [] arrY, arrY1;
	private GRect [] arrO, arrO1;
	private GRect [] arrR, arrR1;
	
	/*
	 * The paddle used to hit the ball towards the bricks is created here.
	 */
	private void makePaddle(){
		paddle = new GRect (0*(WIDTH-PADDLE_WIDTH)*0.5, HEIGHT - PADDLE_Y_OFFSET,PADDLE_WIDTH,PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setFillColor(Color.BLACK);
		add(paddle);
	}
	
	/*
	 * The bricks placed at the top of the program are created here.
	 */
	
	private void placeRedBricks(){
		arrR = new GRect[NBRICK_ROWS];
		for (int k =0; k<NBRICK_ROWS ; k++){
			arrR[k] = new GRect(k*(BRICK_SEP+BRICK_WIDTH),-1*((BRICK_HEIGHT+BRICK_SEP))+(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
			arrR[k].setColor(Color.RED);
			arrR[k].setFilled(true);
			arrR[k].setFillColor(Color.RED);
			add(arrR[k]);
		}
		arrR1 = new GRect[NBRICK_ROWS];
		for (int k =0; k<NBRICK_ROWS ; k++){
			arrR1[k] = new GRect(k*(BRICK_SEP+BRICK_WIDTH),0*((BRICK_HEIGHT+BRICK_SEP))+(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
			arrR1[k].setColor(Color.RED);
			arrR1[k].setFilled(true);
			arrR1[k].setFillColor(Color.RED);
			add(arrR1[k]);
		}
	}
	
	private void placeOrangeBricks(){
		arrO = new GRect[NBRICK_ROWS];
		for (int k =0; k<NBRICK_ROWS ; k++){
			arrO[k] = new GRect(k*(BRICK_SEP+BRICK_WIDTH),((BRICK_HEIGHT+BRICK_SEP))+2*(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
			arrO[k].setColor(Color.ORANGE);
			arrO[k].setFilled(true);
			arrO[k].setFillColor(Color.ORANGE);
			add(arrO[k]);
		}
		arrO1 = new GRect[NBRICK_ROWS];
		for (int k =0; k<NBRICK_ROWS ; k++){
			arrO1[k] = new GRect(k*(BRICK_SEP+BRICK_WIDTH),0*((BRICK_HEIGHT+BRICK_SEP))+2*(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
			arrO1[k].setColor(Color.ORANGE);
			arrO1[k].setFilled(true);
			arrO1[k].setFillColor(Color.ORANGE);
			add(arrO1[k]);
		}
	}
	
	private void placeYellowBricks(){
		arrY = new GRect[NBRICK_ROWS];
		for (int k =0; k<NBRICK_ROWS ; k++){
			arrY[k] = new GRect(k*(BRICK_SEP+BRICK_WIDTH),((BRICK_HEIGHT+BRICK_SEP))+4*(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
			arrY[k].setColor(Color.YELLOW);
			arrY[k].setFilled(true);
			arrY[k].setFillColor(Color.YELLOW);
			add(arrY[k]);
		}
		arrY1 = new GRect[NBRICK_ROWS];
		for (int k =0; k<NBRICK_ROWS ; k++){
			arrY1[k] = new GRect(k*(BRICK_SEP+BRICK_WIDTH),0*((BRICK_HEIGHT+BRICK_SEP))+4*(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
			arrY1[k].setColor(Color.YELLOW);
			arrY1[k].setFilled(true);
			arrY1[k].setFillColor(Color.YELLOW);
			add(arrY1[k]);
		}
		
	}
	
	
	private void placeGreenBricks(){
		arrG = new GRect[NBRICK_ROWS];
		for (int k =0; k<NBRICK_ROWS ; k++){
			arrG[k] = new GRect(k*(BRICK_SEP+BRICK_WIDTH),((BRICK_HEIGHT+BRICK_SEP))+6*(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
			arrG[k].setColor(Color.GREEN);
			arrG[k].setFilled(true);
			arrG[k].setFillColor(Color.GREEN);
			add(arrG[k]);
		}
		arrG1 = new GRect[NBRICK_ROWS];
		for (int k =0; k<NBRICK_ROWS ; k++){
			arrG1[k] = new GRect(k*(BRICK_SEP+BRICK_WIDTH),0*((BRICK_HEIGHT+BRICK_SEP))+6*(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
			arrG1[k].setColor(Color.GREEN);
			arrG1[k].setFilled(true);
			arrG1[k].setFillColor(Color.GREEN);
			add(arrG1[k]);
		}
		
		
	}

	
	private void placeCyanBricks(){
		arr = new GRect[NBRICK_ROWS];
		arr1 = new GRect[NBRICK_ROWS];
			for (int k =0; k<NBRICK_ROWS ; k++){
				arr[k] = new GRect(k*(BRICK_SEP+BRICK_WIDTH),((BRICK_HEIGHT+BRICK_SEP))+8*(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
				arr[k].setColor(Color.CYAN);
				arr[k].setFilled(true);
				arr[k].setFillColor(Color.CYAN);
				add(arr[k]);
			}
			for (int k =0; k<NBRICK_ROWS ; k++){
				arr1[k] = new GRect(k*(BRICK_SEP+BRICK_WIDTH),0*((BRICK_HEIGHT+BRICK_SEP))+8*(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
				arr1[k].setColor(Color.CYAN);
				arr1[k].setFilled(true);
				arr1[k].setFillColor(Color.CYAN);
				add(arr1[k]);
			}	
	}
	
	
	private void placeBricks(){
		placeRedBricks();
		placeOrangeBricks();
		placeYellowBricks();
		placeGreenBricks();
		placeCyanBricks();
	}
}