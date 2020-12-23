package World;

import Entity.Global.Pixels;
import Entity.Global.TileType;
import Entity.Shooter;
import GameObject.GameObject;
import GameObject.LevelItens.Box;
import GameObject.LevelItens.Door;
import Entity.Global.ID;
import Entity.Key;
import Entity.Player;
import GameObject.LevelItens.Saw;
import GameObject.LevelItens.Torch;
import Main.Game;
import Main.HandlerGame;
import World.tiles.*;

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
     //   Game.handler.add(new Tile(xx*32,yy*32,ID.BackBlock,TileType.Back));
        int max=5;
        for(int x=0;x<max+1;x++){
            if(yy==getHeight()-x||xx==getWidth()-x||xx==x-1||yy==x-1)
                Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.Stone));
            if (yy > 5 && yy < getHeight() - 5) {
                if (xx ==5)
                    Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.LeftFloor));
                else if (xx == getWidth() - 6)
                    Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.RightFloor));
            }
            if (xx > 4 && xx < getWidth() - 5) {
                if (pa != 0xFFFFFFFF) {
                    if (yy == getHeight() - 6 || yy == getHeight() - 7)
                        Game.handler.add(new Tile(xx * 32, yy * 32, ID.Depth, TileType.Lava));
                    else if (yy == getHeight() - 8)
                        Game.handler.add(new Tile(xx * 32, yy * 32, ID.Depth, TileType.UpLava));
                }
                if (yy == max)
                    Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.UpFloor));
            }
        }
        //Circle the map with the tiles ^
        //                              |

        if(pa==0xFF0026FF)Game.handler.add(new Player(xx*32,yy*32,ID.Player,Game.handler));
        if(pa==0xFFFFD800)Game.handler.add(new Key(xx*32,yy*32, ID.Key));
        if(pa==0xFF16100C)Game.handler.add(new Door(xx*32,yy*32-90,ID.Door));
        if(pa==0xFF404040)Game.handler.add(new Saw(xx*32-26,yy*32-64,ID.Depth));
        if(pa==0xFF1E0C00)Game.handler.add(new Box(xx*32,yy*32,ID.Block));
        if(pa==0xFFFF6A00)Game.handler.add(new Torch(xx*32,yy*32,ID.Torch));
        if(pa==0xFFADADAD)Game.handler.add(new FakeBlock(xx*32,yy*32,ID.FakeBlock));
        if(pa==0xFFFF0000)Game.handler.add(new Shooter(xx*32,yy*32-16,ID.Shooter));
        if(pa==0xFF563127)Game.handler.add(new MovingPlataform(xx*32,yy*32,ID.Block));

      //  if(pa==0xFF000000)Game.handler.add(new Tile(xx*32,yy*32,ID.BackBlock,TileType.Back));
    }


}



