package com.anwanxtd.tampilan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SplashScreen implements Screen {

    private Game game;
    private Camera kamera;
    private Viewport tampilan;
    private Stage panggung;
    private SpriteBatch batch;

    public SplashScreen(Game game){
        this.game = game;
        kamera = new OrthographicCamera();
        tampilan = new Tampilan(900,1600,kamera);
        batch = new SpriteBatch();
        panggung = new Stage(tampilan, batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 100f, 100f, 50f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        panggung.act();
        panggung.draw();
    }

    @Override
    public void resize(int width, int height) {
        tampilan.update(width, height, true);
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

    @Override
    public void dispose() {
        batch.dispose();
    }
}
