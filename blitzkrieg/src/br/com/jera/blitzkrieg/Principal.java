package br.com.jera.blitzkrieg;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.AutoParallaxBackground;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
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
	
	private Texture mAutoParallaxBackgroundTexture;

	private TextureRegion mParallaxLayerBack;
	private TextureRegion mParallaxLayerMid;
	private TextureRegion mParallaxLayerFront;

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
		
		this.mAutoParallaxBackgroundTexture = new Texture(1024, 1024, TextureOptions.DEFAULT);
		this.mParallaxLayerBack = TextureRegionFactory.createFromAsset(this.mAutoParallaxBackgroundTexture, this, "parallax_background_layer_back.png", 0, 188);
		this.mParallaxLayerMid = TextureRegionFactory.createFromAsset(this.mAutoParallaxBackgroundTexture, this, "parallax_background_layer_mid.png", 0, 669);
		this.mEngine.getTextureManager().loadTextures(new Texture(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA), this.mAutoParallaxBackgroundTexture);
	}

	@Override
	public Scene onLoadScene() {
		final Scene scene = new Scene(2);
		loadBackground(scene);
		Player player1 = new Player();
		Player player2 = new Player();
		buildTower(player1, scene, 30);
		buildTower(player2, scene, CAMERA_WIDTH - 30);
		return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}
	
	private void loadBackground(Scene scene) {
		final AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
		autoParallaxBackground.addParallaxEntity(new ParallaxEntity(0.0f, new Sprite(0, CAMERA_HEIGHT - this.mParallaxLayerBack.getHeight(), this.mParallaxLayerBack)));
		autoParallaxBackground.addParallaxEntity(new ParallaxEntity(-5.0f, new Sprite(0, 80, this.mParallaxLayerMid)));
		scene.setBackground(autoParallaxBackground);
	}
	
	private void buildTower(Player player, Scene scene, int pos) {
		for(int i = 1 ; i <= player.getHeight(); i++ ) {
			Sprite brick = new Sprite(pos, CAMERA_HEIGHT - 20- (16*i), this.brickTextureRegion);
			scene.getTopLayer().addEntity(brick);
		}
	}
}