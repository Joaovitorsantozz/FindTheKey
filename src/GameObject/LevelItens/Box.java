package GameObject.LevelItens;

import Entity.Global.ID;
import Entity.Player;
import GameObject.GameObject;
import Main.Game;
import Main.HandlerGame;

import World.tiles.Tile;


import java.awt.*;
import java.awt.image.BufferedImage;


public class Box extends GameObject {
    public boolean isFalling;
    public BufferedImage spr;

    public Box(int x, int y, ID id) {
        super(x, y, id);
        setWidth(32);
        setHeight(32);
        spr = HandlerGame.spr.getSprite(80, 0, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        Col();
        Move();
        IsColliding(x,(int)(y+velY));
        if (isFalling) {
            velY += 1.4f;
            if (velY > 15) velY = 15;
        }
    }

    public void Move() {
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject e = Game.handler.object.get(i);
            if (e instanceof Player) {
                if (((Player) e).interact) {
                    if (getRightPBox().intersects(e.getP())) setVelX(-5);
                    if (getLeftPBox().intersects(e.getP())) setVelX(5);

                }else setVelX(0);
            }
        }
    }

    protected void Col() {
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject e = Game.handler.object.get(i);
            if (e instanceof Tile) {
                if (getP().intersects(e.getP())) {
                    y = e.getY() - getHeight();
                    velY = 0;
                    isFalling = false;
                } else {
                    isFalling = true;
                }
                if (getRightP().intersects((e.getP())))
                    x = e.getX() - getWidth();
                if (getLeftP().intersects(e.getP()))
                    x = e.getX() + getWidth();
                if(getToP().intersects(e.getP())){
                    y=e.getY()+getHeight()/2;
                    velY=0;
                }
            }if(e.getId()==ID.Depth){

                if(getP().intersects(e.getP())){
                   Game.handler.DeleteObject(this);
                }
            }
        }
    }
    public void IsColliding(int xnext, int ynext) {
        // Colisão de inimigos com inimigos
        Rectangle enemyCurrent = new Rectangle(xnext, ynext, 32, 32);
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject e = Game.handler.object.get(i);
            if (e == this)
                continue;
            if(e.getId()==ID.Block) {
                Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), 32, 32);
                if (enemyCurrent.intersects(targetEnemy)) {
                    y = e.getY() - getHeight();
                    velY = 0;
                    isFalling = false;
                    return;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
     //   Graphics2D g2 = (Graphics2D) g;
        g.drawImage(spr, getX(), getY(), getWidth(), getHeight(), null);
        g.setColor(Color.white);
    }

    public Rectangle getP() {
        return new Rectangle(getX() + (getWidth() / 4), getY() + (getHeight() / 2), getWidth() / 2, getHeight() / 2 + 25);
    }

    public Rectangle getRightP() {
        return new Rectangle(getX() + (getWidth()) - 5, getY() + 5, 5, getHeight() - 10);
    }

    public Rectangle getLeftP() {
        return new Rectangle(getX() - (getWidth() / 2) + 15, getY() + 5, 5, getHeight() - 10);
    }

    public Rectangle getToP() {
        return new Rectangle(getX() + (getWidth() / 4), getY(), getWidth() / 2, getHeight() / 2);
    }

    public Rectangle getRightPBox() {
        return new Rectangle(getX() + (getWidth()), getY() + 5, 50, getHeight() - 10);
    }

    public Rectangle getLeftPBox() {
        return new Rectangle(getX() - (getWidth())-20, getY() + 5, 50, getHeight() - 10);
    }
}
