package Main.console;

import Main.Game;
import Main.utils.CustomColor;
import Main.utils.FontStyle;
import Main.utils.Text.Text;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConsoleButton {
    private int width, height, x, y;
    private BufferedImage icon;
    private boolean onShape;
    public String txt;
    public ButtonsID ids;

    public ConsoleButton(int x, int y, int w, int h, BufferedImage icon, String text, ButtonsID i) {
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
        if (text != null) txt = text;
        else txt = "";
        ids = i;
    }

    public void checkCollision(Cursor cursor) {
        if (ids == ButtonsID.Default) {
            if (cursor.getBound().intersects(getBound())) {
                if (cursor.Click) {
                    //draggred
                    x = Game.W - 350 + 260 - 390;
                    Game.handlergame.con.isDragged = true;
                    Game.handlergame.con.isHide = false;
                }
                if (Game.handlergame.con.isDragged) {
                    if (cursor.click2) {
                        cursor.click2 = false;
                        //Hide
                        x = Game.W - 350 + 260 - 100;
                        Game.handlergame.con.isHide = true;
                        Game.handlergame.con.isDragged = false;
                    }
                }
            }
        } else if (ids == ButtonsID.More) {
            if (cursor.click2) {
                if (cursor.getBound().intersects(getBound())) {
                    Game.handlergame.con.more = true;
                }
            }
        } else if (ids == ButtonsID.Less) {
            if (cursor.click2) {
                if (cursor.getBound().intersects(getBound())) {
                    Game.handlergame.con.less = true;
                }
            }
        }
    }

    public void ButtonDraw(Graphics g) {
        if (!onShape) ButtonIcon(g);
        else ButtonRect(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.blue);
        g2.draw(getBound());
    }

    public void ButtonIcon(Graphics g) {
        g.drawImage(icon, x, y, width, height, null);
    }

    public void ButtonRect(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
        new Text(null, txt, x + 3, y + 13).DrawText(g, Color.blue, "Default");
    }

    public void ButtonMore(Graphics g) {


    }

    public void ButtonLess(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x + 50, y, width, height);
        new Text(null, "-", x + 53, y + 13).DrawText(g, Color.blue, "Default");
    }

    public Rectangle getBound() {
        return new Rectangle(x, y, width, height);
    }
}
