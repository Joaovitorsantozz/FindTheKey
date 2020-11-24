package Main.console;

import Entity.ID;
import Entity.Player;
import GameObject.GameObject;
import Main.Game;
import Main.HandlerGame;
import Main.utils.CustomColor;
import Main.utils.FontStyle;
import Main.utils.LoadImage;
import Main.utils.Text.Text;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ConsoleStyle {
    //Variables
    public int width, height;
    public int x, y;
    public BufferedImage cons = new LoadImage("/UI/Console.png").getImage();
    public boolean isDragged, isHide = true;
    public boolean less, more;
    boolean add = true;

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
        } else if (isHide) {
            x = Game.W - width + 260;
        }
        drawVars(g);
    }

    public void drawVars(Graphics g) {

        //Show vars of object
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject e = Game.handler.object.get(i);
            if (e instanceof Player) {
                Field[] fields = e.getClass().getFields();
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
                    String varname = " " + type + " " + name + " = " + value;
                    if(!modif.equals("public final"))
                    addButtons(name, varname, off, g,f,e);
                    try {
                        CheckValue(fields, e,name);
                    } catch (NoSuchMethodException | IllegalAccessException noSuchMethodException) {
                        noSuchMethodException.printStackTrace();
                    }
                }
            }
        }
    }

    private void addButtons(String name, String varname, int off, Graphics g,Field f,GameObject e) {
        if (isDragged) {
            add=true;
            if (!name.equals("nodeSorter"))
                new Text(FontStyle.getFont(20, 20), varname, x + 40, y + 50 + (off * 50))
                        .DrawText(g, CustomColor.darkbluegray, "Default"
                        );
            if (off*2>=HandlerGame.ch.buttons.size()&&add) {
                add = false;
                HandlerGame.ch.add(new ConsoleButton(x + 240 + varname.length(), y + 85 + (off * 50) - 50, 16, 16, null, "+", ButtonsID.More,f,e));
                HandlerGame.ch.add(new ConsoleButton(x + 280 + varname.length(), y + 85 + (off * 50) - 50, 16, 16, null, "-", ButtonsID.Less,f,e));
            }
        } else if (isHide) {
            for (int bi = 0; bi < HandlerGame.ch.buttons.size(); bi++) {
                ConsoleButton ee = HandlerGame.ch.buttons.get(bi);
                if (ee.getIds() == ButtonsID.More ||
                        ee.getIds() == ButtonsID.Less) HandlerGame.ch.remove(ee);
            }
        }
    }

    public void CheckValue(Field[] fields, GameObject e,String name) throws NoSuchMethodException, IllegalAccessException {

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
