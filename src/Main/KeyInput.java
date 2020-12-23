package Main;

import java.awt.event.*;
import java.util.Arrays;

import Entity.Global.ID;
import Entity.Player;
import GameObject.GameObject;
import GameObject.GameObjectHandler;

public class KeyInput implements KeyListener, FocusListener {
	GameObjectHandler handler;
	public boolean[] press=new boolean[65536];
	public KeyInput(GameObjectHandler hand) {
		this.handler = hand;
	}

	@Override
	public void keyTyped(KeyEvent e) {

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
			}
			if(obj instanceof Player)if(b==KeyEvent.VK_E)((Player) obj).interact=true;
		}
		Arrays.fill(press, true);
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
		Arrays.fill(press, false );
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {
		Arrays.fill(press, false);
	}
}
