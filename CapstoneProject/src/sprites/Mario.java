package sprites;

import java.util.List;
import processing.core.PImage;

/** This class represents the playable character
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/5/24
 */
public class Mario extends Sprite {

	/** Width of this Mario character
	 */
	public static final int MARIO_WIDTH = 40;
	/** Height of this Mario character
	 */
	public static final int MARIO_HEIGHT = 60;

	private double xVel, yVel;

	
	/** Constructor for Mario
	 * @param img image to use for Mario
	 * @param x X coordinate of Mario
	 * @param y Y coordinate of Mario 
	 */
	public Mario(PImage img, int x, int y)
	{
		super(img, x, y, MARIO_WIDTH, MARIO_HEIGHT);
		xVel = 0;
		yVel = 0;
	}
	
	
	/** Makes mario walk left or right across the window
	 * @param dir -1 for left, 1 for right
	 * 
	 * Lead coder: Boon Chew
	*/
	public void walk(int dir)
	{
		xVel += dir;
	}

	/** Makes Mario jump up
	 * 
	 * Lead coder: Nikunj Govil
	 */
	public void jump()
	{
		yVel = -10;
	}

	/** Makes mario do everything that he should do without any keys being pressed
	 * @param obstacles Other sprites that mario could collide with
	 * 
	 * Lead coder: Yashasvi Chitela
	 */
	public void act(List<Sprite> obstacles)
	{
		y += yVel;
		
		for(Sprite s : obstacles) {
			if(s.intersects(this)) {
				y -= yVel;
				yVel = 0;
			}
		}
		
		x += xVel;
		
		for(Sprite s : obstacles) {
			if(s.intersects(this)) {
				x -= xVel;
				xVel = 0;
			}
		}
		
		yVel += 0.5;
		xVel *= 0.9;
	}

}
