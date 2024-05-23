package screens;

import java.awt.Point;
import java.awt.Rectangle;

import jay.jaysound.JayLayer;
import main.DrawingSurface;
import processing.core.PImage;

/** This class represents the menu screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/22/24
 */
public class MenuScreen extends Screen {
	
	private DrawingSurface surface;
	private Rectangle playButton, levelButton;
	private PImage backgroundImage;
	
	private JayLayer sound;
	

	/** Constructs a MenuScreen
	 * @param surface The DrawingSurface this MenuScreen uses
	 */
	public MenuScreen(DrawingSurface surface)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;
		
		playButton = new Rectangle(DRAWING_WIDTH / 2 - 100, 300, 200, 100);
		levelButton = new Rectangle(DRAWING_WIDTH / 2 - 100, 500, 200, 100);

		String song = new String("mainbgmusic.mp3");
		sound = new JayLayer("resources/sound/", "resources/sound/", false);
		sound.addPlayList();
		sound.addSong(0, song);
		sound.changePlayList(0);
	}

	
	/** Sets the MenuScreen to default settings
	 */
	public void setup()
	{
		backgroundImage = surface.loadImage("resources/img/mainscreen.gif");
		backgroundImage.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
		sound.nextSong();
	}
	
	/** Draws this MenuScreen
	 */
	public void draw()
	{
		surface.background(backgroundImage);
		surface.strokeWeight(5);
		surface.textSize(80);
		surface.textAlign(surface.CENTER, surface.CENTER);
		surface.text("Escape OBT", DRAWING_WIDTH / 2, 50);
		surface.textSize(40);
		
		surface.fill(255);
		surface.rect(playButton.x, playButton.y, playButton.width, playButton.height, 20);
		surface.fill(0);
		surface.text("PLAY", playButton.x + playButton.width / 2, playButton.y + playButton.height / 2);

		surface.fill(255);
		surface.rect(levelButton.x, levelButton.y, levelButton.width, levelButton.height, 20);
		surface.fill(0);
		surface.text("LEVELS", levelButton.x + levelButton.width / 2, levelButton.y + levelButton.height / 2);
	}

	/** Executes a mouse press in this MenuScreen
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX, surface.mouseY));
		if (playButton.contains(p)) {
			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
			sound.stopSong();
		} else if (levelButton.contains(p)) {
			surface.switchScreen(ScreenSwitcher.LEVEL_SCREEN);
			sound.stopSong();
		}
	}

}
