package GameObject.LevelItens;

import Entity.Global.ID;
import Entity.particles.ParticleHandler;
import GameObject.GameObject;
import Main.utils.Animator;
import Main.utils.CustomColor;
import Main.utils.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Torch extends GameObject {
    public Animator anim;
    public int maxIndex=6,maxFrames=3;
    public BufferedImage spr,an[];
    public Torch(int x, int y, ID id) {
        super(x, y, id);
        spr=new LoadImage("/GameObject/Torch.png").getImage();
        an=new LoadImage(null).CutHor(maxIndex,0,0,16,22,spr);
        anim=new Animator(maxFrames,maxIndex);
        anim.setAnimation(an);
    }

    @Override
    public void tick() {

        if(new Random().nextInt(100)==1)
     new ParticleHandler().CreateParticlesRect(
             2,120,getX(),getY(),0,2, CustomColor.blue
     );
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(anim.getAnimation(),getX(),getY(),48,66,null);
    }

    @Override
    public Rectangle getP() {return new Rectangle(getX(),getY(),getWidth(),getHeight());}
    public Rectangle getRightP() { return null; }
    public Rectangle getLeftP() { return null; }
    public Rectangle getToP() { return null; }
}
