package sprites;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Nikunj Govil, Boon Chew, Yashavi Chitela
 */
public class Sprite extends Rectangle2D.Double {
	
	private PImage image;
	
	/**
	 * Constructor for Sprite
	 * @param img - image to use for Sprite
	 * @param x - X coordinate of Sprite
	 * @param y - Y coordinate of Sprite
	 * @param w - Width of Sprite
	 * @param h - Height of SPrite
	 */
	public Sprite(PImage img, int x, int y, int w, int h)
	{
		super(x,y,w,h);
		image = img;
	}
	
	/**
	 * Constructor for Sprite without image
	 * @param x - X coordinate of Sprite
	 * @param y - Y coordinate of Sprite
	 * @param w - Width of Sprite
	 * @param h - Height of Sprite
	 */
	public Sprite(int x, int y, int w, int h)
	{
		this(null, x, y, w, h);
	}
	
	/**
	 * Moves Sprite to specified location
	 * @param x - X coordinate of location
	 * @param y - Y coordinate of location
	 */
	public void moveToLocation(double x, double y)
	{
		super.x = x;
		super.y = y;
	}
	
	/**
	 * Moves by specified amount
	 * @param x - X coordinate amount to move
	 * @param y - Y coordinate amount to move
	 */
	public void moveByAmount(double x, double y)
	{
		super.x += x;
		super.y += y;
	}
	/**
	 * Sets screen size
	 * @param windowWidth - Width of screen
	 * @param windowHeight - Height of screen
	 */
	public void applyWindowLimits(int windowWidth, int windowHeight)
	{
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	/**
	 * Draws Sprite
	 * @param g - PApplet object to use
	 */
	public void draw(PApplet g)
	{
		if (image != null)
			g.image(image,(float)x,(float)y,(float)width,(float)height);
		else {
			g.fill(100);
			g.rect((float)x,(float)y,(float)width,(float)height);
		}
	}

}
