package Main;

import Entity.ID;
import GameObject.GameObjectHandler;
import Graphics.Parallax;
import Main.utils.FontStyle;
import Main.utils.Text;
import World.LevelSwitch;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    public static GameObjectHandler handler;
    private static final long serialVersionUID = 1L;
    private Thread render;
    private boolean isRunning;
    public int Frames, upd;
    public static final int W = 1280, H = 730;
    public static HandlerGame handlergame;

    public Game() {
        new Windows(W, H, "Engine", this);
        // Instancias
        handler = new GameObjectHandler();
        handlergame = new HandlerGame();
        this.addKeyListener(new KeyInput(handler));
        //
        start();
    }

    public static void main(String[] args) {
        new Game();
    }

    private void start() {
        isRunning = true;
        Thread tick = new Thread(this, "TickThread");
        tick.start();
        new Thread(this::run2, "RenderThread").start();
    }

    public void tick() {
        if (Thread.currentThread().getName().equals("TickThread")) {
            handler.update();
            UpdateCam();
            handlergame.clock.tick();
            new Parallax().tick();
            new LevelSwitch().upd();
        }
    }

    private void UpdateCam() {
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Player) {
                handlergame.cam.tick(handler.object.get(i));
            }
        }
    }

    public void render() {
        if (Thread.currentThread().getName().equals("RenderThread")) {
            BufferStrategy bs = this.getBufferStrategy();
            if (bs == null) {
                this.createBufferStrategy(3);
                return;
            }
            Graphics g = bs.getDrawGraphics();
            g.setColor(Color.black);
            g.fillRect(0, 0, W, H);
            Graphics2D g2 = (Graphics2D) g;
            g.setFont(FontStyle.getFont(40, 20));
            /////////////////////////////////
            g2.translate(-handlergame.cam.getX(), -handlergame.cam.getY());
            new Parallax().render(g);
            handler.render(g2);//GO
            g2.translate(handlergame.cam.getX(), handlergame.cam.getY());
            /////////////////////////////////
            g.setColor(Color.white);
            new Text(
                    FontStyle.getFont(40,20),"Vou te comer",800,50).DrawText(g,new Color(0,100,0,100));
            g.drawString("FPS =" + Frames, 1000, 50);
            g.drawString("Updates =" + upd,1000,90);
            handlergame.clock.render(g);
            handlergame.invent.render(g2);
            g.dispose();
            bs.show();
        }
    }

    @Override
    public void run() {
        requestFocus();
        long lastTime = System.nanoTime();
        double unprocessed = 0;
        double nsPerTick = 1000000000.0 / 60;
        int frames = 0;
        int ticks = 0;
        long lastTimer1 = System.currentTimeMillis();
        while (isRunning) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (unprocessed >= 1) {
                ticks++;
                tick();
                unprocessed -= 1;

            }

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            frames++;
            render();

            if (System.currentTimeMillis() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                Frames = frames;
                upd = ticks;
                frames = 0;
                ticks = 0;
            }
        }

    }

    private void run2() {
        while (true)
            render();
    }
}
