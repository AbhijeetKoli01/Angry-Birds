AP Project
Table of Contents
Overview
Features
Project Structure
Getting Started
Overview
This project is a physics-based, projectile-launching game inspired by Angry Birds, built using the Java libGDX framework. It has many classes for MainScreen, LevelScreen, Levels , Bird, Pig, and more. The game has a main menu, a level select screen, and a game screen where the player can launch birds at pigs.

Features
Main Menu: A screen with options to start a new game or load a previously saved game or exist the game.
Level Select Screen: A screen that displays all the levels in the game, allowing the player to choose which level they want to play
Game Screen: The main game screen where the player can launch birds at pigs. We also tried to make the physics of this game as realistic as possible to the real world physics. So you can enjoying throwing projectile birds at the pigs.
Project Structure
Core Game Structure:
Main.AngryBirds : The package that initialises the whole program/game screen.
LWJGL3 : This contains the engine that runs the whole game and its mechanics
Assests : This contains every sprites and pictures which would be used in the game including birds, pigs, various buttons and etc.
Core : Contains other various classes which are used in the game like levels and various screens.
Getting Started
To run the game, you need to have Java and libGDX installed on your system. Your JDK should be higher than JDK 10.

Clone the repository using `git clone https://github.com/Anirudh-Singhmaar/AP_Project_2024.git'.
Open the project in your IDE (Eclipse, IntelliJ, etc.).
Run the game
Navigate to Lwjgl3Launcher.java in the lwjgl3 under src/main and run the program.
How To Play
Launch a bird by clicking on the bird in the game screen.
The bird will fly towards the pig, and you can adjust its trajectory by howering over the screen.
After destroying pigs you can proceed to the next level.
