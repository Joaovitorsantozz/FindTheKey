package GameObject;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameObjectHandler {
	public List<GameObject> object = new ArrayList<GameObject>();

	private boolean right = false, left = false, jump = false,attack;

	public void update() {
		for (int i = 0; i < object.size(); i++) {
			GameObject e = object.get(i);
			e.tick();
		}

	}

	public void render(Graphics2D g) {
		Collections.sort(object, GameObject.nodeSorter);
		for (int i = 0; i < object.size(); i++) {
			GameObject e = object.get(i);
			e.render(g);
		}

	}

	public void DrawLife(Graphics2D g,double l,double ml,int x,int y,int w,int h,BufferedImage barModel,BufferedImage barfill,BufferedImage gear) {
		
		int curLife = (int) ((l / ml) * w);
		g.drawImage(barfill,x+w, y+h,(barfill.getWidth()*w),(barfill.getHeight()*h),null);
		g.drawImage(barModel,x, y,barModel.getWidth()*w,barModel.getHeight()*h,null);
		g.drawImage(gear,x-15,y-5,gear.getWidth()*3,gear.getHeight()*3,null);
	}
	
	public void add(GameObject obj) {
		object.add(obj);
	}

	public void DeleteObject(GameObject obj) {
		object.remove(obj);
	}	

	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isAttack() {
		return attack;
	}

	public void setAttack(boolean attack) {
		this.attack = attack;
	}
}
