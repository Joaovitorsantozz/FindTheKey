package Main.console;

import GameObject.GameObject;
import Main.Game;
import Main.utils.LoadImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConsoleHandler {
    public List<ConsoleButton> buttons = new ArrayList<>();
    public Cursor cursor=new Cursor();
    public ConsoleHandler(){
        add(new ConsoleButton(Game.W-350+260-100,30+Game.H/2,64,64,new LoadImage("/UI/Button.png").getImage(),null,ButtonsID.Default,null,null));
    }
    public void UpdateButtons() throws NoSuchMethodException, IllegalAccessException {
        for(int i=0;i<buttons.size();i++){
            buttons.get(i).checkCollision(cursor);
            cursor.Update();
        }
    }
    public void drawButtons(Graphics g){
        for(int i=0;i<buttons.size();i++){buttons.get(i).ButtonDraw(g);}
        cursor.draw(g);
    }
    public void clear(){
        buttons.clear();
    }
    public void add(ConsoleButton bt){
        buttons.add(bt);
    }
    public void remove(ConsoleButton bt){
        buttons.remove(bt);
    }
}
