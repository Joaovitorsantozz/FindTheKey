package Main.utils.Text;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GradientString extends JPanel {
    ///Variables///
    public final Font font;
    public String text;
    public Color c1,c2;
    //---------------//
    private int offX=220;
    private boolean rr,rg,rb;
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
        //r-0 g-1 b-2
        c1=new Color(c[0], c[1], c[2]);
        c2=new Color(c[2],c[2],c[1]);
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
        //less
        if(less[0])c[0]-=3;
        if(less[1])c[1]-=3;
        if(less[2])c[2]-=3;
        //more
        if(more[0])c[0]+=3;
        if(more[1])c[1]+=3;
        if(more[2])c[2]+=3;
        //negative
        if(c[0]<=1)c[0]=1;
        if(c[1]<=1)c[1]=1;
        if(c[2]<=1)c[2]=1;
        //positive
        if(c[0]>=254)c[0]=254;
        if(c[1]>=254)c[1]=254;
        if(c[2]>=254)c[2]=254;



    }
    public int[] c={
      0,0,0
    };
    public void ControlCol(){
        //red
        if(c[0]<=2&&!rr&&!rb)more[0]=true;
        if(c[0]>=254){
            less[0]=true;
            more[0]=false;
            rr=true;
            rb=false;
        }
        if(c[0]==2)less[0]=false;
        //green

        if(c[1]<=2&&!rg&&rr)more[1]=true;
        if(c[1]>=254){
            less[1]=true;
            more[1]=false;
            rg=true;
        }
        if(c[1]==2)less[1]=false;

        //blue
        if(rr&&rg&&!rb)if(c[2]==2||c[2]==1)more[2]=true;

        if(c[2]>=254&&!rb){
            less[2]=true;
            more[2]=false;
            rb=true;
        }
        if(rb&&c[2]<=2){
            rr=false;
            rg=false;
            rb=false;
        }
        if(c[2]==2)less[2]=false;
        System.out.println("less >>"+Arrays.toString(less));
        System.out.println("more >>"+Arrays.toString(more));
        System.out.println("color >>"+Arrays.toString(c));
    }
    public boolean[]less={
      false,false,false
    };
    public boolean[]more={
        false,false,false
    };
}
