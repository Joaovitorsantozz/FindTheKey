package GameObject.LevelItens;

import Entity.ID;
import Entity.Player;
import GameObject.GameObject;
import Main.Game;
import Main.HandlerGame;
import Main.utils.Animator;
import Main.utils.LoadImage;
import World.Level;
import World.LevelSwitch;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Door extends GameObject {
    public BufferedImage spr;
    public BufferedImage lock;
    public BufferedImage atualDraw = null;
    public BufferedImage[] open;
    public BufferedImage openld;
    public boolean showDraw, isOpen;
    Animator an;
    int maxFrames = 5, maxIndex = 9;

    public Door(int x, int y, ID id) {
        super(x, y, id);
        spr = new LoadImage("/GameObject/Door.png").getImage();
        lock = new LoadImage("/GameObject/Lock.png").getImage();
        openld = new LoadImage("/GameObject/Dooropen.png").getImage();
        open = new LoadImage(null).Cut(9, 0, 0, 40, 45, openld);
        atualDraw = spr;
        setWidth(32 * 3);
        setHeight(41 * 3);
        setDepth(9);
        an = new Animator(maxFrames, maxIndex);
    }

    @Override
    public void tick() {
        showDraw = false;
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject e = Game.handler.object.get(i);
            if (e.getId() == ID.Player) {
                if (calculateDistance(getX(), getY(), e.getX(), e.getY()) < 60) {
                    atualDraw = lock;
                    showDraw = true;
                }
            }
        }
        Open();
    }

    public void Open() {
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject e = Game.handler.object.get(i);
            if (e instanceof Player) {
                if (calculateDistance(getX(), getY(), e.getX(), e.getY()) < 160) {
                    if (((Player) e).interact) {
                        if (((Player) e).hasKey) {
                            an.setAnimation(open);
                            isOpen = true;
                            ((Player) e).hasKey = false;
                        }
                    }
                }
            }
            if (an.getIndex() >= 8) {
                LevelSwitch.next = true;
                showDraw = false;
                isOpen = false;
                spr = openld.getSubimage(0, 45 * 3, 40, 45);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (!isOpen) {
            g.drawImage(spr, getX(), getY(), getWidth(), getHeight(), null);
            if (showDraw)
                g.drawImage(atualDraw, getX() + 32, getY() - 60, atualDraw.getWidth() * 3, atualDraw.getHeight() * 3, null);
        } else g.drawImage(an.getAnimation(), getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public Rectangle getP() {
        return null;
    }

    public Rectangle getRightP() {
        return null;
    }

    public Rectangle getLeftP() {
        return null;
    }

    public Rectangle getToP() {
        return null;
    }
}
