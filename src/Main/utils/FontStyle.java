package Main.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontStyle {
	public InputStream stream = FontStyle.class.getResourceAsStream("/Pixeboy.ttf");
	public static Font font;

	public FontStyle() {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, stream);
		} catch (FontFormatException e) {
			System.out.println("Formato n�o suportado" + e.getCause());
		} catch (IOException e) {
			System.out.println("N�o foi localizado a fonte" + e.getCause());
			System.out.println(e);
		}
	}

	public static Font getFont(int size, int style) {
		return (font != null ? font.deriveFont((float) size).deriveFont(style) : new Font("arial", style, size));
	}
}
