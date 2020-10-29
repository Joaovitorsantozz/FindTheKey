package World;

import Entity.ID;
import Entity.Key;
import GameObject.GameObject;
import Main.Game;

import java.io.IOException;


import javax.imageio.ImageIO;



public class Level extends World {

	public Level(String dir) {
		super(dir);
		setDir(dir);
		try {
			spr = ImageIO.read(getClass().getResource(getDir()));
		} catch (IOException e) {
			System.out.println("Arquivo não encontrado/suportado  ->" + e.getCause());
			e.printStackTrace();
		}
		setWidth(spr.getWidth());
		setHeight(spr.getHeight());
		for (int xx = 0; xx < getWidth(); xx++) {
			for (int yy = 0; yy < getHeight(); yy++) {
				int pixel[] = new int[getWidth() * getHeight()];
				int pa = spr.getRGB(xx, yy);
				spr.getRGB(0, 0,getWidth(), getHeight(), pixel, 0,getWidth());
				Game.handler.add(new Tile(xx*32,yy*32,ID.Back,TileType.Back));
				BitMap(xx,yy);
				if(pa==0xFF0026FF){
					for(int i=0;i<Game.handler.object.size();i++){
						GameObject ee=Game.handler.object.get(i);
						if(ee.getId()==ID.Player){
							ee.setX(xx*32);
							ee.setY(yy*32);
						}
					}
				}
				if(pa==0xFFFFD800)Game.handler.add(new Key(xx*32,yy*32, ID.Key));
				if(pa==0xFFFFFFFF){
					Game.handler.add(new Tile(xx*32,yy*32,ID.Block,TileType.Stone));
					if(pixel[xx+((yy-1)*getWidth())]!=0xFFFFFFFF){
						Game.handler.add(new Tile(xx*32,yy*32-32,ID.Block,TileType.DownFloor));
					}
				}
			}
		}

	}
}