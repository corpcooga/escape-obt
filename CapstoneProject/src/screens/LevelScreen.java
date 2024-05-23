package screens;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import jay.jaysound.JayLayer;
import main.DrawingSurface;
import processing.core.PImage;

/** This class represents the levels screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/22/24
 */
public class LevelScreen extends Screen {
	
	private DrawingSurface surface;
	private GameScreen gameScreen;
	private Rectangle[] levelButtons;
	private PImage backgroundImage;
	
	private JayLayer sound;
	
	/** Constructs a LevelScreen
	 * @param surface The DrawingSurface this LevelScreen uses
	 * @param screen The GameScreen this LevelScreen uses
	 */
	public LevelScreen(DrawingSurface surface, GameScreen screen)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;
		gameScreen = screen;
		levelButtons = new Rectangle[5];
		
		String song = new String("levelscreenmusic.mp3");
		sound = new JayLayer("resources/sound/", "resources/sound/", false);
		sound.addPlayList();
		sound.addSong(0, song);
		sound.changePlayList(0);
        
		for (int i = 0; i < 3; i++) {
        		int x = DRAWING_WIDTH / 2 + (i - 1) * 220 - 100;
        		levelButtons[i] = new Rectangle(x, 180, 200, 200);
        }
		for (int i = 3; i < 5; i++) {
	    		int x = DRAWING_WIDTH / 2 + (i - 4) * 220;
	    		levelButtons[i] = new Rectangle(x, 420, 200, 200);
		}
	}
	
	
	/** Sets this LevelScreen to default settings
	 */
	public void setup()
	{
		backgroundImage = surface.loadImage("resources/img/levelscreen.gif");
		backgroundImage.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
		sound.nextSong();
	}
	
	/** Draws this LevelScreen
	 */
	public void draw()
	{
		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			sound.stopSong();
			return;
		}
		
		surface.background(backgroundImage);
		surface.strokeWeight(5);
		surface.textSize(80);
		surface.textAlign(DrawingSurface.CENTER, DrawingSurface.CENTER);
		surface.text("Levels", DRAWING_WIDTH / 2, 50);
		surface.textSize(40);

		// Draw level buttons
		for (int i = 0; i < levelButtons.length; i++) {
			if (i < gameScreen.getHighestLevel())
				surface.fill(255);
			else
				surface.fill(100);
			surface.rect(levelButtons[i].x, levelButtons[i].y, levelButtons[i].width, levelButtons[i].height, 60);
			
			surface.fill(0);
			surface.text("Level " + (i + 1), levelButtons[i].x + levelButtons[i].width / 2,
					levelButtons[i].y + levelButtons[i].height / 2);
		}
	}
	
	/** Executes a mouse press in this LevelScreen
	 */
	public void mousePressed()
	{
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		for (int i = 0; i < levelButtons.length; i++)
			if (levelButtons[i].contains(p) && i < gameScreen.getHighestLevel()) {
				gameScreen.setupLevel(i + 1);
				sound.stopSong();
				surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
				break;
			}
	}
	
}
