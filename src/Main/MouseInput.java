package Main;

import Main.console.Cursor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MouseInput extends MouseAdapter {
    public static int x,y;
    public Cursor cursor;
    public MouseInput(Cursor cursor){this.cursor=cursor;}
    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
      if(e.getButton()==MouseEvent.BUTTON1){
          if(Game.handlergame.con.isHide) cursor.Click=true;
          if(Game.handlergame.con.isDragged)cursor.click2=true;
      }
    }

    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            if(Game.handlergame.con.isHide) cursor.Click=false;
            if(Game.handlergame.con.isDragged)cursor.click2=false;
        }

    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseWheelMoved(MouseWheelEvent e){}

    public void mouseDragged(MouseEvent e){}

    public void mouseMoved(MouseEvent e){
        x=e.getX();
        y=e.getY();
    }
}
