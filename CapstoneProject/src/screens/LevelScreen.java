package screens;

import main.DrawingSurface;

public class LevelScreen extends Screen {
	
	private DrawingSurface surface;

	public LevelScreen(DrawingSurface surface)
	{
		super(DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
		this.surface = surface;
	}

}
