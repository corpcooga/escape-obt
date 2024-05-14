package screens;

import java.awt.Rectangle;

import main.DrawingSurface;

public class GameoverScreen extends Screen {
	
	private DrawingSurface surface;
	
	public GameoverScreen(DrawingSurface surface)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;
	}

}
