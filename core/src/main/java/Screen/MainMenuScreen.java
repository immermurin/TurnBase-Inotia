package Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.Inotia.Main;
import com.badlogic.gdx.utils.viewport.*;

public class MainMenuScreen implements Screen {
    private final Main game;
    private SpriteBatch spriteBatch;
    private Texture background;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;

    public MainMenuScreen(Main game) {
        this.game = game;
        try {
            spriteBatch = new SpriteBatch();
            camera = new OrthographicCamera();
            viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
            viewport.apply();
            camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

            // Load texture with error handling
            background = new Texture(Gdx.files.internal("Background/menubackground.png"));
            background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

            stage = new Stage(viewport, spriteBatch);
            Gdx.input.setInputProcessor(stage);
            setupUI();
        } catch (Exception e) {
            Gdx.app.error("MainMenuScreen", "Error initializing screen", e);
            throw new RuntimeException("Failed to initialize MainMenuScreen", e);
        }
    }

    private void setupUI() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create buttons with text only (no skins)
        TextButton pvpButton = createTextButton("Player vs Player");
        TextButton pvAIButton = createTextButton("Player vs AI");
        TextButton aboutButton = createTextButton("About");
        TextButton gameInfoButton = createTextButton("Game Info");

        // Align buttons at the bottom-left with padding of 50
        table.bottom().left().padLeft(50).padBottom(50);
        table.row().pad(10);
        table.add(pvpButton).row();
        table.add(pvAIButton).row();
        table.add(aboutButton).row();
        table.add(gameInfoButton).row();
    }

    private TextButton createTextButton(String text) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        try {
            BitmapFont font = new BitmapFont();
            font.getData().setScale(2);
            style.font = font;
            style.fontColor = Color.WHITE;

            TextButton button = new TextButton(text, style);
            button.pad(15);
            return button;
        } catch (Exception e) {
            Gdx.app.error("MainMenuScreen", "Error creating button", e);
            throw new RuntimeException("Failed to create button", e);
        }
    }

    @Override
    public void render(float delta) {
        try {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            camera.update();

            spriteBatch.setProjectionMatrix(camera.combined);
            spriteBatch.begin();
            spriteBatch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            spriteBatch.end();

            stage.act(delta);
            stage.draw();
        } catch (Exception e) {
            Gdx.app.error("MainMenuScreen", "Render error", e);
            throw e;
        }
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("MainMenuScreen", "Resizing to: " + width + "x" + height);
        viewport.update(width, height, true);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void dispose() {
        Gdx.app.log("MainMenuScreen", "Disposing resources");
        stage.dispose();
        spriteBatch.dispose();
        background.dispose();
    }

    @Override public void show() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
