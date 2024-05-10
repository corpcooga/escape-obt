package screens;

import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import main.DrawingSurface;
import sprites.Player;
import sprites.Sprite;
import grid.Maze;

/** This class represents the game screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/7/24
 */
public class SecondScreen extends Screen {
	
//	private List<Sprite> obstacles;
	
	private static final double characterFractionOfWindow = 0;  // Bigger = panning happens closer to the edge of window. 1 = right at edge, 0 = panning happens always
	private static final double panningLag = 10;  // Bigger = follow char more slowly. 1 = immediately pan
	
	private Rectangle2D.Double visibleSpace;  // Area of the level that we can see
	private Rectangle2D.Double characterSpace;  // Area of the window that the character can move freely in
	
	private DrawingSurface surface;
	private Player player;
	private Maze maze;
	
	
	/** Constructs a SecondScreen
	 * @param surface The DrawingSurface this SecondScreen uses
	 */
	public SecondScreen(DrawingSurface surface)
	{
		super(800, 600);
		this.surface = surface;
		
		maze = new Maze(20, 16, "testfiles/paintcan/digital.txt");
		
//		obstacles = new ArrayList<Sprite>();
//		obstacles.add(new Sprite(0, 250, 100, 50));
//		obstacles.add(new Sprite(700, 250, 100, 50));
//		obstacles.add(new Sprite(200, 400, 400, 50));
//		obstacles.add(new Sprite(375, 300, 50, 100));
//		obstacles.add(new Sprite(300, 250, 200, 50));
	}
	
	
	/** Spawns new Player into the game
	 */
	public void spawnNewPlayer()
	{
		player = new Player(surface.loadImage("resources/img/mainchar.png"), 
				DRAWING_WIDTH / 2 - Player.PLAYER_WIDTH / 2, 50);
	}
	
	/** Sets up this SecondScreen
	 */
	public void setup()
	{
		spawnNewPlayer();
		visibleSpace = new Rectangle2D.Double(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		characterSpace = new Rectangle2D.Double(visibleSpace.getX() + visibleSpace.getWidth() * (1 - characterFractionOfWindow) * 0.5,
												visibleSpace.getY() + visibleSpace.getHeight() * (1 - characterFractionOfWindow) * 0.5,
												visibleSpace.getWidth() * characterFractionOfWindow,
												visibleSpace.getHeight() * characterFractionOfWindow);
	}
	
	public void slideWorldToImage(Sprite img)
	{
		Point2D.Double center = img.getCenter();
		if (!characterSpace.contains(center)) {
			double newX = visibleSpace.getX();
			double newY = visibleSpace.getY();

			if (center.getX() < characterSpace.getX())
				newX -= (characterSpace.getX() - center.getX()) / panningLag;
			else if (center.getX() > characterSpace.getX() + characterSpace.getWidth())
				newX += (center.getX() - (characterSpace.getX() + characterSpace.getWidth())) / panningLag;

			if (center.getY() < characterSpace.getY())
				newY -= (characterSpace.getY() - center.getY()) / panningLag;
			else if (center.getY() > characterSpace.getY() + characterSpace.getHeight())
				newY += (center.getY() - characterSpace.getY() - characterSpace.getHeight()) / panningLag;
			
			newX = Math.max(newX, 0);
			newY = Math.max(newY, 0);
			newX = Math.min(newX, level.getWidth() - visibleSpace.getWidth());
			newY = Math.min(newY, level.getHeight() - visibleSpace.getHeight());

			visibleSpace.setRect(newX,newY,visibleSpace.getWidth(),visibleSpace.getHeight());
			characterSpace.setRect(visibleSpace.getX()+visibleSpace.getWidth()*(1-characterFractionOfWindow)*0.5,visibleSpace.getY()+visibleSpace.getHeight()*(1-characterFractionOfWindow)*0.5,visibleSpace.getWidth()*characterFractionOfWindow,visibleSpace.getHeight()*characterFractionOfWindow);
		}
	}
	
	/** Draws this SecondScreen and handles game controls
	 */
	public void draw()
	{
		surface.background(0, 255, 255);   
		
		surface.translate((float)-visibleSpace.getX(), (float)-visibleSpace.getY());
		
//		for (Sprite s : obstacles)
//			s.draw(surface);
		
		maze.draw(surface, (int)100, (int)100, 600, 600);
		player.draw(surface);

		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			return;
		}
		if (surface.isPressed(KeyEvent.VK_W))
			player.move(0, 1);
		if (surface.isPressed(KeyEvent.VK_A)) {
//			player.changeImage(surface.loadImage("resources/img/flmainchar.png"));
			player.move(-1, 0);
		}
		if (surface.isPressed(KeyEvent.VK_S))
			player.move(0, -1);
		if (surface.isPressed(KeyEvent.VK_D)) {
//			player.changeImage(surface.loadImage("resources/img/mainchar.png"));
			player.move(1, 0);
		}
		
		player.act(null);
//		player.applyWindowLimits(DRAWING_WIDTH, DRAWING_HEIGHT);
	}

}
