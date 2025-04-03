package Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.Inotia.Main;
import com.badlogic.gdx.utils.viewport.*;

public class MapSelection implements Screen {
    private final Main game;
    private SpriteBatch spriteBatch;
    private Texture background;
    private OrthographicCamera camera;
    private Viewport viewport;

    private Stage stage;
    private String selectedMap;
    private final String[] maps = {"Forest", "Dungeon", "Castle"};
    private String selectedCharacter;

    public MapSelection(Main game) {
        this.game = game;
        spriteBatch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        background = new Texture("background/menubackground.png");
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

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
        Label titleLabel = new Label("Select a Map", new Label.LabelStyle(font, Color.WHITE));
        table.add(titleLabel).pad(10).row();

        // Create and add buttons for each map
        for (String map : maps) {
            TextButton mapButton = new TextButton(map, new TextButton.TextButtonStyle());
            mapButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selectedMap = map;
                    System.out.println("Selected Map: " + selectedMap);
                }
            });
            table.add(mapButton).pad(10).row();
        }

        // Add Next button
        TextButton nextButton = new TextButton("Next", new TextButton.TextButtonStyle());
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedMap != null) {
                    game.startGame(selectedMap);
                } else {
                    System.out.println("Please select a map first.");
                }
            }
        });

        table.add(nextButton).pad(10).row();
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

    public void setSelectedCharacter(String selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
        System.out.println("Character selected for this map selection: " + selectedCharacter);
    }
}
