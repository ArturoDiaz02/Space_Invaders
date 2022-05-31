package model.threads;

import model.gameObjects.Alien;
import model.gameObjects.MovingObject;

import java.awt.*;

public class THAlien extends Thread {
    private Alien alien;
    private boolean running;
    private Thread thread;

    public THAlien(Alien alien) {
        this.alien = alien;
        running = true;

    }

    public void run() {
        while (running) {
            alien.update();

            new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (running) {
                            double numerousAleatory = (Math.random() * 100);

                            if (numerousAleatory < 0.25) {
                                alien.shoot();
                            }

                            try {
                                Thread.sleep(5000);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }


                        }

                    }
            }).start();


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
