package sprites;

import processing.core.PApplet;

/** This class represents a weapon
 * @author Nikunj Govil, Boon Chew, Yashasvi Chitela
 * @version 5/17/2024
 */
public class Weapon extends Sprite {
	
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
	
	
	/** Executes an attack with this Weapon
	 * @param surface The PApplet to draw on
	 * @param x The x-coordinate to use for this Weapon
	 * @param y The y-coordinate to use for this Weapon
	 * @param dirX The direction pointed for x (-1, 0, 1)
	 * @param dirY The direction pointed for y (-1, 0, 1)
	 */
	public void slice(PApplet surface, double x, double y, int dirX, int dirY)
	{
		this.x = 0;
		this.y = 0;
		
		int direction, multiplier = dirX > 0 ? 1 : -1;
		if (dirX == 0)
			direction = dirY < 0 ? 180 : 0;
		else {
			if (dirY > 0)
				direction = 45 * multiplier;
			else if (dirY < 0)
				direction = 135 * multiplier;
			else
				direction = 90 * multiplier;
		}
		direction += 45;
				
		surface.translate((float)x, (float)y);
		surface.rotate((float)Math.toRadians(direction));
		draw(surface);
	}

}
