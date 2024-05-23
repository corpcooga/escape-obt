package screens;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import main.DrawingSurface;
import processing.core.PImage;

/** This class represents the levels screen
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/22/24
 */
public class LevelScreen extends Screen {
	
	private DrawingSurface surface;
	private GameScreen gameScreen;
	private Rectangle[] levelButtons;
	private PImage backgroundImage;
	
	
	public LevelScreen(DrawingSurface surface, GameScreen screen)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;
		gameScreen = screen;

		levelButtons = new Rectangle[5];

		int buttonWidth = 200;
        int buttonHeight = 50;
        int buttonSpacing = 20;
        double startX = surface.width / 2;
        double startY = (surface.height + 2 * buttonHeight + buttonSpacing) / 2;
        
        for (int i = 0; i < 5; i++) {
            int x, y;
            if (i < 3) {
                x = (int)(startX + i * (buttonWidth + buttonSpacing));
                y = (int)startY;
            } else {
                x = (int)(startX + (i - 3) * (buttonWidth + buttonSpacing));
                y = (int)(startY + buttonHeight + buttonSpacing);
            }
            levelButtons[i] = new Rectangle(x, y, buttonWidth, buttonHeight);
        }
	}
	
	
	/** Sets this LevelScreen to default settings
	 */
	public void setup()
	{
		backgroundImage = surface.loadImage("resources/img/levelscreen.gif");
		backgroundImage.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
	}
	
	/** Draws this LevelScreen
	 */
	public void draw()
	{
		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			return;
		}
		
		surface.background(backgroundImage);

		// Draw level buttons
		for (int i = 0; i < levelButtons.length; i++) {
			if (i < gameScreen.getHighestLevel())
				surface.fill(255);
			else
				surface.fill(100);
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
		for (int i = 0; i < levelButtons.length; i++)
			if (levelButtons[i].contains(p) && i < gameScreen.getHighestLevel()) {
				gameScreen.setupLevel(i + 1);
				surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
			}
	}
	
}
