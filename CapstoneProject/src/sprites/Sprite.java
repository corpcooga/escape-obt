package sprites;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

/** This class represents a sprite in the game
* @author Nikunj Govil, Boon Chew, Yashavi Chitela
* @version 5/12/2024
*/
public class Sprite extends Rectangle2D.Double {
	
	private String imageFile;
	private PImage image;
	
	
	/** Constructs a Sprite
	 * @param img image to use for Sprite
	 * @param x x-coordinate of Sprite
	 * @param y y-coordinate of Sprite
	 * @param w width of Sprite
	 * @param h height of SPrite
	 */
	public Sprite(String img, int x, int y, int w, int h)
	{
		super(x, y, w, h);
		imageFile = img;
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
	}
	
	
	/** Moves this Sprite to specified location
	 * @param x x-coordinate of location
	 * @param y y-coordinate of location
	 */
	public void setLocation(double x, double y)
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
	
	/** Determines whether or not this Sprite is in specified limits
	 * @param limits The limits to check for the Sprite
	 * @return true if this Sprite is in the limits, false otherwise
	 */
	public boolean inLimits(Rectangle2D.Double limits)
	{
		return x + width > limits.x && y + height > limits.y
				&& x < limits.x + limits.width && y < limits.y + limits.height;
	}
	
	/** Keeps this Sprite in the screen
	 * @param windowWidth width of screen
	 * @param windowHeight height of screen
	 */
	public void applyWindowLimits(Rectangle2D.Double limits)
	{
		x = Math.min(x, limits.width - width);
		y = Math.min(y, limits.height - height);
		x = Math.max(limits.x, x);
		y = Math.max(limits.y, y);
	}
	
	/** Gets the coordinates of the center of this Sprite
	 * @return A Point2D object containing this Sprite's center coordinates
	 */
	public Point2D.Double getCenter()
	{
		return new Point2D.Double(x + width / 2, y + height / 2);
	}
	
	/** Draws this Sprite
	 * @param g PApplet used to draw
	 */
	public void draw(PApplet g)
	{
		
		if (image == null && imageFile != null)
			image = g.loadImage(imageFile);
		
		if (image != null)
			g.image(image, (float)x, (float)y, (float)width, (float)height);
		else {
			g.fill(50);
			g.rect((float)x, (float)y, (float)width, (float)height);
		}
	}
	
}
