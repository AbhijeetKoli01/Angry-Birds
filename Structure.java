package com.angrybirds.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Structure {
    private Body body;
    private Texture texture;
    private float width;
    private float height;

    public Structure(World world, float x, float y, float width, float height, Texture texture) {
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
        fixtureDef.friction = 0.5F;
        fixtureDef.restitution = 0.1F;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    public void draw(Batch batch) {
        batch.draw(this.texture, this.body.getPosition().x - this.width / 2.0F, this.body.getPosition().y - this.height / 2.0F, this.width, this.height);
    }

    public Body getBody() {
        return this.body;
    }
}
