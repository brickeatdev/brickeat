package com.brickeat.util.constants;




/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 19:49:20 - 28.02.2011
 */
public interface BrickeatConstants {
	// ===========================================================
	// Final Fields
	// ===========================================================

	public static final int FPS = 30;

	public static final int GAME_WIDTH = 480;
	public static final int GAME_WIDTH_HALF = GAME_WIDTH / 2;
	public static final int GAME_HEIGHT = 800;
	public static final int GAME_HEIGHT_HALF = GAME_HEIGHT / 2;

	public static final int PADDLE_HEIGHT = 15;
	public static final int PADDLE_HEIGHT_HALF = PADDLE_HEIGHT / 2;
	public static final int PADDLE_WIDTH = GAME_WIDTH / 8;
	public static final int PADDLE_WIDTH_HALF = PADDLE_WIDTH / 2;
	public static final int PADDLE_INIT_POSX = GAME_WIDTH_HALF - PADDLE_WIDTH_HALF;
	public static final int PADDLE_INIT_POSY = 7 * (GAME_HEIGHT / 8) - PADDLE_HEIGHT;

	public static final int PERSO_HEIGHT = GAME_HEIGHT / 8;
	public static final int PERSO_WIDTH = GAME_WIDTH / 8;
	public static final int PERSO_INIT_POSX = GAME_WIDTH_HALF - (PERSO_WIDTH / 2);
	public static final int PERSO_INIT_POSY = 7 * (GAME_HEIGHT / 8);

	public static final int BALL_WIDTH = PADDLE_WIDTH / 4;
	public static final int BALL_HEIGHT = PADDLE_WIDTH / 4;
	public static final int BALL_RADIUS = BALL_WIDTH / 2;

	public static final int BRICK_WIDTH = GAME_WIDTH / 8;
	public static final int BRICK_HEIGHT = GAME_HEIGHT / 24;
	public static final int BRICK_MARGE = BRICK_WIDTH / 2;
	public static final int MAX_BRICK_LINE = 7;
	public static final int MAX_BRICK_ROW = 3;
	public static final int NB_BRICK = MAX_BRICK_LINE * MAX_BRICK_ROW;

	public static final int SCORE_PADDING = 5;

	// ===========================================================
	// Methods
	// ===========================================================
}
