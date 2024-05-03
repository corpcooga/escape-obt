package screens;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import main.DrawingSurface;
import sprites.Mario;
import sprites.Sprite;


public class SecondScreen extends Screen {
	
	private List<Sprite> obstacles;
	
	private DrawingSurface surface;
	private Rectangle screenRect;
	private Mario mario;
	

	public SecondScreen(DrawingSurface surface)
	{
		super(800,600);
		this.surface = surface;
		
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		
		obstacles = new ArrayList<Sprite>();
		obstacles.add(new Sprite(0,250,100,50));
		obstacles.add(new Sprite(700,250,100,50));
		obstacles.add(new Sprite(200,400,400,50));
		obstacles.add(new Sprite(375,300,50,100));
		obstacles.add(new Sprite(300,250,200,50));
	}
	

	public void spawnNewMario()
	{
		mario = new Mario(surface.loadImage("resources/img/mainchar.png"), 
				DRAWING_WIDTH / 2 - Mario.MARIO_WIDTH / 2, 50);
	}
	
	public void setup()
	{
		spawnNewMario();
	}
	
	public void draw()
	{
		surface.background(0, 255, 255);   

		for (Sprite s : obstacles)
			s.draw(surface);

		mario.draw(surface);
		

		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			return;
		}
		if (surface.isPressed(KeyEvent.VK_LEFT))
			mario.walk(-1);
		if (surface.isPressed(KeyEvent.VK_RIGHT))
			mario.walk(1);
		if (surface.isPressed(KeyEvent.VK_UP))
			mario.jump();

		mario.act(obstacles);

		if (!screenRect.intersects(mario))
			spawnNewMario();
	}

}
