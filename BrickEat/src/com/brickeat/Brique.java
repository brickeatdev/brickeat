package com.brickeat;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Brique extends Sprite {

	public int nb_coups;
	/**
	 * Constructeur.
	 * @param posX float
	 * @param posY float
	 * @param pTextureRegion ITextureRegion
	 * @param pVertexBufferObject VertexBufferObjectManager
	 */
	public Brique(float posX, float posY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObject) {
		super(posX, posY, pTextureRegion, pVertexBufferObject);
	}
}
