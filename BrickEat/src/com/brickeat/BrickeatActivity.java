package com.brickeat;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

import android.widget.LinearLayout;

import com.brickeat.util.constants.BrickeatConstants;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga
 *
 * @author Nicolas Gramlich
 * @since 13:58:12 - 21.05.2011
 */
public class BrickeatActivity extends SimpleBaseGameActivity implements BrickeatConstants {
	// ===========================================================
	// Constants
	// ===========================================================
	LinearLayout ll;

	private int CAMERA_WIDTH = GAME_WIDTH;
	private int CAMERA_HEIGHT = GAME_HEIGHT;

	// ===========================================================
	// Fields
	// ===========================================================

	private Camera mCamera;
	private BitmapTextureAtlas mBitmapTextureAtlas;
	private ITextureRegion mFaceTextureRegion;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public EngineOptions onCreateEngineOptions() {
		// TAILLE AUTO
//        ll = (LinearLayout)findViewById(R.id.fullscreen_content);
//        CAMERA_WIDTH = ll.getWidth();
//        CAMERA_HEIGHT = ll.getHeight();
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		this.mCamera.setCenter(0,0);

		return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
	}

	@Override
	public Engine onCreateEngine(final EngineOptions pEngineOptions) {
		return new LimitedFPSEngine(pEngineOptions, FPS);
	}

	@Override
	public void onCreateResources() {
		loadGraphics();
	}

	@Override
	public Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		final VertexBufferObjectManager vertexBufferObjectManager = this.getVertexBufferObjectManager();
		Scene scene = new Scene();
		scene.setBackground(new Background(Color.BLUE));

		//Commencer le sprite en haut à gauche de l'écran.
	    final int iStartX = -240;
	    final int iStartY = -400;
		final Sprite face = new Sprite(iStartX, iStartY, this.mFaceTextureRegion, vertexBufferObjectManager);

		scene.attachChild(face);
		return scene;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * loadGraphics.
	 */
	private void loadGraphics() {
	    BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
	    mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 1024, 1024, TextureOptions.DEFAULT);
	    mFaceTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, this, "MAQFRANCE.png", 0, 0);
	    mBitmapTextureAtlas.load();    
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
