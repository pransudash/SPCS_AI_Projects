package org.lekan.graphics;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SGObject {
	protected int x, y;
	protected Color color;
	
	
	protected void prepare(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void setColor(Color color) {
		this.color = color;
		// TODO: need to set up and fire listeners to notify frame
		// to trigger canvas repaint after model changed
	}
	
	abstract void paint(Graphics g);
		
}
