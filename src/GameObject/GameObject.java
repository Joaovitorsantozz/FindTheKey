package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Comparator;

import Entity.ID;
import Main.Game;


public abstract class GameObject {
	protected int x, y,dir;
	protected float velX = 0, velY = 0;
	protected ID id;
	protected int depth;
	private int width, height;
	private int Damage;
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getP();
	public abstract Rectangle getRightP();
	public abstract Rectangle getLeftP();
	public abstract Rectangle getToP();
	
	public static Comparator<GameObject> nodeSorter = (Comparator<GameObject>) (n0, n1) -> {
		if (n1.depth < n0.depth)
			return +1;
		if (n1.depth > n0.depth)
			return -1;
		return 0;
	};

	protected boolean Col(GameObject obj, Rectangle rect) {
		for (int i = 0; i < Game.handler.object.size(); i++) {
			GameObject e = Game.handler.object.get(i);
			if (e.getId() == ID.Block) {
				if (rect.intersects(e.getP())) {
					obj.y += obj.velY * -1;
					obj.x += obj.velX * -1;
					return true;
				}
			}
		}
		return false;
	}
	public double calculateDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	protected boolean Moving(int speed,GameObjectHandler hand) {
		// Right
		if (hand.isRight()) {velX = speed;setDir(1);}
		else if(!hand.isLeft())velX=0;
		// Left
		if (hand.isLeft()) {velX = -speed;setDir(-1);}
		else if(!hand.isRight())velX=0;
		// Value

		//
		if (velX != 0)return true;
		return false;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getX() {
		return (int) this.x;
	}

	public void setX(int nx) {
		this.x = nx;
	}

	public int getY() {
		return (int) this.y;
	}

	public void setY(int ny) {
		this.y = ny;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public ID getId() {
		return id;
	}

	public int getDamage() {
		return Damage;
	}

	public void setDamage(int damage) {
		Damage = damage;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	protected float gravity(boolean isAffect,float gravity){
		if(isAffect){
			velY+=gravity;
			if(velY>10)
				velY=10;
		}
		return velY;
	}
	/*
	 * protected void Gravity(GameObject obj, boolean jump) { vspd
	 * +=obj.getGravity(); if (!CheckCol((int) x, (int) (y + 1), obj) && jump) {
	 * vspd = -8; jump = false; }
	 * 
	 * if (!CheckCol((int) x, (int) (y + obj.getVspd()), obj)) {
	 * 
	 * int signVsp = 0; if (vspd >= 0) { signVsp = 1; } else { signVsp = -1; } while
	 * (CheckCol((int) x, (int) (y + signVsp), obj)) { y = y + signVsp; } vspd = 0;
	 * }
	 * 
	 * y = (int) (y + vspd); }
	 */
}
