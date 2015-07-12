package org.lekan.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * Represents a 2D ellipse.
 * 
 * @author Lekan Wang (lekan@lekanwang.com)
 *
 */
public class SGEllipse extends SGObject {
	private int xRadius, yRadius;
	private Stroke stroke;
	
	/**
	 * Creates an ellipse with center (x,y), and given x and y radii.
	 * 
	 * @param x
	 * @param y
	 * @param xRadius
	 * @param yRadius
	 */
	public SGEllipse(int x, int y, int xRadius, int yRadius) {
		this.x = x;
		this.y = y;
		this.xRadius = xRadius;
		this.yRadius = yRadius;
		this.color = SGGraphicsContext.fgColor;
		this.stroke = SGGraphicsContext.stroke;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(color);
		g2.setStroke(stroke);
		
		g2.drawOval(x-xRadius, y-yRadius, 2*xRadius, 2*yRadius);
	}
}
