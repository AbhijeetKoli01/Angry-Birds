package com.angrybirds.game.PhysicsEngine;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Gravity {
    private final World world;

    // Default constructor with Earth-like gravity
    public Gravity() {
        this.world = new World(new Vector2(0.0F, -9.8F), true);
    }

    // Constructor with custom gravity
    public Gravity(float gravityX, float gravityY) {
        this.world = new World(new Vector2(gravityX, gravityY), true);
    }

    // Get the Box2D World instance
    public World getWorld() {
        return this.world;
    }

    // Update the world
    public void update(float deltaTime) {
        this.world.step(deltaTime, 6, 2);  // 6 velocity iterations, 2 position iterations
    }

    // Set custom gravity
    public void setGravity(float x, float y) {
        this.world.setGravity(new Vector2(x, y));
    }

    public void dispose() {
        this.world.dispose();
    }
}
