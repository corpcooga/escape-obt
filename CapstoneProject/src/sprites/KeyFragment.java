package sprites;

/** This class represents a key fragment
 * @author Nikunj Govil, Boon Chew, Yashasvi Chitela
 * @version 5/13/2024
 */
public class KeyFragment extends Sprite {

	/** Constructs a KeyFragment
	 * @param img image to use for this KeyFragment
	 * @param x x-coordinate of this KeyFragment
	 * @param y y-coordinate of this KeyFragment
	 * @param w width of this KeyFragment
	 * @param h height of this KeyFragment
	 */
	public KeyFragment(String img, int x, int y, int w, int h)
	{
		super(img, x, y, w, h);
	}
	
	/** Constructs a KeyFragment with the image specified
	 * @param x x-coordinate of this KeyFragment
	 * @param y y-coordinate of this KeyFragment
	 * @param w width of this KeyFragment
	 * @param h height of this KeyFragment
	 */
	public KeyFragment(int x, int y, int w, int h)
	{
		this("resources/img/keyfragment.gif", x, y, w, h);
	}

}
