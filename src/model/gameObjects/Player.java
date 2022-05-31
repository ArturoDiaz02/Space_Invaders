package model.gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import ui.Assets;
import model.input.KeyBoard;
import model.math.Vector2D;
import model.states.GameState;

public class Player extends MovingObject{
	
	private Vector2D heading;
	private Chronometer fireRate;
	
	public Player(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
		super(position, velocity, maxVel, texture, gameState);
		heading = new Vector2D(0, 1);
		fireRate = new Chronometer();
	}
	
	@Override
	public void update() {

		if(KeyBoard.SHOOT && !fireRate.isRunning()) {
			gameState.getMovingObjects().add(0,new Laser(getCenter().add(heading.scale(width)), heading,
					Constants.LASER_VEL, angle, Assets.blueLaser, gameState));

			fireRate.run(Constants.FIRERATE);
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

		AffineTransform at1 = AffineTransform.getTranslateInstance(position.getX() + width/2 + 5,
				position.getY() + height/2 + 10);

		AffineTransform at2 = AffineTransform.getTranslateInstance(position.getX() + 5, position.getY() + height/2 + 10);

		at1.rotate(angle, -5, -10);
		at2.rotate(angle, width/2 -5, -10);

		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());

		at.rotate(angle, width/2, height/2);

		g2d.drawImage(texture, at, null);
		
	}
	
}
