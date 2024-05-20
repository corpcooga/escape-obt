package sprites;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import processing.core.PImage;
import grid.Level;

/** This class represents a sprite in the game
* @author Nikunj Govil, Boon Chew, Yashavi Chitela
* @version 5/20/2024
*/
public class Sprite extends Rectangle2D.Double {
	
	/** The Level that this Sprite belongs to
	 */
	protected Level level;
	private String imageFile;
	private PImage image;
	
	
	/** Constructs a Sprite
	 * @param img image to use for this Sprite
	 * @param level The Level of this Sprite
	 * @param x x-coordinate of this Sprite
	 * @param y y-coordinate of this Sprite
	 * @param w width of this Sprite
	 * @param h height of this SPrite
	 */
	public Sprite(String img, Level level, int x, int y, int w, int h)
	{
		super(x, y, w, h);
		imageFile = img;
		this.level = level;
	}
	
	/** Constructs a Sprite without an image
	 * @param level The Level of this Sprite
	 * @param x x-coordinate of this Sprite
	 * @param y y-coordinate of this Sprite
	 * @param w width of this Sprite
	 * @param h weight of this Sprite
	 */
	public Sprite(Level level, int x, int y, int w, int h)
	{
		this(null, level, x, y, w, h);
	}
	
	/** Constructs a Sprite without a Level
	 * @param img image to use for this Sprite
	 * @param x x-coordinate of this Sprite
	 * @param y y-coordinate of this Sprite
	 * @param w width of this Sprite
	 * @param h weight of this Sprite
	 */
	public Sprite(String img, int x, int y, int w, int h)
	{
		this(img, null, x, y, w, h);
	}
	
	/** Constructs a Sprite without an image or Level
	 * @param x x-coordinate of this Sprite
	 * @param y y-coordinate of this Sprite
	 * @param w width of this Sprite
	 * @param h weight of this Sprite
	 */
	public Sprite(int x, int y, int w, int h)
	{
		this(null, null, x, y, w, h);
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
	
	/** Determines if this Sprite is in specified limits
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
	
	/** Changes this Sprite's image
	 * @param newImage The image to change to
	 */
	public void setImage(String newImage)
	{
		if (!imageFile.equals(newImage)) {
			imageFile = newImage;
			this.image = null;
		}
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
