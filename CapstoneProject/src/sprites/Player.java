package sprites;

import java.awt.geom.Rectangle2D;
import java.util.List;

/** This class represents the playable character
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/12/24
 */
public class Player extends Sprite {
	
	private double xVel, yVel, speed;
	private boolean isSneaking;

	
	/** Constructs a Player
	 * @param img image to use for this Player
	 * @param x x-coordinate of this Player
	 * @param y y-coordinate of this Player 
	 */
	public Player(String img, int x, int y)
	{
		super(img, x, y, 40, 60);
		xVel = 0;
		yVel = 0;
		speed = 0.6;
		isSneaking = false;
	}
	
	/** Constructs a Player with the image specified
	 * @param x x-coordinate of this Player
	 * @param y y-coordinate of this Player
	 */
	public Player(int x, int y)
	{
		this("resources/img/mainchar.png", x, y);
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
		isSneaking = doSneak;
		if (isSneaking)
			speed = 0.1;
		else
			speed = 0.6;
	}

	/** Handles natural Player movement
	 * @param limits A Rectangle2D representing the limits in game
	 * @param walls Walls that this Player could collide with
	 */
	public void act(Rectangle2D.Double limits, List<Wall> walls)
	{
		moveByAmount(xVel, -yVel);
		applyWindowLimits(limits);
		yVel *= 0.8;
		xVel *= 0.8;
		
//		for(Sprite s : obstacles) {
//			if(s.intersects(this)) {
//				y -= yVel;
//				yVel = 0;
//			}
//		}
//		for(Sprite s : obstacles) {
//			if(s.intersects(this)) {
//				x -= xVel;
//				xVel = 0;
//			}
//		}
	}
	
//	/** Calling this method makes the player pick up certain objects present within the game
//	 * @param obj Object that the character picks up
//	 * 
//	 */
//	public void pickUp(Sprite obj) {
//		
//	}
	
}
