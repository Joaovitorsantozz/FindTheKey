package World;

import java.awt.Graphics;
import java.util.LinkedList;

import Entity.ID;
import Main.Game;

public class WorldHandler {
	LinkedList<World> level = new LinkedList<World>();

	public WorldHandler() {
		for (int i = 0; i < level.size(); i++) {
			World lv = level.get(i);
		}
	}

	public void add(World world) {
		level.add(world);
	}

	public void DeleteObject(World world) {
		level.remove(world);
	}
}
