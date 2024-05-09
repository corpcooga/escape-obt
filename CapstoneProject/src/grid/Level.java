package grid;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import sprites.Sprite;

/** This class represents a level in the game
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/9/24
 */
public class Level extends Rectangle2D.Double {

	private ArrayList<Sprite> scene;
	
	
	/** Constructs a Level
	 * @param imgs images to use for the Sprites in this Level
	 */
	public Level(PImage[] imgs)
	{
		scene = new ArrayList<Sprite>();
		scene.add(new Sprite(imgs[0],150,700,175,300));
		scene.add(new Sprite(imgs[1],525,800,200,200));
		scene.add(new Sprite(imgs[1],1000,800,200,200));
		scene.add(new Sprite(imgs[1],700,800,200,200));
		scene.add(new Sprite(imgs[1],1100,800,200,200));
		scene.add(new Sprite(imgs[1],800,800,200,200));
		scene.add(new Sprite(imgs[1],1250,800,200,200));
		scene.add(new Sprite(imgs[2],1300,200,150,150));
		scene.add(new Sprite(imgs[3],200,500,300,150));
		scene.add(new Sprite(imgs[3],600,500,300,150));
		scene.add(new Sprite(imgs[3],1200,500,300,150));
		this.width = 2000;
		this.height = 2000;
	}
	
	
	/** Draws all Sprites in this Level
	 * @param surface The PApplet used to draw
	 */
	public void draw(PApplet surface)
	{
		for (Sprite s : scene)
			s.draw(surface);
	}
	
}
