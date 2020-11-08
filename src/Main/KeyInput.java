package Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Entity.ID;
import Entity.Player;
import GameObject.GameObject;
import GameObject.GameObjectHandler;
import World.World;

public class KeyInput extends KeyAdapter {
	GameObjectHandler handler;

	public KeyInput(GameObjectHandler hand) {
		this.handler = hand;
	}

	public void keyPressed(KeyEvent e) {
		int b = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject obj = handler.object.get(i);
			if (obj.getId() == ID.Player) {
				if (b == KeyEvent.VK_D) handler.setRight(true);
				if (b == KeyEvent.VK_A) handler.setLeft(true);
				if (b == KeyEvent.VK_SPACE) handler.setJump(true);
				if(b==KeyEvent.VK_SPACE&& Player.canWallJump)Player.wallJump=true;
				if(b==KeyEvent.VK_SPACE&& Player.canWallJump)Player.wallJump=true;

			}
			if(obj instanceof Player)if(b==KeyEvent.VK_E)((Player) obj).interact=true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int b = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject obj = handler.object.get(i);
			if (obj.getId() == ID.Player) {
				if(b==KeyEvent.VK_D)handler.setRight(false);
				if(b==KeyEvent.VK_A)handler.setLeft(false);
				if(b==KeyEvent.VK_SPACE)handler.setJump(false);
				if(obj instanceof Player)if(b==KeyEvent.VK_E)((Player) obj).interact=false;
			}
		}
	}
}
