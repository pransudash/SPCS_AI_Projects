package org.lekan.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/**
 * A class representing a graphical line segment.
 * 
 * @author Lekan Wang (lekan@lekanwang.com)
 *
 */
public class SGLine extends SGObject {

	private int x2, y2;
	
	/**
	 * Creates an SGLine object from (x1,y1) to (x2,y2).
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public SGLine(int x1, int y1, int x2, int y2) {
		this.x = x1;
		this.y = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = SGGraphicsContext.fgColor;
	}
	
	@Override
	void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(this.color);
		g2.draw(new Line2D.Double(x, y, x2, y2));
	}

}
