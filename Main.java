package com.angrybirds.game;

import com.angrybirds.game.Screens.MainScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends Game {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;

    public Main() {
    }

    public void create() {
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(1280.0F, 720.0F, this.camera);
        this.setScreen(new MainScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        this.batch.dispose();
    }
}
