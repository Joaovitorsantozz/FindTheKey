package Entity;

import Entity.Global.ID;
import GameObject.GameObject;
import Main.Game;
import Main.HandlerGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Shooter extends GameObject {
    private double rot = 0;
    private BufferedImage spritebase = HandlerGame.spr.getSprite(64, 41, 16, 7),
            shooter = HandlerGame.spr.getSprite(64, 35, 16, 6);
    private GameObject target = null;

    public Shooter(int x, int y, ID id) {
        super(x, y, id);
        setWidth(32);
        setHeight(32);
        setDepth(5);

    }

    @Override
    public void tick() {
        shoot();
    }

    public GameObject getTarget() {
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject ee = Game.handler.object.get(i);
            if (ee.getId() == ID.Player) target = ee;
        }
        return target;
    }

    public void shoot() {
        System.out.println();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(spritebase, getX(), getY(), 32, 14, null);
        rotateTip(g);
    }

    public void rotateTip(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        this.rot = calcAnglee();
        g2.rotate(rot, x + 16, y+6 );
        g.drawImage(shooter, getX(), getY() - 12, 32, 14, null);
        g2.rotate(-rot, x + 16, y+6 );

    }

    public float calcAnglee() {
        double angle=Math.atan2(getTarget().getX()-x,getTarget().getY()-y);
        return (float)Math.toDegrees(angle);
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
