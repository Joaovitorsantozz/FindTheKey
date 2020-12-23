package Entity;

import Entity.Global.Depth;
import Entity.Global.ID;
import GameObject.GameObject;
import Main.HandlerGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Chain extends GameObject {
    public BufferedImage spr = HandlerGame.spr.getSprite(64, 64, 16, 16);

    public Chain(int x, int y, ID id) {
        super(x, y, id);
        setDepth(Depth.MEDIUM);
        setWidth(32);
        setHeight(32);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        drawDefaultTex(g, spr);
    }

    @Override
    public Rectangle getP() {
        return null;
    }

    @Override
    public Rectangle getRightP() {
        return null;
    }

    @Override
    public Rectangle getLeftP() {
        return null;
    }

    @Override
    public Rectangle getToP() {
        return null;
    }
}
