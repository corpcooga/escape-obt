package sprites;

import grid.Level;

/** This class represents an enemy entity
 * @author Nikunj Govil, Yashasvi Chitela, Boon Chew
 * @version 5/13/24
 */
public class Tickler extends Sprite {
	
	private int aggroRange;
	private double speed;
	

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
		aggroRange = 3;
		speed = 0.4;
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
	 * @param level Represents the level that this Tickler is in
	 */
	public void act(Level level)
	{
		Player player = level.getPlayer();
		int dirX, dirY;
		double moveX, moveY;
		
		if (isInRange(level)) {
			dirX = player.x - x > 0 ? 1 : -1;
			dirY = player.y - y > 0 ? 1 : -1;
			moveX = dirX * speed;
			moveY = dirY * speed;
		} else {
			dirX = (int)(Math.random() * 3) - 1;
			dirY = (int)(Math.random() * 3) - 1;
			moveX = dirX * speed * 3;
			moveY = dirY * speed * 3;
		}
		
		x += moveX;
		for (Wall w : level.getWalls())
			if (w.intersects(this))
				x -= moveX;
		for (Tickler t : level.getTicklers())
			if (t != this && t.intersects(this))
				x -= moveX;
		
		y += moveY;
		for (Wall w : level.getWalls())
			if (w.intersects(this))
				y -= moveY;
		for (Tickler t : level.getTicklers())
			if (t != this && t.intersects(this))
				y -= moveY;
	}
	
	/** Determines if this Tickler is able to see the Player
	 * @param level The level that this Tickler is in
	 * @return true if this Tickler is in range of the Player, false otherwise
	 */
	public boolean isInRange(Level level)
	{
		Player player = level.getPlayer();
		int distance = level.getSpriteDistance(this, player);
		int range = !player.isSneaking() ? aggroRange : aggroRange / 2;
		return distance <= range;
	}

}
