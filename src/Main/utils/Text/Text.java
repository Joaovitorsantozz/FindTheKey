
package Main.utils.Text;

import java.awt.*;

/**
 * Effects for the text
 */



public class Text {
    private int x,y;
    private String text;
    private final Font font;
    FlashString fs;
    public Text(Font font, String text, int x, int y) {
        this.x=x;
        this.y=y;
        this.text=text;

        if(font!=null)this.font=font;
        else this.font=new Font("arial", Font.BOLD,15);
        fs=new FlashString(font ,x,y);
    }


    public void DrawText(Graphics g ,Color color,String Effect){
        switch (Effect) {
            case "Default" -> Default(g);
            case "Flash" -> fs.draw(g, text);
            case "Gradient" -> new GradientString(font, text, Color.green, Color.cyan).paint(g, x, y);
        }
    }
    public void Default(Graphics g){
       g.setFont(font);
       g.setColor(Color.white);
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

