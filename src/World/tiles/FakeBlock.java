package World.tiles;

import Entity.Global.Depth;
import Entity.Global.ID;
import GameObject.GameObject;
import Main.utils.CustomColor;

import java.awt.*;

public class FakeBlock extends GameObject {
    public static boolean fade;
    private int alpha=255;
    public FakeBlock(int x, int y, ID id) {
        super(x, y, id);
        setWidth(32);
        setHeight(32);
        setDepth(Depth.HIGHT);
        fade=false;
    }

    @Override
    public void tick() {
        Fadeuot();
    }
    public void Fadeuot(){
        if(fade){
            if(alpha>1)alpha-=2;
            else if(alpha==0)fade=false;
        }
    }
    @Override
    public void render(Graphics g) {
        Color def=CustomColor.darkbluegray;
        g.setColor(new Color(def.getRed(),def.getGreen(),def.getBlue(),alpha));
        g.fillRect(x,y,64,64);
    }

    @Override
    public Rectangle getP() {return new Rectangle((int) (getX() + (getWidth() / 2) - ((getWidth() / 2) / 2)),(int) (getY() + (getHeight() / 2)), getWidth() / 2, getHeight() / 2); }


    @Override
    public Rectangle getRightP() {
        return null;
    }

    @Override
    public Rectangle getLeftP() {
        return null;
    }

    @Override
    public Rectangle getToP() {
        return null;
    }
}
