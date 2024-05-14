package screens;

import java.awt.Rectangle;

import main.DrawingSurface;

public class DeathScreen extends Screen {
	
	private DrawingSurface surface;
	private Rectangle restartButton;
	
	public DeathScreen(DrawingSurface surface)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;
		
	}

}
