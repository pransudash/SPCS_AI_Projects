package org.lekan.graphics;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * An abstract class to be overridden to create a Graphical program in the SimpleGraphics package. 
 * 
 * Usage:
 * 1. Declare a class that extends GraphicsProgram
 * 2. Declare the boilerplate method:
 * 		public static void main(String[] args) {
 * 			new YourClassName();
 *  	}
 * 3. Override setup() for any setup to be done before the frame displays.
 * 4. Override run() for a main run loop.
 * 5. Override any listener to react to mouse and key events.
 * 6. ???
 * 7. Profit!
 * 
 * @author Lekan Wang (lekan@lekanwang.com)
 *
 */
public abstract class GraphicsProgram implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
	/**
	 * Setting any of these to true will turn on debug mode for the listener.
	 * Debug mode will print a statement any time one of these listeners are triggered.
	 */
	private boolean keyDebug;
	private boolean mouseDebug;
	private boolean mouseMotionDebug;
	private boolean buttonDebug;
	
	private SGFrame frame;
	
	protected final static String NORTH = BorderLayout.NORTH;
	protected final static String SOUTH = BorderLayout.SOUTH;
	protected final static String EAST =  BorderLayout.EAST;
	protected final static String WEST =  BorderLayout.WEST;
	
	/**
	 * Zero-arg constructor. Constructs a 600x800 SGFrame with debug off. Then calls
	 * the setup, the private final show to display the frame, and the run.
	 * 
	 * setup() and run() can be overriden by a subclass to be called polymorphically. 
	 */
	public GraphicsProgram() {
		this.keyDebug = this.mouseDebug = this.mouseMotionDebug = false;
		frame = new SGFrame("Program", 600, 800);
		setup();
		show();
		run();
	}

	/**
	 * A placeholder method that should do setup before 
	 */
	public void setup() {}
	
	/**
	 * Shows the frame between setup and run. 
	 */
	private final void show() {
		this.frame.registerListeners(this);
		this.frame.show();
	}
	
	/**
	 * A placeholder method that should run a main program sequence. This
	 * method should be overridden by the subclass to be used.
	 */
	public void run() {}
	
	public SGFrame getFrame() {
		return this.frame;
	}
	
	/**
	 * Adds a button with the given button text, at the given
	 * BorderLayout location.
	 * 
	 * @param buttonText
	 * @param borderPosition
	 */
	public void addButton(String buttonText, String borderPosition) {
		assertBorderPositionValid(borderPosition);
		
		JButton button = new JButton(buttonText);
		button.addActionListener(this);
		this.frame.addButton(button, borderPosition);
	}
	
	/**
	 * Adds a given JTextField at a location.
	 * 
	 * @param jTextField
	 * @param borderPosition
	 */
	public void addTextField(JTextField jTextField, String borderPosition) {
		assertBorderPositionValid(borderPosition);
		
		jTextField.addActionListener(this);
		this.frame.addTextField(jTextField, borderPosition);
	}
	
	/**
	 * Adds a label at a location.
	 * 
	 * @param label
	 * @param borderPosition
	 */
	public void addLabel(String label, String borderPosition) {
		assertBorderPositionValid(borderPosition);
		
		this.frame.addJComponent(new JLabel(label), borderPosition);
	}
	
	/**
	 * Adds a JComponent to an edge.
	 * 
	 * @param comp
	 * @param borderPosition
	 */
	public void addJComponent(JComponent comp, String borderPosition) {
		assertBorderPositionValid(borderPosition);
		this.frame.addJComponent(comp, borderPosition);
	}
	
	/**
	 * Checks to see if the border position is one of the four
	 * valid edge positions.
	 * @param borderPosition
	 */
	private void assertBorderPositionValid(String borderPosition) {
		assert (borderPosition == BorderLayout.NORTH ||
				borderPosition == BorderLayout.EAST ||
				borderPosition == BorderLayout.WEST ||
				borderPosition == BorderLayout.SOUTH);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (keyDebug) {
			System.out.println("Key Pressed. ID:" + e.getID()
					+ " KeyCode:" + e.getKeyCode()
					+ " KeyChar:" + e.getKeyChar()
					+ " KeyModifiers:" + e.getModifiers());
					
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (keyDebug) {
			System.out.println("Key Released. ID:" + e.getID()
					+ " KeyCode:" + e.getKeyCode()
					+ " KeyChar:" + e.getKeyChar()
					+ " KeyModifiers:" + e.getModifiers());
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (keyDebug) {
			System.out.println("Key Typed. ID:" + e.getID()
					+ " KeyCode:" + e.getKeyCode()
					+ " KeyChar:" + e.getKeyChar()
					+ " KeyModifiers:" + e.getModifiers());
			
		}
	}	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (mouseMotionDebug) {
			System.out.println("Mouse Dragged ( " + e.getX() + "," + e.getY()
					+ "). Button:" + e.getButton()
					+ " Modifiers:" + e.getModifiers());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (mouseMotionDebug) {
			System.out.println("Mouse Moved ( " + e.getX() + "," + e.getY()
					+ "). Button:" + e.getButton()
					+ " Modifiers:" + e.getModifiers());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (mouseDebug) {
			System.out.println("Mouse Clicked ( " + e.getX() + "," + e.getY()
					+ "). Button:" + e.getButton()
					+ " Modifiers:" + e.getModifiers());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (mouseDebug) {
			System.out.println("Mouse Entered ( " + e.getX() + "," + e.getY()
					+ "). Button:" + e.getButton()
					+ " Modifiers:" + e.getModifiers());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (mouseDebug) {
			System.out.println("Mouse Exited ( " + e.getX() + "," + e.getY()
					+ "). Button:" + e.getButton()
					+ " Modifiers:" + e.getModifiers());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (mouseDebug) {
			System.out.println("Mouse Pressed ( " + e.getX() + "," + e.getY()
					+ "). Button:" + e.getButton()
					+ " Modifiers:" + e.getModifiers());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (mouseDebug) {
			System.out.println("Mouse Released ( " + e.getX() + "," + e.getY()
					+ "). Button:" + e.getButton()
					+ " Modifiers:" + e.getModifiers());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (buttonDebug) {
			System.out.println("Action Performed. " + e.getActionCommand()
					+ "Object:" + e.getSource());
		}
	}
	
	public void setKeyDebugMode(boolean keyDebug) {
		this.keyDebug = keyDebug;
	}
	
	public void setMouseDebugMode(boolean mouseDebug) {
		this.mouseDebug = mouseDebug;
	}
	
	public void setMouseMotionDebugMode(boolean mouseMotionDebug) {
		this.mouseMotionDebug = mouseMotionDebug;
	}
	
	public void setButtonDebugMode(boolean buttonDebug) {
		this.buttonDebug = buttonDebug;
	}
	
	public boolean isKeyDebugModeSet() {
		return this.keyDebug;
	}
	
	public boolean isMouseDebugModeSet() {
		return this.mouseDebug;
	}
	
	public boolean isMouseMotionDebugModeSet() {
		return this.mouseMotionDebug;
	}
}