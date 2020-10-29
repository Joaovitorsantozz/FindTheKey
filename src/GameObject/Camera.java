package GameObject;

import Main.Game;

public class Camera {
	private float x, y;

	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void tick(GameObject obj) {
		x+=((obj.getX()-x)-Game.W/2)*0.03f;
		y+=((obj.getY()-y)-Game.H/2)*0.03f;
		if(x<=0)x=0;
		if(x>=Game.W-520)x=Game.W-520;
		if(y<=0)y=0;
		if(y>=Game.H-410)y=Game.H-410;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
