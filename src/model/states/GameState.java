package model.states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import model.gameObjects.Constants;
import model.gameObjects.Alien;
import model.gameObjects.MovingObject;
import model.gameObjects.Player;
import ui.Animation;
import ui.Assets;
import model.math.Vector2D;

public class GameState {
	
	private Player player;
	private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
	private ArrayList<Animation> explosions = new ArrayList<Animation>();
	
	private int aliens;
	
	public GameState() {
		player = new Player(new Vector2D(Constants.WIDTH/2 - Assets.player.getWidth()/2,
				Constants.HEIGHT - Assets.player.getHeight()*2.5), new Vector2D(), Constants.PLAYER_MAX_VEL, Assets.player, this);
		movingObjects.add(player);

		aliens = (int) (Math.random()*(40-20)) + 20;
		startWave();
		
	}

	private void startWave(){
		
		double x, y = 70, vx = 280;
		int ARow = 0;

		for (int i = 0; i < aliens; i++) {
			BufferedImage texture = Assets.aliens[(int)(Math.random()*Assets.aliens.length)];
			x = vx + 40;

			if (i % 10 == 0) {
				vx = 280;
				x = vx + 40;
				y += 40;
				movingObjects.add(new Alien(new Vector2D(x, y), new Vector2D(0, 1),
						Constants.METEOR_VEL*Math.random() + 1, texture, this));
			}else {
				movingObjects.add(new Alien(new Vector2D(x, y), new Vector2D(0, 1),
						Constants.METEOR_VEL*Math.random() + 1, texture, this));
			}

			vx = x;
		}

	}
	
	public void playExplosion(Vector2D position){
		explosions.add(new Animation(
				Assets.exp,
				50,
				position.subtract(new Vector2D(Assets.exp[0].getWidth()/2, Assets.exp[0].getHeight()/2))
				));
	}
	
	public void update() {
		for(int i = 0; i < movingObjects.size(); i++)
			movingObjects.get(i).update();
		
		for(int i = 0; i < explosions.size(); i++){
			Animation anim = explosions.get(i);
			anim.update();
			if(!anim.isRunning()){
				explosions.remove(i);
			}
			
		}
		
		for(int i = 0; i < movingObjects.size(); i++) {
			if (movingObjects.get(i) instanceof Alien) return;
		}
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		for(int i = 0; i < movingObjects.size(); i++)
			movingObjects.get(i).draw(g);
		
		for(int i = 0; i < explosions.size(); i++){
			Animation anim = explosions.get(i);
			g2d.drawImage(anim.getCurrentFrame(), (int)anim.getPosition().getX(), (int)anim.getPosition().getY(),
					null);
			
		}
	}

	public ArrayList<MovingObject> getMovingObjects() {
		return movingObjects;
	}
}