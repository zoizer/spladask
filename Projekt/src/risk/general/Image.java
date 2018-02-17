package risk.general;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Image implements Serializable {
	private static final long serialVersionUID = -2322694784584531760L;
	private transient List<BufferedImage> image;
	
	public Image() {
		image = new ArrayList<BufferedImage>();
	}
	
	public BufferedImage get() {
		return image.get(0);
	}
	
	public void set(BufferedImage img) {
		image.clear();
		image.add(img);
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeInt(image.size());
		for (BufferedImage eachImage : image) {
			ImageIO.write(eachImage,  "png", out);
		}
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		final int imageCount = in.readInt();
		image = new ArrayList<BufferedImage>(imageCount);
		for (int i = 0; i < imageCount; i++) {
			image.add(ImageIO.read(in));
		}
	}
}
