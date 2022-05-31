package model.states;

import java.awt.Graphics;

public abstract class State {
	
	private static State currentState = null;
	private static Graphics g;
	
	public static State getCurrentState() {
		return currentState;
	}
	public static void changeState(State newState) {
		currentState = newState;

	}

	public static Graphics getG() {
		return g;
	}

	public static void setGraphics(Graphics newG) {
		g = newG;
	}
	public abstract void update();
	public abstract void draw(Graphics g);
	
}
