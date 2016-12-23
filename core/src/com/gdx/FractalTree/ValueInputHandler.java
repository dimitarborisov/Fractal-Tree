package com.gdx.FractalTree;

public class ValueInputHandler {
	private float minLen = 10;
	
	private float x, y;
	private float xThreshold, yThreshold;
	
	public ValueInputHandler(float xThreshold, float yThreshold){
		x = 0f;
		y = 0f;
		
		this.xThreshold = xThreshold;
		this.yThreshold = yThreshold;
	}

	
	/**
	 * If a new touch is detected initialize the anchor x, y
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public void setNewProcessing(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * Process the new input, increasing the values if a threshold is exceeded
	 * it is using the initial x,y set with setNewProcessing()
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public void processInput(float x, float y){
		if(this.x + xThreshold <= x ){
			this.x = x;
			GdxTree.ANGLSTEP = GdxTree.ANGLSTEP + 1;
		}else if(this.x - xThreshold >= x ){
			this.x = x;
			GdxTree.ANGLSTEP = GdxTree.ANGLSTEP - 1;
		}
		
		if(this.y + yThreshold <= y){
			this.y = y;
			GdxTree.LENSTEP = GdxTree.LENSTEP + 1;
		}else if(this.y - yThreshold >= y && GdxTree.LENSTEP > minLen){
			this.y = y;
			GdxTree.LENSTEP = GdxTree.LENSTEP - 1;
		}
	}
	
}
