package com.gdx.FractalTree;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class GdxTree extends ApplicationAdapter {
	public static float LENMIN = 1f;
	public static float LENSTEP = 11;
	public static float ANGLSTEP = 30f;
	public static float LENGTH = 100;
	public static float THICKSTEP = 0.5f;

	SpriteBatch batch;
	BitmapFont font;
	ShapeRenderer shapeRenderer;
	OrthographicCamera ortoCam;
	float camWidth, camHeight;
	float width;
	float height;
	
	ValueInputHandler vih;

	@Override
	public void create() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		font = new BitmapFont();
		
		camWidth = width;
		camHeight = height;
		
		ortoCam = new OrthographicCamera();
		ortoCam.setToOrtho(false, camWidth, camHeight);

		vih = new ValueInputHandler(10f, 10f);

		// add event handler
		Gdx.input.setInputProcessor(new touchInputProcessor());
	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		ortoCam.setToOrtho(false, camWidth, camHeight);
		ortoCam.update();
		drawBranch(width / 2, 0, LENGTH, 5f, 0f);
		drawText();
	}

	@Override
	public void dispose() {
		batch.dispose();
		shapeRenderer.dispose();
	}

	private void drawText(){
		StringBuilder sb = new StringBuilder();
		sb.append("LENSTEP: ");
		sb.append(LENSTEP);
		sb.append('\n');
		sb.append("ANGLSTEP: ");
		sb.append(ANGLSTEP);
		
		batch.begin();
		font.draw(batch, sb.toString(), 10, 50);
		batch.end();
	}
	
	private void drawBranch(float x, float y, float length, float thickness, float angle) {
		// calculate end points with the given angle and length
		float a = length * MathUtils.sinDeg(angle);
		float c = length * MathUtils.cosDeg(angle);

		float x1 = x + a;
		float y1 = y + c;

		shapeRenderer.setProjectionMatrix(ortoCam.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.rectLine(x, y, x1, y1, thickness);
		shapeRenderer.end();

		//update camera
		if(y1 > camHeight){
			camHeight = y1;
		}
		
		
		// calculate new length and angle
		float len = length - LENSTEP;
		float angl1 = angle + ANGLSTEP;
		float angl2 = angle - ANGLSTEP;

		if (len >= LENMIN) {
			drawBranch(x + a, y + c, len, thickness - THICKSTEP, angl1);
			drawBranch(x + a, y + c, len, thickness - THICKSTEP, angl2);
		}
	
		
	}

	private class touchInputProcessor implements InputProcessor {
		public boolean keyDown(int keycode) {
			return false;
		}

		public boolean keyUp(int keycode) {
			return false;
		}

		public boolean keyTyped(char character) {
			return false;
		}

		public boolean touchDown(int x, int y, int pointer, int button) {
			vih.setNewProcessing(x, y);
			return false;
		}

		public boolean touchUp(int x, int y, int pointer, int button) {
			return false;
		}

		public boolean touchDragged(int x, int y, int pointer) {
			vih.processInput(x, y);
			return false;
		}

		public boolean mouseMoved(int x, int y) {
			return false;
		}

		public boolean scrolled(int amount) {
			return false;
		}
	}
}
