package screens;

import java.awt.event.*;
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
	}
	
	/** Draws this SecondScreen and handles game controls
	 */
	public void draw()
	{
		surface.background(0, 255, 255);   

//		for (Sprite s : obstacles)
//			s.draw(surface);
		
		maze.draw(surface, (int)player.x, (int)player.y, 600, 600);
		player.draw(surface);

		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			return;
		}
		if (surface.isPressed(KeyEvent.VK_W))
			player.move(0, 1);
		if (surface.isPressed(KeyEvent.VK_A)) {
			player.changeImage(surface.loadImage("resources/img/flmainchar.png"));
			player.move(-1, 0);
		}
		if (surface.isPressed(KeyEvent.VK_S))
			player.move(0, -1);
		if (surface.isPressed(KeyEvent.VK_D)) {
			player.changeImage(surface.loadImage("resources/img/mainchar.png"));
			player.move(1, 0);
		}
		
		player.act(null);
//		player.applyWindowLimits(DRAWING_WIDTH, DRAWING_HEIGHT);
	}

}
