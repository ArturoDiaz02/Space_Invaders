package model.states;

import model.info.ScoreData;
import model.gameObjects.Constants;
import model.interfas.Action;
import model.math.Vector2D;
import model.util.Button;
import model.util.Text;
import ui.Assets;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ScoreState extends State{
	
	private Button returnButton;
	
	private PriorityQueue<ScoreData> highScores;
	
	private Comparator<ScoreData> scoreComparator;
	
	private ScoreData[] auxArray;
	
	public ScoreState() {
		returnButton = new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				Assets.greyBtn.getHeight(),
				Constants.HEIGHT - Assets.greyBtn.getHeight() * 2,
				Constants.RETURN,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new MenuState());
					}
				}
			);
		
		scoreComparator = new Comparator<ScoreData>() {
			@Override
			public int compare(ScoreData e1, ScoreData e2) {
				return e1.getScore() < e2.getScore() ? -1: e1.getScore() > e2.getScore() ? 1: 0;
			}
		};
		
		highScores = new PriorityQueue<ScoreData>(10, scoreComparator);

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/data.txt"));
			ArrayList<ScoreData> sd = (ArrayList<ScoreData>) ois.readObject();
			highScores.addAll(sd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update() {
		returnButton.update();
	}

	@Override
	public void draw(Graphics g) {
		returnButton.draw(g);
		
		auxArray = highScores.toArray(new ScoreData[highScores.size()]);
		
		Arrays.sort(auxArray, scoreComparator);
		
		
		Vector2D scorePos = new Vector2D(
				Constants.WIDTH / 2 - 200,
				100
				);
		Vector2D datePos = new Vector2D(
				Constants.WIDTH / 2 + 200,
				100
				);
		
		Text.drawText(g, Constants.SCORE, scorePos, true, Color.BLUE, Assets.fontBig);
		Text.drawText(g, Constants.DATE, datePos, true, Color.BLUE, Assets.fontBig);
		
		scorePos.setY(scorePos.getY() + 40);
		datePos.setY(datePos.getY() + 40);
		
		for(int i = auxArray.length - 1; i > -1; i--) {
			
			ScoreData d = auxArray[i];
			
			Text.drawText(g, Integer.toString(d.getScore()), scorePos, true, Color.WHITE, Assets.fontMed);
			Text.drawText(g, d.getDate(), datePos, true, Color.WHITE, Assets.fontMed);
			
			scorePos.setY(scorePos.getY() + 40);
			datePos.setY(datePos.getY() + 40);
			
		}
		
	}
	
}
