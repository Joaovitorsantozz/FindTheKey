package Graphics.UI;

import Entity.ID;
import GameObject.GameObject;
import Main.Game;
import Main.utils.FontStyle;
import Main.utils.LoadImage;
import World.Level;
import World.LevelSwitch;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;

public class Cronometer {
    public BufferedImage clock;
    private int x,y;
    private int seconds=0,minutes=0,milis;
    public Cronometer(int x, int y,int mm,int sec) {
        clock=new LoadImage("/UI/Clock.png").getImage();
        this.x=x;
        this.y=y;
        this.minutes=mm;
        this.seconds=sec;

    }

    public void tick() {
        CountSeconds(milis,seconds,minutes);
        if(minutes==0&&seconds<=0) LevelSwitch.rest=true;
        setFormatTime();
    }
    public void CountSeconds(int milis,int seconds,int minutes){
        milis++;
        if(milis>60){
            milis=0;
            seconds--;
        }
        if(seconds<=0&&minutes>0){
            seconds=60;
            minutes--;
        }
        if(minutes<=0&&seconds<=0){
            minutes=0;
            seconds=0;
            System.out.println("Tempo esgotado");
        }
        this.milis=milis;
        this.seconds=seconds;
        this.minutes=minutes;
    }
    public int getValue(){
        return seconds=minutes*60;
    }
    public String setFormatTime() {
        String format = "";
        //Set the format of time hour:minutes\\
        if (minutes < 10)format += "0" + minutes + ":";
        else format += minutes + ":";
        if (seconds < 10)format += "0" + seconds;
        else format += seconds;
        //-------------------------------------\\
        return format;
    }

    public void render(Graphics g) {
        createRectangle(g);
        g.drawImage(clock, x, y, clock.getWidth() * 3, clock.getHeight() * 3, null);
        g.setColor(Color.white);
        g.setFont(FontStyle.getFont(40, 20));
        g.drawString(setFormatTime(),x+85,y+60);
    }
    public void createRectangle(Graphics g){
        //escuro
        g.setColor(new Color(135,60,14));
        g.fillRect(x+64,y+30,40*3,16*2);
        g.setColor(new Color(86,38,8));
        for(int i=0;i<5;i++){
            g.drawRect((x+64)+i,(y+30)+i,40*3,16*2);
        }
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

}


