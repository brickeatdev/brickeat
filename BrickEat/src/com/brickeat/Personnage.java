package com.brickeat;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Classe Personnage : Image du personnage qui tient la planche.
 * @author brickeatdev
 */
public class Personnage extends Sprite {
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
	public Personnage(float posX, float posY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObject) {
		super(posX, posY, pTextureRegion, pVertexBufferObject);
	}
}
