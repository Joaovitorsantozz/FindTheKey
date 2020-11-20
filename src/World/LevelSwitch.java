package World;

import Graphics.UI.Cronometer;
import Main.Game;
import Main.HandlerGame;

public class LevelSwitch {
    public static int LEVEL = 4;
    public static boolean next, rest;
    public int sec, m;

    public void upd() {
        if (next) {
            next = false;
            ToNextLevel();
        }
        if (rest) {
            rest = false;
            Restart();
        }
    }

    public void ToNextLevel() {
        Game.handler.ClearObjects();
        LEVEL++;
        String nt = "/Level/level" + LEVEL + ".png";
        HandlerGame.level = new Level(nt, 520);
        Time();
        Game.handlergame.clock = new Cronometer(40, 40, getMin(), getSec());
    }

    public int OffSet() {
        int offset = -520;
        switch (LEVEL) {
            case 1:
                offset = -520;
                break;
            case 2, 3:
                offset = 520;
                break;
            case 4:
                offset = -1055;
                break;

            default:
                break;
        }
        return offset;
    }

    public void Time() {
        switch (LEVEL) {
            case 1 -> {
                m = 0;
                sec = 10;
            }
            case 2, 3 -> {
                m = 0;
                sec = 35;
            }

            default -> {
                m = 0;
                sec = 30;
            }
        }
    }

    public void Restart() {
        Game.handler.ClearObjects();
        Time();
        HandlerGame.level = new Level("/Level/level" + LEVEL + ".png", OffSet());
        Game.handlergame.clock = new Cronometer(40, 40, getMin(), getSec());
    }

    private int getSec() {
        return sec;
    }

    private int getMin() {
        return m;
    }
}
