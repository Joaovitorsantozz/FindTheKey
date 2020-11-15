package Main.utils.Text;

import java.awt.*;

public class FlashString extends Effect{
    private boolean increase,decrease;
    private int alpha=255;
    public FlashString(Font font, int x, int y) {
        super(font, x, y);
    }

    @Override
    public void draw(Graphics g,String text) {

        if(alpha>=254)decrease=true;
        if(alpha<=1)increase=true;

        if(decrease&&alpha>=1){
            increase=false;
            alpha-=5;
        }
        if(increase&&alpha<255){
            decrease=false;
            alpha+=5;
        }
        g.setFont(font);
        g.setColor(new Color(255,255,255,alpha));
        g.drawString(text,x,y);
    }
}
