Escape O.B.T.
Authors: Nikunj Govil, Boon Chew, Yashashvi Chitela
Revision: 5/5/24


Introduction: 
[In a few paragraphs totaling about ½ page, introduce the high-level concept of your program. What this looks like depends a lot on what type of thing you are making. An introduction for an application will look different than one for a game. In general, your introduction should address questions like these:
What does your program do?
What problem does it solve? Why did you write it?
What is the story?
What are the rules? What is the goal?
Who would want to use your program?
What are the primary features of your program?]


On November 9, 1983, The Ticklers invaded Ohio and destroyed all signs of human civilization… except for the player. Now they’re stuck in Ohio and need to find a way out so that they can live their life in tickle-less bliss.


The player moves around in a scrolling, grid-based maze on the map of Ohio. They have limited sight, and need to find their way to the exit. There are multiple levels to go through. In the maze, there are ticklers that the player needs to evade. After every maze, the player is closer to escaping Ohio and the mazes get harder.


The player needs to avoid monstrous entities while trying to escape the state of Ohio. To escape, they need to navigate their way through the maze to all of the key fragments. With all of the necessary key fragments, they can then navigate to the exit to a part of Ohio, and finish that level. They must do this many times throughout a few levels to completely escape the entire state.


Most people these days struggle, sitting in their homes all day, losing their survival instincts. This game heightens their senses and keeps them ready to escape Ohio when needed. This project was created because we wanted to help people improve their survival skills, as people (in movies at least) are really bad at making decisions that impact their survival positively.


People in need of a dopamine boost or just want to have fun would want to use our program.


Instructions:
[Which keyboard keys will do what? 
Where will you need to click? 
Will you have menus that need to be navigated? What will they look like? 
Do actions need to be taken in a certain order?]
[WASD] - Move
[SHIFT] - Sneak: Move around much slower but entities will be less aware of you
[ESC] - Exit to the main menu
Click the START button to enter the first level.
Click the LEVELS button to enter any level you have finished already.
Click the INSTRUCTIONS button for the instructions.
There is one menu to view all the levels you have done and click on the subsequent level you want to play.


Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
* The player moves through a scrolling grid-based maze and can move smoothly between tiles in the grid (rather than moving from tile to tile). They can see all of the tiles in an area around their character.
* Key fragments are hidden throughout the maze. The player needs to navigate through the maze and collect all of them. Once they have all the necessary key fragments, they can find the exit tile and move on to the next level.
* Entities cause your character to die immediately upon contact. These entities move slowly and randomly without any regard for the player until they are a few tiles away from them. Then, the entities speed up slightly and move directly towards the player. They cannot be eliminated.
* The player can sneak, which makes them move much slower. While sneaking, entities can’t see the player until they are directly next to each other. The player’s area of vision decreases while sneaking, and can only sneak for a few seconds before getting up again. There is a small cooldown between each time the player sneaks.
* The levels are sourced from a text file, which is then processed to generate the level. Different characters in the text file relate to different items in-game, such as walls, the player, and enemy entities.


Want-to-have Features:
* The player has three hearts and loses one upon contact with monsters. Once fully depleted, the game ends, and the player has to restart the level. If low on hearts, health kit items are hidden throughout the maze that can be used to regenerate hearts.
* A lantern item is located somewhere near the start of the first level, which allows the player to see a bigger area around themselves. The lantern can be equipped simply by walking over it.
* The player can find weapons to attack the entities and kill them instead of evading or outrunning them. These weapons shoot out projectiles that are aimed in the direction the player is facing, and deal damage to the entities.
* As the player progresses through the levels, the entities get faster. They start off moving about half as fast as the player and eventually go just slightly slower than the player. The entities also get stronger by taking longer to kill.
* The game has background music. As monsters get closer to the player, eerie ambient sounds start playing in the background to alert the player. Sound effects also play for most actions in the game, such as picking up items, shooting the monster, dying, getting hit, walking, and more. While sneaking, sound effects are enhanced.


Stretch Features:
* When entering a level, there is another screen where the player selects the difficulty: easy, normal, or hard. Depending on the level of difficulty, the entities will vary in speed and strength, the player will have different amounts of hearts.
* The player has a strength meter, which increases progressively throughout the game and can be further increased by picking up workout equipment hidden throughout the maze. The strength meter increases the player’s agility, their health, and their range of sight.
* There is a separate mode called “INFINITE” where levels are randomly generated. After completing a level, a new level is randomly generated. Once the player dies, the amount of levels they passed is recorded as their score. They have a high score in this mode, which is their goal to beat every time they enter into the mode.


Class List:
* Main - Starts the game
* DrawingSurface - Draws all items to the screen
* Player - User-controlled player that navigates through the maze extends Sprite
* Sprite - Class to represent objects on the screen
* Weapon - Items used to kill entities
* Tickler - Monsters with random movement until within the vicinity of the player
* KeyFragment - Parts of the key that need to be collected before escaping Ohio
* Screen - Template for screens
* FirstScreen - Main menu screen
* SecondScreen - Game screen
* ScreenSwitcher - Switches between the game’s screens
* GridTemplate - Represents a grid that can be drawn and interacted with


Credits:
* [List the group members and describe how each member contributed to the completion of the final program. This could be classes written, art assets created, leadership/organizational skills exercises, or other tasks. Before you start coding the project, this section should describe how you plan on splitting the work. As the project progresses, it should transform into a description of who completed each task.
* Give credit to all outside resources used. This includes downloaded images or sounds, external java libraries, parent/tutor/student coding help, etc.]
* Nikunj - Javadocs and classes
* Boon - Javadoc, UML
* Yash - AI image generator (with some editing), UML, audio creation,