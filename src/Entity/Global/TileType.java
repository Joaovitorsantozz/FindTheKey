package Entity.Global;

import Entity.Global.Depth;
import Main.HandlerGame;
import World.tiles.Tile;

import java.awt.image.BufferedImage;

public enum TileType {
    UpFloor(), UpSolid(),
    DownFloor(), DownFlor(),DownMusg(),
    RightFloor(), RightSolid(),
    LeftFloor(), LeftSolid(),
    Stone(),
    Back(),FalseBlock(),
    PolStone(),
    Lava(),
    UpLava();

    public BufferedImage SetImage(BufferedImage spr, Tile t) {
        switch (this) {
            case UpFloor:
                spr = HandlerGame.spr.getSprite(0, 16, 16, 16);
                break;
            case DownFloor:
                spr = HandlerGame.spr.getSprite(0, 0, 16, 16);
                break;
            case LeftFloor:
                spr = HandlerGame.spr.getSprite(0, 32, 16, 16);
                break;
            case RightFloor:
                spr = HandlerGame.spr.getSprite(0, 48, 16, 16);
                break;
            case Stone:
                spr = HandlerGame.spr.getSprite(0, 64, 16, 16);
                break;
            case Back:
                spr = HandlerGame.spr.getSprite(16, 0, 16, 16);
                t.setDepth(Depth.LITTLE-1);
                break;
            case PolStone:
                spr = HandlerGame.spr.getSprite(0, 64, 16, 16);
                break;
            case Lava:
                spr = HandlerGame.spr.getSprite(16, 16, 16, 16);
                break;
            case UpLava:
                spr = HandlerGame.spr.getSprite(16, 32, 16, 16);
                break;
            case UpSolid:
                spr = HandlerGame.spr.getSprite(0, 80, 16, 16);
                break;
            case RightSolid:
            case DownMusg:
                spr = HandlerGame.spr.getSprite(0, 96, 16, 16);
                break;
            case LeftSolid:
                spr = HandlerGame.spr.getSprite(0, 112, 16, 16);
                break;
            case DownFlor:
                spr = HandlerGame.spr.getSprite(0, 128, 16, 16);
                break;
            default:
                break;
        }
        return spr;
    }
}
