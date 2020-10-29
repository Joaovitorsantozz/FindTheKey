package World;

import Entity.ID;
import Graphics.UI.Cronometer;
import Main.Game;

import java.awt.image.BufferedImage;

public abstract class World {
    private int WIDTH, HEIGHT;
    public BufferedImage spr;
    private String dir;

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

    public void BitMap(int xx, int yy) {
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
            if (yy == getHeight() - 3)
                Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.DownFloor));
            else if (yy == 2)
                Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.UpFloor));
        }
    }
}



