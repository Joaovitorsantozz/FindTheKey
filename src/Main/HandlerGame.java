package Main;

import GameObject.Camera;
import Graphics.SpriteSheet;
import Graphics.UI.Cronometer;
import Graphics.UI.Inventory;
import Main.console.ConsoleHandler;
import Main.console.ConsoleStyle;
import Main.console.Cursor;
import Main.utils.FontStyle;
import World.Level;
import World.LevelSwitch;
import Graphics.*;

import java.awt.*;

public class HandlerGame {
    public static FontStyle font;
    public static SpriteSheet spr;
    public Camera cam;
    public static Level level;
    public Cronometer clock;
    public Inventory invent;
    public Parallax parallax;
    public ConsoleStyle con;
    public static ConsoleHandler ch;
    public static boolean tran;
    public Transition trans=new Transition();
    public HandlerGame() {
        spr = new SpriteSheet("/SpriteSheet.png");
        font = new FontStyle();
        cam = new Camera(0, 0);
        //Quando coloca o levelswitch ele buga e vai 2x o primeiro level
        level = new Level("/Level/level1.png", -420);
        clock = new Cronometer(40, 40, 0, 15);
        invent = new Inventory(60, 150, 1);
        parallax = new Parallax();
        con=new ConsoleStyle(350,Game.H-60);
        ch=new ConsoleHandler();
    }

    public void tick() {
        clock.tick();
        parallax.tick();
        try {
            ch.UpdateButtons();
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void render(Graphics g) {
        parallax.render(g);
    }
    public void renderNotAffect(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        clock.render(g);
        invent.render(g2);
        con.drawConsole(g);
        ch.drawButtons(g);
        trans.drawTransition(g2);
    }
}
