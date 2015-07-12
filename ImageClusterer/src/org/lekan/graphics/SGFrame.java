package org.lekan.graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * A simple frame that uses the box layout
 * 
 * @author Lekan Wang (lekan@lekanwang.com)
 *
 */
public class SGFrame implements SGConstants {
	// Components
	JFrame frame;
	private JPanel panel;
	private CanvasPanel graphicsPane;
	private JPanel northPanel, eastPanel, southPanel, westPanel;
	
	// Font Metrics
	private FontMetrics fm;
	
	/** List of SGObjects to be drawn. */
	private List<SGObject> objects;
	
	/** Custom panel for drawing SGObjects */
	private class CanvasPanel extends JPanel {
		private static final long serialVersionUID = -6691558494152802471L;

		public CanvasPanel() {
			super();
		}
		
		/**
		 * The panel's custom paint method.
		 * @param g
		 */
		@Override
		public void paint(Graphics g) {
			// Let UI delegate paint first
			super.paintComponent(g);
			
			// Set antialiasing on
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			// Paint our graphics objects
			synchronized(objects) {
				for (SGObject obj : objects) {
					obj.paint(g);
				}
			}
		}
		
		/**
		 * Marks explicitly that something has changed. Will revalidate and
		 * trigger repaint the panel.
		 */
		public void refresh() {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					CanvasPanel.this.revalidate();
					CanvasPanel.this.repaint();
				}
			});
		}
	}

	/**
	 * Creates a frame with the given title, and a graphics area of the 
	 * given width and height.
	 * 
	 * @param title Title of the frame
	 * @param width Width of the graphics area
	 * @param height Height of the graphics area
	 */
	public SGFrame (String title, int width, int height) {
		initComponents(title, width, height);
		
		objects = new ArrayList<SGObject>();
	}
	
	/**
	 * Gets the width of the text in pixels.
	 * 
	 * @param text
	 * @return
	 */
	public int getTextWidth(SGText text) {
		fm = graphicsPane.getFontMetrics(text.getFont());
		return fm.stringWidth(text.getText());
	}
	
	/**
	 * Gets the ascent of the text in pixels. The ascent is the distance from the
	 * baseline to the top of ascenders (the top part of letters like 'l' or 'f' or
	 * 'b').
	 * 
	 * @param text
	 * @return
	 */
	public int getTextAscent(SGText text) {
		fm = graphicsPane.getFontMetrics(text.getFont());
		return fm.getAscent();
	}
	
	/**
	 * Gets the height of the text in pixels. The height is the distance from
	 * the bottom of the descenders (the part of letters below the baseline, as
	 * in the letters 'g' or 'q' or 'p' or 'y') to the top of the ascenders
	 * (the top part of letters like 'l' or 'f' or 'b').
	 * 
	 * @param text
	 * @return
	 */
	public int getTextHeight(SGText text) {
		fm = graphicsPane.getFontMetrics(text.getFont());
		return fm.getHeight();
	}
	
	/**
	 * Initializes the frame's components.
	 * @param title
	 * @param width
	 * @param height
	 */
	private void initComponents(String title, int width, int height) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		panel = new JPanel(new BorderLayout());
		graphicsPane = new CanvasPanel();
		graphicsPane.setBackground(COLOR_BG_DEFAULT);
		graphicsPane.setPreferredSize(new Dimension(width, height));
		panel.add(graphicsPane, BorderLayout.CENTER);
		
		northPanel = new JPanel(new FlowLayout());
		eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
		southPanel = new JPanel(new FlowLayout());
		westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
		panel.add(northPanel, BorderLayout.NORTH);
		panel.add(eastPanel, BorderLayout.EAST);
		panel.add(southPanel, BorderLayout.SOUTH);
		panel.add(westPanel, BorderLayout.WEST);
		
		frame.add(panel);
	}
	
	/**
	 * Registers the provided multi-listener object
	 * @param listener
	 */
	void registerListeners(Object listener) {
		assert (listener instanceof MouseListener &&
				listener instanceof MouseMotionListener &&
				listener instanceof KeyListener);
		graphicsPane.addMouseListener((MouseListener)listener);
		graphicsPane.addMouseMotionListener((MouseMotionListener)listener);
		graphicsPane.addKeyListener((KeyListener)listener);		
	}
	/**
	 * Makes the frame visible. The frame will not display until this is
	 * called. This allows the user to do prelimiary setup of the frame
	 * before displaying anything. This should only be called when the fram
	 * is not already visible.
	 */
	public void show() {
		if (frame.isVisible()) {
			System.err.println("Frame is already visible.");
			return;
		}
		
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Adds an SGObject onto the canvas.
	 * @param obj
	 */
	public void addObject(SGObject obj) {
		synchronized(objects) {
			objects.add(obj);
		}
		
		graphicsPane.refresh();
	}
	
	/**
	 * Attempts to remove the parameter object from the canvas.
	 * 
	 * If the object is on the canvas, it will be removed, and the method returns true.
	 * Otherwise, it will return false.
	 * 
	 * @param obj
	 */
	public boolean removeObject(SGObject obj) {
		boolean objectFound = false;
		synchronized(objects) {
			objectFound = objects.remove(obj);
		}
		
		graphicsPane.refresh();
		
		return objectFound;
	}
	
	/**
	 * Adds a JButton at the position
	 * @param button
	 * @param position
	 */
	void addButton(JButton button, String position) {
		addJComponent(button, position);
	}
	
	/**
	 * Adds a JTextField at the position
	 * @param textField
	 * @param position
	 */
	void addTextField(JTextField textField, String position) {
		textField.setMaximumSize(textField.getPreferredSize());
		addJComponent(textField, position);
	}
	
	/**
	 * Adds a JComponent at the position
	 * @param button
	 * @param position
	 */
	void addJComponent(JComponent button, String position) {
		if (position.equals(BorderLayout.NORTH)) {
			northPanel.add(button);
		} else if (position.equals(BorderLayout.EAST)) {
			eastPanel.add(button);
		} else if (position.equals(BorderLayout.SOUTH)) {
			southPanel.add(button);
		} else if (position.equals(BorderLayout.WEST)) {
			westPanel.add(button);
		}
	}
	
	/**
	 * Sets the dimensions of the graphics pane in the frame
	 * @param width
	 * @param height
	 */
	public void setDimensions(int width, int height) {
		graphicsPane.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Sets the title of the frame
	 * @param title
	 */
	public void setTitle(final String title) {
		// Final frame copy for anonymous inner class
		final JFrame finalFrame = frame;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				finalFrame.setTitle(title);
			}
		});
	}
	
	/**
	 * Clears everything in the graphics area.
	 */
	public void clearGraphics() {
		synchronized(objects) {
			objects.clear();
		}
		graphicsPane.refresh();
	}
	
	/**
	 * Draws a line from (startX, startY) to (endX, endY).
	 * 
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	public void drawLine(final int startX, final int startY, final int endX, final int endY) {
		final SGLine line = new SGLine(startX, startY, endX, endY);
		addObject(line);
	}
	
	/**
	 * Draws a rectangle at (x,y), with the passed width and height.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void drawRect(final int x, final int y, final int width, final int height) {
		final SGRect rect = new SGRect(x, y, width, height);
		addObject(rect);
	}
	
	/**
	 * Draws a rounded rectangle with the default roundedness at (x,y), with the passed
	 * in width and height.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void drawRoundedRect(final int x, final int y, final int width, final int height) {
		final SGRoundedRect roundedRect = new SGRoundedRect(x, y, width, height);
		addObject(roundedRect);
	}
	
	/**
	 * Draws a rounded rectangle at (x,y), with the passed in width and height, and the
	 * passed in arc width and arc height.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param arcWidth
	 * @param arcHeight
	 */
	public void drawRoundedRect(final int x, final int y, final int width, final int height,
			final int arcWidth, final int arcHeight) {
		final SGRoundedRect roundedRect = new SGRoundedRect(x, y, width, height, arcWidth, arcHeight);
		addObject(roundedRect);
	}
	
	/**
	 * Draws an ellipse at (x,y) with given radii.
	 * 
	 * @param x
	 * @param y
	 * @param xRadius
	 * @param yRadius
	 */
	public void drawEllipse(final int x, final int y, final int xRadius, final int yRadius) {
		final SGEllipse ellipse = new SGEllipse(x, y, xRadius, yRadius);
		addObject(ellipse);
	}
	
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
	public void drawQuadCurve(final int startX, final int startY, final int controlX,
			final int controlY, final int endX, final int endY) {
		final SGQuadCurve quadCurve = new SGQuadCurve(startX, startY, controlX, controlY, endX, endY);
		addObject(quadCurve);
	}
	
	/**
	 * Draws an image located at the filename at (x,y)
	 * 
	 * @param x
	 * @param y
	 * @param filename
	 */
	public void drawImage(final int x, final int y, String filename) {
		final SGImage image = new SGImage(x, y, filename);
		addObject(image);
	}
	
	/**
	 * Draws the given image at (x,y)
	 * @param x
	 * @param y
	 * @param image
	 */
	public void drawImage(final int x, final int y, BufferedImage image) {
		final SGImage sgImage = new SGImage(x, y, image);
		addObject(sgImage);
	}
	
	/**
	 * Creates a text object at the given coordinates with the given text.
	 * @param text
	 * @param x
	 * @param y
	 */
	public void drawText(final String text, final int x, final int y) {
		final SGText textObj = new SGText(text, x, y);
		addObject(textObj);
	}
	
	/**
	 * Creates a text object at the given coordinates with the given text
	 * and the given font.
	 * 
	 * @param text
	 * @param x
	 * @param y
	 * @param font
	 */
	public void drawText(final String text, final int x, final int y, final Font font) {
		final SGText textObj = new SGText(text, x, y, font);
		addObject(textObj);
	}
	
	
	/**
	 * Sets the background color of the drawing context.
	 * 
	 * @param color
	 */
	public void setBackgroundColor(Color color) {
		SGGraphicsContext.setBackgroundColor(color);
		graphicsPane.setBackground(color);
	}
	
	/**
	 * Sets the foreground color of the drawing context.
	 * 
	 * @param color
	 */
	public void setForegroundColor(Color color) {
		SGGraphicsContext.setForegroundColor(color);
	}
	
	/**
	 * Sets the stroke.
	 * 
	 * @param stroke
	 */
	public void setStrokeWidth(double width) {
		SGGraphicsContext.setStrokeWidth(width);
	}
}
