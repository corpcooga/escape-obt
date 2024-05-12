package sprites;

import processing.core.PImage;

/** This class represents a Wall that cannot be walked through
 * @author Nikunj Govil, Boon Chew, Yashasvi Chitela
 * @version 5/12/2024
 */
public class Wall extends Sprite {

	/** Constructs a Wall
	 * @param img image to use for this Wall
	 * @param x x-coordinate of this Wall
	 * @param y y-coordinate of this Wall
	 * @param w width of this Wall
	 * @param h height of this Wall
	 */
	public Wall(PImage img, int x, int y, int w, int h)
	{
		super(img, x, y, w, h);
	}
	
	/** Constructs a Wall with the image specified
	 * @param x x-coordinate of this Wall
	 * @param y y-coordinate of this Wall
	 * @param w width of this Wall
	 * @param h height of this Wall
	 */
	public Wall(int x, int y, int w, int h)
	{
		super(null, x, y, w, h);
	}

}
