package sprites;

import java.util.ArrayList;

import grid.Level;

/** This class represents the playable character
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/15/24
 */
public class Player extends Sprite {
	
	private double xVel, yVel, speed;
	private boolean sneaking;
	private int numFragments;

	
	/** Constructs a Player
	 * @param img image to use for this Player
	 * @param x x-coordinate of this Player
	 * @param y y-coordinate of this Player
	 * @param w width of this Player
	 * @param h height of this Player
	 */
	public Player(String img, int x, int y, int w, int h)
	{
		super(img, x, y, w, (int)(h * 1.5));
		speed = 0.6;
		xVel = 0;
		yVel = 0;
		numFragments = 0;
		sneaking = false;
	}
	
	/** Constructs a Player with the image specified
	 * @param x x-coordinate of this Player
	 * @param y y-coordinate of this Player
	 * @param w width of this Player
	 * @param h height of this Player
	 */
	public Player(int x, int y, int w, int h)
	{
		this("resources/img/player.gif", x, y, w, h);
	}
	
	
	/** Accelerates this Player by a specified x/y amount
	 * @param xChange The amount to accelerate x by
	 * @param yChange The amount to accelerate y by
	 */
	public void accelerate(int xChange, int yChange)
	{
		xVel += xChange * speed;
		yVel += yChange * speed;
	}
	
	/** Makes this Player move slower, decreases range of vision, and makes it harder for entities 
	 * to see this Player
	 * @param doSneak Determines whether this Player should sneak or not
	 */
	public void sneak(boolean doSneak)
	{
//		TODO add all necessary features to sneaking
		sneaking = doSneak;
		if (sneaking)
			speed = 0.1;
		else
			speed = 0.6;
	}
	
	/** Checks if this Player is sneaking
	 * @return true if this Player is sneaking, false otherwise
	 */
	public boolean isSneaking()
	{
		return sneaking;
	}
	
	/** Gets the number of KeyFragments this Player has collected
	 * @return The number of KeyFragments this Player has collected
	 */
	public int getNumFragments()
	{
		return numFragments;
	}
	
	/** Handles Player movement and collisions
	 * @param level Represents the level that this Player is in
	 */
	public void act(Level level)
	{
//		Movement + movement collision handling
		x += xVel;
		for (Wall w : level.getWalls())
			if (w.intersects(this)) {
				x -= xVel;
				xVel = 0;
			}
		
		y -= yVel;
		for (Wall w : level.getWalls())
			if (w.intersects(this)) {
				y += yVel;
				yVel = 0;
			}
		
		applyWindowLimits(level);
		yVel *= 0.8;
		xVel *= 0.8;
		
//		Other sprite interaction
		ArrayList<KeyFragment> keyFragments = level.getKeyFragments();
		for (int i = 0; i < keyFragments.size(); i++)
			if (keyFragments.get(i).intersects(this)) {
				numFragments++;
				level.removeKeyFragment(i);
				i--;
			}
		
		for (Tickler t : level.getTicklers())
			if (t.intersects(this))
				level.changeLevel(0);
		
		if (level.getExit().intersects(this))
			if (level.allFragmentsCollected())
				level.changeLevel(1);
	}
	
}
