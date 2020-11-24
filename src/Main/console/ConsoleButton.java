package Main.console;

import Entity.Player;
import GameObject.GameObject;
import Main.Game;
import Main.utils.CustomColor;
import Main.utils.FontStyle;
import Main.utils.Text.Text;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;

public class ConsoleButton {
    private int width, height, x, y;
    private BufferedImage icon;
    private boolean onShape;
    public String txt;
    public ButtonsID ids;
    public Field field;
    public FieldValues fieldValues=new FieldValues();
    public GameObject cls;
    public ConsoleButton(int x, int y, int w, int h, BufferedImage icon, String text, ButtonsID i, Field f, GameObject clas) {
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
        field=f;
        cls=clas;
    }

    public void checkCollision(Cursor cursor)throws NoSuchMethodException , IllegalAccessException  {
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
                    if(field.getType().getName().equals("boolean"))fieldValues.setTrue(field,cls);
                    else if(!field.getType().getName().equals("string")){
                        fieldValues.increase=true;
                        fieldValues.Increment(field,cls);
                    }
                }
            }
        } else if (ids == ButtonsID.Less) {
            if (cursor.click2) {
                if (cursor.getBound().intersects(getBound())) {
                    if(field.getType().getName().equals("boolean"))fieldValues.setFalse(field,cls);
                    else if(!field.getType().getName().equals("string")){
                        fieldValues.decrease=true;
                        fieldValues.Decrease(field,cls);
                    }
                }
            }
        }
    }

    public void ButtonDraw(Graphics g) {
        if (!onShape) ButtonIcon(g);
        else ButtonRect(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.blue);
    }

    public void ButtonIcon(Graphics g) {
        g.drawImage(icon, x, y, width, height, null);
    }

    public void ButtonRect(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
        new Text(null, txt, x + 3, y + 13).DrawText(g, Color.blue, "Default");
    }

    public Rectangle getBound() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ButtonsID getIds() {
        return ids;
    }

    public void setIds(ButtonsID ids) {
        this.ids = ids;
    }
}
