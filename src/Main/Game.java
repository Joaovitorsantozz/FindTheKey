package Main;

import Entity.ID;
import Entity.Player;
import GameObject.GameObject;
import GameObject.GameObjectHandler;
import Main.utils.FontStyle;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    public static GameObjectHandler handler;
    private static final long serialVersionUID = 1L;
    private Thread tick;
    private Thread render;
    private boolean isRunning;
    private int Frames, upd;
    public static final int W = 1280, H = 730;
    public static HandlerGame handlergm;
    public Game() {
        new Windows(W, H, "Engine", this);
        // Instancias
        handler = new GameObjectHandler();
        handler.add(new Player(150, 100, ID.Player, handler));
        handlergm = new HandlerGame();
        this.addKeyListener(new KeyInput(handler));

        //
        start();
    }

    public static void main(String[] args) {
        new Game();
    }

    private void start() {
        isRunning = true;
        tick = new Thread(this, "TickThread");
        tick.start();
        new Thread(this::run2, "RenderThread").start();
    }

    public void tick() {
        if (Thread.currentThread().getName() == "TickThread") {
            handler.update();
            UpdateCam();
            handlergm.clock.tick();
        }
    }

    private void UpdateCam() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject e = handler.object.get(i);
            if (handler.object.get(i).getId() == ID.Player) {
                handlergm.cam.tick(handler.object.get(i));
            }
        }
    }

    public void render() {
        if (Thread.currentThread().getName() == "RenderThread") {
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
            g2.translate(-handlergm.cam.getX(), -handlergm.cam.getY());
            handler.render(g2);
            g2.translate(handlergm.cam.getX(), handlergm.cam.getY());
            /////////////////////////////////
            g.setColor(Color.white);
          //  g.drawString("FPS =" + Frames, 10, 50);
            //g.drawString("Updates =" + upd, 10, 90);
            handlergm.clock.render(g);
            handlergm.invent.render(g2);
            g.dispose();
            bs.show();
        }
    }

    public void stop() {
        isRunning = false;
        try {
            tick.join();
            render.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Causa - " + e.getCause());
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
            boolean shouldRender = true;
            while (unprocessed >= 1) {
                ticks++;
                tick();
                unprocessed -= 1;
                shouldRender = true;
            }

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                frames++;
                render();
            }

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
