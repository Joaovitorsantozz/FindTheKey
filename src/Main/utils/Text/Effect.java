package Main.utils.Text;

import java.awt.*;
import java.util.LinkedList;

public abstract class Effect {
    protected int x,y;
    protected Font font;
    public Graphics g;

    public Effect(Font font,int x,int y){
      if(font!=null) this.font=font;
      else this.font=new Font("arial",Font.BOLD,100);
      this.x=x;
      this.y=y;
    }

    public void setGraphics(Graphics g){this.g=g;}
    public abstract void draw(Graphics g,String text);
}
