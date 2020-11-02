package GameObject;


import Entity.particles.Particles;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameObjectHandler {
	public List<GameObject> object = new ArrayList<GameObject>();
	public List<Particles>particles=new ArrayList<Particles>();
	private boolean right = false, left = false, jump = false,attack;

	public void update() {
		for (int i = 0; i < object.size(); i++) {
			GameObject e = object.get(i);
			e.tick();
		}
		for(int i=0;i<particles.size();i++){
			Particles p=particles.get(i);
			p.tick();
		}
	}

	public void render(Graphics2D g) {
		Collections.sort(object, GameObject.nodeSorter);
		for (int i = 0; i < object.size(); i++) {
			GameObject e = object.get(i);
			e.render(g);
		}
		for(int i=0;i<particles.size();i++){
			Particles p=particles.get(i);
			p.render(g);
		}
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
