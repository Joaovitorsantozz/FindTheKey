

		package Graphics;

		import java.awt.Color;
		import java.awt.GradientPaint;
		import java.awt.Graphics;
		import java.awt.Graphics2D;

		import Entity.ID;
		import GameObject.GameObject;
		import Main.Game;
		import Main.HandlerGame;
		import Main.utils.LoadImage;

public class Parallax {
	// ONLY BASIC PARALLAX
	private int velx,x;
	private int w,h;

	public Parallax() {
		w=Game.W*3;
		h=Game.H+HandlerGame.level.getHeight()*12;
	}

	public void tick() {
		x+=velx;
		for(int i=0;i<Game.handler.object.size();i++) {
			GameObject e=Game.handler.object.get(i);
			if(e.getId()==ID.Player) {
				if(Game.handler.isRight())velx--;
				if(Game.handler.isLeft())velx++;
			}
		}
		if(velx>w)velx=0;
		if(velx<w)velx=0;
	}

	public void render(Graphics g) {
		Graphics2D g2=(Graphics2D)g;
		Color c2=new Color(33, 0, 127);
		Color c1=new Color(255, 0, 0, 255);
		GradientPaint p=new GradientPaint(0,0,c2,0,h*4,c1);
		g2.setPaint(p);
		g.fillRect(x+velx,0,w*3,h);
		g.fillRect(x,0,w,h);
		g.fillRect(x-velx,0,w*3,h);

	}


}
