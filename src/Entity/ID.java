package Entity;

import Main.HandlerGame;

import java.awt.image.BufferedImage;
import java.util.logging.Handler;

public enum ID {
	Player(),
	Block(),
	Depth(),
	Key(),
	Door(),
	Particle();


	public BufferedImage SetImage(BufferedImage spr) {
		if (this == ID.Key) {
			spr = HandlerGame.spr.getSprite(64, 0, 16, 16);
		}
		return spr;
	}
}
