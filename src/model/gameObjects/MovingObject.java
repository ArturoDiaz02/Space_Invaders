package model.gameObjects;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.info.ScoreData;
import model.math.Vector2D;
import model.states.GameState;
import model.states.ScoreState;
import model.states.State;
import ui.Assets;
import model.music.Sound;

public abstract class MovingObject extends GameObject{
	
	protected Vector2D velocity;
	protected AffineTransform at;
	protected double angle;
	protected double maxVel;
	protected int width;
	protected int height;
	protected GameState gameState;
	private Sound destroySound;
	
	public MovingObject(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
		super(position, texture);
		this.velocity = velocity;
		this.maxVel = maxVel;
		this.gameState = gameState;
		width = texture.getWidth();
		height = texture.getHeight();
		angle = 0;
		destroySound = new Sound(Assets.explosionSound);
		
	}
	
	protected void collidesWith(){
		
		ArrayList<MovingObject> movingObjects = gameState.getMovingObjects(); 
		
		for(int i = 0; i < movingObjects.size(); i++){
			
			MovingObject m  = movingObjects.get(i);

			if(m.equals(this))
				continue;
			
			double distance = m.getCenter().subtract(getCenter()).getMagnitude();
			
			if(distance < m.width/2 + width/2 && movingObjects.contains(this)){
				if(!(m instanceof Laser) ^ !(this instanceof Laser)){
					objectCollision(m, this);
				}

			}
		}

	}
	
	private void objectCollision(MovingObject a, MovingObject b){

		if (b instanceof Laser && ((Laser) b).isId()){

			if(gameState.getAliens() > 1){
				gameState.playExplosion(getCenter());

				for (int i = 0; i < gameState.getTHAliens().size(); i++) {
					gameState.getTHAliens().get(i).stopThread(a);

				}

				gameState.setAliens(gameState.getAliens() - 1);
				a.Destroy();
				b.Destroy();

			}else{

				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/data.txt"));
					ArrayList<ScoreData> sd = (ArrayList<ScoreData>) ois.readObject();
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/data.txt"));
					sd.add(new ScoreData(gameState.getPlayer().getScore()));
					oos.writeObject(sd);
					ois.close();
					oos.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

				a.Destroy();
				b.Destroy();

				gameState.end();
				State.changeState(new ScoreState());

			}



		}else if (a instanceof Player && b instanceof Laser){
				gameState.playExplosion(getCenter());
				a.Destroy();
				b.Destroy();

		}else if (a instanceof Meteor && b instanceof Laser){
			gameState.playExplosion(getCenter());
			a.Destroy();
			b.Destroy();

		}

		


		
	}

	protected void Destroy(){
		if(!(this instanceof Laser)){
			destroySound.play();
		}

		gameState.getMovingObjects().remove(this);
	}
	
	protected Vector2D getCenter(){
		return new Vector2D(position.getX() + width/2, position.getY() + height/2);
	}
	
}
