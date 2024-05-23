package screens;

import jay.jaysound.JayLayer;
import main.DrawingSurface;
import processing.core.PImage;

/** This class represents the winning screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/23/24
 */
public class WinScreen extends Screen {
	
	private DrawingSurface surface;
	private PImage backgroundImage;
	
	private JayLayer sound;
	

	/** Constructs a WinScreen
	 * @param surface The DrawingSurface this WinScreen uses
	 */
	public WinScreen(DrawingSurface surface)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;
		
		String song = new String("escapeohio.mp3");
		sound = new JayLayer("resources/sound/", "resources/sound/", false);
		sound.addPlayList();
		sound.addSong(0, song);
		sound.changePlayList(0);
	}
	
	
	/** Sets the WinScreen to default settings
	 */
	public void setup()
	{
		backgroundImage = surface.loadImage("resources/img/winscreen.gif");
		backgroundImage.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
		sound.nextSong();
	}
	
	/** Draws this WinScreen
	 */
	public void draw()
	{
		surface.background(backgroundImage);
	}
	
}
