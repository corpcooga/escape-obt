package sprites;

import java.util.List;
import processing.core.PImage;

/** This class represents the playable character
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/5/24
 */
public class Player extends Sprite {

	/** Width of this the Player character
	 */
	public static final int PLAYER_WIDTH = 40;
	/** Height of this the Player character
	 */
	public static final int PLAYER_HEIGHT = 60;

	private double xVel, yVel;

	
	/** Constructs a Player
	 * @param img image to use for this Player
	 * @param x x-coordinate of this Player
	 * @param y y-coordinate of this Player 
	 */
	public Player(PImage img, int x, int y)
	{
		super(img, x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
		xVel = 0;
		yVel = 0;
	}
	
	/** Accelerates this Player by a specified x/y amount
	 * @param xChange The amount to accelerate x by
	 * @param yChange The amount to accelerate y by
	 */
	public void move(int xChange, int yChange)
	{
		xVel += xChange;
		yVel += yChange;
	}

	/** Moves the Player naturally
	 * @param obstacles Other sprites that the Player could collide with
	 */
	public void act(List<Sprite> obstacles)
	{
		moveByAmount(xVel, -yVel);
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
	
	public void pickUp(Sprite obj) {
		
	}

}
