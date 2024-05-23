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
		surface.strokeWeight(5);
		surface.textSize(80);
		surface.textAlign(DrawingSurface.CENTER, DrawingSurface.CENTER);
		surface.fill(0);
		surface.text("Instructions", DRAWING_WIDTH / 2, 50);
		
		surface.textSize(18);
		surface.text("[WASD] or [ARROW KEYS] - Move the character\n"
				+ "[SHIFT] - Sneak: Move slower, ticklers will be less aware of you\n"
				+ "[SPACE] - Attack: Swipe your sword (if equipped) in the direction you’re facing, killing ticklers\n"
				+ "[ESC] - Exit to the main menu", 
				DRAWING_WIDTH / 2, DRAWING_HEIGHT / 2);
	}
	
}
