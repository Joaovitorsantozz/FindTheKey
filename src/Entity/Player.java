package Entity;

import Entity.Global.Depth;
import Entity.Global.ID;
import Entity.particles.ParticleHandler;
import GameObject.GameObject;
import GameObject.GameObjectHandler;
import Main.Game;
import Main.utils.Animator;
import Main.utils.CustomColor;
import Main.utils.LoadImage;
import World.LevelSwitch;
import World.tiles.FakeBlock;
import World.tiles.Tile;
import Entity.Global.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject {
    private GameObjectHandler hand;
    public float speed = 5f,jumpforce=15f;
    public boolean isFalling, canJump,interact,hasKey;
    public static boolean canWallJump,wallJump;
    public final int maxFrames = 6, maxIndex = 8;
    private BufferedImage sprP,
            anim[], idle[];
    private int count=120;
    private Animator an;
    public Player(int x, int y, ID id, GameObjectHandler hand) {
        super(x, y, id);
        // TODO Auto-generated constructor stub
        this.hand = hand;
        this.dir = 1;
        setDepth(Depth.MEDIUM);
        setWidth(16 * 3);
        setHeight(32 * 3);
        sprP = new LoadImage("/PlayerSheet.png").getImage();
        anim = new LoadImage(null).Cut(8, 0, 0, 32, 32, sprP);
        idle = new LoadImage(null).Cut(8, 32, 0, 32, 32, sprP);
        an = new Animator(maxFrames, maxIndex);
        //////////////////
    }

    @Override
    public void tick() {
        // Do logic
        Fall();
        x += getVelX();
        y += getVelY();
        ///// Logic/////
        if(Key.spaw) {
            count++;
            if(count>10) {
                new ParticleHandler().CreateAmount(
                        3,50,35,getX()-64,getY()+15,1.3f,1.3f,new Color
                                (CustomColor.yellow.getRed(),CustomColor.yellow.getGreen(),CustomColor.yellow.getBlue())
                );
                Key.spaw=false;
                count=0;
            }
        }
        // Call Methods//
        Col();
        Moving((int) speed, hand);
    }

    public void Fall() {
        if (isFalling) {
            float grv = 1.4f;
            velY += grv;
            if (velY > 15)
                velY = 15;
        }
        if (hand.isJump()) {
            if (canJump)
                setVelY(-jumpforce);
            canJump = false;
        }
        if (canWallJump) {
                if (wallJump) {
                    wallJump=false;
                    setVelY(-20);
                    //Make the impact for back
                    if (dir == 1) setVelX(-20);
                    if (dir == -1) setVelX(20);
                    canWallJump=false;
                }
                //------------------------\\
                hand.setJump(false);
            }
    }

    protected void Col() {
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject e = Game.handler.object.get(i);
            if (e.getId() == ID.Block) {
                if (getP().intersects(e.getP())) {
                    y = e.getY() - getHeight();
                    canJump = true;
                    velY = 0;
                    isFalling = false;
                } else {
                    isFalling = true;
                }
                if (getToP().intersects(e.getP())) {
                    y = e.getY()+32;
                    velY = 0;
                }
                if (getRightP().intersects(e.getP())) {
                    x = getX() - e.getWidth() + 25;
                    canWallJump= e instanceof Tile && ((Tile) e).getTileType() == TileType.PolStone;
                }
                if (getLeftP().intersects(e.getP())) {
                    x = e.getX() + (getWidth() / 2) - 10;
                    canWallJump = e instanceof Tile && ((Tile) e).getTileType() == TileType.PolStone;
                }
            }else if(e.getId()==ID.Depth){
                if(getP().intersects(e.getP())){
                    LevelSwitch.rest=true;  
                }
            }else if(e instanceof FakeBlock){
                if(getP().intersects(e.getP()))FakeBlock.fade=true;
            }
        }
    }

    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //Anim\\
        if (Moving((int) speed, hand)) an.setAnimation(anim);
        else if (!Moving((int) speed, hand)) an.setAnimation(idle);
        //Draw Key\\

        //Switch Image\\
        if (getDir() == 1) g.drawImage(an.getAnimation(), getX(), getY(), 96, 96, null);
        else if (getDir() == -1) g.drawImage(an.getAnimation(), getX() + 68, getY(), -96, 96, null);

        if(drawBounds)DrawBounds(g2);
    }


    @Override
    public Rectangle getP() {
        return new Rectangle((int) (getX() + (getWidth() / 2) - ((getWidth() / 2) - 45 / 2)), (int) (getY() + (getHeight() / 2)), getWidth() / 2, getHeight() / 2 + 20);
    }

    public Rectangle getRightP() {
        return new Rectangle(getX() + (getWidth()) + 3, getY() + 25, 5, getHeight() - 40);
    }

    public Rectangle getLeftP() {
        return new Rectangle(getX() + 13, getY() + 25, 5, getHeight() - 40);
    }

    public Rectangle getToP() {
        return new Rectangle(getX() + (getWidth() / 2) - ((getWidth() / 2) / 2) + 10, getY() + 20, getWidth() / 2, getHeight() / 2 - 20);
    }

    public Rectangle HitDetection() {
        return new Rectangle((int) getX() - 75, getY(), 200, 120);
    }


}