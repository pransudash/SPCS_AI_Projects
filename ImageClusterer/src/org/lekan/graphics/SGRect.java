package org.lekan.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * A class representing a 2D rectangle.
 * 
 * @author Lekan Wang (lekan@lekanwang.com)
 *
 */
public class SGRect extends SGObject {
	protected int width, height;
	
	/**
	 * Creates an SGRect at (x,y), with width and height. 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public SGRect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = SGGraphicsContext.fgColor;
	}
	
	@Override
	void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(this.color);
		g2.drawRect(x, y, width, height);
	}
}
