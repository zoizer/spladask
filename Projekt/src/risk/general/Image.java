package risk.general;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * This class represents a serializable image
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class Image implements Serializable {
	private static final long serialVersionUID = -2322694784584531760L;
	
	/**
	 * The image
	 */
	private transient List<BufferedImage> image;
	
	
	public Image() {
		image = new ArrayList<BufferedImage>();
	}
	
	/**
	 * Get the image
	 * @return The Image
	 */
	public BufferedImage get() {
		return image.get(0);
	}
	
	/**
	 * Set the image
	 * @param img The image
	 */
	public void set(BufferedImage img) {
		image.clear();
		image.add(img);
	}
	
	/**
	 * Used to allow good serialization of the image.
	 * @param out Image destination
	 * @throws IOException Unknown fatal error
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeInt(image.size());
		for (BufferedImage eachImage : image) {
			ImageIO.write(eachImage,  "png", out);
		}
	}
	
	/**
	 * Used to allow good serialization of the image.
	 * @param in the Image source
	 * @throws IOException Unknown fatal error
	 * @throws ClassNotFoundException Unknown fatal error
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		final int imageCount = in.readInt();
		image = new ArrayList<BufferedImage>(imageCount);
		for (int i = 0; i < imageCount; i++) {
			image.add(ImageIO.read(in));
		}
	}
}
