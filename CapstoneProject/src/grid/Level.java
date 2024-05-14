package grid;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import sprites.*;

/** This class represents the game's grid
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/13/24
 */
public class Level extends Rectangle2D.Double {
	
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
//		TODO maye find way to get rid of width/height parameters?
		grid = new char[width][height];
		readData("resources/levels/level1.txt", grid);
		
		x = 0;
		y = 0;
		this.width = width * 100;
		this.height = height * 100;
		
		numLevel = 1;
		walls = new ArrayList<Wall>();
		keyFragments = new ArrayList<KeyFragment>();
		ticklers = new ArrayList<Tickler>();
		readSprites();
		
		levelFragments = keyFragments.size();
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
	 * @return An ArrayList containing all KeyFragmentsin this Level
	 */
	public ArrayList<KeyFragment> getKeyFragments()
	{
		return keyFragments;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public Exit getExit()
	{
		return exit;
	}
	
	/** Removes the KeyFragment at the specified index
	 * @param idx The index of the KeyFragment to remove
	 */
	public void removeKeyFragment(int idx)
	{
		keyFragments.remove(idx);
	}
	
	public boolean allFragmentsCollected()
	{
		return player.getNumFragments() == levelFragments;
	}
	
	/** Progresses to the next level
	 */
	public void nextLevel()
	{
//		TODO find way to make grid have proper size based on level txt file
		grid = new char[100][100];
		readData("resources/levels/level" + ++numLevel + ".txt", grid);
		
		width = grid.length * 100;
		height = grid[0].length * 100;
		
		walls = new ArrayList<Wall>();
		keyFragments = new ArrayList<KeyFragment>();
		ticklers = new ArrayList<Tickler>();
		readSprites();
		
		levelFragments = keyFragments.size();
	}
	
	public void readSprites()
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
					keyFragments.add(new KeyFragment((int)sx, (int)sy, (int)sw / 2, (int)sh / 2));
				
//				Tickler
				else if (grid[i][j] == 'T')
					ticklers.add(new Tickler((int)sx, (int)sy, (int)sw / 2, (int)sh / 2));
				
//				Player
				else if (grid[i][j] == 'P')
					player = new Player((int)sx, (int)sy);
				
//				Exit
				else if (grid[i][j] == 'X')
					exit = new Exit((int)sx, (int)sy, (int)sw, (int)sh);
			}
	}
	
	/** Draws this Level
	 * @param marker The PApplet used for drawing
	 * @param visible A Rectangle2D representing the visible in-game area
	 */
	public void draw(PApplet marker, Rectangle2D.Double visible)
	{
		for (Wall wall : walls)
			if (wall.inLimits(visible))
				wall.draw(marker);
		for (KeyFragment key : keyFragments)
			if (key.inLimits(visible))
				key.draw(marker);
		for (Tickler tickler : ticklers)
			if (tickler.inLimits(visible))
				tickler.draw(marker);
		
		if (allFragmentsCollected())
			exit.setImage("resources/img/dooropen.gif");
		
		exit.draw(marker);
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
