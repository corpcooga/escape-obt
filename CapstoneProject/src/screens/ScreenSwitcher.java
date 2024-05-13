package screens;

/** This class is used to switch through the different screens
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/13/24
 */
public interface ScreenSwitcher
{
	/** The main menu screen
	 */
	public static final int MENU_SCREEN = 0;
	/** The gameplay screen
	 */
	public static final int GAME_SCREEN = 1;
	/** The level selection screen
	 */
	public static final int LEVEL_SCREEN = 2;
	
	
	/** Changes the current Screen
	 * @param i The screen to switch to
	 */
	public void switchScreen(int i);
	
}
