package ui;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage[] player = new BufferedImage[2];

	public static BufferedImage[] exp = new BufferedImage[9];

	public static BufferedImage blueLaser, redLaser;

	public static BufferedImage[] aliens = new BufferedImage[2];

	public static BufferedImage[] numbers = new BufferedImage[11];

	public static Clip backGroundMusic, explosionSound, playerShootSound, alienShootSound, playerLooseSound;
	public static BufferedImage blueBtn;
	public static BufferedImage greyBtn;

	public static Font fontMed;

	public static void init() {
		player[0] = Loader.ImageLoader("src/ui/images/ships/player.png");

		player[1] = Loader.ImageLoader("src/ui/images/ships/player2.png");
		
		blueLaser = Loader.ImageLoader("src/ui/images/lasers/laserBlue01.png");

		redLaser = Loader.ImageLoader("src/ui/images/lasers/laserRed01.png");

		fontMed = Loader.loadFont("src/ui/images/fonts/futureFont.ttf", 20);
		
		for(int i = 0; i < aliens.length; i++) {
			aliens[i] = Loader.ImageLoader("src/ui/images/aliens/A" + (i + 1) + ".png");
		}

		for(int i = 0; i < exp.length; i++) {
			exp[i] = Loader.ImageLoader("src/ui/images/explosion/" + i + ".png");
		}

		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = Loader.ImageLoader("src/ui/images/numbers/" + i + ".png");
		}

		greyBtn = Loader.ImageLoader("src/ui/images/buttons/grey_button.png");
		blueBtn = Loader.ImageLoader("src/ui/images/buttons/blue_button.png");

		backGroundMusic = Loader.loadSound("src/ui/images/sounds/backgroundMusic.wav");
		explosionSound = Loader.loadSound("src/ui/images/sounds/explosion.wav");
		playerShootSound = Loader.loadSound("src/ui/images/sounds/playerShoot.wav");
		alienShootSound = Loader.loadSound("src/ui/images/sounds/alienShoot.wav");
		playerLooseSound = Loader.loadSound("src/ui/images/sounds/playerLoose.wav");
		
	}
	
}
