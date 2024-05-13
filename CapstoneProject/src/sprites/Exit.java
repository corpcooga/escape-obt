package sprites;

/** This class represents the exit to a level
 * @author Nikunj Govil, Boon Chew, Yashasvi Chitela
 * @version 5/12/2024
 */
public class Exit extends Sprite {
	
	private boolean isOpen;
	

	/** Constructs a Exit
	 * @param img image to use for this Exit
	 * @param x x-coordinate of this Exit
	 * @param y y-coordinate of this Exit
	 * @param w width of this Exit
	 * @param h height of this Exit
	 */
	public Exit(String img, int x, int y, int w, int h)
	{
		super(img, x, y, w, h);
		isOpen = false;
	}
	
	/** Constructs a Exit with the image specified
	 * @param x x-coordinate of this Exit
	 * @param y y-coordinate of this Exit
	 * @param w width of this Exit
	 * @param h height of this Exit
	 */
	public Exit(int x, int y, int w, int h)
	{
		this("resources/img/doorclosed.gif", x, y, w, h);
	}
	
	
	/** Sets this door to the proper image
	 */
	public void setProperImage()
	{
		if (isOpen)
			setImage("resources/img/dooropen.gif");
		else
			setImage("resources/img/doorclosed.gif");
	}

}
