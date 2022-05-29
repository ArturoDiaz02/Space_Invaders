package model.gameObjects;

import java.awt.Graphics;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.math.Vector2D;


public abstract class GameObject {
	protected ImageView texture;
	protected Vector2D position;
	
	public GameObject(Vector2D position, ImageView texture)
	{
		this.position = position;
		this.texture = texture;
	}
	
	public abstract void draw();

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public ImageView getTexture() {
		return texture;
	}
	
}
