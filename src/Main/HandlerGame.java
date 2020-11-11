package Main;

import GameObject.Camera;
import Graphics.SpriteSheet;
import Graphics.UI.Cronometer;
import Graphics.UI.Inventory;
import Main.utils.FontStyle;
import Main.utils.Text;
import World.Level;
import World.LevelSwitch;

public class HandlerGame {
	public static FontStyle font;
	public static SpriteSheet spr;
	public Camera cam;
	public static Level level;
	public Cronometer clock;
	public Inventory invent;
	public HandlerGame() {
		spr = new SpriteSheet("/SpriteSheet.png");
		font = new FontStyle();
		cam = new Camera(0, 0);
		level = new Level("/Level/level"+ LevelSwitch.LEVEL +".png",-520);
		clock=new Cronometer(40,40,0,10);
		invent=new Inventory(60,150,1);
	}

}
