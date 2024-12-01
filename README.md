AP Project: Physics-Based Projectile Game
Table of Contents
Overview
Features
Project Structure
Getting Started
How to Play
Future Enhancements
Overview
This project is a physics-based, projectile-launching game inspired by Angry Birds. Built using the Java libGDX framework, it offers a fun and engaging gameplay experience. The game implements realistic physics to simulate the flight and impact of projectiles, providing an immersive experience.

Key components of the game include:

A main menu for navigation.
A level selection screen to choose gameplay challenges.
A gameplay screen where players launch birds to topple structures and defeat pigs.
Features
1. Main Menu
   Provides options to:
   Start a new game.
   Load a saved game.
   Exit the game.
2. Level Select Screen
   Displays all available levels.
   Allows players to choose levels dynamically, unlocking new challenges as they progress.
3. Game Screen
   The core gameplay environment.
   Features realistic physics-based mechanics for projectile launches.
   Players can aim, adjust trajectories, and strategize to maximize impact and destroy the pigs’ defenses.
4. Graphics and Assets
   High-quality, custom-designed sprites for birds, pigs, and environmental elements.
   Interactive buttons and animations enhance the user experience.
   Project Structure
   Core Game Packages
   Main.AngryBirds

Initializes the game and manages the overall flow between screens.
LWJGL3

Contains the engine responsible for running the game’s physics and mechanics.
Assets

Stores all game resources, including:
Sprites (birds, pigs, structures).
UI elements (buttons, menus).
Core

Includes various supporting classes and logic for:
Levels.
Screens (main menu, level select, gameplay).
Getting Started
Prerequisites
Ensure the following software is installed on your system:

Java Development Kit (JDK) version 10 or higher.
libGDX framework for game development.
Installation Steps
Clone the repository:

bash
Copy code
git clone   https://github.com/AbhijeetKoli01
Open the project in your favorite IDE (e.g., Eclipse, IntelliJ IDEA).

Navigate to the Lwjgl3Launcher.java file located in the lwjgl3 package under src/main.

Run the program to launch the game.

How to Play
Launch Birds:

Click and drag on a bird to set its launch angle and power.
Release to launch the bird toward the pigs.
Adjust Trajectory:

Hover over the screen to analyze the trajectory and fine-tune your aim.
Destroy Pigs:

Aim strategically to topple the structures and destroy all pigs to progress to the next level.
Progression:

Successfully complete each level to unlock new challenges.
Future Enhancements
