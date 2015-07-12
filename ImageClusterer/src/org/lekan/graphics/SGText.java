package org.lekan.graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SGText extends SGObject {
	public static final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 14); 
	private Font font;
	private String text;
	
	/**
	 * Constructs an SGText object with the given text, at the given coordinate.
	 * @param text
	 * @param x
	 * @param y
	 */	
	public SGText(String text, int x, int y) {
		this(text, x, y, DEFAULT_FONT);
	}
	
	/**
	 * Constructs an SGText object with the given text, at the given coordinate,
	 * with the given font.
	 * @param text
	 * @param x
	 * @param y
	 * @param font
	 */
	public SGText(String text, int x, int y, Font font) {
		this.x = x;
		this.y = y;
		this.color = SGGraphicsContext.fgColor;
		this.font = font;
		this.text = text;
	}
	
	@Override
	void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(color);
		g2.setFont(font);

		g2.drawString(text, x, y);

	}
	
	/**
	 * Sets the text of this SGText object.
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Sets the font of this SGText object.
	 * @param font
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * Package getter for the font
	 */
	Font getFont() {
		return this.font;
	}
	
	public String getText() {
		return this.text;
	}
}
