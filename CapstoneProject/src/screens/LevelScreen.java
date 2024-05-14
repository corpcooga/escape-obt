package screens;

import java.awt.Point;
import java.awt.Rectangle;

import main.DrawingSurface;

/** This class represents the levels screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/13/24
 */
public class LevelScreen extends Screen {

	private DrawingSurface surface;
	private Rectangle[] levelButtons;
	

	public LevelScreen(DrawingSurface surface)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;

		levelButtons = new Rectangle[5];

		int buttonWidth = 200;
        int buttonHeight = 50;
        int buttonSpacing = 20;
        int totalButtonWidth = 3 * buttonWidth + 2 * buttonSpacing;
        int startX = (surface.width - totalButtonWidth) / 2;
        int startY = (surface.height - 2 * buttonHeight - buttonSpacing) / 2;
        
        for (int i = 0; i < 5; i++) {
            int x, y;
            if (i < 3) {
                x = startX + i * (buttonWidth + buttonSpacing);
                y = startY;
            } else {
                x = startX + (i - 3) * (buttonWidth + buttonSpacing);
                y = startY + buttonHeight + buttonSpacing;
            }
            levelButtons[i] = new Rectangle(x, y, buttonWidth, buttonHeight);
        }
	}
	

	public void draw()
	{
		surface.background(255);

		// Draw level buttons
		for (int i = 0; i < levelButtons.length; i++) {
			surface.fill(200);
			surface.rect(levelButtons[i].x, levelButtons[i].y, levelButtons[i].width, levelButtons[i].height);

			surface.fill(0);
			surface.textAlign(DrawingSurface.CENTER, DrawingSurface.CENTER);
			surface.text("Level " + (i + 1), levelButtons[i].x + levelButtons[i].width / 2,
					levelButtons[i].y + levelButtons[i].height / 2);
		}
	}
	
	public void mousePressed()
	{
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		for(int i = 0; i < levelButtons.length; i++) {
			if(levelButtons[i].contains(p)) {
				surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
			}
		}
	}

}
