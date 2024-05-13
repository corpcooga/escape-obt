package screens;

import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import main.DrawingSurface;
import sprites.Player;
import sprites.Sprite;
import grid.Level;

/** This class represents the game screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/12/24
 */
public class GameScreen extends Screen {
	
	// Bigger = panning happens closer to the edge of window (1 = panning at edge, 0 = panning constantly)
	private static final double characterFractionOfWindow = 0;
	// Bigger = follow char more slowly (1 = pan immediately)
	private static final double panningLag = 10;
	
	// Area of the level that can be seen
	private Rectangle2D.Double visibleSpace;
	// Area of the window that the player can move freely in
	private Rectangle2D.Double playerSpace;
	
	private DrawingSurface surface;
	private Level level;
	
	
	/** Constructs a GameScreen
	 * @param surface The DrawingSurface this GameScreen uses
	 */
	public GameScreen(DrawingSurface surface)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;
		
		level = new Level(21, 21, "resources/levels/level1.txt");
	}
	
	
	/** Sets up this GameScreen
	 */
	public void setup()
	{
		visibleSpace = new Rectangle2D.Double(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		playerSpace = new Rectangle2D.Double(visibleSpace.getX() + visibleSpace.getWidth() * (1 - characterFractionOfWindow) * 0.5,
												visibleSpace.getY() + visibleSpace.getHeight() * (1 - characterFractionOfWindow) * 0.5,
												visibleSpace.getWidth() * characterFractionOfWindow,
												visibleSpace.getHeight() * characterFractionOfWindow);
	}
	
	public void slideWorldToImage(Sprite img)
	{
		Point2D.Double center = img.getCenter();
		
		if (!playerSpace.contains(center))
		{
			double newX = visibleSpace.getX();
			double newY = visibleSpace.getY();

			if (center.getX() < playerSpace.getX())
				newX -= (playerSpace.getX() - center.getX()) / panningLag;
			else if (center.getX() > playerSpace.getX() + playerSpace.getWidth())
				newX += (center.getX() - (playerSpace.getX() + playerSpace.getWidth())) / panningLag;

			if (center.getY() < playerSpace.getY())
				newY -= (playerSpace.getY() - center.getY()) / panningLag;
			else if (center.getY() > playerSpace.getY() + playerSpace.getHeight())
				newY += (center.getY() - playerSpace.getY() - playerSpace.getHeight()) / panningLag;
			
			newX = Math.max(newX, 0);
			newY = Math.max(newY, 0);
			newX = Math.min(newX, level.getWidth() - visibleSpace.getWidth());
			newY = Math.min(newY, level.getHeight() - visibleSpace.getHeight());

			visibleSpace.setRect(newX, newY, visibleSpace.getWidth(), visibleSpace.getHeight());
			playerSpace.setRect(visibleSpace.getX() + visibleSpace.getWidth() * (1 - characterFractionOfWindow) * 0.5, 
									visibleSpace.getY() + visibleSpace.getHeight() * (1 - characterFractionOfWindow) * 0.5, 
									visibleSpace.getWidth() * characterFractionOfWindow,
									visibleSpace.getHeight() * characterFractionOfWindow);
		}
	}
	
	/** Draws this GameScreen and handles game controls
	 */
	public void draw()
	{
		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			return;
		}
		
		surface.translate((float)-visibleSpace.getX(), (float)-visibleSpace.getY());
		surface.background(0, 0, 0);
		
		level.draw(surface, visibleSpace);
		
		Player player = level.getPlayer();
		if (surface.isPressed(KeyEvent.VK_W))
			player.accelerate(0, 1);
		if (surface.isPressed(KeyEvent.VK_S))
			player.accelerate(0, -1);
		if (surface.isPressed(KeyEvent.VK_A))
			player.accelerate(-1, 0);
		if (surface.isPressed(KeyEvent.VK_D))
			player.accelerate(1, 0);
		if (surface.isPressed(KeyEvent.VK_SHIFT))
			player.sneak(true);
		else 
			player.sneak(false);
		player.act(level);
		
		slideWorldToImage(player);
	}

}
