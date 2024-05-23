package screens;

import java.awt.Point;
import java.awt.Rectangle;

import main.DrawingSurface;
import processing.core.PImage;

/** This class represents the death screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/22/24
 */
public class DeathScreen extends Screen {
	
	private DrawingSurface surface;
	private GameScreen gameScreen;
	private Rectangle restartButton, levelButton;
	private PImage backgroundImage;
	

	/** Constructs a DeathScreen
	 * @param surface The DrawingSurface this DeathScreen uses
	 */
	public DeathScreen(DrawingSurface surface, GameScreen gameScreen)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;
		
		restartButton = new Rectangle(DRAWING_WIDTH / 2 - 100, 400, 200, 100);
		levelButton = new Rectangle(DRAWING_WIDTH / 2 - 100, 550, 200, 100);
		this.gameScreen = gameScreen;
	}
	
	
	/** Sets this DeathScreen to default settings
	 */
	public void setup()
	{
		backgroundImage = surface.loadImage("resources/img/gameoverscreen.gif");
		backgroundImage.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
	}
	
	/** Draws this DeathScreen
	 */
	public void draw()
	{
		surface.background(backgroundImage);
		surface.strokeWeight(5);
		surface.textSize(40);
		surface.textAlign(DrawingSurface.CENTER, DrawingSurface.CENTER);
		
		surface.fill(255);
		surface.rect(restartButton.x, restartButton.y, restartButton.width, restartButton.height, 20);
		surface.fill(0);
		surface.text("RESTART", restartButton.x + restartButton.width / 2, restartButton.y + restartButton.height / 2);
		
		surface.fill(255);
		surface.rect(levelButton.x, levelButton.y, levelButton.width, levelButton.height, 20);
		surface.fill(0);
		surface.text("EXIT", levelButton.x + levelButton.width / 2, levelButton.y + levelButton.height / 2);
	}
	
	/** Executes a mouse press in this DeathScreen
	 */
	public void mousePressed()
	{
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (restartButton.contains(p)) {
			gameScreen.setupLevel(gameScreen.getLevel());
			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
		} else if (levelButton.contains(p)) {
			gameScreen.setupLevel(gameScreen.getLevel());
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
		}
	}
}
