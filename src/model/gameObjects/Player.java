package model.gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.info.ScoreData;
import model.states.ScoreState;
import model.states.State;
import ui.Assets;
import model.input.KeyBoard;
import model.math.Vector2D;
import model.states.GameState;
import model.music.Sound;

public class Player extends MovingObject{
	
	private Vector2D heading;
	private Chronometer fireRate;
	private int score;
	private Sound shootSound;
	
	public Player(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
		super(position, velocity, maxVel, texture, gameState);
		heading = new Vector2D(0, 1);
		fireRate = new Chronometer();
		score = 0;
		shootSound = new Sound(Assets.playerShootSound);
	}
	
	@Override
	public void update() {

		if(KeyBoard.SHOOT && !fireRate.isRunning()) {
			gameState.getMovingObjects().add(new Laser(getCenter().add(heading.scale(width)), heading,
					Constants.LASER_VEL, angle, Assets.blueLaser, gameState, true));

			fireRate.run(Constants.FIRERATE);
			shootSound.play();
		}

		if(KeyBoard.RIGHT) position.setX(position.getX() + Constants.PLAYER_MAX_VEL);
		if(KeyBoard.LEFT) position.setX(position.getX() - Constants.PLAYER_MAX_VEL);

		heading = heading.setDirection(angle - Math.PI/2);

		if(position.getX() > Constants.WIDTH) position.setX(0);
		if(position.getY() > Constants.HEIGHT) position.setY(0);
		
		if(position.getX() < 0) position.setX(Constants.WIDTH);
		if(position.getY() < 0) position.setY(Constants.HEIGHT);

		fireRate.update();
		collidesWith();
	}
	
	
	@Override
	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D)g;

		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());

		g2d.drawImage(texture, at, null);
		
	}

	public void addScore(int score) {
		this.score += score;
	}

	@Override
	public void Destroy(){
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/data.txt"));
			ArrayList<ScoreData> sd = (ArrayList<ScoreData>) ois.readObject();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/data.txt"));
			sd.add(new ScoreData(score));
			oos.writeObject(sd);
			ois.close();
			oos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		super.Destroy();
		gameState.end();
		State.changeState(new ScoreState());
	}

	public int getScore() {
		return score;
	}
}
