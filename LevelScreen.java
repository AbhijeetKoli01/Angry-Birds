package com.angrybirds.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class LevelScreen implements Screen {
    private final Game game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private SpriteBatch spriteBatch;
    private Texture BackGround;
    private Texture BackTexture;
    private Texture Level_1;
    private Texture Level_2;
    private Texture Level_3;
    private Stage stage;
    private Sound clickSound;

    public LevelScreen(Game game) {
        this.game = game;
    }

    public void show() {
        // Initialize click sound
        clickSound = Gdx.audio.newSound(Gdx.files.internal("click_sound.mp3"));

        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(1280.0F, 720.0F, this.camera);
        this.camera.position.set(this.viewport.getWorldWidth() / 2.0F, this.viewport.getWorldHeight() / 2.0F, 0.0F);
        this.camera.update();
        this.spriteBatch = new SpriteBatch();
        this.stage = new Stage(this.viewport, this.spriteBatch);
        Gdx.input.setInputProcessor(this.stage);

        // Load textures
        this.BackGround = new Texture("BackGround.jpg");
        this.BackTexture = new Texture("Back.png");
        this.Level_1 = new Texture("Level_1.png");
        this.Level_2 = new Texture("Level_2.png");
        this.Level_3 = new Texture("Level_3.png");

        // Setup buttons
        this.setupLevelButtons();
        this.setupBackButton();
    }

    private void setupLevelButtons() {
        float buttonWidth = (float)this.Level_1.getWidth();
        float buttonHeight = (float)this.Level_1.getHeight();
        float centerX = this.viewport.getWorldWidth() / 2.0F - buttonWidth / 2.0F;
        float centerY = this.viewport.getWorldHeight() / 2.0F;

        // Level 1 button
        ImageButton Level_1Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(this.Level_1)));
        Level_1Button.setSize(buttonWidth, buttonHeight);
        Level_1Button.setPosition(centerX - 300.0F, centerY - buttonHeight / 2.0F);
        Level_1Button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play(1.0f); // Play click sound
                LevelScreen.this.game.setScreen(new Level_1(LevelScreen.this.game));
            }
        });
        this.stage.addActor(Level_1Button);

        // Level 2 button
        ImageButton Level_2Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(this.Level_2)));
        Level_2Button.setSize(buttonWidth, buttonHeight);
        Level_2Button.setPosition(centerX, centerY - buttonHeight / 2.0F);
        Level_2Button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play(1.0f); // Play click sound
                LevelScreen.this.game.setScreen(new Level_2(LevelScreen.this.game));
            }
        });
        this.stage.addActor(Level_2Button);

        // Level 3 button
        ImageButton Level_3Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(this.Level_3)));
        Level_3Button.setSize(buttonWidth, buttonHeight);
        Level_3Button.setPosition(centerX + 300.0F, centerY - buttonHeight / 2.0F);
        Level_3Button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play(1.0f); // Play click sound
                LevelScreen.this.game.setScreen(new Level_3(LevelScreen.this.game));
            }
        });
        this.stage.addActor(Level_3Button);
    }

    private void setupBackButton() {
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(this.BackTexture)));
        backButton.setSize(70.0F, 70.0F);
        backButton.setPosition(this.viewport.getWorldWidth() - 90.0F, this.viewport.getWorldHeight() - 90.0F);
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play(1.0f); // Play click sound
                LevelScreen.this.game.setScreen(new MainScreen(LevelScreen.this.game));
            }
        });
        this.stage.addActor(backButton);
    }

    public void resize(int width, int height) {
        this.viewport.update(width, height, true);
        this.camera.update();
    }

    public void render(float delta) {
        ScreenUtils.clear(1.0F, 1.0F, 1.0F, 1.0F);
        this.spriteBatch.begin();
        this.spriteBatch.draw(this.BackGround, 0.0F, 0.0F, this.viewport.getWorldWidth(), this.viewport.getWorldHeight());
        this.spriteBatch.end();

        this.stage.act(delta);
        this.stage.draw();
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
    }

    public void dispose() {
        this.spriteBatch.dispose();
        this.BackGround.dispose();
        this.BackTexture.dispose();
        this.stage.dispose();
        clickSound.dispose();
    }
}
