package no.uib.inf101.model.entities;

import java.awt.Rectangle;

/**
 * Abstract base class for all entities in the game.
 * Provides common functionality for movement, health, and attack capabilities.
 */
public abstract class Entity {
    protected Rectangle bounds;
    protected int health;
    protected int speed;
    protected int attackPower;
    protected int entitysize;
    protected int attackRangeWidth;
    protected int attackRangeLength;
    protected int maxHealth = 100;
    protected int x;
    protected int y;

    /**
     * Creates a new entity at the specified coordinates.
     * 
     * @param x The x-coordinate of the entity's position
     * @param y The y-coordinate of the entity's position
     */
    public Entity(int x, int y) {
        entitysize = 50;
        bounds = new Rectangle(x, y, entitysize, entitysize);
        health = maxHealth;
        attackRangeWidth = 1;
        attackRangeLength = 1;
        this.x = x;
        this.y = y;
    }

    /**
     * Reduces the entity's health by the specified amount.
     * 
     * @param damage The amount of damage to take
     */
    public void takeDamage(int damage) {
        health -= damage;
    }

    /**
     * Moves the entity by the specified amount.
     * 
     * @param dx The amount to move in the x direction
     * @param dy The amount to move in the y direction
     */
    public void move(int dx, int dy) {
        bounds.x += dx;
        bounds.y += dy;
        x += dx;
        y += dy;
    }

    /**
     * Gets the entity's bounding rectangle.
     * 
     * @return The entity's bounds
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Sets the size of the entity's bounds.
     * 
     * @param size The new size for the bounds
     */
    public void setBoundsSize(int size) {
        this.bounds = new Rectangle(this.bounds.x, this.bounds.y, size, size);
    }

    /**
     * Gets the entity's current health.
     * 
     * @return The current health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets the entity's maximum health.
     * 
     * @return The maximum health
     */
    public int getMaxhealth() {
        return maxHealth;
    }

    /**
     * Gets the entity's movement speed.
     * 
     * @return The movement speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Gets the entity's attack power.
     * 
     * @return The attack power
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Gets the width of the entity's attack range.
     * 
     * @return The attack range width
     */
    public int getAttackRangeWidth() {
        return attackRangeWidth;
    }

    /**
     * Gets the length of the entity's attack range.
     * 
     * @return The attack range length
     */
    public int getAttackRangeLength() {
        return attackRangeLength;
    }

    /**
     * Gets the entity's size.
     * 
     * @return The entity size
     */
    public int getEntitySize() {
        return entitysize;
    }

    /**
     * Sets the entity's size.
     * 
     * @param entitysize The new entity size
     */
    public void setEntitySize(int entitysize) {
        this.entitysize = entitysize;
        setBoundsSize(entitysize);
    }

    /**
     * Sets the entity's attack power.
     * 
     * @param attackPower The new attack power
     */
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    /**
     * Sets the entity's attack range.
     * 
     * @param attackRangeWidth The new attack range width
     * @param attackRangeLength The new attack range length
     */
    public void setAttackRange(int attackRangeWidth, int attackRangeLength) {
        this.attackRangeWidth = attackRangeWidth;
        this.attackRangeLength = attackRangeLength;
    }

    /**
     * Sets the entity's movement speed.
     * 
     * @param speed The new movement speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Sets the entity's current health.
     * 
     * @param health The new health value
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Sets the entity's maximum health.
     * 
     * @param maxhealth The new maximum health
     */
    public void setMaxHealth(int maxhealth) {
        this.maxHealth = maxhealth;
    }

    /**
     * Gets the entity's x-coordinate.
     * 
     * @return The x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the entity's y-coordinate.
     * 
     * @return The y-coordinate
     */
    public int getY() {
        return y;
    }
}
