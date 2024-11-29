package com.angrybirds.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Bird {
    private Body body;
    private Texture texture;
    private float radius;

    public Bird(World world, float x, float y, Texture texture) {
        this.texture = texture;
        this.radius = 30.0F;
        this.body = this.createCircleBody(world, x, y, this.radius);
    }

    private Body createCircleBody(World world, float x, float y, float radius) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0F;
        fixtureDef.restitution = 0.5F;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    public Body getBody() {
        return this.body;
    }

    public void draw(Batch batch) {
        Vector2 position = this.body.getPosition();
        float diameter = this.radius * 2.0F;
        batch.draw(this.texture, position.x - this.radius, position.y - this.radius, diameter, diameter);
    }
}
