package com.brickeat;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Classe Planche : image de la planche.
 * @author brickeatdev
 */
public class Planche extends Sprite {
	// ===========================================================
	// Constructeur
	// ===========================================================

	/**
	 * Constructeur.
	 * @param posX float
	 * @param posY float
	 * @param pTextureRegion ITextureRegion
	 * @param pVertexBufferObject VertexBufferObjectManager
	 */
	public Planche(float posX, float posY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObject) {
		super(posX, posY, pTextureRegion, pVertexBufferObject);
	}

}
