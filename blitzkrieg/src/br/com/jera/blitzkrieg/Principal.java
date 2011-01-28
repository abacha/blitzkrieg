package br.com.jera.blitzkrieg;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class Principal extends BaseGameActivity {

	private static final int CAMERA_WIDTH = 720;
	private static final int CAMERA_HEIGHT = 480;
	private Texture brickTexture;
	private TextureRegion brickTextureRegion;

	@Override
	public Engine onLoadEngine() {
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				camera));
	}

	@Override
	public void onLoadResources() {
		this.brickTexture = new Texture(16, 16, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.brickTextureRegion = TextureRegionFactory.createFromAsset(this.brickTexture, this, "brick.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(this.brickTexture);
	}

	@Override
	public Scene onLoadScene() {
		final Scene scene = new Scene(2);
		scene.setBackground(new ColorBackground(0, 0, 0));
		Player player1 = new Player();
		Player player2 = new Player();
		buildTower(player1, scene);
		return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}
	
	private void buildTower(Player player, Scene scene) {
		for(int i = 1 ; i <= player.getHeight(); i++ ) {
			Sprite brick = new Sprite(30, 400 - (16*i), this.brickTextureRegion);
			scene.getTopLayer().addEntity(brick);
		}
	}
}