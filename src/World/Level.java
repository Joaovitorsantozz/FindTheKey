package World;

import Entity.ID;
import Main.Game;

import javax.imageio.ImageIO;
import java.io.IOException;


public class Level extends World {
	public int offset;
	public Level(String dir,int Offset) {
		super(dir);
		offset=Offset;
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
				int[] pixel = new int[getWidth() * getHeight()];
				int pa = spr.getRGB(xx, yy);
				spr.getRGB(0, 0,getWidth(), getHeight(), pixel, 0,getWidth());
				BitMap(xx,yy,pa);
				if(pa==0xFFFFFFFF){
					Game.handler.add(new Tile(xx*32,yy*32,ID.Block,TileType.PolStone));
					if(pixel[xx+((yy-1)*getWidth())]!=0xFFFFFFFF){
						Game.handler.add(new Tile(xx*32,yy*32-32,ID.Block,TileType.DownFloor));
					}
				}
			}
		}
	}
}