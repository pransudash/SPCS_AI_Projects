package org.lekan.graphics;

import java.awt.Color;
import java.awt.Stroke;

/**
 * A class to store the graphics context for drawing, such as color, stroke,
 * etc. All accesses to 
 * @author Lekan Wang (lekan@lekanwang.com)
 */
final class SGGraphicsContext {

	/** Foreground color */
	static Color fgColor;
	
	/** Background color */
	static Color bgColor;
	
	/** Stroke */
	static double strokeWidth;
	static Stroke stroke;
	
	static {
		fgColor = SGConstants.COLOR_FG_DEFAULT;
		bgColor = SGConstants.COLOR_BG_DEFAULT;
		stroke = SGConstants.STROKE_SOLID_THIN;
	}
	
	public static synchronized final void setForegroundColor(Color fgColor) {
			SGGraphicsContext.fgColor = fgColor;
	}
	
	public static synchronized final void setBackgroundColor(Color bgColor) {
		SGGraphicsContext.bgColor = bgColor;
	}
	
	public static synchronized final void setStrokeWidth(double strokeWidth) {
		SGGraphicsContext.strokeWidth = strokeWidth;
	}
	
}
