package sprites;

import grid.Level;
import jay.jaysound.JayLayer;

/** This class represents an enemy entity
 * @author Nikunj Govil, Yashasvi Chitela, Boon Chew
 * @version 5/20/24
 */
public class Tickler extends Sprite {
	
	private final double speed;
	private final int aggroRange;
	
	private JayLayer sound;
	private final int nearby = 0;

	/** Constructs a Tickler
	 * @param img image to use for this Tickler
	 * @param level The Level of this Tickler
	 * @param x x-coordinate of this Tickler
	 * @param y y-coordinate of this Tickler
	 * @param w width of this Tickler
	 * @param h height of this Tickler
	 */
	public Tickler(String img, Level level, int x, int y, int w, int h)
	{
		super(img, level, x, y, w, h);
		speed = Level.TILE_SIZE * 0.005 * (1 + level.getLevel() * 0.2);
		aggroRange = 2;
		
		String[] soundEffects = new String[]{"ticklerclose.mp3"};
		sound=new JayLayer("resources/sound/","resources/sound/",false);
		sound.addPlayList();
		sound.addSoundEffects(soundEffects);
		sound.changePlayList(0);
	}
	
	/** Constructs a Tickler with an automatic image
	 * @param level The Level of this Player
	 * @param x x-coordinate of this Tickler
	 * @param y y-coordinate of this Tickler
	 * @param w width of this Tickler
	 * @param h height of this Tickler
	 */
	public Tickler(Level level, int x, int y, int w, int h)
	{
		this("resources/img/tickler.gif", level, x, y, w, h);
	}
	
	
	/** Handles Tickler movement
	 */
	public void act()
	{
		Player player = level.getPlayer();
		
		int dirX, dirY;
		double moveX, moveY;
		if (isInRange()) {
			dirX = player.x - x > 0 ? 1 : -1;
			dirY = player.y - y > 0 ? 1 : -1;
			moveX = dirX * speed;
			moveY = dirY * speed;
			sound.playSoundEffect(nearby);
		} else {
			dirX = (int)(Math.random() * 3) - 1;
			dirY = (int)(Math.random() * 3) - 1;
			moveX = dirX * speed * 3;
			moveY = dirY * speed * 3;
		}
		
		x += moveX;
		for (Wall wall : level.getWalls())
			if (wall.intersects(this))
				x -= moveX;
		for (Tickler tickler : level.getTicklers())
			if (tickler != this && tickler.intersects(this))
				x -= moveX;
		
		y += moveY;
		for (Wall wall : level.getWalls())
			if (wall.intersects(this))
				y -= moveY;
		for (Tickler tickler : level.getTicklers())
			if (tickler != this && tickler.intersects(this))
				y -= moveY;
	}
	
	/** Determines if this Tickler is able to see the Player
	 * @return true if this Tickler is in range of the Player, false otherwise
	 */
	public boolean isInRange()
	{
		Player player = level.getPlayer();
		int distance = level.getSpriteDistance(this, player);
		int range = !player.isSneaking() ? aggroRange : 0;
		return distance <= range;
	}

}
