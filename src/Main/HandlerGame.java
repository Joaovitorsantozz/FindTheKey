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


    public HandlerGame() {
        spr = new SpriteSheet("/SpriteSheet.png");
        font = new FontStyle();
        cam = new Camera(0, 0);
        level = new Level("/Level/level" + LevelSwitch.LEVEL + ".png", -520);
        clock = new Cronometer(40, 40, 0, 10);
        invent = new Inventory(60, 150, 1);
        parallax = new Parallax();
        ch=new ConsoleHandler();
        con=new ConsoleStyle(350,Game.H-60);
    }

    public void tick() {
        clock.tick();
        parallax.tick();
        ch.UpdateButtons();
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
    }
}
