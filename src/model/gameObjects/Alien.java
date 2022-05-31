package model.gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import model.math.Vector2D;
import model.states.GameState;
import ui.Assets;
import model.music.Sound;

public class Alien extends MovingObject{

	private boolean direction;
	private Vector2D heading;
	private Chronometer fireRate;
	private Sound shootSound;

	public Alien(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
		super(position, velocity, maxVel, texture, gameState);
		this.velocity = velocity.scale(maxVel);
		this.direction = true;
		heading = new Vector2D(0, 1);
		fireRate = new Chronometer();
		shootSound = new Sound(Assets.alienShootSound);
		
	}

	@Override
	public void update() {

		if(direction){
			position = position.add(new Vector2D(5, 0));

			if(position.getX() > Constants.WIDTH){
				position = position.add(new Vector2D(-5, height));
				direction = false;
			}

		}else{
			position = position.add(new Vector2D(-5, 0));

			if(position.getX() < -width){
				position = position.add(new Vector2D(5, height));
				direction = true;
			}
		}


		
	}

	public void shoot(){
		if ((int) (Math.random()*(10-1) + 1) % 5 == 0) {
			gameState.getMovingObjects().add(new Laser(getCenter().add(heading.scale(width)), heading,
					Constants.LASER_VEL, angle, Assets.redLaser, gameState, false));

			fireRate.run(Constants.FIRERATE);
			shootSound.play();
		}
	}
	
	@Override
	public void Destroy(){
		gameState.getPlayer().addScore(40);
		super.Destroy();
	}

	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		
		//at.rotate(angle, width/2, height/2);
		
		g2d.drawImage(texture, at, null);
	}

}
