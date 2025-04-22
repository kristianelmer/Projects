package tycoon.model.objects;

import java.awt.Rectangle;

public class Object {
    private int x;
    private int y;
    private int level;
    private int size = 48;
    private Rectangle bounds;
    public Object(int x, int y, int level){
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
}
