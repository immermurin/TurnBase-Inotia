package com.Inotia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

    public void switchToMainMenu() {
        this.setScreen(new MainMenuScreen(this));
    }

    public void switchToCharacterSelection() {
        this.setScreen(new CharacterSelectionScreen(this));
    }

    public void switchToMapSelection() {
        this.setScreen(new MapSelection(this));
    }

    public void switchToAboutScreen() {
        this.setScreen(new AboutScreen(this));
    }

    public void switchToGameInfoScreen() {
        this.setScreen(new GameInfoScreen(this));
    }

    public void startGame(String selectedMap) {
        System.out.println("Starting game on map: " + selectedMap);
        // Implement game start logic here
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
        // Logic to handle transitioning to the MapSelection screen with the selected character
        System.out.println("Switching to MapSelection with character: " + selectedCharacter);

        // Assuming you have a MapSelection screen setup:
        Main game = new Main();
        MapSelection mapSelectionScreen = new MapSelection(game);

        // Optionally, pass the selected character to the MapSelection screen if needed:
        mapSelectionScreen.setSelectedCharacter(selectedCharacter);

        // Transition to the MapSelection screen
        game.setScreen(mapSelectionScreen);
    }
}
