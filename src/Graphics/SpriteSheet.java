package Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage spr;

	public SpriteSheet( String dir) {
		try {
			spr = ImageIO.read(getClass().getResource(dir));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BufferedImage getSprite(int x,int y,int w,int h) {
		return spr.getSubimage(x, y, w, h);
	}
}
