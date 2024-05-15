package grid;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import sprites.*;

/** This class represents the game's grid
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/15/24
 */
public class Level extends Rectangle2D.Double {
	
//	TODO make other Sprite speed based on tileSize
	private static final int tileSize = 100;
	
	private ArrayList<Wall> walls;
	private ArrayList<KeyFragment> keyFragments;
	private ArrayList<Tickler> ticklers;
	private Player player;
	private Exit exit;
	
	private char[][] grid;
	private int numLevel, levelFragments;
	
	
	/** Constructs a Level based on a text file
	 * @param width The width of the grid
	 * @param height The height of the grid
	 */
	public Level(int width, int height)
	{
//		TODO maybe find way to get rid of width/height parameters?
		grid = new char[width][height];
		readData("resources/levels/level1.txt", grid);
		
		x = 0;
		y = 0;
		this.width = width * tileSize;
		this.height = height * tileSize;
		
		setupSprites();
		numLevel = 1;
	}
	
	/** Construct an empty 2D array with some default dimensions
	 */
	public Level()
	{
		this(21, 21);
	}
	
	
	/** Gets all Walls in this Level
	 * @return An ArrayList containing all Walls in this Level
	 */
	public ArrayList<Wall> getWalls()
	{
		return walls;
	}
	
	/** Gets all KeyFragments in this Level
	 * @return An ArrayList containing all KeyFragments in this Level
	 */
	public ArrayList<KeyFragment> getKeyFragments()
	{
		return keyFragments;
	}
	
	/** Gets all Ticklers in this Level
	 * @return An ArrayList containing all Ticklers in this Level
	 */
	public ArrayList<Tickler> getTicklers()
	{
		return ticklers;
	}
	
	/** Gets the Player in this Level
	 * @return A Player object that represents this Level's Player
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/** Gets the Exit in this Level
	 * @return An Exit object that represents this Level's Exit
	 */
	public Exit getExit()
	{
		return exit;
	}
	
	/** Gets a Sprite's coordinates in this Level using their literal coordinates
	 * @param sprite the Sprite to get the Level coordinates of
	 * @return a Point object containing array coordinates of a Sprite
	 */
	public Point getSpriteArrayCoordinates(Sprite sprite)
	{
		Point2D.Double spriteCenter = sprite.getCenter();
		int realX = (int)(spriteCenter.getX() / tileSize);
		int realY = (int)(spriteCenter.getY() / tileSize);
		return new Point(realX, realY);
	}
	
	/** Gets the distance between two Sprites in this Level
	 * @param s1 The first Sprite used to calculate the distance
	 * @param s2 The second Sprite used to calculate the distance
	 * @return The maximum of the x-distance and y-distance between the Sprites
	 */
	public int getSpriteDistance(Sprite s1, Sprite s2)
	{
		Point coord1 = getSpriteArrayCoordinates(s1);
		Point coord2 = getSpriteArrayCoordinates(s2);
		int xDist = Math.abs(coord1.x - coord2.x);
		int yDist = Math.abs(coord1.y - coord2.y);
		return Math.max(xDist, yDist);
	}
	
	/** Removes the KeyFragment at the specified index
	 * @param idx The index of the KeyFragment to remove
	 */
	public void removeKeyFragment(int idx)
	{
		keyFragments.remove(idx);
	}
	
	/** Checks if all KeyFragments in this level have been collected 
	 * @return true if all KeyFragments have been collected, false otherwise
	 */
	public boolean allFragmentsCollected()
	{
		return player.getNumFragments() == levelFragments;
	}
	
	/** Changes the level and sets it up
	 * @param levelChange The amount to change the current level by
	 */
	public void changeLevel(int levelChange)
	{
//		TODO find way to make grid have proper size based on level txt file
		grid = new char[100][100];
		numLevel += levelChange;
		readData("resources/levels/level" + numLevel + ".txt", grid);
		
		width = grid.length * tileSize;
		height = grid[0].length * tileSize;
		
		setupSprites();
	}
	
	private void setupSprites()
	{
		walls = new ArrayList<Wall>();
		keyFragments = new ArrayList<KeyFragment>();
		ticklers = new ArrayList<Tickler>();
		readSprites();
		
		levelFragments = keyFragments.size();
	}
	
	/** Reads through grids and adds all Sprites to the corresponding ArrayList
	 */
	private void readSprites()
	{
//		Sprite width and heights
		float sw = (float)width / grid[0].length;
		float sh = (float)height / grid.length;
		
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
			{
//				Sprite x and y's
				float sx = (float)x + sw * j;
				float sy = (float)y + sh * i;
				
//				Wall
				if (grid[i][j] == '#')
					walls.add(new Wall((int)sx, (int)sy, (int)sw, (int)sh));
				
//				Key Fragment
				else if (grid[i][j] == '*')
					keyFragments.add(new KeyFragment(
							(int)sx + (int)sw / 4, (int)sy + (int)sh / 4, (int)sw / 2, (int)sh / 2));
				
//				Tickler
				else if (grid[i][j] == 'T')
					ticklers.add(new Tickler(
							(int)sx + (int)sw / 4, (int)sy + (int)sh / 4, (int)sw / 2, (int)sh / 2));
				
//				Player
				else if (grid[i][j] == 'P')
					player = new Player(
							(int)sx + (int)(sw * 0.3), (int)sy + (int)(sh * 0.3), 
							(int)(sw * 0.4), (int)(sh * 0.4));
				
//				Exit
				else if (grid[i][j] == 'X')
					exit = new Exit((int)sx, (int)sy, (int)sw, (int)sh);
			}
	}
	
	/** Draws this Level and handles Sprite behavior
	 * @param marker The PApplet used for drawing
	 * @param visible A Rectangle2D representing the visible in-game area
	 */
	public void draw(PApplet marker, Rectangle2D.Double visible)
	{
		player.act(this);
		
		if (allFragmentsCollected())
			exit.setImage("resources/img/dooropen.gif");
		exit.draw(marker);
		
		for (Wall wall : walls)
			if (wall.inLimits(visible))
				wall.draw(marker);
		for (KeyFragment key : keyFragments)
			if (key.inLimits(visible))
				key.draw(marker);
		for (Tickler tickler : ticklers)
			if (tickler.inLimits(visible)) {
				tickler.act(this);
				tickler.draw(marker);
			}
		
		player.draw(marker);
	}
	
	/** Reads data from a text file and loads it into an array
	 * @param filename The text file to read from
	 * @param gameData The array to load the information into
	 */
	public void readData(String filename, char[][] gameData)
	{
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			FileReader reader = null;
			Scanner in = null;
			int count = 0;
			
			try {
					reader = new FileReader(dataFile);
					in = new Scanner(reader);
					
					while (in.hasNext()) {
						String line = in.nextLine();
						for(int i = 0; i < line.length(); i++)
							if (count < gameData.length && i < gameData[count].length)
								gameData[count][i] = line.charAt(i);
						count++;
					}

			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}
		} else
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
	}
	
	/**	Prints out a formatted version of this Level
	 */
	public String toString()
	{
		StringBuffer out = new StringBuffer("");
		for (char[] row : grid) {
			for (char c : row)
				out.append(c);
			out.append("\n");
		}
		return out.toString();
	}
	
}
