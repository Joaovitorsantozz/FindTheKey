package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Comparator;

import Entity.ID;


public abstract class GameObject {
	protected int x, y,dir;
	protected float velX = 0, velY = 0;
	protected ID id;
	private int depth;
	private int width, height;
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
	protected static Comparator<GameObject> nodeSorter = Comparator.comparingInt(n0 -> n0.depth);
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
		return velX != 0;
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
		return this.x;
	}
	public void setX(int nx) {
		this.x = nx;
	}
	public int getY() {
		return  this.y;
	}
	public void setY(int ny) {
		this.y = ny;
	}
	public float getVelX() { return velX; }
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
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	protected void gravity(boolean isAffect, float gravity){
		if(isAffect){
			velY+=gravity;
			if(velY>10)
				velY=10;
		}
	}

}
