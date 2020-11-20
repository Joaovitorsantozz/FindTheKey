package Main.console;

import Main.Game;
import Main.utils.LoadImage;

import java.awt.*;
import java.util.LinkedList;

public class ConsoleHandler {
    public LinkedList<ConsoleButton> buttons=new LinkedList<>();
    public Cursor cursor=new Cursor();
    public ConsoleHandler(){
        add(new ConsoleButton(Game.W-350+260-100,30+Game.H/2,64,64,new LoadImage("/UI/Button.png").getImage()));
    }
    public void UpdateButtons(){
        for(ConsoleButton e:buttons){e.checkCollision(cursor);}
        cursor.Update();
    }
    public void drawButtons(Graphics g){
        for (ConsoleButton e : buttons) { e.ButtonDraw(g); }
        cursor.draw(g);
    }
    public void add(ConsoleButton bt){
        buttons.add(bt);
    }
}
