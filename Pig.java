package com.angrybirds.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Pig {
    private Body body;
    private Texture texture;
    private float width;
    private float height;

    public Pig(World world, float x, float y, float width, float height, Texture texture) {
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.body = this.createRectangleBody(world, x, y, width, height);
    }

    private Body createRectangleBody(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2.0F, height / 2.0F);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0F;
        fixtureDef.restitution = 0.3F;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    public Body getBody() {
        return this.body;
    }

    public void draw(Batch batch) {
        Vector2 position = this.body.getPosition();
        batch.draw(this.texture, position.x - this.width / 2.0F, position.y - this.height / 2.0F, this.width, this.height);
    }
}
