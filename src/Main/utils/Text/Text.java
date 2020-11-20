
package Main.utils.Text;

import Main.utils.FontStyle;

import java.awt.*;

/**
 * Effects for the text
 */



public class Text {
    private int x,y;
    private String text;
    private final Font font;
    FlashString fs;
    GradientString gradientString;
    public Text(Font font, String text, int x, int y) {
        this.x=x;
        this.y=y;
        this.text=text;

        if(font!=null)this.font=font;
        else this.font= FontStyle.getFont(20,20);
        fs=new FlashString(font ,x,y);
        gradientString=new GradientString(font,text,new Color(222, 11, 11),new Color(85, 22, 187));
    }


    public void DrawText(Graphics g,Color color,String Effect){
        switch (Effect) {
            case "Default" -> Default(g,color);
            case "Flash" -> fs.draw(g, text);
            case "Gradient" -> gradientString.paint(g,x,y);
            case "AnimateString"->gradientString.animateString(g,x,y);
        }
    }
    public void Default(Graphics g,Color color){
       g.setFont(font);
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

