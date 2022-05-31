package model.threads;

import model.gameObjects.Alien;
import model.gameObjects.MovingObject;

import java.awt.*;

public class THAlien extends Thread {
    private Alien alien;
    private boolean running;

    public THAlien(Alien alien) {
        this.alien = alien;
        running = true;

    }

    public void run() {
        while (running) {
            alien.update();

            try {
                Thread.sleep(20);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    public void stopThread(MovingObject alien) {
        running = !(this.alien == alien);
    }


}
