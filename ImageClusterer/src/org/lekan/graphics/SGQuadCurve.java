package org.lekan.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.QuadCurve2D;

/**
 * Represents a quadratic curve in 2D.
 * 
 * @author Lekan Wang (lekan@lekawang.com)
 *
 */
public class SGQuadCurve extends SGObject {

	/**
	 * Stroke for this curve
	 */
	private Stroke stroke;
	
	/**
	 * Control points for this curve.
	 */
	private int xc, yc;
	
	/**
	 * Endpoint for this curve
	 */
	private int x2, y2;
	
	/**
	 * Draws a quadratic curve from (startX,startY) to (endX,endY), using (controlX,controlY)
	 * as the control point.
	 * 
	 * @param startX
	 * @param startY
	 * @param controlX
	 * @param controlY
	 * @param endX
	 * @param endY
	 */
	public SGQuadCurve(int startX, int startY, int controlX, int controlY, int endX, int endY) {
		this.x = startX;
		this.y = startY;
		this.xc = controlX;
		this.yc = controlY;
		this.x2 = endX;
		this.y2 = endY;
		
		this.stroke = SGGraphicsContext.stroke;
		this.color = SGGraphicsContext.fgColor;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(color);
		g2.setStroke(stroke);
		
		QuadCurve2D q = new QuadCurve2D.Double();
		q.setCurve(x, y, xc, yc, x2, y2);
		g2.draw(q);
	}
}
