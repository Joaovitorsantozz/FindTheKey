package Main.console;

import Entity.Global.ID;
import GameObject.GameObject;
import Main.Game;
import Main.HandlerGame;
import Main.console.fields.FieldValues;
import Main.utils.LoadImage;
import World.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;


public class ConsoleStyle {
    //Variables
    public int width, height;
    public int x, y;
    public BufferedImage cons = new LoadImage("/UI/Console.png").getImage();
    public boolean isDragged, isHide = true;
    public boolean showvars, showops = true;
    public boolean changestate;
    public ConsoleButton consoleButtons;

    FieldValues fieldValues = new FieldValues();


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
            x = Game.W - width + 320;
        }


        fieldValues.drawVars(g, x, y);

    }

    public void Show(Graphics g) {
        showOps(g);
    }

    public void removeButton(ButtonsID id) {
        for (int i = 0; i < HandlerGame.ch.buttons.size(); i++) {
            ConsoleButton cb = HandlerGame.ch.buttons.get(i);
            if (cb.getIds() == id) HandlerGame.ch.buttons.remove(cb);
        }
    }

    public void ExitButton(boolean canShow) {
        if (canShow) {
            HandlerGame.ch.add(new ConsoleButton(x + 60, y + 30, 32, 32,
                    new LoadImage("/UI/exit.png").getImage(), "", ButtonsID.Exit, null, null));
        } else {
            removeButton(ButtonsID.Exit);
            removeButton(ButtonsID.Less);
            removeButton(ButtonsID.More);
        }
        if (isHide) removeButton(ButtonsID.Exit);

    }

    public void showOps(Graphics g) {
        //Add the button class
        int offset = 0;
        String name = null;
        GameObject object = null;
        if (isDragged) {
            for (int i = 0; i < Game.handler.object.size(); i++) {
                GameObject ee = Game.handler.object.get(i);
                if (!(ee instanceof Tile)) {
                    offset++;
                    object = ee;
                    name = object.getClass().getSimpleName();
                    if (showops)
                        HandlerGame.ch.add(new ConsoleButton(x + 110, (y + 40) + offset * 50, 64 + name.length(), 32, null,
                                name, ButtonsID.ClassVars, null, object));
                }
            }
        }
        if (isHide || showvars) removeButton(ButtonsID.ClassVars);

    }

    public GameObject getPlayer() {
        GameObject player = null;
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject ee = Game.handler.object.get(i);
            if (ee.getId() == ID.Player) player = ee;
        }
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
