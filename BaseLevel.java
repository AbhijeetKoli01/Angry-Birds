package com.angrybirds.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class BaseLevel implements Screen {
    private Game game;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private SpriteBatch spriteBatch;
    private Texture BackGround;
    private Texture RedbirdTexture;
    private Texture PinkbirdTexture;
    private Texture GlassTexture;
    private Texture CatapultTexture;
    private Texture BackTexture;
    private Texture PigTexture;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Body[] birdBodies;
    private Body leftGlassBody;
    private Body rightGlassBody;
    private Body topGlassBody;
    private Body catapultBody;
    private Body groundBody;
    private Body pigBody;
    private int currentBirdIndex = 0;

    public BaseLevel() {
    }

    public void show() {
        this.world = new World(new Vector2(0.0F, -9.8F), true);
        debugRenderer = new Box2DDebugRenderer();
        this.camera = new OrthographicCamera((float) Gdx.graphics.getWidth(), (float)Gdx.graphics.getHeight());
        this.viewport = new FitViewport(1280.0F, 720.0F, this.camera);

        // Example of creating a ball body (already in your code)
        BodyDef balldef = new BodyDef();
        balldef.type = BodyDef.BodyType.DynamicBody;
        balldef.position.set(35.0F, 12.0F);
        CircleShape shape = new CircleShape();
        shape.setRadius(50.0F);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 25.0F;
        fixtureDef.friction = 0.6F;
        fixtureDef.restitution = 0.75F;
        this.world.createBody(balldef).createFixture(fixtureDef);
        shape.dispose();
    }

    public void render(float delta) {
        world.step(delta, 6, 2); // Step the world to update physics
        ScreenUtils.clear(1.0F, 1.0F, 1.0F, 1.0F); // Clear the screen
        debugRenderer.render(world, camera.combined); // Render the physics world
    }

    public void resize(int width, int height) {
        this.viewport.update(width, height, true);
        this.camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
    }
}
