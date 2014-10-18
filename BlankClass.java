/*
 * File: BlankClass.java
 * ---------------------
 * This class is a blank one that you can change at will. Remember, if you change
 * the class name, you'll need to change the filename so that it matches.
 * Then you can extend GraphicsProgram, ConsoleProgram, or DialogProgram as you like.
 */

/*
 * File: Breakout.java
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
		Canvas C1 = new Canvas();
		C1.setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
		C1.setBackground(Color.white);
		makeBall();
		makePaddle();
		addMouseListeners();
		placeBricks();
		while(ball.getY()<APPLICATION_HEIGHT - PADDLE_Y_OFFSET){
			ball.move(vx,vy);
			pause(10);
			checkForPaddle();
			checkForSide();
			checkForBrick();
		}
	}	

	/*
	  * This allows the paddle to move along the x-axis as the mouse moves.(non-Javadoc)
	  * @see acm.program.Program#mouseMoved(java.awt.event.MouseEvent)
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
	
	private void checkForBrick(){
		if (getElementAt(ball.getX()+0.5*BALL_RADIUS, ball.getY()) == fifthBricks){
			remove(fifthBricks);
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
	
	private GRect fifthBricks;
	
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
	private void placeBricks(){
		for (int r = 0; r<2;r++){
			for (int i = 0;i<NBRICK_ROWS;i++){
				GRect bricks = new GRect (i*(BRICK_SEP+BRICK_WIDTH),r*(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
		//		boolean filled = true;
				bricks.setColor (Color.RED);
				bricks.setFilled(true);
				bricks.setFillColor(Color.RED);
				add(bricks);
				
				GRect secondBricks = new GRect (i*(BRICK_SEP+BRICK_WIDTH),r*((BRICK_HEIGHT+BRICK_SEP))+2*(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
				secondBricks.setColor (Color.ORANGE);
				secondBricks.setFilled(true);
				secondBricks.setFillColor(Color.ORANGE);
				add(secondBricks);
				
				GRect thirdBricks = new GRect (i*(BRICK_SEP+BRICK_WIDTH),r*((BRICK_HEIGHT+BRICK_SEP))+4*(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
				thirdBricks.setColor (Color.YELLOW);
				thirdBricks.setFilled(true);
				thirdBricks.setFillColor(Color.YELLOW);
				add(thirdBricks);
				
				GRect fourthBricks = new GRect (i*(BRICK_SEP+BRICK_WIDTH),r*((BRICK_HEIGHT+BRICK_SEP))+6*(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
				fourthBricks.setColor (Color.GREEN);
				fourthBricks.setFilled(true);
				fourthBricks.setFillColor(Color.GREEN);
				add(fourthBricks);
				
				GRect fifthBricks = new GRect (i*(BRICK_SEP+BRICK_WIDTH),r*((BRICK_HEIGHT+BRICK_SEP))+8*(BRICK_HEIGHT+BRICK_SEP)+BRICK_Y_OFFSET,BRICK_WIDTH,BRICK_HEIGHT);
				fifthBricks.setColor(Color.CYAN);
				fifthBricks.setFilled(true);
				fifthBricks.setFillColor(Color.CYAN);
				add(fifthBricks);
			}	
		}	
		
	}
	
}

