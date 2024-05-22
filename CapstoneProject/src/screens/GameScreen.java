package screens;

import java.awt.Point;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import main.DrawingSurface;
import sprites.Player;
import sprites.Sprite;
import grid.Level;

/** This class represents the game screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/22/24
 */
public class GameScreen extends Screen {
	
	// Bigger = panning happens closer to the edge of window (1 = panning at edge, 0 = panning constantly)
	private static final double characterFractionOfWindow = 0.1;
	// Bigger = follow char more slowly (1 = pan immediately)
	private static final double panningLag = 15;
	
	// Area of the level that can be seen
	private Rectangle2D.Double visibleSpace;
	// Area of the window that the player can move freely in
	private Rectangle2D.Double playerSpace;
	
	private DrawingSurface surface;
	private Level level;
	
	private int sneakFrame, weaponFrame;
	
	
	/** Constructs a GameScreen
	 * @param surface The DrawingSurface this GameScreen uses
	 */
	public GameScreen(DrawingSurface surface)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;
		
		level = new Level(this);
	}
	
	
	/** Sets up this GameScreen
	 */
	public void setup()
	{
		sneakFrame = 0;
		weaponFrame = 0;
		
		Point2D.Double playerCoords = level.getPlayer().getCenter();
		visibleSpace = new Rectangle2D.Double(playerCoords.getX(), playerCoords.getY(), DRAWING_WIDTH, DRAWING_HEIGHT);
		playerSpace = new Rectangle2D.Double(visibleSpace.getX() + visibleSpace.getWidth() * (1 - characterFractionOfWindow) * 0.5,
												visibleSpace.getY() + visibleSpace.getHeight() * (1 - characterFractionOfWindow) * 0.5,
												visibleSpace.getWidth() * characterFractionOfWindow,
												visibleSpace.getHeight() * characterFractionOfWindow);
	}
	
	/** Moves the game's visible space to a specified Sprite
	 * @param sprite The Sprite to move the visible space towards
	 */
	public void slideWorldToImage(Sprite sprite)
	{
		Point2D.Double center = sprite.getCenter();
		
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
			
			newX = Math.max(newX, level.getX());
			newY = Math.max(newY, level.getY());
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
		surface.background(0);
		
		level.draw(surface);
		
//		Player controls
		Player player = level.getPlayer();
		if (sneakFrame < 300 && surface.isPressed(KeyEvent.VK_SHIFT)) {
			sneakFrame++;
			player.setSneak(true);
		} else {
			player.setSneak(false);
			if (sneakFrame >= 300)
				sneakFrame++;
			if (sneakFrame >= 600)
				sneakFrame = 0;
		}
		Point dir = calcDirection();
		player.accelerate(dir.x, dir.y);
		if (weaponFrame > 0 && weaponFrame < 20) {
			player.attack(surface, dir.x, dir.y, false);
			weaponFrame++;
		} else {
			if (weaponFrame >= 20)
				weaponFrame++;
			if (weaponFrame >= 60)
				weaponFrame = 0;
		}
		
		slideWorldToImage(player);
	}
	
	public void keyPressed()
	{
		if (weaponFrame == 0 && surface.key == KeyEvent.VK_SPACE && !surface.isPressed(KeyEvent.VK_SPACE)) {
			Point dir = calcDirection();
			level.getPlayer().attack(surface, dir.x, dir.y, true);
			weaponFrame++;
		}
	}
	
	private Point calcDirection()
	{
		int dirX = 0, dirY = 0;
		if (surface.isPressed(KeyEvent.VK_W) || surface.isPressed(KeyEvent.VK_UP))
			dirY += 1;
		if (surface.isPressed(KeyEvent.VK_S) || surface.isPressed(KeyEvent.VK_DOWN))
			dirY -= 1;
		if (surface.isPressed(KeyEvent.VK_A) || surface.isPressed(KeyEvent.VK_LEFT))
			dirX -= 1;
		if (surface.isPressed(KeyEvent.VK_D) || surface.isPressed(KeyEvent.VK_RIGHT))
			dirX += 1;
		return new Point(dirX, dirY);
	}
	
	public void switchToDeathScreen() 
	{
		surface.switchScreen(ScreenSwitcher.DEATH_SCREEN);
	}
	
	public void switchToWinScreen() 
	{
		surface.switchScreen(ScreenSwitcher.WIN_SCREEN);
	}
	
	public void setupLevel(int numLevel)
	{
		level.setupLevel(numLevel);
	}
	
	public int getLevel()
	{
		return level.getLevel();
	}
	
	public int getHighestLevel()
	{
		return level.getHighestLevel();
	}

}
