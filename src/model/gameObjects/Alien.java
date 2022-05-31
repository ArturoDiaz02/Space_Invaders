package model.gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import model.math.Vector2D;
import model.states.GameState;

public class Alien extends MovingObject{

	private boolean direction;

	public Alien(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
		super(position, velocity, maxVel, texture, gameState);
		this.velocity = velocity.scale(maxVel);
		this.direction = true;
		
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
	
	@Override
	public void Destroy(){
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
