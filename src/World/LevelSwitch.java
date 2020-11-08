package World;

import Main.Game;
import Main.HandlerGame;

public class LevelSwitch {
    public static int LEVEL=1;
    public static boolean next,rest;
    public int Offset;
    public void upd(){
        if(next){
            next=false;
            ToNextLevel();
        }
        if(rest){
            rest=false;
            Restart();
        }
    }
    public void ToNextLevel(){
        Game.handler.ClearObjects();
        LEVEL++;
        String nt="/Level/level"+LEVEL+".png";
        HandlerGame.level=new Level(nt,520);
    }
    public int OffSet(){
        int offset=-520;
        switch (LEVEL){
            case 1:offset=-520;
                break;
            case 2:offset=520;
                break;
            default:break;
        }
        return offset;
    }
    public void Restart(){
        Game.handler.ClearObjects();
        HandlerGame.level=new Level("/Level/level"+LEVEL+".png",OffSet());
    }
}
