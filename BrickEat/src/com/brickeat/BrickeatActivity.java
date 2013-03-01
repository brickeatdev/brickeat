package com.brickeat;

import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.svg.opengl.texture.atlas.bitmap.SVGBitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

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
	private Planche planche;
	private Personnage perso;
	private List<Brique> listeBrique;

	private Camera mCamera;
	private BuildableBitmapTextureAtlas mBuildableBitmapTextureAtlas;
	private ITextureRegion[] briqueTextureRegion;
	private ITextureRegion persoTextureRegion;
	private ITextureRegion plancheTextureRegion;

	/** Instance du jeu. */
	public static BrickeatActivity instance;


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/**
	 * Partage de l'instance du jeu.
	 * getSharedInstance().
	 * @return instance
	 */
	public static BrickeatActivity getSharedInstance() {
		return instance;
	}

	@Override
	public EngineOptions onCreateEngineOptions() {
		instance = this;
		// TAILLE AUTO
//        ll = (LinearLayout)findViewById(R.id.fullscreen_content);
//        CAMERA_WIDTH = ll.getWidth();
//        CAMERA_HEIGHT = ll.getHeight();

		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

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

		Scene scene = new Scene();
		scene.setBackground(new Background(Color.WHITE));

		final VertexBufferObjectManager vertexBufferObjectManager = this.getVertexBufferObjectManager();

		this.listeBrique = new ArrayList<Brique>();
		Brique brique;
		int line = 0;
		int row = 0;
		for (int i = 0; i < NB_BRICK; i++) {
			if (i % 7 == 0 && i != 0) {
				row = 0;
				line++;
			}
			float posX = row * BRICK_WIDTH + BRICK_MARGE;
			float posY = line * BRICK_HEIGHT + BRICK_MARGE;
			brique = new Brique(posX, posY, this.briqueTextureRegion[i], vertexBufferObjectManager);
			listeBrique.add(brique);
			scene.attachChild(brique);
			row++;
		}

		//Le sprite commence en haut à gauche de l'écran.
		this.perso = new Personnage(PERSO_INIT_POSX, PERSO_INIT_POSY, this.persoTextureRegion, vertexBufferObjectManager);
	    this.planche = new Planche(PADDLE_INIT_POSX, PADDLE_INIT_POSY, this.plancheTextureRegion, vertexBufferObjectManager);

	    scene.attachChild(perso);
		scene.attachChild(planche);

		return scene;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * loadGraphics.
	 */
	private void loadGraphics() {
		// Mise en place du background SVG
		this.mBuildableBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 1024, 1024, TextureOptions.NEAREST);
		SVGBitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		persoTextureRegion = SVGBitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBuildableBitmapTextureAtlas, this, "perso_fr.svg", PERSO_WIDTH, PERSO_HEIGHT);
		plancheTextureRegion = SVGBitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBuildableBitmapTextureAtlas, this, "planche_fr.svg", PADDLE_WIDTH, PADDLE_HEIGHT);
		List<String> imageBrique = new ArrayList<String>();
		imageBrique.add("brique_moule_fr.svg");
		imageBrique.add("brique_fromage_fr.svg");
		imageBrique.add("brique_vin_fr.svg");

		this.briqueTextureRegion = new ITextureRegion[NB_BRICK];
		int nb_image = 0;
		for (int i = 0; i < NB_BRICK; i++) {
			if (i % 7 == 0 && i != 0) {
				nb_image++;
			}
			briqueTextureRegion[i] = SVGBitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBuildableBitmapTextureAtlas, this, imageBrique.get(nb_image), BRICK_WIDTH, BRICK_HEIGHT);
		}

//		briqueTextureRegion[i++] = SVGBitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBuildableBitmapTextureAtlas, this, "brique_vin_fr.svg", BRICK_WIDTH, BRICK_HEIGHT);
//		briqueTextureRegion[i++] = SVGBitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBuildableBitmapTextureAtlas, this, "brique_fromage_fr.svg", BRICK_WIDTH, BRICK_HEIGHT);
//		briqueTextureRegion[i++] = SVGBitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBuildableBitmapTextureAtlas, this, "brique_moule_fr.svg", BRICK_WIDTH, BRICK_HEIGHT);
//		briqueTextureRegion[i++] = SVGBitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBuildableBitmapTextureAtlas, this, "brique_vin_fr.svg", BRICK_WIDTH, BRICK_HEIGHT);
//		briqueTextureRegion[i++] = SVGBitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBuildableBitmapTextureAtlas, this, "brique_fromage_fr.svg", BRICK_WIDTH, BRICK_HEIGHT);
//		briqueTextureRegion[i++] = SVGBitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBuildableBitmapTextureAtlas, this, "brique_moule_fr.svg", BRICK_WIDTH, BRICK_HEIGHT);

		try {
			this.mBuildableBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.mBuildableBitmapTextureAtlas.load();
		} catch (final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
