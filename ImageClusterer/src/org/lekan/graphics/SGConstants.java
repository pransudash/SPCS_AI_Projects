package org.lekan.graphics;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;

/**
 * Useful constants, especially colors.
 * 
 * @author Lekan Wang (lekan@lekanwang.com)
 *
 */
public interface SGConstants {
	// --------    COLORS   -----------
	public static final Color COLOR_TRANSPARENT = new Color(0,0,0,100);
	public static final Color COLOR_BLACK = Color.BLACK;
	public static final Color COLOR_WHITE = Color.WHITE;
	public static final Color COLOR_RED = new Color(255,0,0);
	public static final Color COLOR_RED_50P = new Color(255,127,127);
	public static final Color COLOR_BLUE = new Color(0,0,255);
	public static final Color COLOR_BLUE_50P = new Color(127,127,255);
	public static final Color COLOR_GREEN = new Color(0,255,0);
	public static final Color COLOR_GREEN_50P = new Color(127,255,127);
	public static final Color COLOR_ORANGE = new Color(255,165,0);
	public static final Color COLOR_YELLOW = Color.YELLOW;
	public static final Color COLOR_MAGENTA = Color.MAGENTA;
	public static final Color COLOR_CYAN = Color.CYAN;
	public static final Color COLOR_PINK = Color.PINK;
	public static final Color COLOR_VIOLET = new Color(102,0,153);
	public static final Color COLOR_FOREST_GREEN = new Color(34,139,34);
	public static final Color COLOR_BEIGE = new Color(255,255,204);
	public static final Color COLOR_GOLD = new Color(255,215,0);
	public static final Color COLOR_SAFETY_GREEN = new Color(215,255,173);
	public static final Color COLOR_STANFORD_CARDINAL = new Color(153,0,0);
	public static final Color COLOR_OLIVE_DRAB = new Color(107,142,35);
	public static final Color COLOR_ORCHID = new Color(218,112,214);
	public static final Color COLOR_BROWN = new Color(165,42,42);
	public static final Color COLOR_SIENNA = new Color(160,82,45);
	public static final Color COLOR_HONEYDEW = new Color(240,255,240);
	public static final Color COLOR_SALMON = new Color(250,128,114);
	
	// Grays
	public static final Color COLOR_GRAY_90P = new Color(25,25,25);
	public static final Color COLOR_GRAY_80P = new Color(51,51,51);
	public static final Color COLOR_GRAY_70P = new Color(76,76,76);
	public static final Color COLOR_GRAY_60P = new Color(102,102,102);
	public static final Color COLOR_GRAY_50P = new Color(127,127,127);
	public static final Color COLOR_GRAY_40P = new Color(153,153,153);
	public static final Color COLOR_GRAY_30P = new Color(178,178,178);
	public static final Color COLOR_GRAY_20P = new Color(204,204,204);
	public static final Color COLOR_GRAY_10P = new Color(229,229,229);
	
	
	
	// -------- DEFAULT COLORS --------
	public static final Color COLOR_FG_DEFAULT = COLOR_BLACK;
	public static final Color COLOR_BG_DEFAULT = COLOR_WHITE;
	
	// --------    STROKES   ----------
	public static final Stroke STROKE_SOLID_THIN = new BasicStroke(1.0f);
	
}
