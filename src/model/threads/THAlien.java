package model.threads;

import model.gameObjects.Alien;
import model.gameObjects.MovingObject;

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

            if ((Math.random() * 100) < 1.5) {
                alien.shoot();
            }

            try {
                Thread.sleep(25);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    public void stopThread(MovingObject alien) {
        running = !(this.alien == alien);
    }

    public Alien getAlien() {
        return alien;
    }


}
