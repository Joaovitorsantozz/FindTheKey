package World.tiles;

import Entity.Global.Depth;
import Entity.Global.ID;
import Entity.Global.TileType;
import GameObject.GameObject;
import Main.Game;
import Main.HandlerGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MovingPlataform extends GameObject {
    private boolean isMoving;
    private boolean right = true, left;
    private int changeSide;
    public BufferedImage spr = HandlerGame.spr.getSprite(0, 80, 16, 16);

    public MovingPlataform(int x, int y, ID id) {
        super(x, y, id);
        setWidth(32);
        setHeight(32);
        setDepth(Depth.MEDIUM + 1);
    }

    @Override
    public void tick() {
        checkPlayer();
        checkCollision();
        x += velX;
    }

    public void checkPlayer() {
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject ee = Game.handler.object.get(i);
            if (ee.getId() == ID.Player) {
                if (isMoving) ee.setVelX(getVelX());
            }
        }
    }

    public void checkCollision() {
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject ee =Game.handler.object.get(i);
            if (ee instanceof Tile||ee instanceof PointsPlataform) {
                if (getRightP().intersects(ee.getP())) {
                    x = getX() - ee.getWidth() / 2 + 15;
                    changeSide++;
                    if (changeSide >= 120) {
                        changeSide = 0;
                        right = false;
                        left = true;
                    }
                }
                if (getLeftP().intersects(ee.getP())) {
                    x = getX() + (ee.getWidth() / 2) - 15;
                    changeSide++;
                    if (changeSide >= 120) {
                        changeSide = 0;
                        right = true;
                        left = false;
                    }
                }
                if (right) setVelX(1);
                if (left) setVelX(-1);
            }
            if (ee.getId() == ID.Player) {
                if (getP().intersects(ee.getP())) {
                    if (velX != 0) {
                        isMoving = true;
                    }
                } else isMoving = false;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawDefaultTex(g, spr);
        g.setColor(Color.white);

    }

    @Override
    public Rectangle getP() {
        return new Rectangle(getX() + (getWidth() / 4), getY() + (getHeight() / 2), getWidth() / 2, getHeight() / 2 + 25);
    }

    @Override
    public Rectangle getRightP() {
        return new Rectangle(getX() + (getWidth()) + 5, getY() + 10, 5, getHeight() - 10);
    }

    public Rectangle getLeftP() {
        return new Rectangle(getX() - 8, getY() + 10, 5, getHeight() - 10);
    }

    @Override
    public Rectangle getToP() {
        return null;
    }
}

