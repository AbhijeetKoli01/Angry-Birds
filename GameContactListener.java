package com.angrybirds.game.Screens;

import com.badlogic.gdx.physics.box2d.*;

public class GameContactListener implements ContactListener {
    private Body pigBody;

    public GameContactListener(Body pigBody) {
        this.pigBody = pigBody;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if ((fixtureA.getBody() == pigBody && fixtureB.getBody().getType() == BodyDef.BodyType.DynamicBody) ||
            (fixtureB.getBody() == pigBody && fixtureA.getBody().getType() == BodyDef.BodyType.DynamicBody)) {
            System.out.println("Pig hit by bird, destroy pig");
            pigBody.getWorld().destroyBody(pigBody);  // Destroy the pig body on collision
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
