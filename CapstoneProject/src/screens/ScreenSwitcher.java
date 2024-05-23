package screens;

/** This class is used to switch through the different screens
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/22/24
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
	/** The death screen
	 */
	public static final int DEATH_SCREEN = 3;
	/** The win screen
	 */
	public static final int WIN_SCREEN = 4;
	/** The instructions screen
	 */
	public static final int INSTRUCTION_SCREEN = 5;

	
	/** Changes the current Screen
	 * @param i The screen to switch to
	 */
	public void switchScreen(int i);
	
}
