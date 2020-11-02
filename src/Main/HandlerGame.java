package Main;

import java.awt.Graphics;

import Entity.ID;
import GameObject.Camera;
import Graphics.Parallax;
import Graphics.SpriteSheet;
import Graphics.UI.Cronometer;
import Graphics.UI.Inventory;
import Main.utils.FontStyle;
import World.Level;
import World.WorldHandler;

public class HandlerGame {

	public static FontStyle font;
	public static SpriteSheet spr;
	private WorldHandler worldhandler;
	public Camera cam;
	public static Level level;
	public Cronometer clock;
	public Inventory invent;
	public HandlerGame() {
		spr = new SpriteSheet("/SpriteSheet.png");
		font = new FontStyle();
		worldhandler = new WorldHandler();
		cam = new Camera(0, 0);
		level = new Level("/Level/map.png");
		worldhandler.add(level);
		clock=new Cronometer(40,40,3);
		invent=new Inventory(60,150,1);
	}

}
