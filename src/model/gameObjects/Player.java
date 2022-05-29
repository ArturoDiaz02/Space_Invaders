package model.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.math.Vector2D;
import ui.FXController;

public class Player extends MovingObject{

	private Vector2D heading;
	private Chronometer fireRate;
	
	public Player(Vector2D position, Vector2D velocity, double maxVel, ImageView texture, FXController controller) {
		super(position, velocity, maxVel, texture, controller);
		heading = new Vector2D();
		heading.setDirection(90);
		fireRate = new Chronometer();
	}

	@Override
	public void draw() {

		
	}

	public void moveLeft() {
		if (texture.getX() - Constants.PLAYER_MAX_VEL > 0) {
			texture.setX(texture.getX() - Constants.PLAYER_MAX_VEL);
			position.setX(position.getX() - Constants.PLAYER_MAX_VEL);
		}

		fireRate.update();
	}

	public void moveRight() {
		if (position.getX() + Constants.PLAYER_MAX_VEL < 860) {
			position.setX(position.getX() + Constants.PLAYER_MAX_VEL);
			texture.setX(texture.getX() + Constants.PLAYER_MAX_VEL);
		}

		fireRate.update();
	}

	public void shoot(Image image) {
		if(!fireRate.isRunning()) {
			controller.getMovingObjects().add(0,new Laser(
					getCenter().add(heading.scale(width)),
					heading,
					Constants.LASER_VEL,
					new ImageView(image),
					controller
			));
			fireRate.run(Constants.FIRERATE);
		}
		fireRate.update();
	}
}
