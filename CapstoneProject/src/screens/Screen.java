package screens;

/** The container class for game screens
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/13/24
 */
public abstract class Screen {
	
	/** The original width and height of the screen
	 */
	public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	
	/** Constructs a Screen with specified dimensions
	 * @param width The width of the screen
	 * @param height The height of the screen
	 */
	public Screen(int width, int height)
	{
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
	}
	
	
	/** Sets up the Screen
	 */
	public void setup() {}
	
	/** Draws the Screen
	 */
	public void draw() {}
	
	/** Executes a mouse press
	 */
	public void mousePressed() {}
	
	/** Executes a mouse release
	 */
	public void mouseReleased() {}
	
	public void keyPressed() {}
	
}
