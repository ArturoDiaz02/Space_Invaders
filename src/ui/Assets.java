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

	public static BufferedImage[] meteors = new BufferedImage[7];

	public static Clip backGroundMusic, explosionSound, playerShootSound, alienShootSound, playerLooseSound;

	public static BufferedImage blueBtn, greyBtn;

	public static Font fontMed, fontBig;

	public static void init() {
		player[0] = Loader.ImageLoader("resource/ships/player.png");

		player[1] = Loader.ImageLoader("resource/ships/player2.png");
		
		blueLaser = Loader.ImageLoader("resource/lasers/laserBlue01.png");

		redLaser = Loader.ImageLoader("resource/lasers/laserRed01.png");

		fontMed = Loader.loadFont("resource/fonts/futureFont.ttf", 20);
		fontBig = Loader.loadFont("resource/fonts/futureFont.ttf", 42);
		
		for(int i = 0; i < aliens.length; i++) {
			aliens[i] = Loader.ImageLoader("resource/aliens/A" + (i + 1) + ".png");
		}

		for(int i = 0; i < exp.length; i++) {
			exp[i] = Loader.ImageLoader("resource/explosion/" + i + ".png");
		}

		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = Loader.ImageLoader("resource/numbers/" + i + ".png");
		}

		for(int i = 0; i < meteors.length; i++) {
			meteors[i] = Loader.ImageLoader("resource/meteors/" + (i + 1) + ".png");
		}

		greyBtn = Loader.ImageLoader("resource/buttons/grey_button.png");
		blueBtn = Loader.ImageLoader("resource/buttons/blue_button.png");

		backGroundMusic = Loader.loadSound("resource/sounds/backgroundMusic.wav");
		explosionSound = Loader.loadSound("resource/sounds/explosion.wav");
		playerShootSound = Loader.loadSound("resource/sounds/playerShoot.wav");
		alienShootSound = Loader.loadSound("resource/sounds/alienShoot.wav");
		playerLooseSound = Loader.loadSound("resource/sounds/playerLoose.wav");
		
	}
	
}
