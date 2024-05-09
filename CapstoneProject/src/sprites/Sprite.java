package sprites;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

/** This class represents a sprite in the game
 * @author Nikunj Govil, Boon Chew, Yashavi Chitela
 * @version 5/5/2024
 */
public class Sprite extends Rectangle2D.Double {
	
	private PImage image;
	private boolean isOnGrid;
	
	/** Constructs a Sprite
	 * @param img image to use for Sprite
	 * @param x x-coordinate of Sprite
	 * @param y y-coordinate of Sprite
	 * @param w width of Sprite
	 * @param h height of SPrite
	 */
	public Sprite(PImage img, int x, int y, int w, int h)
	{
		super(x, y, w, h);
		image = img;
		isOnGrid = true;
	}
	
	
	/** Constructs a Sprite without an image
	 * @param x x-coordinate of Sprite
	 * @param y y-coordinate of Sprite
	 * @param w width of Sprite
	 * @param h weight of Sprite
	 */
	public Sprite(int x, int y, int w, int h)
	{
		this(null, x, y, w, h);
		isOnGrid = true;
	}
	
	/** Moves this Sprite to specified location
	 * @param x x-coordinate of location
	 * @param y y-coordinate of location
	 */
	public void moveToLocation(double x, double y)
	{
		super.x = x;
		super.y = y;
	}
	
	/** Moves this Sprite by specified amount
	 * @param x The amount to move along the x-axis
	 * @param y The amount to move along the y-axis
	 */
	public void moveByAmount(double x, double y)
	{
		super.x += x;
		super.y += y;
	}
	
	/** Keeps this Sprite in the screen
	 * @param windowWidth width of screen
	 * @param windowHeight height of screen
	 */
	public void applyWindowLimits(int windowWidth, int windowHeight)
	{
		x = Math.min(x, windowWidth-width);
		y = Math.min(y, windowHeight-height);
		x = Math.max(0, x);
		y = Math.max(0, y);
	}
	
	/** Draws this Sprite
	 * @param g PApplet used to draw
	 */
	public void draw(PApplet g)
	{
		if(isOnGrid) {
			if (image != null)
				g.image(image, (float)x, (float)y, (float)width, (float)height);
			else {
				g.fill(100);
				g.rect((float)x, (float)y, (float)width, (float)height);
			}
		}
	}
	
	public void removeSprite(Sprite toRemove) {
		toRemove.isOnGrid = false;
	}
	
	public void changeImage(PImage pic) {
		
	}
}