package main;

import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import screens.FirstScreen;
import screens.Screen;
import screens.ScreenSwitcher;
import screens.SecondScreen;

/** This class draws everything in the program
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/5/24
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher {

	private ArrayList<Integer> keys;
	private ArrayList<Screen> screens;
	
	private Screen activeScreen;
	
	/** The scaling factor of the screen's original to current dimensions
	 */
	public float ratioX, ratioY;

	
	/** Constructs a DrawingSurface with multiple screens
	 */
	public DrawingSurface()
	{
		screens = new ArrayList<Screen>();
		keys = new ArrayList<Integer>();
		
		FirstScreen screen1 = new FirstScreen(this);
		screens.add(screen1);
		
		SecondScreen screen2 = new SecondScreen(this);
		screens.add(screen2);
		
		activeScreen = screens.get(0);
	}
	
	
	/** Sets up the screen dimensions
	 */
	public void settings()
	{
		setSize(800, 600);
	}
	
	/** Sets up each of the different screens
	 */
	public void setup()
	{
		for (Screen s : screens)
			s.setup();
	}
	
	/** Scales the screen properly and draws the current screen 
	 */
	public void draw()
	{
		ratioX = (float)width / activeScreen.DRAWING_WIDTH;
		ratioY = (float)height / activeScreen.DRAWING_HEIGHT;

		push();
		scale(ratioX, ratioY);
		activeScreen.draw();
		pop();
	}
	
	/** Handles all key presses
	 */
	public void keyPressed()
	{
		if (!keys.contains(keyCode))
			keys.add(keyCode);
		if (key == ESC) // prevents the program from closing on escape key
			key = 0;
	}
	
	/** Removes all keys that aren't pressed
	 */
	public void keyReleased()
	{
		while(keys.contains(keyCode))
			keys.remove(Integer.valueOf(keyCode));
	}
	
	/** Checks if a key is being pressed
	 * @param code The key to check
	 * @return true if the key is pressed, false otherwise
	 */
	public boolean isPressed(Integer code)
	{
		return keys.contains(code);
	}
	
	/** Executes a mouse press
	 */
	public void mousePressed()
	{
		activeScreen.mousePressed();
	}
	
	/** Executes a mouse move
	 */
	public void mouseMoved()
	{
		activeScreen.mouseMoved();
	}
	
	/** Executes a mouse drag
	 */
	public void mouseDragged()
	{
		activeScreen.mouseDragged();
	}
	
	/** Executes a mouse release
	 */
	public void mouseReleased()
	{
		activeScreen.mouseReleased();
	}
	
	/** Scales the literal click coordinates to the proper coordinates on the screen
	 */
	public Point assumedCoordinatesToActual(Point assumed)
	{
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}
	
	/** Scales the proper coordinates on the screen to the literal click coordinates
	 */
	public Point actualCoordinatesToAssumed(Point actual)
	{
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

	@Override
	/** Changes the current screen
	 */
	public void switchScreen(int i)
	{
		activeScreen = screens.get(i);
	}

}
