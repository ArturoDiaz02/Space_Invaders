package ui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.gameObjects.Constants;
import model.gameObjects.MovingObject;
import model.gameObjects.Player;
import model.math.Vector2D;

import java.util.ArrayList;

public class FXController {

    @FXML
    private ImageView background;
    @FXML
    private AnchorPane pane;
    private final String BACKGROUND_IMAGE = "ui/images/backGround/back.jpg";
    private final String PLAYER_IMAGE = "ui/images/ships/player.png";
    private Player player;
    private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();

    public FXController() {}

    public void initialize(Scene scene) {
        background.setImage(new Image(BACKGROUND_IMAGE));
        background.setFitHeight(609);
        background.setFitWidth(900);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.A){
                    player.moveLeft();

                }else if (event.getCode() == KeyCode.D){
                    player.moveRight();

                }else if (event.getCode() == KeyCode.SPACE){
                    player.shoot(new Image("ui/images/lasers/laserBlue01.png"));
                }
            }
        });
        gameState();
    }

    public void gameState(){
        ImageView image = new ImageView(new Image(PLAYER_IMAGE));

        player = new Player(new Vector2D(Constants.WIDTH/2 - image.getFitWidth()/2,
                Constants.HEIGHT/2 - image.getFitHeight()/2), new Vector2D(), Constants.PLAYER_MAX_VEL, image, this);

        movingObjects.add(player);
        pane.getChildren().add(player.getTexture());
        player.getTexture().setX((894/2)-13);
        player.getTexture().setY(414);



    }

    public void draw() {

    }

    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }
}
