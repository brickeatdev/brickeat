package com.brickeat;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Classe Niveau : Pays du monde.
 * @author brickeatdev 
 */
public class Niveau extends Sprite {
	/** id du niveau. */
	public int id;
	/** Pays du Niveau. */
	public String pays;

	/**
	 * Constructeur.
	 * @param posX float
	 * @param posY float
	 * @param pTextureRegion ITextureRegion
	 * @param pVertexBufferObject VertexBufferObjectManager
	 */
	public Niveau(float posX, float posY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObject) {
		super(posX, posY, pTextureRegion, pVertexBufferObject);
	}
}
