package Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.Inotia.Main;

public class TitleScreen implements Screen {
    private final Main game;
    private SpriteBatch spriteBatch;
    private Texture background;
    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;
    private float progress = 0;
    private static final float LOADING_SPEED = 30f;
    private boolean switching = false;

    public TitleScreen(Main game) {
        this.game = game;
        System.out.println("TitleScreen initialized.");

        camera = new OrthographicCamera();
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setProjectionMatrix(camera.combined);

        try {
            // Ensure the background texture is loaded correctly
            background = new Texture(Gdx.files.internal("background/back.png"));
            background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        } catch (Exception e) {
            System.err.println("Error loading background texture.");
            e.printStackTrace();
            background = null;  // If the background fails, it will be null
        }
    }

    @Override
    public void render(float delta) {
        if (switching) return; // Skip rendering if we're switching screens

        progress += LOADING_SPEED * delta;
        if (progress >= 100 && !switching) {
            progress = 100;
            switching = true;
            // Delay the screen switch until after rendering completes
            Gdx.app.postRunnable(() -> game.switchToMainMenu());
        }

        // Clear the screen and update the camera
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        camera.update();

        // Draw the background if available
        spriteBatch.begin();
        if (background != null) {
            spriteBatch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        }
        spriteBatch.end();

        // Draw loading bar
        drawLoadingBar();
    }

    private void drawLoadingBar() {
        float barWidth = 500;
        float barHeight = 25;
        float barX = (Gdx.graphics.getWidth() - barWidth) / 2;
        float barY = 5; // Position near the bottom
        float cornerRadius = 10;

        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Background of the bar (Gray)
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(barX, barY, barWidth, barHeight);

        // Metallic Effect - Gradient from White to Light Gray
        for (int i = 0; i < barHeight; i++) {
            float progressRatio = (progress / 100) * barWidth;
            float gradientFactor = (float) i / barHeight; // Gradient transition factor

            // Set the gradient colors (pure white to light gray)
            float r = 1f - (gradientFactor * 0.2f); // Max white (1.0), reducing slightly
            float g = 1f - (gradientFactor * 0.2f);
            float b = 1f - (gradientFactor * 0.2f);

            shapeRenderer.setColor(new Color(r, g, b, 1f)); // Ensures a white metallic look
            shapeRenderer.rect(barX, barY + i, progressRatio, 1); // Row-by-row gradient fill
        }

        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        System.out.println("Disposing TitleScreen resources.");
        if (spriteBatch != null) {
            spriteBatch.dispose();
        }
        if (shapeRenderer != null) {
            shapeRenderer.dispose();
        }
        if (background != null) {
            background.dispose();
        }
    }

    @Override public void show() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
