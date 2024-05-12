package screens;

import java.awt.Point;
import java.awt.Rectangle;
import main.DrawingSurface;

/** This class represents the menu screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/12/24
 */
public class MenuScreen extends Screen {

	private DrawingSurface surface;
	private Rectangle button;
	
	
	/** Constructs a MenuScreen
	 * @param surface The DrawingSurface this FirstScreen uses
	 */
	public MenuScreen(DrawingSurface surface)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;
		
		button = new Rectangle(800 / 2 - 100, 600 / 2 - 50, 200, 100);
	}
	
	
	/** Draws this MenuScreen
	 */
	public void draw()
	{
		surface.background(255, 255, 255);
		
		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		surface.fill(0);
		String str = "PLAY";
		float w = surface.textWidth(str);
		surface.text(str, button.x + button.width / 2 - w / 2, button.y + button.height / 2);
	}
	
	/** Executes a mouse press in this MenuScreen
	 */
	public void mousePressed()
	{
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
	}
	
}
