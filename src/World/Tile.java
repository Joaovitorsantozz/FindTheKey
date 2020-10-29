package World;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entity.ID;
import GameObject.GameObject;
import Main.Game;
import Main.HandlerGame;

public class Tile extends GameObject {
	private BufferedImage spr;
	private TileType t;
	private int w = 32, h = 32;

	public Tile(int x, int y, ID id, TileType tt) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		t = tt;
		setDepth(1);
		setWidth(32);
		setHeight(32);
		spr=t.SetImage(spr);
		
	}

	@Override
	public void tick() {
	
	}



	public TileType getTileType() {
		return t;
	}

	public void setTileType(TileType tile) {
		t = tile;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(spr, getX(), getY(), 32, 32, null);
	}

	@Override

	public Rectangle getP() {return new Rectangle((int) (getX() + (getWidth() / 2) - ((getWidth() / 2) / 2)),(int) (getY() + (getHeight() / 2)), w / 2, h / 2); }

	public Rectangle getRightP() {
		return new Rectangle(getX(),getY(),getWidth(),getHeight());
	}

	public Rectangle getLeftP() {
		return new Rectangle(getX(), getY() + 5, 5, getHeight() - 10);
	}

	public Rectangle getToP() {
		return new Rectangle(getX() + (getWidth() / 2) - ((getWidth() / 2) / 2), getY(), w / 2, h / 2);
	}
}
