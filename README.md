# Escape O.B.T.

A collaborative 2D top-down stealth and survival dungeon crawler built from scratch in Java. Navigate procedurally inspired grid mazes, evade hostile patrols using dynamic vision and sneaking mechanics, collect key fragments, and fight your way to freedom.

---

## Gameplay Demonstration
* [Watch Gameplay Demo Video](https://drive.google.com/file/d/1qU-lls3OacRDlRyZmbVWrs8V05SQZ_I_/view?usp=sharing)
* UML Diagram
<img width="100%" alt="image" src="https://github.com/user-attachments/assets/ad298821-f08e-432d-9237-035eb8452cc4" />


---

## Premise & Overview
On November 9, 1983, hostile entities took over the streets of Ohio. As the sole survivor, you must navigate a sequence of increasingly difficult, scrolling grid-based labyrinths under limited visibility. 

To escape each sector:
1. Navigate corridors while evading patrolling entities.
2. Collect all hidden **Key Fragments** scattered across the map.
3. Locate the unlocked exit portal to advance to the next sector.

---

## Key Features

### Core Mechanics & Engine
* **Smooth Tile Interpolation:** Free-form, continuous movement across a tile-based grid coordinate system rather than rigid cell-by-cell stepping.
* **Dynamic Fog-of-War & Vision:** Restricted player field-of-view that adjusts dynamically based on player state and inventory (e.g., expanding when equipping a lantern).
* **Stealth & Detection System:** Sneaking mechanism (`Shift`) reduces movement speed and sight line, drastically lowering enemy detection radius.
* **Data-Driven Level Parsing:** Levels are ingested and parsed from structured text files to dynamically instantiate tile matrices, entity spawn points, and collectible coordinates.

### Combat & Audio
* **Entity AI:** Enemies patrol randomly until acquiring line-of-sight/proximity, initiating chase states with scalable movement velocity.
* **Melee Combat:** Equip and swing a sword (`Space`) to eliminate tracking entities.
* **Dynamic Audio Engine:** Powered by `JayLayer` with contextual ambient audio shifts and proximity-based SFX cues as enemies approach.

---

## Controls

| Key | Action |
| :--- | :--- |
| **`W` `A` `S` `D`** / **Arrow Keys** | Move Character |
| **`Shift`** | Sneak (reduces speed & enemy awareness) |
| **`Space`** | Attack with equipped sword |
| **`Esc`** | Return to Main Menu |

---

## Architecture & Class Design

The project is built on an object-oriented state architecture utilizing pure Java and Swing/Processing components:

* **Core & Loop:** `Main`, `DrawingSurface` (rendering & window context), `Level` (tile and map logic).
* **Entity Hierarchy:** `Sprite` (base class) extended by `Player` and `Tickler` (AI enemy).
* **Interactive Elements:** `KeyFragment`, `Weapon`, `Lantern`, `Wall`, `Floor`, and `Exit`.
* **Screen State Machine:** Governed by the `ScreenSwitcher` interface managing `MenuScreen`, `GameScreen`, `LevelScreen`, `InstructionScreen`, `DeathScreen`, and `WinScreen`.

---

## How to Run

### Option 1: Run Pre-Built Executable (.jar)
1. Ensure Java Runtime Environment (JRE 8+) is installed.
2. Download or clone this repository.
3. Run the JAR via terminal:
   ```bash
   java -jar EscapeOBT.jar
