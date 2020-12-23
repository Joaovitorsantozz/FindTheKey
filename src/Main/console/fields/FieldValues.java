package Main.console.fields;

import Entity.Global.ID;
import GameObject.GameObject;
import Main.Game;
import Main.HandlerGame;
import Main.console.ButtonsID;
import Main.console.ConsoleButton;
import Main.utils.CustomColor;
import Main.utils.FontStyle;
import Main.utils.Text.Text;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldValues implements FieldManipulate {
    public int valueIncrease = 5, valdecrease = 0;
    public boolean increase, decrease;
    public GameObject obj;

    @Override
    public void Increment(Field field, Object obj) throws IllegalAccessException {
        if (increase) {
            switch (field.getType().getName()) {
                case "float":
                    float actual = field.getFloat(obj);
                    actual++;
                    field.setFloat(obj, actual);
                    break;
                case "int":
                    int act = field.getInt(obj);
                    act++;
                    field.setInt(obj, act);
                    break;
                default:
                    break;
            }
            increase = false;
        }
    }

    @Override
    public void Decrease(Field field, Object obj) throws IllegalAccessException {
        if (decrease) {
            switch (field.getType().getName()) {
                case "float":
                    float actual = field.getFloat(obj);
                    actual--;
                    field.setFloat(obj, actual);
                    break;
                case "int":
                    int act = field.getInt(obj);
                    act--;
                    field.setInt(obj, act);
                    break;
                default:
                    break;
            }
            decrease = false;
        }
    }

    @Override
    public void setTrue(Field field, Object obj) throws NoSuchMethodException, IllegalAccessException {
        if (!field.getType().getName().equals("boolean")) throw new NoSuchMethodException("The type need be a boolean");
        field.set(obj, true);
    }

    @Override
    public void setFalse(Field field, Object obj) throws NoSuchMethodException, IllegalAccessException {
        if (!field.getType().getName().equals("boolean")) throw new NoSuchMethodException("The type need be a boolean");
        field.set(obj, false);
    }

    public void drawVars(Graphics g, int x, int y) {
        for(int i=0;i<Game.handler.object.size();i++) {
            GameObject ee=Game.handler.object.get(i);
            //Show vars of object
            if(ee.getId()==ID.Player) {
                String name = "", type = "", modif = "", value = "", varname = "";
                Field[] fields = ee.getClass().getFields();
                int off = 0;
                for (Field f : fields) {
                    name = f.getName();
                    type = f.getType().getName();
                    modif = Modifier.toString(f.getModifiers());
                    value = "";
                    try {
                        value = String.valueOf(f.get(ee));
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                    varname = " " + type + " " + name + " = " + value;
                    if (!modif.equals("public final")) {
                        off++;
                        addButtons(varname, g, f, ee, Game.handlergame.con.isDragged, Game.handlergame.con.isHide,
                                x, y, off);
                    }
                }
            }
        }
    }

    private synchronized void addButtons(String varname, Graphics g, Field f, GameObject e, boolean bool1, boolean bool2, int x, int y, int off) {
        if (bool1) {
            if (!f.getName().equals("nodeSorter"))
                new Text(FontStyle.getFont(20, 20), varname, x + 40, y + 50 + (off * 50))
                        .DrawText(g, CustomColor.darkbluegray, "Default"
                        );
            if (off * 2 >= HandlerGame.ch.buttons.size()) {
                HandlerGame.ch.add(new ConsoleButton(x + 240 + varname.length(), y + 85 + (off * 50) - 50, 16, 16, null, "+", ButtonsID.More, f, e));
                HandlerGame.ch.add(new ConsoleButton(x + 280 + varname.length(), y + 85 + (off * 50) - 50, 16, 16, null, "-", ButtonsID.Less, f, e));
            }
        } else if (bool2) {
            for (int bi = 0; bi < HandlerGame.ch.buttons.size(); bi++) {
                ConsoleButton ee = HandlerGame.ch.buttons.get(bi);
                if (ee.getIds() == ButtonsID.More ||
                        ee.getIds() == ButtonsID.Less) HandlerGame.ch.remove(ee);
            }
        }
    }

    public GameObject getObj() {
        GameObject player = null;
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject ee = Game.handler.object.get(i);
            if (ee.getId() == ID.Player) player = ee;
        }
        return player;
    }

    public void setObj(GameObject obj) {
        this.obj = obj;
    }
}
