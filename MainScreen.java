package com.angrybirds.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainScreen implements Screen {
    private Game game;
    private SpriteBatch spriteBatch;
    private Texture BackGround;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Stage stage;
    private Sound clickSound;
    private Music backgroundMusic; // Music instance
    private boolean isMuted = false; // Track mute state

    public MainScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Initialize background music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"));
        backgroundMusic.setLooping(true); // Make the music loop
        backgroundMusic.play(); // Start playing the music

        clickSound = Gdx.audio.newSound(Gdx.files.internal("click_sound.mp3"));
        this.spriteBatch = new SpriteBatch();
        this.BackGround = new Texture(Gdx.files.internal("BackGround.jpg"));
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(1280.0F, 720.0F, this.camera);
        this.viewport.apply();
        this.stage = new Stage(this.viewport, this.spriteBatch);
        Gdx.input.setInputProcessor(this.stage);

        // Textures for the buttons
        Texture playTexture = new Texture(Gdx.files.internal("Play.png"));
        Texture exitTexture = new Texture(Gdx.files.internal("Exit.png"));

        // Create the play and exit buttons
        ImageButton playButton = new ImageButton(new TextureRegionDrawable(playTexture));
        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(exitTexture));

        float buttonWidth = 250.0F;
        float buttonHeight = 100.0F;

        playButton.setSize(buttonWidth, buttonHeight);
        exitButton.setSize(buttonWidth, buttonHeight);

        // Center the buttons horizontally
        float centerX = this.viewport.getWorldWidth() / 2.0F - buttonWidth / 2.0F;
        playButton.setPosition(centerX, 200.0F);
        exitButton.setPosition(centerX + 250.0F, 200.0F);

        // Button listeners
        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play(1.0f); // Play the click sound with full volume
                MainScreen.this.game.setScreen(new LevelScreen(MainScreen.this.game));
            }
        });

        exitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play(1.0f); // Play the click sound with full volume
                Gdx.app.exit();
            }
        });

        // Add the buttons to the stage
        this.stage.addActor(playButton);
        this.stage.addActor(exitButton);
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height, true);
        this.camera.update();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1.0F, 1.0F, 1.0F, 1.0F);
        this.spriteBatch.begin();
        this.spriteBatch.draw(this.BackGround, 0.0F, 0.0F, this.camera.viewportWidth, this.camera.viewportHeight);
        this.spriteBatch.end();

        // Handle music mute functionality
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.M)) {
            toggleMute();
        }

        this.stage.act(delta);
        this.stage.draw();
    }

    private void toggleMute() {
        if (isMuted) {
            backgroundMusic.play();
            isMuted = false;
            System.out.println("Music unmuted");
        } else {
            backgroundMusic.pause();
            isMuted = true;
            System.out.println("Music muted");
        }
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
        if (backgroundMusic.isPlaying()) {
            backgroundMusic.pause();
        }
    }

    @Override
    public void resume() {
        if (!isMuted) {
            backgroundMusic.play();
        }
    }

    @Override
    public void dispose() {
        this.spriteBatch.dispose();
        this.BackGround.dispose();
        this.stage.dispose();
        backgroundMusic.dispose(); // Dispose of the music resource
        clickSound.dispose(); // Dispose of the click sound resource
    }
}
