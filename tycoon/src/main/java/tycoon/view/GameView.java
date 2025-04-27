package tycoon.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import tycoon.controller.Controller;
import tycoon.model.GameModel;
import tycoon.model.GameState;
import tycoon.model.Menu;
import tycoon.model.items.Item;

public class GameView extends JPanel implements Runnable {
    Thread gameThread;
    double FPS = 15;
    GameModel model;
    Controller controller = new Controller();

    BufferedImage bank;
    BufferedImage ChickenCoop;
    BufferedImage factory;
    BufferedImage farmHouse;
    BufferedImage fishHut;
    BufferedImage middleClassHouse;
    BufferedImage PeasantHouse;
    BufferedImage shop;
    BufferedImage wealthyHouse;
    HashMap<Item, BufferedImage> structureImages;
    ArrayList<Item> items;

    BufferedImage welcome;
    BufferedImage grass;


    Menu menu = new Menu();

    public GameView(){
        getImages();
        model = new GameModel(controller);

        this.setPreferredSize(new Dimension(model.getGameWidth(),model.getGameHeight()));
        this.setBackground(Color.lightGray);
        this.setDoubleBuffered(true);
        this.addKeyListener(controller);
        this.setFocusable(true);
    }

    private void getImages(){
        items = menu.getitems();
        structureImages = new HashMap<>();
        try {
            farmHouse = ImageIO.read(getClass().getResourceAsStream("/craftpix/Objects/7House/1.png"));
            welcome = ImageIO.read(getClass().getResourceAsStream("/welcome.png"));
            grass = ImageIO.read(getClass().getResourceAsStream("/craftpix/tiles/grass.png"));
            structureImages.put(items.get(0), ImageIO.read(getClass().getResourceAsStream("/structures/chickencoop.png")));
            structureImages.put(items.get(1), ImageIO.read(getClass().getResourceAsStream("/structures/fishhut.png")));
            structureImages.put(items.get(2), ImageIO.read(getClass().getResourceAsStream("/structures/peasanthouse.png")));
            structureImages.put(items.get(3), ImageIO.read(getClass().getResourceAsStream("/structures/farmhouse.png")));
            structureImages.put(items.get(4), ImageIO.read(getClass().getResourceAsStream("/structures/middleclasshouse.png")));
            structureImages.put(items.get(5), ImageIO.read(getClass().getResourceAsStream("/structures/shop.png")));
            structureImages.put(items.get(6), ImageIO.read(getClass().getResourceAsStream("/structures/factory.png")));
            structureImages.put(items.get(7), ImageIO.read(getClass().getResourceAsStream("/structures/bank.png")));
            structureImages.put(items.get(8), ImageIO.read(getClass().getResourceAsStream("/structures/wealthyhouse.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        drawMenu(g2);
        if (model.emtyObject()){
            drawHovering(g2);
        }
        else{
            drawMovingObject(g2);
        }

    }
    private void drawSetObjects(Graphics2D g2){
        ArrayList<Item> setObjects = model.getSetObjects();
        for (int i = 0; i < setObjects.size(); i++){
            Item object = setObjects.get(i);
            g2.drawImage(farmHouse, object.getX(), object.getY(), model.getTileSize(), model.getTileSize(), null);
        }
    }
    private void drawMovingObject(Graphics2D g2){
        Item object = model.getMovingObject();
        g2.drawImage(farmHouse, object.getX(), object.getY(), model.getTileSize(), model.getTileSize(), null);
    }
    private void drawHovering(Graphics2D g2){
        g2.setColor(new Color(0, 0, 0, 200));
        Item object = model.getHoverTile();
        g2.drawRect(object.getX(), object.getY(), model.getTileSize(), model.getTileSize());
    }
    private void drawMenu(Graphics2D g2){
        g2.setColor(new Color(150, 75, 0));
        g2.fillRect(model.getGameWidth()-106, 0, 106, model.getGameHeight());
        for (int i = 0; i < items.size(); i++){
            g2.drawImage(grass, model.getGameWidth()-103, 100*i+3*i, 100, 100, null);
            g2.drawImage(structureImages.get(items.get(i)), model.getGameWidth()-103, 100*i+3*i, 100, 100, null);
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
