package screens;

import main.DrawingSurface;
import processing.core.PImage;

/** This class represents the winning screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/22/24
 */
public class WinScreen extends Screen {
	
	private DrawingSurface surface;
	private PImage backgroundImage;
	

	/** Constructs a WinScreen
	 * @param surface The DrawingSurface this WinScreen uses
	 */
	public WinScreen(DrawingSurface surface)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;
	}
	
	
	/** Sets the WinScreen to default settings
	 */
	public void setup()
	{
		backgroundImage = surface.loadImage("resources/img/GameOver.gif");
		backgroundImage.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
	}
	
	/** Draws this WinScreen
	 */
	public void draw()
	{
		surface.background(backgroundImage);
	}
}
