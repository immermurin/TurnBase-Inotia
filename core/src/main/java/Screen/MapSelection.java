package Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.*;
import com.Inotia.Main;

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

    // Constructor accepts selectedCharacter as a parameter
    public MapSelection(Main game, String selectedCharacter) {
        this.game = game;
        this.selectedCharacter = selectedCharacter; // Store selected character
        spriteBatch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 600, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        background = new Texture("background/menubackground.png");

        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        setupUI();
    }

    private void setupUI() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Load font
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2);

        // Label style
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        Label titleLabel = new Label("Select a Map", labelStyle);
        table.add(titleLabel).pad(10).row();

        // TextButton Style
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;

        // Map Selection Buttons
        for (String map : maps) {
            TextButton mapButton = new TextButton(map, buttonStyle);
            mapButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selectedMap = map;
                    System.out.println("Selected Map: " + selectedMap);
                }
            });
            table.add(mapButton).pad(10).row();
        }

        // Next Button - Updated
        TextButton nextButton = new TextButton("Next", buttonStyle);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedMap != null && selectedCharacter != null) {
                    game.startGame(selectedMap, selectedCharacter);
                } else if (selectedMap == null) {
                    System.out.println("Please select a map first.");
                } else {
                    System.out.println("Character selection missing.");
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
        System.out.println("Selected Character for Map Selection: " + selectedCharacter);
    }
}
