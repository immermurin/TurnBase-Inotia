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
        spriteBatch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        background = new Texture("background/menubackground.png");  // Keep background image
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        setupUI();
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

        // Button listeners
        pvpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.switchToCharacterSelection();  // Switch to Character Selection
            }
        });

        pvAIButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.switchToCharacterSelection();  // Switch to Character Selection
            }
        });

        aboutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.switchToAboutScreen();  // Transition to About Screen
            }
        });

        gameInfoButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.switchToGameInfoScreen();  // Transition to Game Info Screen
            }
        });

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

        // Set up the font for the buttons (no skin)
        BitmapFont font = new BitmapFont();
        style.font = font;
        
        TextButton button = new TextButton(text, style);
        return button;
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
    }

    @Override public void show() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
