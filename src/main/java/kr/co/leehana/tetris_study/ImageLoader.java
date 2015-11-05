package kr.co.leehana.tetris_study;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Hana Lee
 * @since 2015-11-05 11:16
 */
public class ImageLoader {

	public static Image[] loadImage(String path, int width) throws IOException {
		BufferedImage load = ImageIO.read(ImageLoader.class.getResource(path));
		Image[] images = new Image[load.getWidth() / width];

		for (int i = 0; i < images.length; i++) {
			images[i] = load.getSubimage(i * width, 0, width, width);
		}

		return images;
	}
}
