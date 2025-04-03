package Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.Inotia.Main;
import com.badlogic.gdx.utils.viewport.*;

public class AboutScreen implements Screen {
    private final Main game;
    private SpriteBatch spriteBatch;
    private Texture background;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;

    // Textures for buttons
    private Texture buttonUpTexture;
    private Texture buttonDownTexture;

    public AboutScreen(Main game) {
        this.game = game;
        spriteBatch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        background = new Texture("background/menubackground.png");  // Add a background image
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

        // Add game title label using a bitmap font
        BitmapFont font = new BitmapFont();
        Label titleLabel = new Label("About the Game", new Label.LabelStyle(font, Color.WHITE));
        titleLabel.setFontScale(2);
        table.add(titleLabel).pad(20).colspan(2).row();

        // Add game description
        Label descriptionLabel = new Label("Inotia: Into the Unknown is a medieval horror-themed game where players explore dangerous realms and fight fearsome enemies.", new Label.LabelStyle(font, Color.WHITE));
        table.add(descriptionLabel).pad(20).colspan(2).row();

        // Add credits section
        Label creditsLabel = new Label("Developed by: Your Name\nMusic by: Composer Name\nArt by: Artist Name", new Label.LabelStyle(font, Color.WHITE));
        table.add(creditsLabel).pad(20).colspan(2).row();

        // Add a back button to return to the main menu
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
