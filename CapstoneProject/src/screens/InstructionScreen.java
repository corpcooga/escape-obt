package screens;

import java.awt.Point;
import java.awt.Rectangle;

import main.DrawingSurface;

/** This class represents the instructions screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/22/24
 */
public class InstructionScreen extends Screen {

	private DrawingSurface surface;
	private Rectangle backButton;
	private int width = 250;
	private int height = 150;
	
	
	public InstructionScreen(DrawingSurface surface)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;

		backButton = new Rectangle(surface.width - width, surface.height - height, width, height);
	}
	

	public void draw()
	{
		surface.background(255);
	}

	public void mousePressed()
	{
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX, surface.mouseY));
		if (backButton.contains(p)) {
			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
		}
	}
	
}
