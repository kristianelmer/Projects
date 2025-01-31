package pokemon;

import java.util.Random;

public class Pokemon {
    ////// Oppgave 1a
    // Create field variables here:
    private String name;
    private int healthPoints;
    private int maxHealthPoints;
    private int strength;

    ///// Oppgave 1b
    // Create a constructor here:
    public Pokemon(String name, int healthPoints, int strength){
        this.name = name;
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.maxHealthPoints = healthPoints;
    }

    ///// Oppgave 2

    /**
     * Get name of the pokémon
     *
     * @return name of pokémon
     */
    String getName() {
        return this.name;
    }

    /**
     * Get strength of the pokémon
     *
     * @return strength of pokémon
     */
    int getStrength() {
        return this.strength;
    }

    /**
     * Get current health points of pokémon
     *
     * @return current HP of pokémon
     */
    int getCurrentHP() {
        return this.healthPoints;
    }

    /**
     * Get maximum health points of pokémon
     *
     * @return max HP of pokémon
     */
    int getMaxHP() {
        return maxHealthPoints;
    }

    /**
     * Check if the pokémon is alive.
     * A pokemon is alive if current HP is higher than 0
     *
     * @return true if current HP > 0, false if not
     */
    boolean isAlive() {
        return this.healthPoints > 0;
    }


    ///// Oppgave 4

    /**
     * The Pokémon takes a given amount of damage. This method reduces the number of
     * health points the pokémon has by <code>damageTaken</code>.
     * If <code>damageTaken</code> is higher than the number of current
     * health points then set current HP to 0.
     * <p>
     * It should not be possible to deal negative damage, i.e. increase the number of health points.
     * <p>
     * The method should print how much HP the Pokémon is left with.
     *
     * @param damageTaken the amount to reduce the Pokémon's HP by
     */
    void takeDamage(int damageTaken) {
        if (damageTaken < 0){
            System.out.println("damage taken is negative");
        }
        else if (damageTaken > this.healthPoints){
            healthPoints = 0;
        }
        else{
            this.healthPoints -= damageTaken;
        }
        System.out.println(this.name + " takes " + damageTaken + "damage and is left with " + this.healthPoints + "/" + this.maxHealthPoints + "HP");
    }

    ///// Oppgave 5

    /**
     * Attack another pokémon. The method conducts an attack by <code>this</code>
     * on <code>target</code>. Calculate the damage using the pokémons strength
     * and a random element. Reduce <code>target</code>s health.
     * <p>
     * If <code>target</code> has 0 HP then print that it was defeated.
     *
     * @param target pokémon that is being attacked
     */
    void attack(Pokemon target) {
        Random rand = new Random();
        int damageInflincted = (int) (rand.nextInt(this.strength+1));
        System.out.println(this.name + " attacks " + target.name + ".");
        target.takeDamage(damageInflincted);
        if (target.healthPoints == 0){
            System.out.println(target.name + " is defeated by " + this.name + ".");
        }
        
    }

    ///// Oppgave 3
    @Override
    public String toString() {
        return name + " HP: (" + this.healthPoints + "/" + this.maxHealthPoints +  ") STR: " + this.strength; 
    }

}
