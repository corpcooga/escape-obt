package screens;

import java.awt.event.KeyEvent;
import main.DrawingSurface;

/** This class represents the instructions screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/22/24
 */
public class InstructionScreen extends Screen {

	private DrawingSurface surface;
	
	
	public InstructionScreen(DrawingSurface surface)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;
	}
	

	public void draw()
	{
		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			return;
		}
		
		surface.background(255);
		surface.fill(0);
		surface.textAlign(DrawingSurface.CENTER);
		surface.textSize(15);
		surface.text("[WASD] or [ARROW KEYS] - Move\r\n"
				+ "[SHIFT] - Sneak: Move around much slower but entities will be less aware of you\r\n"
				+ "[SPACE] - Attack: Swipe your sword (if equipped) in the direction you’re facing, killing entities\r\n"
				+ "[ESC] - Exit to the main menu\r\n"
				+ "Click the PLAY button to enter the first level.\r\n"
				+ "Click the LEVELS button to enter any level you have finished already.\r\n", 400, 250);
	}
	
}
