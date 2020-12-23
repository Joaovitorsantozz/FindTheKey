package World.tiles;

import Entity.Chain;
import Entity.Global.ID;
import Entity.Global.Pixels;
import GameObject.GameObject;
import Main.Game;
import Main.HandlerGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PointsPlataform extends GameObject {
    private BufferedImage sprite = HandlerGame.spr.getSprite(64, 48, 16, 16);
    private int w = 0;
    private int finalx = 0, distance = 0;

    public PointsPlataform(int x, int y, int finalx, ID id) {
        super(x, y, id);
        setDepth(30);
        setWidth(32);
        setHeight(32);
        this.finalx = finalx;
        createChain();
    }

    @Override
    public void tick() {
    }

    public void createChain() {
        int xx =getX();
        while (xx < finalx+32) {
            xx+=32;
            Game.handler.add(new Chain(xx-32, getY(), ID.Chain));
        }
    }

    @Override
    public void render(Graphics g) {
        if (getDir() == -1)
            g.drawImage(sprite, getX() + 32, getY(), -getWidth(), getHeight(), null);
        else
            g.drawImage(sprite, getX(), getY(), getWidth(), getHeight(), null);

    }

    @Override
    public Rectangle getP() {
        return new Rectangle(getX() + (getWidth() / 4), getY() + (getHeight() / 2), getWidth() / 2, getHeight() / 2 + 25);
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
