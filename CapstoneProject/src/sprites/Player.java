package sprites;

import java.util.ArrayList;

import grid.Level;
import jay.jaysound.JayLayer;
import processing.core.PApplet;

/** This class represents the playable character
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/20/24
 */
public class Player extends Sprite {
	
	private final double speed;
	
	private Weapon weapon;
	private double xVel, yVel;
	private boolean sneaking, hasLantern;
	private int numFragments, visionRange, walkFrame;
	
	private JayLayer sound;
	private final int walk = 0, tickle = 1, jumpscare = 2, shing = 3;

	
	/** Constructs a Player
	 * @param img image to use for this Player
	 * @param level The Level of this Player
	 * @param x x-coordinate of this Player
	 * @param y y-coordinate of this Player
	 * @param w width of this Player
	 * @param h height of this Player
	 */
	public Player(String img, Level level, int x, int y, int w, int h)
	{
		super(img, level, x, y, w, (int)(h * 1.5));
		speed = Level.TILE_SIZE * 0.005;
		xVel = 0;
		yVel = 0;
		numFragments = 0;
		sneaking = false;
		hasLantern = false;
		walkFrame = 0;
		
		String[] soundEffects = new String[] {"walk.mp3", "tickle.mp3", "jumpscare.mp3", "shing.mp3"};
		sound = new JayLayer("resources/sound/", "resources/sound/", false);
		sound.addPlayList();
		sound.addSoundEffects(soundEffects);
		sound.changePlayList(0);
	}
	
	/** Constructs a Player with an automatic image
	 * @param x x-coordinate of this Player
	 * @param y y-coordinate of this Player
	 * @param w width of this Player
	 * @param h height of this Player
	 */
	public Player(Level level, int x, int y, int w, int h)
	{
		this("resources/img/player.gif", level, x, y, w, h);
	}
	
	
	/** Accelerates this Player by a specified x/y amount
	 * @param xChange The amount to accelerate x by
	 * @param yChange The amount to accelerate y by
	 */
	public void accelerate(int xChange, int yChange)
	{
		double slowFactor = sneaking ? 0.2 : 1;
		xVel += xChange * speed * slowFactor;
		yVel += yChange * speed * slowFactor;
		if (xChange != 0 || yChange != 0) {
			if (walkFrame % 30 == 10)
				sound.playSoundEffect(walk);
			walkFrame++;
		} else
			walkFrame = 0;
	}
		
	public void attack(PApplet surface, int dirX, int dirY, boolean firstAttack)
	{
		if (weapon != null) {
			weapon.slice(surface, x, y, dirX, dirY);
			if (firstAttack)
				sound.playSoundEffect(shing);
		}
	}
	
	/** Sets this Player's sneaking status
	 * @param doSneak What to set this Player's sneaking status to
	 */
	public void setSneak(boolean doSneak)
	{
		sneaking = doSneak;
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
	
	/** Gets the distance that this Player can see
	 * @return The distance that this Player can see
	 */
	public int getVisionRange()
	{
		return visionRange;
	}
	
	/** Handles Player movement and collisions
	 */
	public void act()
	{
//		Movement + movement collision handling
		x += xVel;
		for (Wall wall : level.getWalls())
			if (wall.intersects(this)) {
				x -= xVel;
				xVel = 0;
			}
		
		y -= yVel;
		for (Wall wall : level.getWalls())
			if (wall.intersects(this)) {
				y += yVel;
				yVel = 0;
			}
		
		applyWindowLimits(level);
		yVel *= 0.8;
		xVel *= 0.8;
		
		visionRange = sneaking ? 0 : 1;
		if (hasLantern)
			visionRange++;
		
//		Other sprite interaction
		ArrayList<KeyFragment> keyFragments = level.getKeyFragments();
		for (int i = 0; i < keyFragments.size(); i++)
			if (keyFragments.get(i).intersects(this)) {
				numFragments++;
				level.removeKeyFragment(i);
				i--;
			}
		
		for (Tickler tickler : level.getTicklers())
			if (tickler.intersects(this)) {
				level.setupLevel(level.getLevel());
				sound.playSoundEffect(tickle);
				sound.playSoundEffect(jumpscare);
			}
		
		Weapon levelWeapon = level.getWeapon();
		if (levelWeapon != null && levelWeapon.intersects(this)) {
			level.pickUpWeapon();
			weapon = levelWeapon;
		}
		
//		TODO add sound for picking up item
		Lantern lantern = level.getLantern();
		if (lantern != null && lantern.intersects(this)) {
			level.pickUpLantern();
			hasLantern = true;
		}
		
		Exit exit = level.getExit();
		if (exit.intersects(this))
			if (exit.isOpen())
				level.setupLevel(level.getLevel() + 1);
	}
	
}
