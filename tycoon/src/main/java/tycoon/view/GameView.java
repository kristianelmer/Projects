package tycoon.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import tycoon.controller.Controller;
import tycoon.model.GameModel;
import tycoon.model.GameState;
import tycoon.model.objects.Objects;

public class GameView extends JPanel implements Runnable {
    Thread gameThread;
    double FPS = 15;
    GameModel model;
    Controller controller = new Controller();

    BufferedImage farmHouse;
    BufferedImage welcome;

    public GameView(){
        try {
            farmHouse = ImageIO.read(getClass().getResourceAsStream("/craftpix/Objects/7House/1.png"));
            welcome = ImageIO.read(getClass().getResourceAsStream("/welcome.jpg"));
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
            drawWelcome(g2);
        }
        else if (model.getGameState() == GameState.ACTIVE){
            drawActiveGame(g2);
        }
        else if (model.getGameState() == GameState.QUIT){
            System.exit(0);
        }
    }

    private void drawWelcome(Graphics2D g2){
        g2.drawImage(welcome, 0, 0, model.getGameWidth(), model.getGameHeight(), null);
    }
    private void drawActiveGame(Graphics2D g2){
        drawSetObjects(g2);
        if (model.emtyObject()){
            drawHovering(g2);
        }
        else{
            drawMovingObject(g2);
        }

    }
    private void drawSetObjects(Graphics2D g2){
        ArrayList<Objects> setObjects = model.getSetObjects();
        for (int i = 0; i < setObjects.size(); i++){
            Objects object = setObjects.get(i);
            g2.drawImage(farmHouse, object.getX(), object.getY(), model.getTileSize(), model.getTileSize(), null);
        }
    }
    private void drawMovingObject(Graphics2D g2){
        Objects object = model.getMovingObject();
        g2.drawImage(farmHouse, object.getX(), object.getY(), model.getTileSize(), model.getTileSize(), null);
    }
    private void drawHovering(Graphics2D g2){
        g2.setColor(new Color(0, 0, 0, 200));
        Objects object = model.getHoverTile();
        g2.drawRect(object.getX(), object.getY(), model.getTileSize(), model.getTileSize());
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
