package World;

import Entity.Global.ID;
import Entity.Global.Pixels;
import Main.Game;
import Main.HandlerGame;
import Main.utils.LoadImage;
import World.tiles.PointsPlataform;
import World.tiles.Tile;
import Entity.Global.TileType;

import java.util.Random;


public class Level extends World {
    public int offset;
    public int sort;
    public int[] p;

    public Level(String dir, int Offset) {
        super(dir);
        offset = Offset;
        setDir(dir);
        spr = new LoadImage(getDir()).getImage();
        setWidth(spr.getWidth());
        setHeight(spr.getHeight());
        for (int xx = 0; xx < getWidth(); xx++) {
            for (int yy = 0; yy < getHeight(); yy++) {
                p = new int[getWidth() * getHeight()];
                int pa = spr.getRGB(xx, yy);
                spr.getRGB(0, 0, getWidth(), getHeight(), p, 0, getWidth());
                BitMap(xx, yy, pa);
                if (pa == 0xFFFFFFFF) {
                    sort = new Random().nextInt(100);
                    Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.PolStone));
                    if (p[xx + ((yy - 1) * getWidth())] != 0xFFFFFFFF) {
                        if (yy > 5) {
                            if (sort < 60)
                                Game.handler.add(new Tile(xx * 32, yy * 32 - 32, ID.Block, TileType.DownFloor));
                            else Game.handler.add(new Tile(xx * 32, yy * 32 - 32, ID.Block, TileType.DownMusg));
                        }
                    }
                }

                if (pa == Pixels.PLATAFORM_POINT) {
                    Game.handler.add(new PointsPlataform(xx * 32, yy * 32, calcDistance(xx, yy), ID.StartPoint));
                    Game.handler.add(new PointsPlataform(calcDistance(xx,yy), yy * 32, calcDistance(xx,yy), ID.FinalPoint));
                }
            }
        }
    }


    public int getPixel(int x, int y) {
        int[] p;
        int pa = 0;

        p = new int[getWidth() * getHeight()];
        pa = spr.getRGB(x, y);
        spr.getRGB(0, 0, getWidth(), getHeight(), p, 0, getWidth());

        return pa;
    }

    public int calcDistance(int x, int y) {

        int f = 0;
        int distance = 0;
        for (int xx = 0; xx < getWidth(); xx++) {
            for (int yy = 0; yy < getHeight(); yy++) {
                int pixel = getPixel(x, y);
                if (pixel == Pixels.PLATAFORM_POINT &&
                        getPixel(x  + distance, y) != Pixels.TILE) {
                    distance++;
                }
                if (getPixel(x + distance, y ) == Pixels.TILE) {
                    f = (x* 32 + distance * 32) - 32;
                    break;
                }
            }
        }
        return f;

    }
}