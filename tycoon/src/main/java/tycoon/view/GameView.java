package tycoon.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import tycoon.controller.Controller;
import tycoon.model.GameModel;
import tycoon.model.GameState;

public class GameView extends JPanel implements Runnable {
    Thread gameThread;
    double FPS = 30;
    GameModel model;
    Controller controller = new Controller();

    public GameView(){
        try {
            BufferedImage farmHouse = ImageIO.read(getClass().getResourceAsStream("/craftpix/Objects/7House/1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        model = new GameModel(controller);

        this.setPreferredSize(new Dimension(model.getGameWidth(),model.getGameHeight()));
        this.setBackground(Color.lightGray);
        this.setDoubleBuffered(true);
        this.addKeyListener(controller);
        this.setFocusable(true);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (model.getGameState() == GameState.WELCOME){
            
        }
    }












    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while (gameThread != null) {
            double drawinterval = 1000000000 / FPS;
            double nextdrawtime = System.nanoTime() + drawinterval;
            model.update();
            repaint();
            try {
                double remainingtime = nextdrawtime - System.nanoTime();
                remainingtime /= 1000000;
                if (remainingtime < 0) {
                    remainingtime = 0;
                }
                Thread.sleep((long) remainingtime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }

}
