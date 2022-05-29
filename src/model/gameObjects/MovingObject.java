package model.gameObjects;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import model.math.Vector2D;
import ui.FXController;

public abstract class MovingObject extends GameObject {
	
	protected Vector2D velocity;
	protected double maxVel;
	protected double width;
	protected double height;
	protected FXController controller;
	
	public MovingObject(Vector2D position, Vector2D velocity, double maxVel, ImageView texture, FXController controller) {
		super(position, texture);
		this.velocity = velocity;
		this.maxVel = maxVel;
		this.controller = controller;
		width = texture.getFitWidth();
		height = texture.getFitHeight();
		
	}
	
	protected void collidesWith(){
		
		ArrayList<MovingObject> movingObjects = controller.getMovingObjects(); 
		
		for(int i = 0; i < movingObjects.size(); i++){
			
			MovingObject m  = movingObjects.get(i);
			
			if(m.equals(this))
				continue;
			
			double distance = m.getCenter().subtract(getCenter()).getMagnitude();
			
			if(distance < m.width/2 + width/2 && movingObjects.contains(this)){
				objectCollision(m, this);
			}
		}
	}
	
	private void objectCollision(MovingObject a, MovingObject b){
		
		if(!(a instanceof Alien && b instanceof Alien)){
			a.Destroy();
			b.Destroy();
		}
		
	}
	
	
	protected void Destroy(){
		controller.getMovingObjects().remove(this);
	}
	
	protected Vector2D getCenter(){
		return new Vector2D(position.getX() + width/2, position.getY() + height/2);
	}

}
