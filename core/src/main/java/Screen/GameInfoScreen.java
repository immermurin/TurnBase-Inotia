package Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.Inotia.Main;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameInfoScreen implements Screen {
    private final Main game;
    private SpriteBatch spriteBatch;
    private Texture background;
    private OrthographicCamera camera;
    private Viewport viewport;

    // Textures for buttons
    private Texture buttonUpTexture;
    private Texture buttonDownTexture;

    private Stage stage;

    public GameInfoScreen(Main game) {
        this.game = game;
        spriteBatch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        background = new Texture("background/menubackground.png");
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        // Load button textures
        buttonUpTexture = new Texture("ui/button-up.png");
        buttonDownTexture = new Texture("ui/button-down.png");

        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        setupUI();
    }

    private void setupUI() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Add game title label using BitmapFont
        BitmapFont font = new BitmapFont();
        Label titleLabel = new Label("Game Information", new Label.LabelStyle(font, Color.WHITE));
        titleLabel.setFontScale(2);
        table.add(titleLabel).pad(20).colspan(2).row();

        // Add game description and mechanics
        Label descriptionLabel = new Label("Inotia: Into the Unknown is a turn-based strategy game.\n\n" +
            "Game Mechanics:\n" +
            "1. Choose a character and a map.\n" +
            "2. Engage in turn-based combat with AI or other players.\n" +
            "3. Use your character's unique skills to defeat enemies.\n\n" +
            "Controls:\n" +
            "1. Use arrow keys or mouse to navigate.\n" +
            "2. Press 'Enter' to confirm selections.\n\n" +
            "Tips:\n" +
            "1. Consider your character's strengths and weaknesses.\n" +
            "2. Use your skills wisely to gain the upper hand.", new Label.LabelStyle(font, Color.WHITE));
        table.add(descriptionLabel).pad(20).colspan(2).row();

        // Add back button
        TextButton backButton = new TextButton("Back to Main Menu", new TextButton.TextButtonStyle(
            new TextureRegionDrawable(new TextureRegion(buttonUpTexture)),
            new TextureRegionDrawable(new TextureRegion(buttonDownTexture)),
            null, font));
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.switchToMainMenu();  // Switch to main menu
            }
        });

        table.add(backButton).pad(50).row();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        spriteBatch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        stage.dispose();
        background.dispose();
        buttonUpTexture.dispose();
        buttonDownTexture.dispose();
    }

    @Override public void show() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
