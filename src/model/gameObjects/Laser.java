package model.gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import model.math.Vector2D;
import model.states.GameState;

public class Laser extends MovingObject{

	private boolean id;

	public Laser(Vector2D position, Vector2D velocity, double maxVel, double angle, BufferedImage texture, GameState gameState, boolean id) {
		super(position, velocity, maxVel, texture, gameState);
		this.angle = angle;
		this.velocity = velocity.scale(maxVel);
		this.id = id;
	}

	@Override
	public void update() {
		position = position.add(velocity);
		if(position.getX() < 0 || position.getX() > Constants.WIDTH ||
				position.getY() < 0 || position.getY() > Constants.HEIGHT){
			Destroy();
		}
		
		collidesWith();
		
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		at = AffineTransform.getTranslateInstance(position.getX() - width/2, position.getY());
		
		at.rotate(angle, width/2, 0);
		
		g2d.drawImage(texture, at, null);
		
	}
	
	@Override
	public Vector2D getCenter(){
		return new Vector2D(position.getX() + width/2, position.getY() + width/2);
	}

	public boolean isId() {
		return id;
	}
}
