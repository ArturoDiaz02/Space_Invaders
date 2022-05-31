package model.states;

import model.gameObjects.Constants;
import model.interfas.Action;
import model.threads.THAlien;
import ui.Assets;
import model.util.Button;

import java.awt.Graphics;
import java.util.ArrayList;

public class MenuState extends State {
	
	private ArrayList<Button> buttons;
	private GameState gameState;
	
	public MenuState() {
		buttons = new ArrayList<Button>();
		gameState = new GameState();
		
		buttons.add(new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				Constants.WIDTH / 2 - Assets.greyBtn.getWidth()/2,
				Constants.HEIGHT / 2 - Assets.greyBtn.getHeight(),
				Constants.PLAY,
				new Action() {
					@Override
					public void doAction() {

						State.changeState(gameState);

						gameState.startWave(getG());

						for (THAlien alien : gameState.getTHAliens()) {
							alien.start();
						}

					}
				}
				));
		
		buttons.add(new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				Constants.WIDTH / 2 - Assets.greyBtn.getWidth()/2,
				Constants.HEIGHT / 2 + Assets.greyBtn.getHeight()/2 ,
				Constants.EXIT,
				new Action() {
					@Override
					public void doAction() {
						System.exit(0);
					}
				}
				));
		
	}
	
	
	@Override
	public void update() {
		for(Button b: buttons) {
			b.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		for(Button b: buttons) {
			b.draw(g);
		}
	}

}
