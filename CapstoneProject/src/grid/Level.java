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
import screens.GameScreen;
import sprites.*;

/** This class represents the game's grid
 * @author Boon Chew, Nikunj Govil, Yashasvi Chitela
 * @version 5/21/24
 */
public class Level extends Rectangle2D.Double {
	
	public static final int TILE_SIZE = 80;
	
	private GameScreen gameScreen;
	private ArrayList<Wall> walls;
	private ArrayList<KeyFragment> keyFragments;
	private ArrayList<Tickler> ticklers;
	private ArrayList<Floor> floors;
	private Player player;
	private Exit exit;
	private Weapon weapon;
	private Lantern lantern;
	
	private char[][] grid;
	private int numLevel, levelFragments;
	
	
	/** Constructs a Level of a specified number
	 * @param numLevel
	 */
	public Level(GameScreen surface, int numLevel)
	{
		setupLevel(numLevel);
		gameScreen = surface;
	}
	
	/** Construct a level 1 Level
	 */
	public Level(GameScreen surface)
	{
		this(surface, 1);
	}
	
	
	/** Sets up this Level with the specified number level
	 * @param numLevel The level number to set up
	 */
	public void setupLevel(int numLevel)
	{
		String fileName = "resources/levels/level" + numLevel + ".txt";
		Point dimensions = readDataDimensions(fileName);
		
		grid = new char[dimensions.x][dimensions.y];
		readData(fileName, grid);
		
		x = 0;
		y = 0;
		width = dimensions.x * TILE_SIZE;
		height = dimensions.y * TILE_SIZE;
		this.numLevel = numLevel;
		
		loadSprites();
		
		levelFragments = keyFragments.size();
	}
	
	/** Gets a Sprite's coordinates in this Level using their literal coordinates
	 * @param sprite the Sprite to get the Level coordinates of
	 * @return a Point object containing array coordinates of a Sprite
	 */
	public Point getSpriteArrayCoordinates(Sprite sprite)
	{
		Point2D.Double spriteCenter = sprite.getCenter();
		int realX = (int)((spriteCenter.getX() + x) / TILE_SIZE);
		int realY = (int)((spriteCenter.getY() + y) / TILE_SIZE);
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
	
	/** Determines if a Sprite can be seen in a given range
	 * @param Sprite The Sprite to test if it can be seen
	 * @return true if Sprite can be seen, false otherwise
	 */
	public boolean canBeSeen(Sprite sprite)
	{
		if (getSpriteDistance(sprite, player) <= player.getVisionRange())
			return true;
		if (lantern != null && getSpriteDistance(sprite, lantern) <= 1)
			return true;
		return false;
	}
	
	/** Draws this Level and handles Sprite behavior
	 * @param marker The PApplet used for drawing
	 */
	public void draw(PApplet marker)
	{
		player.act();
		
		for (Floor floor : floors)
			if (canBeSeen(floor))
				floor.draw(marker);
		if (weapon != null && canBeSeen(weapon))
			weapon.draw(marker);
		if (lantern != null && canBeSeen(lantern))
			lantern.draw(marker);
		if (canBeSeen(exit))
			exit.draw(marker);
		for (Wall wall : walls)
			if (canBeSeen(wall))
				wall.draw(marker);
		for (KeyFragment key : keyFragments)
			if (canBeSeen(key))
				key.draw(marker);
		for (Tickler tickler : ticklers) {
			tickler.act();
			if (canBeSeen(tickler))
				tickler.draw(marker);
		}
		player.draw(marker);
	}
	
	public void die()
	{
		gameScreen.switchToDeathScreen();
	}
	
	/** Removes the KeyFragment at the specified index, then updates exit status
	 * @param idx The index of the KeyFragment to remove
	 * @return true if the indicated KeyFragment is the last one in the level, false otherwise
	 */
	public boolean pickUpKeyFragment(int idx)
	{
		keyFragments.remove(idx);
		if (player.getNumFragments() == levelFragments) {
			exit.open();
			return true;
		}
		return false;
	}
	
	/** Removes the Tickler at the specified index
	 * @param idx The index of the Tickler to remove
	 */
	public void killTickler(int idx)
	{
		ticklers.remove(idx);
	}
	
	/** Sets this Level's weapon to null
	 */
	public void pickUpWeapon()
	{
		weapon = null;
	}
	
	/** Sets this Level's lantern to null
	 */
	public void pickUpLantern()
	{
		lantern = null;
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
	
	/** Gets the Weapon in this Level
	 * @return A Weapon object that represents this Level's Weapon
	 */
	public Weapon getWeapon()
	{
		return weapon;
	}
	
	/** Gets the Lantern in this Level
	 * @return A Lantern object that represents this Level's Lantern
	 */
	public Lantern getLantern()
	{
		return lantern;
	}
	
	/** Gets the level number of this Level
	 * @return The level number of this Level
	 */
	public int getLevel()
	{
		return numLevel;
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
	
	/** Loads all Sprites from the grid to the corresponding ArrayList
	 */
	private void loadSprites()
	{
		walls = new ArrayList<Wall>();
		keyFragments = new ArrayList<KeyFragment>();
		ticklers = new ArrayList<Tickler>();
		floors = new ArrayList<Floor>();
		
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
				
				else {
//					Floor
					floors.add(new Floor((int)sx, (int)sy, (int)sw, (int)sh));
					
//					Key Fragment
					if (grid[i][j] == '*')
						keyFragments.add(new KeyFragment((int)(sx + sw / 4), (int)(sy + sh / 4), 
														(int)sw / 2, (int)sh / 2));
					
//					Tickler
					else if (grid[i][j] == 'T')
						ticklers.add(new Tickler(this, (int)(sx + sw / 4), (int)(sy + sh / 4), 
												(int)sw / 2, (int)sh / 2));
					
//					Player
					else if (grid[i][j] == 'P')
						player = new Player(this, (int)(sx + sw * 0.3), (int)(sy + sh * 0.3), 
											(int)(sw * 0.4), (int)(sh * 0.4));
					
//					Exit
					else if (grid[i][j] == 'X')
						exit = new Exit((int)sx, (int)sy, (int)sw, (int)sh);
					
//					Weapon
					else if (grid[i][j] == 'W')
						weapon = new Weapon(this, (int)(sx + sw * 0.2), (int)(sy + sh * 0.2), 
											(int)(sw * 0.6), (int)(sh * 0.6));
					
//					Lantern
					else if (grid[i][j] == 'L')
						lantern = new Lantern((int)(sx + sw * 0.2), (int)(sy + sh * 0.2), 
											(int)(sw * 0.6), (int)(sh * 0.6));
				}
			}
	}
	
	/** Reads data from a text file and loads it into an array
	 * @param filename The text file to read from
	 * @param gameData The array to load the information into
	 */
	private void readData(String filename, char[][] gameData)
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
	
	/** Gets the dimensions of a data file
	 * @param filename The text file to read from
	 * @return A Point object containing the bottom right corner of the file
	 */
	private Point readDataDimensions(String filename)
	{
		File dataFile = new File(filename);
		
		if (dataFile.exists()) {
			int rows = 0, cols = 0;
			Scanner in = null;
			
			try {
				in = new Scanner(new FileReader(dataFile));
				String lastLine = "";
				
				while (in.hasNext()) {
					lastLine = in.nextLine();
					rows++;
				}
				cols = lastLine.length();
				return new Point(rows, cols);

			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}
		} else
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
	}
	
}
