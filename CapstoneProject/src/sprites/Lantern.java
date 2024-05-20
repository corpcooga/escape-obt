package sprites;

/** This class represents a key fragment
 * @author Nikunj Govil, Boon Chew, Yashasvi Chitela
 * @version 5/20/2024
 */
public class Lantern extends Sprite {

	/** Constructs a Lantern
	 * @param img image to use for this Lantern
	 * @param x x-coordinate of this Lantern
	 * @param y y-coordinate of this Lantern
	 * @param w width of this Lantern
	 * @param h height of this Lantern
	 */
	public Lantern(String img, int x, int y, int w, int h)
	{
		super(img, x, y, w, h);
	}
	
	/** Constructs a Lantern with an automatic image
	 * @param x x-coordinate of this Lantern
	 * @param y y-coordinate of this Lantern
	 * @param w width of this Lantern
	 * @param h height of this Lantern
	 */
	public Lantern(int x, int y, int w, int h)
	{
		this("resources/img/lantern.gif", x, y, w, h);
	}

}
