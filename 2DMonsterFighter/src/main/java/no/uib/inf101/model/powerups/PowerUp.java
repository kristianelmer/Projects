package no.uib.inf101.model.powerups;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import no.uib.inf101.model.entities.Player;

/**
 * Abstract base class for all power-ups in the game.
 * Power-ups are collectible items that provide various benefits to the player.
 */
public class PowerUp {
    protected BufferedImage powerUpImage;
    private Rectangle bounds;
    protected int x;
    protected int y;
    protected Player player;
    private int size = 30;

    /**
     * Creates a new PowerUp at the specified coordinates.
     * 
     * @param x The x-coordinate of the power-up's position
     * @param y The y-coordinate of the power-up's position
     * @param player The player that can collect this power-up
     */
    public PowerUp(int x, int y, Player player){
        this.x = x;
        this.y = y;
        this.player = player;
        bounds = new Rectangle(x, y, size, size);
    }

    /**
     * Gets the image representing this power-up.
     * 
     * @return The power-up's image
     */
    public BufferedImage getImage(){
        return powerUpImage;
    }

    /**
     * Gets the x-coordinate of this power-up.
     * 
     * @return The x-coordinate
     */
    public int getX(){
        return x;
    }

    /**
     * Gets the y-coordinate of this power-up.
     * 
     * @return The y-coordinate
     */
    public int getY(){
        return y;
    }

    /**
     * Gets the bounding rectangle of this power-up.
     * 
     * @return The power-up's bounds
     */
    public Rectangle getBounds(){
        return bounds;
    }

    /**
     * Activates the power-up's effect.
     * This method must be overridden by subclasses to implement specific power-up effects.
     * 
     * @throws UnsupportedOperationException if not overridden by a subclass
     */
    public void activate() {
        throw new UnsupportedOperationException("Unimplemented method 'activate'");
    }

    /**
     * Gets the size of this power-up.
     * 
     * @return The power-up's size
     */
    public int getSize(){
        return size;
    }
}
