package org.lekan.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * A class representing a rounded rectangle.
 * 
 * @author Lekan Wang (lekan@lekanwang.com)
 *
 */
public class SGRoundedRect extends SGRect {
	
	private int arcWidth, arcHeight;
	private final static double DEFAULT_ARC_SIZE_RATIO = 0.1;
	
	public SGRoundedRect(int x, int y, int width, int height) {
		this(x, y, width, height,
				(int)Math.ceil(DEFAULT_ARC_SIZE_RATIO * width),
				(int)Math.ceil(DEFAULT_ARC_SIZE_RATIO * height));
	}
	
	public SGRoundedRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		super(x, y, width, height);
		
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
	}
	
	@Override
	void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(this.color);
		g2.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}
}
