import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 * 
 * @author Pransu
 * Main class is the controller for the speech recognition system.
 *
 */

public class Controller {

	public static void main(String[] args) {
		
		TargetDataLine tLine = null;
		int size = 0, hz = 0, length = 0;
		AudioFormat format = new AudioFormat(14, 16000, 1, false, false);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format); // format is an AudioFormat object
		
		if (!AudioSystem.isLineSupported(info))
		    System.out.println("Error!");
		    
		// Obtain and open the line.
		try {
		    tLine = (TargetDataLine) AudioSystem.getLine(info);
		    tLine.open(format);
		} catch (LineUnavailableException ex) {
		    System.out.println("Error!"); 
		}
		
		byte[] b = new byte[100];
		
		tLine.start();
		tLine.read(b, 0, 100);
		
	}
}
