package Main.console;

import Main.Game;
import Main.utils.CustomColor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConsoleButton {
    private int width, height, x, y;
    private BufferedImage icon;
    private boolean onShape;

    public ConsoleButton(int x, int y, int w, int h, BufferedImage icon) {
        this.x = x;
        this.y = y;
        if (icon != null) {
            this.icon = icon;
            width = icon.getWidth() * 2;
            height = icon.getHeight() * 2;
        } else {
            onShape = true;
            width = w;
            height = h;
        }
    }

    public void checkCollision(Cursor cursor) {
        if (cursor.getBound().intersects(getBound())) {
            if (cursor.Click) {
                //draggred
                x = Game.W - 350 + 260 - 390;
                Game.handlergame.con.isDragged = true;
                Game.handlergame.con.isHide = false;
            }
            if (Game.handlergame.con.isDragged) {
                if (cursor.click2) {
                    cursor.click2=false;
                    //Hide
                    x = Game.W - 350 + 260 - 100;
                    Game.handlergame.con.isHide = true;
                    Game.handlergame.con.isDragged = false;
                }
            }
        }
    }

    public void ButtonDraw(Graphics g) {
        if (!onShape) ButtonIcon(g);
        else ButtonRect(g);
        Graphics2D g2 = (Graphics2D) g;

    }

    public void ButtonIcon(Graphics g) {
        g.drawImage(icon, x, y, width, height, null);
    }

    public void ButtonRect(Graphics g) {
        g.setColor(CustomColor.lightbrown);
        g.fillRect(x, y, width, height);
        for (int i = 0; i < 3; i++) {
            g.setColor(CustomColor.brown);
            g.drawRect(x, y, width + i, height + i);
        }
    }

    public Rectangle getBound() {
        return new Rectangle(x, y, width, height);
    }
}
