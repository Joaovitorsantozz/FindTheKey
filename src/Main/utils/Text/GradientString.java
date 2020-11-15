package Main.utils.Text;

import javax.swing.*;
import java.awt.*;

public class GradientString extends JPanel {
    ///Variables///
    public final Font font;
    public String text;
    public Color c1,c2;
    //---------------//
    public GradientString(Font f,String txt,Color c1,Color c2) {
        text=txt;
        this.c1=c1;
        this.c2=c2;
        font=f;
        /////////////////////////
        setSize(300, 300);
        setBackground(Color.white);
    }

    /**
     *Draw the gradientString
     */
    public void paint(Graphics g,int x,int y) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setFont(font);
        GradientPaint gp = new GradientPaint(
                x,y,
                c1,
                x+text.length()*font.getSize()/2f,y,
                c2
        );
        g2D.setPaint(gp);
        g2D.drawString(text,x,y);


    }
}
