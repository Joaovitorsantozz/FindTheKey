package World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import Entity.ID;
import Entity.particles.ParticleHandler;
import GameObject.GameObject;
import Main.HandlerGame;

public class Tile extends GameObject {
	private BufferedImage spr;
	private TileType t;


	public Tile(int x, int y, ID id, TileType tt) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		t = tt;
		setDepth(100);
		setWidth(32);
		setHeight(32);
		spr=t.SetImage(spr,this);
		
	}

	@Override
	public void tick() {

		if(getTileType()==TileType.UpLava){
			if(new Random().nextInt(100)==1)new ParticleHandler().CreateParticlesImage(
					5,50,getX(),getY(),0,0.8f,HandlerGame.spr.getSprite(64,16,16,16)
			);
		}
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

	public Rectangle getP() {return new Rectangle((int) (getX() + (getWidth() / 2) - ((getWidth() / 2) / 2)),(int) (getY() + (getHeight() / 2)), getWidth() / 2, getHeight() / 2); }

	public Rectangle getRightP() {
		return new Rectangle(getX(),getY(),getWidth(),getHeight());
	}

	public Rectangle getLeftP() {
		return new Rectangle(getX(), getY() + 5, 5, getHeight() - 10);
	}

	public Rectangle getToP() {
		return new Rectangle(getX() + (getWidth() / 2) - ((getWidth() / 2) / 2), getY(), getWidth() / 2, getHeight() / 2);
	}
}
