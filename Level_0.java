//package com.angrybirds.game.Screens;
//
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.InputAdapter;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.Body;
//import com.badlogic.gdx.physics.box2d.BodyDef;
//import com.badlogic.gdx.physics.box2d.CircleShape;
//import com.badlogic.gdx.physics.box2d.FixtureDef;
//import com.badlogic.gdx.physics.box2d.PolygonShape;
//import com.badlogic.gdx.physics.box2d.World;
//import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
//import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//
//public class Level_1 implements Screen {
//    private Game game;
//    private ShapeRenderer shapeRenderer;
//    private OrthographicCamera camera;
//    private FitViewport viewport;
//    private static final float PIXELS_PER_METER = 1.0F;
//    private SpriteBatch spriteBatch;
//    private Texture BackGround;
//    private Texture RedBirdTexture;
//    private Texture YellowBirdTexture;
//    private Texture GlassTexture;
//    private Texture CatapultTexture;
//    private Texture BackTexture;
//    private Texture PigTexture;
//    private Texture WoodTexture;
//    private World world;
//    private Body[] birdBodies;
//    private Body[] glassBodies;
//    private Body[] woodBodies;
//    private Body[] pigBodies;
//    private Body catapultBody;
//    private Body groundBody;
//    private float backButtonX;
//    private float backButtonY;
//    private float backButtonRadius;
//    private int currentBirdIndex = 0;
//    private boolean isBirdLaunched = false;
//
//    public Level_1(Game game) {
//        this.game = game;
//        this.backButtonRadius = 35.0F;
//    }
//
//    public void show() {
//        this.shapeRenderer = new ShapeRenderer();
//        this.camera = new OrthographicCamera();
//        this.viewport = new FitViewport(1280.0F, 720.0F, this.camera);
//        this.viewport.apply();
//        this.camera.position.set(this.camera.viewportWidth / 2.0F, this.camera.viewportHeight / 2.0F, 0.0F);
//        this.camera.update();
//        this.spriteBatch = new SpriteBatch();
//        this.BackGround = new Texture("LevelBackground.jpg");
//        this.RedBirdTexture = new Texture("RedBird.png");
//        this.YellowBirdTexture = new Texture("YellowBird.png");
//        this.GlassTexture = new Texture("Glass.png");
//        this.WoodTexture = new Texture("Wood.png");
//        this.CatapultTexture = new Texture("Catapult.png");
//        this.BackTexture = new Texture("Back.png");
//        this.PigTexture = new Texture("Pigs.png");
//        this.world = new World(new Vector2(0.0F, -9.8F), true);
//        this.createGroundBody();
//        this.createBirdBodies();
//        this.createGlassBodies();
//        this.createWoodBodies();
//        this.createPigBodies();
//        this.createCatapultBody();
//        this.loadNextBirdOntoCatapult();
//        this.backButtonX = this.camera.viewportWidth - 100.0F;
//        this.backButtonY = this.camera.viewportHeight - 50.0F;
//        Gdx.input.setInputProcessor(new InputAdapter() {
//            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//                screenY = Gdx.graphics.getHeight() - screenY;
//                float distance = Vector2.dst((float)screenX, (float)screenY, Level_1.this.backButtonX, Level_1.this.backButtonY);
//                if (distance <= Level_1.this.backButtonRadius) {
//                    Level_1.this.goBackToPreviousScreen();
//                    return true;
//                } else if (!Level_1.this.isBirdLaunched && Level_1.this.currentBirdIndex < Level_1.this.birdBodies.length) {
//                    Level_1.this.launchBird((float)screenX, (float)screenY);
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        });
//    }
//
//    private void createGroundBody() {
//        this.groundBody = this.createRectangleBody(this.camera.viewportWidth / 2.0F, 10.0F, this.camera.viewportWidth, 20.0F, BodyType.StaticBody);
//    }
//
//    private void createBirdBodies() {
//        this.birdBodies = new Body[3];
//        this.birdBodies[0] = this.createCircleBody(350.0F, 125.0F, 37.5F, BodyType.DynamicBody);
//        this.birdBodies[1] = this.createCircleBody(200.0F, 80.0F, 37.5F, BodyType.DynamicBody);
//        this.birdBodies[2] = this.createCircleBody(275.0F, 80.0F, 37.5F, BodyType.DynamicBody);
//    }
//
//    private void createGlassBodies() {
//        this.glassBodies = new Body[5];
//        float glassWidth = 8.5F;
//        float glassHeight = 45.0F;
//        this.glassBodies[0] = this.createRectangleBody(900.0F, 200.0F, glassWidth, glassHeight, BodyType.DynamicBody);
//        this.glassBodies[1] = this.createRectangleBody(940.0F, 200.0F, glassWidth, glassHeight, BodyType.DynamicBody);
//        this.glassBodies[2] = this.createRectangleBody(980.0F, 200.0F, glassWidth, glassHeight, BodyType.DynamicBody);
//        this.glassBodies[3] = this.createRectangleBody(1020.0F, 200.0F, glassWidth, glassHeight, BodyType.DynamicBody);
//        this.glassBodies[4] = this.createRectangleBody(1060.0F, 200.0F, glassWidth, glassHeight, BodyType.DynamicBody);
//    }
//
//    private void createWoodBodies() {
//        this.woodBodies = new Body[2];
//        this.woodBodies[0] = this.createRectangleBody(950.0F, 300.0F, 200.0F, 30.0F, BodyType.DynamicBody);
//        this.woodBodies[1] = this.createRectangleBody(1050.0F, 400.0F, 100.0F, 30.0F, BodyType.DynamicBody);
//    }
//
//    private void createPigBodies() {
//        this.pigBodies = new Body[2];
//        this.pigBodies[0] = this.createCircleBody(1000.0F, 250.0F, 40.0F, BodyType.DynamicBody);
//        this.pigBodies[1] = this.createCircleBody(1100.0F, 450.0F, 40.0F, BodyType.DynamicBody);
//    }
//
//    private void createCatapultBody() {
//        this.catapultBody = this.createRectangleBody(350.0F, 80.0F, 52.5F, 125.0F, BodyType.StaticBody);
//    }
//
//    private Body createCircleBody(float x, float y, float radius, BodyDef.BodyType type) {
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = type;
//        bodyDef.position.set(x, y);
//        Body body = this.world.createBody(bodyDef);
//        CircleShape shape = new CircleShape();
//        shape.setRadius(radius);
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;
//        fixtureDef.density = 1.0F;
//        fixtureDef.restitution = 0.5F;
//        fixtureDef.friction = 1.0F;
//        body.createFixture(fixtureDef);
//        shape.dispose();
//        return body;
//    }
//
//    private Body createRectangleBody(float x, float y, float width, float height, BodyDef.BodyType type) {
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = type;
//        bodyDef.position.set(x, y);
//        Body body = this.world.createBody(bodyDef);
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(width / 2.0F, height / 2.0F);
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;
//        fixtureDef.density = 1.0F;
//        fixtureDef.restitution = 0.3F;
//        body.createFixture(fixtureDef);
//        shape.dispose();
//        return body;
//    }
//
//    private void launchBird(float screenX, float screenY) {
//        Body bird = this.birdBodies[this.currentBirdIndex];
//        Vector2 launchDirection = (new Vector2(screenX - bird.getPosition().x, screenY - bird.getPosition().y)).nor().scl(-500.0F);
//        bird.applyLinearImpulse(launchDirection, bird.getWorldCenter(), true);
//        this.isBirdLaunched = true;
//    }
//
//    private void loadNextBirdOntoCatapult() {
//        if (this.currentBirdIndex < this.birdBodies.length) {
//            Body nextBird = this.birdBodies[this.currentBirdIndex];
//            nextBird.setTransform(350.0F, 125.0F, 0.0F);
//            nextBird.setLinearVelocity(0.0F, 0.0F);
//            nextBird.setAngularVelocity(0.0F);
//        }
//
//    }
//
//    public void resize(int width, int height) {
//        this.viewport.update(width, height, true);
//        this.camera.update();
//    }
//
//    public void render(float delta) {
//        ScreenUtils.clear(1.0F, 1.0F, 1.0F, 1.0F);
//        this.world.step(0.016666668F, 6, 2);
//        if (this.isBirdLaunched) {
//            Body activeBird = this.birdBodies[this.currentBirdIndex];
//            if ((double)activeBird.getLinearVelocity().len() < 0.1 && (double)activeBird.getPosition().y < 0.1) {
//                ++this.currentBirdIndex;
//                if (this.currentBirdIndex < this.birdBodies.length) {
//                    this.isBirdLaunched = false;
//                    this.loadNextBirdOntoCatapult();
//                }
//            }
//        }
//
//        this.spriteBatch.begin();
//        this.spriteBatch.draw(this.BackGround, 0.0F, 0.0F, this.camera.viewportWidth, this.camera.viewportHeight);
//        this.drawBirds();
//        this.drawGlassBlocks();
//        this.drawWoodBlocks();
//        this.drawPigs();
//        this.drawCatapult();
//        this.drawBackButton();
//        this.spriteBatch.end();
//    }
//
//    private void drawBirds() {
//        for(int i = 0; i < this.birdBodies.length; ++i) {
//            if (i >= this.currentBirdIndex) {
//                this.drawSprite(i == 1 ? this.YellowBirdTexture : this.RedBirdTexture, this.birdBodies[i], 37.5F);
//            }
//        }
//
//    }
//
//    private void drawGlassBlocks() {
//        Body[] var1 = this.glassBodies;
//        int var2 = var1.length;
//
//        for(int var3 = 0; var3 < var2; ++var3) {
//            Body glassBody = var1[var3];
//            this.drawSprite(this.GlassTexture, glassBody, 17.5F, 87.5F);
//        }
//
//    }
//
//    private void drawWoodBlocks() {
//        Body[] var1 = this.woodBodies;
//        int var2 = var1.length;
//
//        for(int var3 = 0; var3 < var2; ++var3) {
//            Body woodBody = var1[var3];
//            this.drawSprite(this.WoodTexture, woodBody, 100.0F, 15.0F);
//        }
//
//    }
//
//    private void drawPigs() {
//        Body[] var1 = this.pigBodies;
//        int var2 = var1.length;
//
//        for(int var3 = 0; var3 < var2; ++var3) {
//            Body pigBody = var1[var3];
//            this.drawSprite(this.PigTexture, pigBody, 37.5F);
//        }
//
//    }
//
//    private void drawCatapult() {
//        this.drawSprite(this.CatapultTexture, this.catapultBody, 26.25F, 62.5F);
//    }
//
//    private void drawBackButton() {
//        this.spriteBatch.draw(this.BackTexture, this.backButtonX - this.backButtonRadius, this.backButtonY - this.backButtonRadius, this.backButtonRadius * 2.0F, this.backButtonRadius * 2.0F);
//    }
//
//    private void drawSprite(Texture texture, Body body, float radius) {
//        this.drawSprite(texture, body, radius, radius);
//    }
//
//    private void drawSprite(Texture texture, Body body, float widthHalf, float heightHalf) {
//        float x = body.getPosition().x - widthHalf;
//        float y = body.getPosition().y - heightHalf;
//        this.spriteBatch.draw(texture, (x - widthHalf) * 1.0F, (y - heightHalf) * 1.0F, widthHalf * 2.0F * 1.0F, heightHalf * 2.0F * 1.0F);
//    }
//
//    private void goBackToPreviousScreen() {
//        this.game.setScreen(new LevelScreen(this.game));
//        this.dispose();
//    }
//
//    public void pause() {
//    }
//
//    public void resume() {
//    }
//
//    public void hide() {
//    }
//
//    public void dispose() {
//        this.shapeRenderer.dispose();
//        this.spriteBatch.dispose();
//        this.BackGround.dispose();
//        this.RedBirdTexture.dispose();
//        this.YellowBirdTexture.dispose();
//        this.GlassTexture.dispose();
//        this.WoodTexture.dispose();
//        this.CatapultTexture.dispose();
//        this.BackTexture.dispose();
//        this.PigTexture.dispose();
//        this.world.dispose();
//    }
//}
