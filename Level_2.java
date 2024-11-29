package com.angrybirds.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Level_2 implements Screen {
    private static final float PIXELS_PER_METER = 100.0F;
    private Game game;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private World world;
    private Body[] birdBodies;
    private Body leftWoodBody;
    private Body rightWoodBody;
    private Body topWoodBody;
    private Body catapultBody;
    private Body groundBody;
    private Body pigBody;
    private Body pigBodyAboveWood;
    private Box2DDebugRenderer debugRenderer;
    private Sound birdLaunchSound;
    private int currentBirdIndex = 0;
    private boolean isBirdLaunched = false;

    public Level_2(Game game) {
        this.game = game;
    }

    public void show() {
        this.shapeRenderer = new ShapeRenderer();
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(1280.0F, 720.0F, this.camera);
        this.viewport.apply();
        this.camera.position.set(this.viewport.getWorldWidth() / 2.0F, this.viewport.getWorldHeight() / 2.0F, 0.0F);
        this.camera.update();
        this.world = new World(new Vector2(0.0F, -9.8F), true);
        this.debugRenderer = new Box2DDebugRenderer();
        this.createGroundBody();
        this.createBirdBodies();
        this.createWoodBodiesWithGravity();
        this.createCatapultBody();
        this.loadNextBirdOntoCatapult();
        birdLaunchSound = Gdx.audio.newSound(Gdx.files.internal("angry-birds-laugh.mp3"));
        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = Gdx.graphics.getHeight() - screenY;
                if (!Level_2.this.isBirdLaunched && Level_2.this.currentBirdIndex < Level_2.this.birdBodies.length) {
                    Level_2.this.launchBird((float)screenX / 100.0F, (float)screenY / 100.0F);
                    return true;
                } else {
                    return false;
                }
            }
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    // Detect the ESC key press and go back to the previous screen
                    Level_2.this.goBackToPreviousScreen();
                    return true;
                } else if (keycode == Input.Keys.SPACE) {
                    // Restart the level when the spacebar is pressed
                    Level_2.this.restartLevel();
                    return true;
                } else if (keycode == Input.Keys.ENTER) {
                    // Transition to Level 3 when Enter is pressed
                    Level_2.this.goToLevel3();
                    return true;
                }
                return false;  // Allow other key events to be processed
            }

        });
    }
    private void goToLevel3() {
        // Assuming Level_3 is another class that implements Screen
        this.game.setScreen(new Level_3(this.game));  // Transition to Level 3
    }

    private void restartLevel() {
        // Reset the bird and other game objects to their initial states
        this.isBirdLaunched = false;
        this.currentBirdIndex = 0;
        this.loadNextBirdOntoCatapult();  // Reload the first bird onto the catapult

        // Reset bird body positions and velocities
        for (Body bird : this.birdBodies) {
            bird.setTransform(3.5F, 1.25F, 0.0F);  // Set to initial position
            bird.setLinearVelocity(0.0F, 0.0F);   // Stop any movement
            bird.setAngularVelocity(0.0F);         // Stop any rotation
        }

        // Reset other game objects, such as pigs, woods, etc.
        this.resetWoodBodies();
        this.resetPig();
    }

    private void resetWoodBodies() {
        // Reset positions of the wood bodies to their initial state if needed
        this.leftWoodBody.setTransform(8.8F, 1.5F, 0.0F);
        this.rightWoodBody.setTransform(10.8F, 1.5F, 0.0F);
        this.topWoodBody.setTransform(9.8F, 2.6F, 0.0F);
    }

    private void resetPig() {
        // Reset the pig's position if necessary
        this.pigBody.setTransform(9.8F, 1.5F, 0.0F);
    }

    private void createGroundBody() {
        this.groundBody = this.createRectangleBody(6.5F, 0.1F, 12.8F, 0.2F, BodyType.StaticBody);
    }

    private void createBirdBodies() {
        this.birdBodies = new Body[2];
        this.birdBodies[0] = this.createCircleBody(3.5F, 1.25F, 0.375F, BodyType.DynamicBody);
        this.birdBodies[1] = this.createCircleBody(2.0F, 0.8F, 0.375F, BodyType.DynamicBody);
    }

    private void createWoodBodiesWithGravity() {
        this.leftWoodBody = this.createRectangleBody(8.8F, 1.5F, 0.35F, 1.75F, BodyType.DynamicBody);
        this.rightWoodBody = this.createRectangleBody(10.8F, 1.5F, 0.35F, 1.75F, BodyType.DynamicBody);
        this.topWoodBody = this.createRectangleBody(9.8F, 2.6F, 2.0F, 0.3F, BodyType.DynamicBody);
        this.pigBody = this.createCircleBody(9.8F, 1.5F, 0.35F, BodyType.DynamicBody);
        this.pigBodyAboveWood = this.createCircleBody(9.8F, 3.3F, 0.35F, BodyType.DynamicBody);
    }

    private void createCatapultBody() {
        this.catapultBody = this.createRectangleBody(3.5F, 0.8F, 0.525F, 1.25F, BodyType.StaticBody);
    }

    private Body createCircleBody(float x, float y, float radius, BodyDef.BodyType type) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.position.set(x, y);
        Body body = this.world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0F;
        fixtureDef.restitution = 0.5F;
        fixtureDef.friction = 1.75F;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    private Body createRectangleBody(float x, float y, float width, float height, BodyDef.BodyType type) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.position.set(x, y);
        Body body = this.world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2.0F, height / 2.0F);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5F;
        fixtureDef.friction = 0.4F;
        fixtureDef.restitution = 0.2F;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    private void launchBird(float screenX, float screenY) {
        Body bird = this.birdBodies[this.currentBirdIndex];
        Vector2 launchDirection = (new Vector2(screenX - bird.getPosition().x, screenY - bird.getPosition().y)).nor().scl(-5.0F);
        bird.applyLinearImpulse(launchDirection, bird.getWorldCenter(), true);
        birdLaunchSound.play();
        this.isBirdLaunched = true;
    }

    private void loadNextBirdOntoCatapult() {
        if (this.currentBirdIndex < this.birdBodies.length) {
            Body nextBird = this.birdBodies[this.currentBirdIndex];
            nextBird.setTransform(3.5F, 1.25F, 0.0F);
            nextBird.setLinearVelocity(0.0F, 0.0F);
            nextBird.setAngularVelocity(0.0F);
        }

    }

    public void render(float delta) {
        ScreenUtils.clear(1.0F, 1.0F, 1.0F, 1.0F);
        this.world.step(0.016666668F, 6, 2);
        if (this.isBirdLaunched && this.currentBirdIndex < this.birdBodies.length) {
            Body activeBird = this.birdBodies[this.currentBirdIndex];
            float velocity = activeBird.getLinearVelocity().len();
            if (activeBird.getPosition().x < -2.0F || activeBird.getPosition().x > 15.0F || velocity < 0.1F) {
                activeBird.setLinearVelocity(0.0F, 0.0F);
                this.world.destroyBody(activeBird);
                ++this.currentBirdIndex;
                if (this.currentBirdIndex < this.birdBodies.length) {
                    this.isBirdLaunched = false;
                    this.loadNextBirdOntoCatapult();
                } else {
                    System.out.println("Game over");
                }
            }
        }

        this.debugRenderer.render(this.world, this.camera.combined);
        this.shapeRenderer.setProjectionMatrix(this.camera.combined);
        this.shapeRenderer.begin(ShapeType.Filled);
        this.drawGround();
        this.drawBirds();
        this.drawWoodBlocks();
        this.drawPig();
        this.drawCatapult();
        this.shapeRenderer.end();
        this.shapeRenderer.begin(ShapeType.Line);
        this.shapeRenderer.setColor(0.0F, 0.0F, 0.0F, 1.0F);
        this.drawGround();
        this.drawBirds();
        this.drawWoodBlocks();
        this.drawPig();
        this.drawCatapult();
        this.shapeRenderer.end();
    }

    private void drawGround() {
        this.shapeRenderer.setColor(0.5F, 0.25F, 0.0F, 1.0F);
        this.drawRectangle(this.groundBody, 1280.0F, 20.0F);
    }

    private void drawBirds() {
        for (int i = 0; i < this.birdBodies.length; ++i) {
            if (i >= this.currentBirdIndex) {
                // Set the color for the birds
                if (i == 0) {
                    this.shapeRenderer.setColor(1.0F, 0.0F, 0.0F, 1.0F); // Red for the first bird
                } else {
                    this.shapeRenderer.setColor(1.0F, 1.0F, 0.0F, 1.0F); // Yellow for the second bird
                }
                this.drawCircle(this.birdBodies[i], 37.5F);
            }
        }
    }

    private void drawWoodBlocks() {
        this.shapeRenderer.setColor(0.5F, 0.25F, 0.0F, 1.0F);
        this.drawRectangle(this.leftWoodBody, 35.0F, 175.0F);
        this.drawRectangle(this.rightWoodBody, 35.0F, 175.0F);
        this.drawRectangle(this.topWoodBody, 200.0F, 30.000002F);
    }

    private void drawPig() {
        this.shapeRenderer.setColor(0.0F, 1.0F, 0.0F, 1.0F);
        this.drawCircle(this.pigBody, 37.5F);
        this.drawCircle(this.pigBodyAboveWood, 37.5F);
    }

    private void drawCatapult() {
        this.shapeRenderer.setColor(0.5F, 0.25F, 0.0F, 1.0F);
        this.drawRectangle(this.catapultBody, 52.499996F, 125.0F);
    }

    private void drawRectangle(Body body, float width, float height) {
        this.shapeRenderer.rect(body.getPosition().x * 100.0F - width / 2.0F, body.getPosition().y * 100.0F - height / 2.0F, width / 2.0F, height / 2.0F, width, height, 1.0F, 1.0F, (float)Math.toDegrees((double)body.getAngle()));
    }

    private void drawCircle(Body body, float radius) {
        this.shapeRenderer.circle(body.getPosition().x * 100.0F, body.getPosition().y * 100.0F, radius);
    }

    private void goBackToPreviousScreen() {
        this.game.setScreen(new LevelScreen(this.game));
    }

    public void resize(int width, int height) {
        this.viewport.update(width, height);
    }

    public void hide() {
        this.dispose();
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
        this.world.dispose();
        this.shapeRenderer.dispose();
        this.debugRenderer.dispose();
    }
}
