
/*
 * File: BreakoutExtensions.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import java.awt.Color;

import acm.graphics.*;


public class BlankClass extends GCompound {
	private static final int WIDTH = 400;
	
	private static final int BRICK_HEIGHT = 8;
	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
	
	/* Private instance variables*/
	private GOval shape;
	private GLabel label;
	private double powerUpHeight  = 0.75* BRICK_HEIGHT;
	private double powerUpWidth = 0.5* BRICK_WIDTH;
	
	public BlankClass(){
		GOval shape = new GOval (powerUpWidth,powerUpHeight);
		shape.setColor(Color.RED);
		GLabel label = new GLabel ("L",powerUpWidth,powerUpHeight) ;
		add(shape);
		add(label);
	 }


}
