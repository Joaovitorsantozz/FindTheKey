package Entity;

import Main.HandlerGame;

import java.awt.image.BufferedImage;
import java.util.logging.Handler;

public enum ID {
	Torch(),
	Player(),
	Block(),
	Depth(),
	Key(),
	Door(),
	Particle(),
	SmalParticle();


	public BufferedImage SetImage(BufferedImage spr) {
		switch(this){
			case Key:spr=HandlerGame.spr.getSprite(64,0,16,16);
			break;
			default:break;
		}
		return spr;
	}

}
