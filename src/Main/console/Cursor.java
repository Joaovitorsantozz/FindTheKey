package Main.console;

import Main.MouseInput;
import Main.utils.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cursor {
    public BufferedImage cursor=new LoadImage("/UI/Cursor.png").getImage();
    public  int x,y;
    public boolean Click,click2;
    public void Update(){
        x=MouseInput.x;
        y=MouseInput.y;
    }
    public void draw(Graphics g){
        g.drawImage(cursor,x,y,32,32,null);
    }
    public Rectangle getBound(){
        return new Rectangle(x,y,32,32);
    }
}
