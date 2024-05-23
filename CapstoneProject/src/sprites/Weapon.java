package sprites;

import java.util.ArrayList;
import processing.core.PApplet;
import grid.Level;
import jay.jaysound.JayLayer;

/** This class represents a weapon
 * @author Nikunj Govil, Boon Chew, Yashasvi Chitela
 * @version 5/23/2024
 */
public class Weapon extends Sprite {
	
	private JayLayer sound;
	
	
	/** Constructs a Weapon
	 * @param img image to use for this Weapon
	 * @param level The Level of this Weapon
	 * @param x x-coordinate of this Weapon
	 * @param y y-coordinate of this Weapon
	 * @param w width of this Weapon
	 * @param h height of this Weapon
	 */
	public Weapon(String img, Level level, int x, int y, int w, int h)
	{
		super(img, level, x, y, w, h);
		
		String[] soundEffects = new String[] {"ticklerdie.mp3"};
		sound = new JayLayer("resources/sound/", "resources/sound/", false);
		sound.addPlayList();
		sound.addSoundEffects(soundEffects);
		sound.changePlayList(0);
	}
	
	/** Constructs a Weapon with an automatic image
	 * @param level The Level of this weapon
	 * @param x x-coordinate of this Weapon
	 * @param y y-coordinate of this Weapon
	 * @param w width of this Weapon
	 * @param h height of this Weapon
	 */
	public Weapon(Level level, int x, int y, int w, int h)
	{
		this("resources/img/sword.gif", level, x, y, w, h);
	}
	
	
	/** Executes an attack with this Weapon
	 * @param surface The PApplet to draw on
	 * @param x The x-coordinate to use for this Weapon
	 * @param y The y-coordinate to use for this Weapon
	 * @param dirX The direction pointed for x (-1, 0, 1)
	 * @param dirY The direction pointed for y (-1, 0, 1)
	 */
	public void slice(PApplet surface, double x, double y, int dirX, int dirY)
	{
		int direction, multiplier = dirX > 0 ? 1 : -1;
		if (dirX == 0)
			direction = dirY < 0 ? 180 : 0;
		else {
			if (dirY > 0)
				direction = 45 * multiplier;
			else if (dirY < 0)
				direction = 135 * multiplier;
			else
				direction = 90 * multiplier;
		}
		direction += 45;
		
		this.x = x + width * 0.4 + 1.5 * dirX * width;
		this.y = y + height * 0.4 - 1.5 * dirY * height;
		ArrayList<Tickler> ticklers = level.getTicklers();
		for (int i = 0; i < ticklers.size(); i++)
			if (ticklers.get(i).intersects(this)) {
				level.killTickler(i);
				i--;
				sound.playSoundEffect(0);
			}
		
		surface.translate((float)this.x, (float)this.y);
		surface.rotate((float)Math.toRadians(direction));
		surface.translate((float)-this.x, (float)-this.y);
		draw(surface);
	}

}
