package com.Inotia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Screen.*;

public class Main extends Game {
    private SpriteBatch batch;

    @Override
    public void create() {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        batch = new SpriteBatch();
        this.setScreen(new TitleScreen(this));
    }

    @Override
    public void setScreen(Screen screen) {
        if (this.getScreen() != null) {
            this.getScreen().dispose();
        }
        super.setScreen(screen);
    }

    public void switchToMainMenu() {
        this.setScreen(new MainMenuScreen(this));
    }

    public void switchToCharacterSelection() {
        this.setScreen(new CharacterSelectionScreen(this));
    }

    public void startGame(String selectedMap, String selectedCharacter) {
        System.out.println("Starting game with character " + selectedCharacter +
            " on map: " + selectedMap);
        // Here you would normally start your actual game screen
        // this.setScreen(new GameScreen(this, selectedCharacter, selectedMap));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void switchToMapSelection(String selectedCharacter) {
        System.out.println("Switching to MapSelection with character: " + selectedCharacter);
        this.setScreen(new MapSelection(this, selectedCharacter));
    }
}
