package tycoon.model;

import java.util.ArrayList;

public class GameModel {
    int money;
    int xp;
    int tilesize;
    ArrayList<Object> movingObjects;
    ArrayList<Object> setObjects;
    public GameModel(){
        money = 0;
        xp = 0;
        tilesize = 48;
        movingObjects = new ArrayList<>();
        setObjects = new ArrayList<>();
    }
    private void set(Object object){
        setObjects.add(object);
        movingObjects.remove(object);
    }
}
