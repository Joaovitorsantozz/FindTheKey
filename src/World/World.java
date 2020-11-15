package World;

import GameObject.LevelItens.Box;
import GameObject.LevelItens.Door;
import Entity.ID;
import Entity.Key;
import Entity.Player;
import GameObject.LevelItens.Saw;
import Main.Game;

import java.awt.image.BufferedImage;

public  class World {
    private int WIDTH, HEIGHT;
    public BufferedImage spr;
    private String dir;
    public boolean next=false;
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

    public void BitMap(int xx, int yy,int pa) {
        if (yy == getHeight() - 1 || xx == getWidth() - 1 || xx == 0 || yy == 0) {
            Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.Stone));
        }
        if (yy == getHeight() - 2 || xx == getWidth() - 2 || xx == 1 || yy == 1) {
            Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.Stone));
        }
        //Circle the map with the tiles//
        if (yy > 2 && yy < getHeight() - 2) {
            if (xx == 2)
                Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.LeftFloor));
            else if (xx == getWidth() - 3)
                Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.RightFloor));
        }
        if (xx > 1 && xx < getWidth() - 2) {
            if (pa != 0xFFFFFFFF) {
                if (yy == getHeight() - 3 || yy == getHeight() - 4)
                    Game.handler.add(new Tile(xx * 32, yy * 32, ID.Depth, TileType.Lava));
                else if (yy == getHeight() - 5)
                    Game.handler.add(new Tile(xx * 32, yy * 32, ID.Depth, TileType.UpLava));
            }
            if (yy == 2)
                Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.UpFloor));
        }
        if(pa==0xFF0026FF)Game.handler.add(new Player(xx*32,yy*32,ID.Player,Game.handler));
        if(pa==0xFFFFD800)Game.handler.add(new Key(xx*32,yy*32, ID.Key));
        if(pa==0xFF16100C)Game.handler.add(new Door(xx*32,yy*32-90,ID.Door));
        if(pa==0xFF404040)Game.handler.add(new Saw(xx*32-26,yy*32-64,ID.Depth));
        if(pa==0xFF1E0C00)Game.handler.add(new Box(xx*32,yy*32,ID.Block));
    }

}



