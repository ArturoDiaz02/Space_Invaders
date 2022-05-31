package ui;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage player;

	public static BufferedImage[] exp = new BufferedImage[9];

	public static BufferedImage blueLaser, greenLaser, redLaser;

	public static BufferedImage[] aliens = new BufferedImage[2];

	public static void init() {
		player = Loader.ImageLoader("src/ui/images/ships/player.png");
		
		blueLaser = Loader.ImageLoader("src/ui/images/lasers/laserBlue01.png");
		
		for(int i = 0; i < aliens.length; i++) {
			aliens[i] = Loader.ImageLoader("src/ui/images/aliens/A" + (i + 1) + ".png");
		}

		for(int i = 0; i < exp.length; i++) {
			exp[i] = Loader.ImageLoader("src/ui/images/explosion/" + i + ".png");
		}
		
	}
	
}
