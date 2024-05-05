package screens;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import main.DrawingSurface;
import sprites.Player;
import sprites.Sprite;

/** This class represents the game screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/5/24
 */
public class SecondScreen extends Screen {
	
	private List<Sprite> obstacles;
	
	private DrawingSurface surface;
	private Player mario;
	
	
	/** Constructs a SecondScreen
	 * @param surface The DrawingSurface this SecondScreen uses
	 */
	public SecondScreen(DrawingSurface surface)
	{
		super(800, 600);
		this.surface = surface;
		
		obstacles = new ArrayList<Sprite>();
		obstacles.add(new Sprite(0, 250, 100, 50));
		obstacles.add(new Sprite(700, 250, 100, 50));
		obstacles.add(new Sprite(200, 400, 400, 50));
		obstacles.add(new Sprite(375, 300, 50, 100));
		obstacles.add(new Sprite(300, 250, 200, 50));
	}
	
	
	/** Spawns Mario into the game
	 */
	public void spawnNewMario()
	{
		mario = new Player(surface.loadImage("resources/img/mainchar.png"), 
				DRAWING_WIDTH / 2 - Player.MARIO_WIDTH / 2, 50);
	}
	
	/** Sets up this SecondScreen
	 */
	public void setup()
	{
		spawnNewMario();
	}
	
	/** Draws this SecondScreen and handles game controls
	 */
	public void draw()
	{
		surface.background(0, 255, 255);   

		for (Sprite s : obstacles)
			s.draw(surface);

		mario.draw(surface);

		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			return;
		}
		if (surface.isPressed(KeyEvent.VK_LEFT))
			mario.walk(-1);
		if (surface.isPressed(KeyEvent.VK_RIGHT))
			mario.walk(1);
		if (surface.isPressed(KeyEvent.VK_UP))
			mario.jump();

		mario.act(obstacles);
		mario.applyWindowLimits(DRAWING_WIDTH, DRAWING_HEIGHT);
	}

}
