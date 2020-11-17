package Main.utils.Text;

import javax.swing.*;
import java.awt.*;

public class GradientString extends JPanel {
    ///Variables///
    public final Font font;
    public String text;
    public Color c1,c2;
    //---------------//
    private int offX;

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
    public void animateString(Graphics g,int x,int y){
        Manipulate();
        c1=new Color(c[0], c[1], c[2]);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setFont(font);
        GradientPaint gp = new GradientPaint(
                x,y,
                c1,
                x+text.length()*(font.getSize()/4f)+offX,y,
                c2
        );
        g2D.setPaint(gp);
        g2D.drawString(text,x,y);
    }
    public void Manipulate(){
        ControlCol();

        System.out.println("Red ->"+ c[0]);
        System.out.println("Green ->"+ c[1]);
        System.out.println("Blue ->"+ c[2]);
    }
    public int[] c={
      255,0,0
    };
    public void ControlCol(){
        //less
        if(less[0])c[0]--;
        if(less[1])c[1]--;
        if(less[2])c[2]--;
    

        //negative
        if(c[0]<=1)c[0]=1;
        if(c[1]<=1)c[1]=1;
        if(c[2]<=1)c[2]=1;
        //positive
        if(c[0]>=254)c[0]=254;
        if(c[1]>=254)c[1]=254;
        if(c[2]>=254)c[2]=254;
    }
    public boolean[]less={
      false,false,false
    };
    public boolean[]more={
        false,false,false
    };
}
