package tycoon.model.items;

import java.awt.Rectangle;

public class Item {
    private int x;
    private int y;
    private int level;
    private int size = 48;
    private Rectangle bounds;
    public Item(int x, int y, int level){
        this.x = x;
        this.y = y;
        this.level = level;
        this.bounds = new Rectangle(x, y, size, size);
    }
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
        this.bounds = new Rectangle(x, y, size, size);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return level;
    }
    public void setSize(int size){
        this.size = size;
        this.bounds = new Rectangle(x, y, size, size);
    }
    public Rectangle getBounds(){
        return bounds;
    }
}
