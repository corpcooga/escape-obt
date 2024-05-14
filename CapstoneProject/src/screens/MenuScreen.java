package screens;

import java.awt.Point;
import java.awt.Rectangle;
import main.DrawingSurface;

/** This class represents the menu screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/13/24
 */
public class MenuScreen extends Screen {

	private DrawingSurface surface;
	private Rectangle playButton, levelButton;
	
	
	/** Constructs a MenuScreen
	 * @param surface The DrawingSurface this FirstScreen uses
	 */
	public MenuScreen(DrawingSurface surface)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;
		
		playButton = new Rectangle(800 / 2 - 100, 600 / 2 - 50, 200, 100);
		levelButton = new Rectangle(800 / 2 - 100, 500, 200, 100);
	}
	
	
	/** Draws this MenuScreen
	 */
	public void draw()
	{
		surface.background(255, 255, 255);
		
		surface.fill(255);
		surface.rect(playButton.x, playButton.y, playButton.width, playButton.height, 10, 10, 10, 10);
		surface.fill(0);
		String str = "PLAY";
		float w = surface.textWidth(str);
		surface.text(str, playButton.x + playButton.width / 2 - w / 2, playButton.y + playButton.height / 2);
		
		surface.fill(255);
		surface.rect(levelButton.x, levelButton.y, levelButton.width, levelButton.height, 10, 10, 10, 10);
		surface.fill(0);
		str = "LEVELS";
		w = surface.textWidth(str);
		surface.text(str, levelButton.x + levelButton.width / 2 - w / 2, levelButton.y + levelButton.height / 2);
	}
	
	/** Executes a mouse press in this MenuScreen
	 */
	public void mousePressed()
	{
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (playButton.contains(p))
			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
		else if (levelButton.contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL_SCREEN);
	}
	
}
