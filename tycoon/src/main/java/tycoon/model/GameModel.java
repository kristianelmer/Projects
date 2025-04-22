package tycoon.model;

import java.util.ArrayList;
import java.util.Iterator;

import tycoon.controller.Controller;
import tycoon.model.objects.FarmHouse;
import tycoon.model.objects.Objects;

public class GameModel {
    int money;
    int xp;
    int tilesize;
    Objects movingObject;
    ArrayList<Objects> setObjects;
    Controller controller;
    GameState gameState;
    Objects hoverTile;
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
        hoverTile = new Objects(12, 10, 1);
        movingObject = null;
    }
    private void set(Objects object){
        setObjects.add(object);
        movingObject = null;
    }
    private void makeMovable(Objects object){
        movingObject = null;
        setObjects.remove(object);
    }
    private void remove(Objects object){
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
        Iterator<Objects> it = setObjects.iterator();
        while (it.hasNext()){
            System.out.println(setObjects.size());
            Objects object = (Objects) it.next();
            if (object.getX() == hoverTile.getX() && object.getY() == hoverTile.getY()){
                return true;
            }
        }
        return false;
    }
    private Objects getObject(){
        Iterator<Objects> it = setObjects.iterator();
        while (it.hasNext()){
            Objects object = (Objects) it.next();
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
    private void move(int dx, int dy, Objects object){
        object.setLocation(object.getX()+dx*tilesize, object.getY()+dy*tilesize);
    }

    //getters
    public ArrayList<Objects> getSetObjects(){
        return setObjects;
    }
    public Objects getMovingObject(){
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
    public Objects getHoverTile(){
        return hoverTile;
    }
}
