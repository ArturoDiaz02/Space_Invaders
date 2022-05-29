package model.gameObjects;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import model.math.Vector2D;
import ui.FXController;

public class Alien extends MovingObject {

	public Alien(Vector2D position, Vector2D velocity, double maxVel, ImageView texture, FXController controller) {
		super(position, velocity, maxVel, texture, controller);
		this.velocity = velocity.scale(maxVel);
		
	}

	public void update() {
		position = position.add(velocity);
		
		if(position.getX() > Constants.WIDTH)
			position.setX(-width);
		if(position.getY() > Constants.HEIGHT)
			position.setY(-height);
		
		if(position.getX() < -width)
			position.setX(Constants.WIDTH);
		if(position.getY() < -height)
			position.setY(Constants.HEIGHT);

		
	}

	@Override
	public void draw() {
		

	}
	
	
}
