package main;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import grid.Level;
import screens.*;

/** This class draws everything in the program
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/9/24
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher {
	
	private static final int DRAWING_WIDTH = 800, DRAWING_HEIGHT = 600;
	
	private static final double characterFractionOfWindow = 0;  // Bigger = panning happens closer to the edge of window. 1 = right at edge, 0 = panning happens always
	private static final double panningLag = 10;  // Bigger = follow char more slowly. 1 = immediately pan
	
	private Rectangle2D.Double visibleSpace;  // Area of the level that we can see
	private Rectangle2D.Double characterSpace;  // Area of the window that the character can move freely in

	private ArrayList<Integer> keys;
	private ArrayList<Screen> screens;
	
	private Screen activeScreen;
	private Level level;
	
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
		SecondScreen screen2 = new SecondScreen(this);
		screens.add(screen1);
		screens.add(screen2);
		
		activeScreen = screens.get(0);
	}
	
	
	/** Sets up the screen dimensions
	 */
	public void settings()
	{
		setSize(DRAWING_WIDTH, DRAWING_HEIGHT);
	}
	
	/** Sets up different aspects of the game
	 */
	public void setup()
	{
		for (Screen s : screens)
			s.setup();
		PImage[] assets = new PImage[] {super.loadImage("img/house.jpg"),
				super.loadImage("img/tree.gif"),
				super.loadImage("img/sun.jpg"),
				super.loadImage("img/cloud.png")};
//		mario = new Sprite(img,0,940,50,60);
		
		level = new Level(assets);
		visibleSpace = new Rectangle2D.Double(0, level.getHeight() - DRAWING_HEIGHT, DRAWING_WIDTH, DRAWING_HEIGHT);
		characterSpace = new Rectangle2D.Double(visibleSpace.getX() + visibleSpace.getWidth() * (1 - characterFractionOfWindow) * 0.5,
												visibleSpace.getY() + visibleSpace.getHeight() * (1 - characterFractionOfWindow) * 0.5,
												visibleSpace.getWidth() * characterFractionOfWindow,
												visibleSpace.getHeight() * characterFractionOfWindow);

	}
	
	/** Scales the screen properly and draws the current screen 
	 */
	public void draw()
	{
		ratioX = (float)width / activeScreen.DRAWING_WIDTH;
		ratioY = (float)height / activeScreen.DRAWING_HEIGHT;

		push();
		scale(ratioX, ratioY);
		translate((float)-visibleSpace.getX(),(float)-visibleSpace.getY());
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
	 * @param i The screen to switch to
	 */
	public void switchScreen(int i)
	{
		activeScreen = screens.get(i);
	}

}
