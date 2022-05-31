package model.gameObjects;

import model.info.ScoreData;
import model.math.Vector2D;
import model.states.GameState;
import model.states.ScoreState;
import model.states.State;
import ui.Assets;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Meteor extends MovingObject{

	private int status;
	
	public Meteor(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
		super(position, velocity, maxVel, texture, gameState);
		status = 0;

	}

	@Override
	public void update() {}

	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		
		at.rotate(angle, width/2, height/2);
		
		g2d.drawImage(texture, at, null);
	}

	@Override
	public void Destroy(){
		if (status < 6){
			status++;
			texture = Assets.meteors[status];
		}else{
			super.Destroy();
		}

	}

}
