package Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.*;
import com.Inotia.Main;

public class CharacterSelectionScreen implements Screen {
    private final Main game;
    private SpriteBatch spriteBatch;
    private Texture background;
    private OrthographicCamera camera;
    private Viewport viewport;

    private Stage stage;
    private String selectedCharacter;
    private final String[] characters = {
        "Bambi", "Barathrum", "Chichi", "Danzo", "Davion", "Entity",
        "Gadot", "Invoker", "Kamish", "Laswrath", "Makoi", "Tusk", "Yoonho"
    };

    public CharacterSelectionScreen(Main game) {
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

        // Load a BitmapFont properly
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2); // Optional: Adjust font size

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE; // Optional: Set font color

        Label titleLabel = new Label("Select Your Character", labelStyle);
        table.add(titleLabel).pad(10).row();

        Table charTable = new Table();
        ScrollPane scrollPane = new ScrollPane(charTable);

        // Define a reusable TextButtonStyle with the font
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;

        for (String character : characters) {
            TextButton charButton = new TextButton(character, buttonStyle);
            charButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selectedCharacter = character;
                    System.out.println("Selected Character: " + selectedCharacter);
                }
            });
            charTable.add(charButton).pad(5).row();
        }

        table.add(scrollPane).width(400).height(200).pad(10).row();

        // Randomize button
        TextButton randomButton = new TextButton("Randomize", buttonStyle);
        randomButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedCharacter = characters[(int) (Math.random() * characters.length)];
                System.out.println("Randomly Selected Character: " + selectedCharacter);
            }
        });

        // Next button
        TextButton nextButton = new TextButton("Next", buttonStyle);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedCharacter != null) {
                    game.switchToMapSelection(selectedCharacter);
                } else {
                    System.out.println("Please select a character first.");
                }
            }
        });

        table.add(randomButton).pad(10);
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
}
