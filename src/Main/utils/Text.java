package Main.utils;

import java.awt.*;

public class Text {
    private int x,y;
    private String text;
    public Font font;

    public Text(Font font ,String text,int x,int y){
        this.x=x;
        this.y=y;
        this.text=text;
        if(font!=null)this.font=font;
        else this.font=new Font("arial", Font.BOLD,15);

    }
    public void DrawText(Graphics g ,Color color){
        g.setColor(color);
        g.drawString(text,x,y);
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
