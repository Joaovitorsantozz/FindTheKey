package World;

import Graphics.UI.Cronometer;

import java.awt.image.BufferedImage;

public abstract class World {
	private int WIDTH, HEIGHT;
	public BufferedImage spr;
	private String dir;

	public World(String dir) {
		this.dir = dir;
	}

	public BufferedImage getSpr() {
		return spr;
	}

	public void setSpr(BufferedImage spr) {
		this.spr = spr;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getWidth() {
		return WIDTH;
	}

	public void setWidth(int wIDTH) {
		WIDTH = wIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public void setHeight(int hEIGHT) {
		HEIGHT = hEIGHT;
	}

	public abstract void BitMap(int xx ,int yy);


}
