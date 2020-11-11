
package Main.utils;
import java.awt.*;

/**
 * Effects for the text
 */

class Effect{
    Font font;
    Color color;
    String text;
    int alpha=255;
    boolean increase=false,decrease = false;
    public void Default(Graphics g,int x,int y){
        g.setFont(font);
        g.setColor(color);
        g.drawString(text,x,y);
    }
    public void Flash(Graphics g,int x,int y){
        //SetValue
        if(alpha==255){
            decrease=true;
            increase=false;
        }
        if(alpha<=1){
            increase=true;
            decrease=false;
        }
        //Increase or decrease
        if(alpha>0&&decrease)alpha-=5;
        if(increase&&alpha<255)alpha+=5;
        g.setFont(font);
        g.setColor(new Color(color.getRed(),color.getGreen(),color.getBlue(),alpha));
        g.drawString(text,x,y);
        System.out.println(alpha);
    }
    public void setAll(String text,Font font,Color color){
        this.font=font;
        this.color=color;
        this.text=text;
    }

}

public class Text extends Effect{
    private int x,y;
    private String text;
    private final Font font;
    public Text(Font font, String text, int x, int y) {
        this.x=x;
        this.y=y;
        this.text=text;
        if(font!=null)this.font=font;
        else this.font=new Font("arial", Font.BOLD,15);
    }


    public void DrawText(Graphics g ,Color color,String Effect){
        setAll(text,font,color);
        if(Effect.equals("Default"))Default(g,x,y);
        else if(Effect.equals("Flashes"))Flash(g,x,y);
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

