package World;

import Main.HandlerGame;

import java.awt.image.BufferedImage;

public enum TileType {
	UpFloor(),
	DownFloor(),
	RightFloor(),
	LeftFloor(),
	Stone(),
	Back();
	public BufferedImage SetImage(BufferedImage spr){
		switch (this) {
			case UpFloor:spr=HandlerGame.spr.getSprite(0,16,16,16);
				break;
			case DownFloor:spr=HandlerGame.spr.getSprite(0,0,16,16);
				break;
			case LeftFloor:spr=HandlerGame.spr.getSprite(0,32,16,16);
			    break;
			case RightFloor:spr=HandlerGame.spr.getSprite(0,48,16,16);
				break;
			case Stone:spr=HandlerGame.spr.getSprite(0,64,16,16);
				break;
			case Back:spr=HandlerGame.spr.getSprite(16,0,16,16);
				break;
			default:break;
		}
		return spr;
	}
}
