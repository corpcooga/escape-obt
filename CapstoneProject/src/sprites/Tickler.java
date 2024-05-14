package sprites;

import grid.Level;

/** This class represents an enemy entity
 * @author Nikunj Govil, Yashasvi Chitela, Boon Chew
 * @version 5/13/24
 */
public class Tickler extends Sprite {
	
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
		speed = 0.2;
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
		if (isInRange(player)) {
			int dirX = player.x - x > 0 ? 1 : -1;
			int dirY = player.y - y > 0 ? 1 : -1;
			double moveX = dirX * speed;
			double moveY = dirY * speed;
			
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
		} else {
//			TODO Random movement
		}
	}
	
	/** Determines if this Tickler is able to see the Player
	 * @param player The Player to check if this Tickler is in range of
	 * @return true if this Tickler is in range of the Player, false otherwise
	 */
	public boolean isInRange(Player player)
	{
//		TODO find a more solid distance to be seen in
		return Math.sqrt(Math.pow(player.x - x, 2) + Math.pow(player.y - y, 2)) < 300;
	}

}
