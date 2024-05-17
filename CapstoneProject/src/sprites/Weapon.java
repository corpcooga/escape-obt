package sprites;

import processing.core.PApplet;

/** This class represents a weapon
 * @author Nikunj Govil, Boon Chew, Yashasvi Chitela
 * @version 5/17/2024
 */
public class Weapon extends Sprite {
	
	private float rotation;
	

	/** Constructs a Weapon
	 * @param img image to use for this Weapon
	 * @param x x-coordinate of this Weapon
	 * @param y y-coordinate of this Weapon
	 * @param w width of this Weapon
	 * @param h height of this Weapon
	 */
	public Weapon(String img, int x, int y, int w, int h)
	{
		super(img, x, y, w, h);
		rotation = 0;
	}
	
	/** Constructs a Weapon with the image specified
	 * @param x x-coordinate of this Weapon
	 * @param y y-coordinate of this Weapon
	 * @param w width of this Weapon
	 * @param h height of this Weapon
	 */
	public Weapon(int x, int y, int w, int h)
	{
		this("resources/img/sword.gif", x, y, w, h);
	}
	
	
	public void slice(PApplet surface, double x, double y)
	{
		this.x = 0;
		this.y = 0;
		
		surface.translate((float)(x + width / 2), (float)(y + height / 2));
		rotation++;
		surface.rotate((float)Math.toRadians(rotation));
		draw(surface);
	}

}
