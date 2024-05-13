package sprites;

import grid.Level;

/** This class represents an enemy entity
 * @author Nikunj Govil, Yashasvi Chitela, Boon Chew
 * @version 5/12/24
 */
public class Tickler extends Sprite {
	
	/** Constructs a Tickler
	 * @param img image to use for this Tickler
	 * @param x x-coordinate of this Tickler
	 * @param y y-coordinate of this Tickler
	 * @param w width of this Tickler
	 * @param h height of this Tickler
	 */
	public Tickler(String img, int x, int y, int w, int h)
	{
		super(img, x, y, w, h);
	}
	
	/** Constructs a Tickler with the image specified
	 * @param x x-coordinate of this Tickler
	 * @param y y-coordinate of this Tickler
	 * @param w width of this Tickler
	 * @param h height of this Tickler
	 */
	public Tickler(int x, int y, int w, int h)
	{
		this("resources/img/tickler.gif", x, y, w, h);
	}
	
	/** Handles Tickler movement
	 * @param level Represents the level that this Tickler
	 */
	public void act(Level level)
	{
//		x += xVel;
//		for(Wall w : level.getWalls())
//			if (w.intersects(this)) {
//				x -= xVel;
//				xVel = 0;
//			}
//		
//		y -= yVel;
//		for(Wall w : level.getWalls())
//			if (w.intersects(this)) {
//				y += yVel;
//				yVel = 0;
//			}
//		
//		applyWindowLimits(level);
//		
//		yVel *= 0.8;
//		xVel *= 0.8;
	}

}
