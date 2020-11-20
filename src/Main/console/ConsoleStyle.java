package Main.console;

import Entity.ID;
import Entity.Player;
import GameObject.GameObject;
import Main.Game;
import Main.HandlerGame;
import Main.utils.FontStyle;
import Main.utils.LoadImage;
import Main.utils.Text.Text;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.LinkedList;

public class ConsoleStyle {
    //Variables
    private int width, height;
    public int x, y;
    public BufferedImage cons = new LoadImage("/UI/Console.png").getImage();
    public boolean isDragged, isHide = true;
    public boolean less, more;
    boolean add = true;
    int value1 = 0, value2 = 0;

    public ConsoleStyle(int width, int height) {
        this.width = width;
        this.height = height;
        x = Game.W - width + 260;
        y = 30;
    }

    public void drawConsole(Graphics g) {
        g.drawImage(cons, x, y, width, height, null);
        if (isDragged) {
            x = Game.W - width - 30;
            drawVars(g);
        } else if (isHide) x = Game.W - width + 260;

    }

    public void drawVars(Graphics g) {

        //Show vars of object
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject e = Game.handler.object.get(i);
            if (e instanceof Player) {
                Class test = e.getClass();
                Field[] fields = test.getFields();
                int off = 0;
                for (Field f : fields) {
                    off++;
                    String name = f.getName();
                    String type = f.getType().getName();
                    int mod = f.getModifiers();
                    String modif = Modifier.toString(mod);
                    String value = "";
                    try {
                        value = String.valueOf(f.get(e));
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                    String varname = "" + modif + " " + type + " " + name + " " + value;
                    if (name.equals("speed")) {
                        new Text(FontStyle.getFont(20, 20),
                                varname, x + 40, y + 50 + (off * 50)).DrawText(g, Color.white, "Default");
                        if (isDragged) {
                            if (add) {
                                HandlerGame.ch.add(new ConsoleButton(x + 240, y + 85 + (off * 50) - 50, 16, 16, null, "+", ButtonsID.More));
                                HandlerGame.ch.add(new ConsoleButton(x + 280, y + 85 + (off * 50) - 50, 16, 16, null, "-", ButtonsID.Less));
                            }
                        }
                    }
                    CheckValue(fields, e);
                }
            }
        }
    }

    public void CheckValue(Field[] fields, GameObject e) {
        for (Field f : fields) {
            String type = f.getType().getName();

            if (more) {
                more = false;
                try {
                    f.set(e, value1++);
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            } else if (less) {
                less = false;
                try {
                    f.set(e, value1--);
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
