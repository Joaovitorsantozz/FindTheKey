package Main.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadImage {
	private BufferedImage img;
	private BufferedImage anim[];

	public LoadImage(String dir) {
		if(dir!=null) {
			try {
				img = ImageIO.read(getClass().getResource(dir));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public BufferedImage getImage() {
		return img;
	}

	public BufferedImage[] Cut(int max, int x, int y, int w, int h,BufferedImage img) {
		anim = new BufferedImage[max];
		for (int i = 0; i < max; i++) {
			anim[i] = img.getSubimage(x, y + (i * h), w, h);
		}
		return anim;
	}
	public BufferedImage[] CutHor(int max, int x, int y, int w, int h,BufferedImage img) {
		anim = new BufferedImage[max];
		for (int i = 0; i < max; i++) {
			anim[i] = img.getSubimage(x+(i*w), y, w, h);
		}
		return anim;
	}
}
