package sprites;

/** This class represents the floor that is walked on
 * @author Nikunj Govil, Boon Chew, Yashasvi Chitela
 * @version 5/21/2024
 */
public class Floor extends Sprite {

	/** Constructs a Floor
	 * @param img image to use for this Floor
	 * @param x x-coordinate of this Floor
	 * @param y y-coordinate of this Floor
	 * @param w width of this Floor
	 * @param h height of this Floor
	 */
	public Floor(String img, int x, int y, int w, int h)
	{
		super(img, x, y, w, h);
	}
	
	/** Constructs a Floor with an automatic image
	 * @param x x-coordinate of this Floor
	 * @param y y-coordinate of this Floor
	 * @param w width of this Floor
	 * @param h height of this Floor
	 */
	public Floor(int x, int y, int w, int h)
	{
		this("resources/img/floor.gif", x, y, w, h);
	}

}
