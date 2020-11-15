package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Windows {
	private static final long serialVersionUID = 1L;
	public JFrame jframe;

	public Windows(int width, int height, String name, Game game) {
		jframe = new JFrame(name);
		jframe.setPreferredSize(new Dimension(width, height));
		jframe.setMaximumSize(new Dimension(width, height));
		jframe.setMinimumSize(new Dimension(width, height));
		jframe.add(game, BorderLayout.CENTER);
		jframe.setUndecorated(true);
		jframe.setResizable(false);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
	}

}
