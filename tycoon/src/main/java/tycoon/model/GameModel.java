package tycoon.model;

import java.util.ArrayList;
import java.util.Iterator;

import tycoon.controller.Controller;
import tycoon.model.items.FarmHouse;
import tycoon.model.items.Item;

public class GameModel {
    int money;
    int xp;
    int tilesize;
    Item movingObject;
    ArrayList<Item> setObjects;
    Controller controller;
    GameState gameState;
    Item hoverTile;
    int gameWidth;
    int gameHeight;
    int cols;
    int rows;
    public GameModel(Controller controller){
        money = 0;
        xp = 0;
        tilesize = 48;
        setObjects = new ArrayList<>();
        this.controller = controller;
        gameState = GameState.WELCOME;
        gameWidth = 1200;
        gameHeight = 960;
        cols = gameWidth/tilesize;
        rows = gameHeight/tilesize;
        hoverTile = new Item(12, 10, 1);
        movingObject = null;
    }
    private void set(Item object){
        setObjects.add(object);
        movingObject = null;
    }
    private void makeMovable(Item object){
        movingObject = null;
        setObjects.remove(object);
    }
    private void remove(Item object){
        if (movingObject != null) {
            movingObject = null;
        }
        else {
            setObjects.remove(object);
        }
    }
    public boolean emtyObject(){
        return movingObject == null;
    }
    private boolean tileNotEmpty(){
        Iterator<Item> it = setObjects.iterator();
        while (it.hasNext()){
            System.out.println(setObjects.size());
            Item object = (Item) it.next();
            if (object.getX() == hoverTile.getX() && object.getY() == hoverTile.getY()){
                return true;
            }
        }
        return false;
    }
    private Item getObject(){
        Iterator<Item> it = setObjects.iterator();
        while (it.hasNext()){
            Item object = (Item) it.next();
            if (object.getX() == hoverTile.getX() && object.getY() == hoverTile.getY()){
                return object;
            }
        }
        return null;   
    }
    public void update(){
        updateKeyPressed();
    }
    private void updateKeyPressed(){
        if (controller.qpressed) gameState = GameState.QUIT;
        
        if (gameState == GameState.WELCOME){
            if (controller.spacepressed) gameState = GameState.ACTIVE;
        }
        else if (gameState == GameState.ACTIVE){
            if (emtyObject()){
                if (controller.downPressed) move(0, 1, hoverTile);
                if (controller.upPressed) move(0, -1, hoverTile);
                if (controller.leftPressed) move(-1, 0, hoverTile);
                if (controller.rightPressed) move(1, 0, hoverTile);
                if (controller.spacepressed && tileNotEmpty()) makeMovable(getObject());
                if (controller.qpressed && tileNotEmpty()) remove(getObject());
                if (controller.rpressed) movingObject = new FarmHouse(hoverTile.getX(), hoverTile.getY(), 1);
            }
            else{
                if (controller.downPressed) move(0, 1, movingObject);
                if (controller.upPressed) move(0, -1, movingObject);
                if (controller.leftPressed) move(-1, 0, movingObject);
                if (controller.rightPressed) move(1, 0, movingObject);
                if (controller.spacepressed) set(movingObject);
            }
        }
    }
    private void move(int dx, int dy, Item object){
        object.setLocation(object.getX()+dx*tilesize, object.getY()+dy*tilesize);
    }

    //getters
    public ArrayList<Item> getSetObjects(){
        return setObjects;
    }
    public Item getMovingObject(){
        return movingObject;
    }
    public int getGameWidth(){
        return gameWidth;
    }
    public int getGameHeight(){
        return gameHeight;
    }
    public GameState getGameState(){
        return gameState;
    }
    public int getTileSize(){
        return tilesize;
    }
    public Item getHoverTile(){
        return hoverTile;
    }
}
