package GameObject;


import Entity.particles.Particles;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameObjectHandler {
	public List<GameObject> object = new ArrayList<>();
	public List<Particles>particles= new ArrayList<>();
	private boolean right = false, left = false, jump = false,attack;
	public void update() {
		for (int i=0;i<object.size();i++) { object.get(i).tick(); }
		for (int i=0;i<particles.size();i++) { particles.get(i).tick(); }
		object.sort(GameObject.nodeSorter);
	}

	public void render(Graphics2D g) {
		for (int i=0;i<object.size();i++) { object.get(i).render(g); }
		for (int i=0;i<particles.size();i++) { particles.get(i).render(g); }

	}
	public void ClearObjects(){
		object.clear();
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


}
