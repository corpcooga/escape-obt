package sprites;

import java.util.List;
import processing.core.PImage;

public class Mario extends Sprite {

	public static final int MARIO_WIDTH = 40;
	public static final int MARIO_HEIGHT = 60;

	private double xVel, yVel;

	
	public Mario(PImage img, int x, int y)
	{
		super(img, x, y, MARIO_WIDTH, MARIO_HEIGHT);
		xVel = 0;
		yVel = 0;
	}
	
	
	/** 
	 * Makes mario walk left or right across the window
	 * @param dir -1 for left, 1 for right
	 * 
	 * Lead coder: Boon Chew
	*/
	public void walk(int dir)
	{
		xVel += dir;
	}

	/** 
	 * Makes mario jump up
	 * 
	 * Lead coder: Nikunj Govil
	 */
	public void jump()
	{
		yVel = -10;
	}

	/** 
	 * Makes mario do everything that he should do without any keys being pressed
	 * (such as fall to the ground)
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
