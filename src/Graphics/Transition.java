package Graphics;

import Main.Game;
import Main.utils.FontStyle;
import Main.utils.Text.Text;
import World.LevelSwitch;

import java.awt.*;

public class Transition {
    public int width = 1, height = 0;
    public boolean increase, decrease;
    int cur;
    FontStyle fs=new FontStyle();
    public Text txt;
    //if not use a instance of fontstyle the font is not init
    public Transition() {
        txt= new Text(fs.getFont(180, 125), "Level " + LevelSwitch.LEVEL, 390, 500);
    }
    public void drawTransition(Graphics2D g) {
        if (increase && width < 49 && height < 49) {
            cur=0;
            width++;
            height++;
        }
        if (width >= 48) {
            cur++;
            if (cur >= 60) {
                increase = false;
                decrease = true;
            }
        }
        if (decrease && width >= 0 && height >= 0) {
            height--;
            width--;
        }
        for (int x = 0; x < Game.W / 32; x++) {
            for (int y = 0; y < Game.H / 32; y++) {
                g.setColor(Color.black);
                g.fillRect(x * 48, y * 48, width, height);
            }
        }
        if (width == 0) {
            decrease = false;
            txt.setText("Level "+LevelSwitch.LEVEL);
            txt.DrawText(g, Color.white, "Fadeout");
        }
    }


}
