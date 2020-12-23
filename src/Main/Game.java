package Main;

import Entity.Global.ID;
import GameObject.GameObjectHandler;
import Main.utils.FontStyle;
import Main.utils.Text.Text;
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
    public static Text text;
    BufferStrategy bs;
    public Game() {
        new Windows(W, H, "Engine", this);
        // Instancias
        handler = new GameObjectHandler();
        handlergame = new HandlerGame();
        this.addKeyListener(new KeyInput(handler));
        this.addMouseMotionListener(new MouseInput(HandlerGame.ch.cursor));
        this.addMouseListener(new MouseInput(HandlerGame.ch.cursor));
        text = new Text(FontStyle.getFont(150, Font.BOLD), "Hasnt Focus!", 300, 400);
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
        Image cursorImage = Toolkit.getDefaultToolkit().getImage("/UI/Cursor.png");
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "");
        setCursor(blankCursor);
    }

    public void tick() {
        if (Thread.currentThread().getName().equals("TickThread")&&hasFocus()) {
            handler.update();
            UpdateCam();
            new LevelSwitch().upd();
            handlergame.tick();

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
             bs = this.getBufferStrategy();
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
            finalRender(g, g2);
            if (!hasFocus()) {
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(0, 0, W, H);
                text.DrawText(g,Color.white,"Default");
            }
            ////////////////////////////////////////////
            g.dispose();
            bs.show();
        }
    }
    public void finalRender(Graphics g, Graphics2D g2) {
        g2.translate(-handlergame.cam.getX(), -handlergame.cam.getY());
        handlergame.render(g);
        handler.render(g2);//GO
        g2.translate(handlergame.cam.getX(), handlergame.cam.getY());
        //Things that will be not affect by cam
        g.setColor(Color.white);
        g.drawString("FPS =" + Frames, 1000, 50);
        g.drawString("Updates =" + upd, 1000, 90);
        handlergame.renderNotAffect(g);
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
            boolean ticked = false;
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;
            while (unprocessed >= 1) {
                ticks++;
                tick();
                ticked = true;
                unprocessed -= 1;

            }


            if (ticked) {
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
