package org.lekan.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SGImage extends SGObject {
	
	// The image
	private BufferedImage img;
	
	public SGImage(int x, int y, String filename) {
		this.x = x;
		this.y = y;
		
		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.err.println("Unable to open the image \"" + filename + "\"");
			e.printStackTrace();
		}
	}
	
	public SGImage(int x, int y, BufferedImage img) {
		this.x = x;
		this.y = y;
		this.img = img;
	}
	
	public int getWidth() {
		return img.getWidth();
	}
	
	public int getHeight() {
		return img.getHeight();
	}
	
	public int getPixel(int x, int y) {
		return img.getRGB(x, y);
	}
	
	public void setPixel(int x, int y, int rgb) {
		img.setRGB(x, y, rgb);
	}

	@Override
	void paint(Graphics g) {
		g.drawImage(img, x, y, null);
		
	}

}
