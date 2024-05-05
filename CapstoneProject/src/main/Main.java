package main;

import processing.core.PApplet;

/** This class runs the program
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/5/24
 */
public class Main {
	
	/** Creates a new screen for the game to run
	 * @param args useless parameter
	 */
	public static void main(String args[])
	{
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		drawing.windowResizable(true);
	}
	
}
