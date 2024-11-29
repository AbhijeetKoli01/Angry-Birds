package com.angrybirds.game.PhysicsEngine;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class Collision implements ContactListener {
    public Collision() {
    }

    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        // Handle bird vs obstacle collision
        if (this.isBird(bodyA) && this.isObstacle(bodyB)) {
            this.handleBirdObstacleCollision(bodyA, bodyB);
        } else if (this.isObstacle(bodyA) && this.isBird(bodyB)) {
            this.handleBirdObstacleCollision(bodyB, bodyA);
        }

        // Handle bird vs ground collision
        else if (this.isBird(bodyA) && this.isGround(bodyB)) {
            this.handleBirdGroundCollision(bodyA);
        }
    }

    public void endContact(Contact contact) {
    }

    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

    private boolean isBird(Body body) {
        return body.getUserData() != null && body.getUserData().equals("bird");
    }

    private boolean isObstacle(Body body) {
        return body.getUserData() != null && body.getUserData().equals("obstacle");
    }

    private boolean isGround(Body body) {
        return body.getUserData() != null && body.getUserData().equals("ground");
    }

    private void handleBirdObstacleCollision(Body bird, Body obstacle) {
        System.out.println("Bird hit an obstacle!");
        obstacle.setUserData("destroyed");
        obstacle.setActive(false);
    }

    private void handleBirdGroundCollision(Body bird) {
        System.out.println("Bird hit the ground!");
    }
}
