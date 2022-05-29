package model.gameObjects;

import javafx.scene.image.ImageView;
import model.math.Vector2D;
import ui.FXController;

public class Laser extends MovingObject{

	public Laser(Vector2D position, Vector2D velocity, double maxVel, ImageView texture, FXController gameState) {
		super(position, velocity, maxVel, texture, gameState);
		this.velocity = velocity.scale(maxVel);
	}

	public void update() {
		position = position.add(velocity);
		if(position.getX() < 0 || position.getX() > Constants.WIDTH ||
				position.getY() < 0 || position.getY() > Constants.HEIGHT){
			Destroy();
		}
		
		collidesWith();
		
	}

	@Override
	public void draw() {

		
	}
	
	@Override
	public Vector2D getCenter(){
		return new Vector2D(position.getX() + width/2, position.getY() + width/2);
	}
	
}
