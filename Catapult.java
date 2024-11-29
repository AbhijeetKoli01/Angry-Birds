package com.angrybirds.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Catapult {
    private World world;
    private Body catapultBody;
    private Texture texture;
    private float width;
    private float height;

    public Catapult(World world, float x, float y, float width, float height, Texture texture) {
        this.world = world;
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.createCatapultBody(x, y);
    }

    private void createCatapultBody(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(x, y);
        this.catapultBody = this.world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.width / 2.0F, this.height / 2.0F);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0F;
        fixtureDef.friction = 0.5F;
        this.catapultBody.createFixture(fixtureDef);
        shape.dispose();
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(this.texture, this.catapultBody.getPosition().x - this.width / 2.0F, this.catapultBody.getPosition().y - this.height / 2.0F, this.width, this.height);
    }

    public Body getBody() {
        return this.catapultBody;
    }

    public void dispose() {
        this.texture.dispose();
    }
}
