package no.uib.inf101.model.powerups;

import java.util.ArrayList;

import no.uib.inf101.model.GameModel;
import no.uib.inf101.model.entities.Player;

/**
 * A factory class that creates random power-ups.
 * It can create different types of power-ups at random positions.
 */
public class RandomPowerUpFactory {
    private ArrayList<PowerUp> powerUps;

    private AttackBoost attackBoost;
    public AttackRangeBoost attackRangeBoost;
    private HealthRegen healthRegen;
    private MaxHealthGain maxHealthGain;
    private SpeedBoost speedBoost;
    public BecomeRanged becomeRanged;
    private AttackSpeedBoost attackSpeedBoost;

    private Player player;
    private boolean isTest;

    /**
     * Creates a new RandomPowerUpFactory for the specified player.
     * 
     * @param player The player that can collect the power-ups
     */
    public RandomPowerUpFactory(Player player, boolean isTest){
        powerUps = new ArrayList<>();
        this.player = player;
        this.isTest = isTest;
    }

    /**
     * Gets a random power-up at a random position on the map.
     * 
     * @return A randomly selected power-up
     */
    public PowerUp getRandomPowerUp(){
        becomeRanged = new BecomeRanged(getRandom(GameModel.mapwidth), getRandom(GameModel.mapheight), player, isTest);
        attackRangeBoost = new AttackRangeBoost(getRandom(GameModel.mapwidth), getRandom(GameModel.mapheight), player, isTest);
        attackBoost = new AttackBoost(getRandom(GameModel.mapwidth), getRandom(GameModel.mapheight), player, isTest);
        healthRegen = new HealthRegen(getRandom(GameModel.mapwidth), getRandom(GameModel.mapheight), player, isTest);
        maxHealthGain = new MaxHealthGain(getRandom(GameModel.mapwidth), getRandom(GameModel.mapheight), player, isTest);
        speedBoost = new SpeedBoost(getRandom(GameModel.mapwidth), getRandom(GameModel.mapheight), player, isTest);
        attackSpeedBoost = new AttackSpeedBoost(getRandom(GameModel.mapwidth), getRandom(GameModel.mapheight), player, isTest);

        powerUps.add(becomeRanged);
        powerUps.add(attackRangeBoost);
        powerUps.add(attackBoost);
        powerUps.add(healthRegen);
        powerUps.add(maxHealthGain);
        powerUps.add(speedBoost);
        powerUps.add(attackSpeedBoost);

        PowerUp currPowerUp = powerUps.get(getRandom(powerUps.size()));
        
        return currPowerUp;
    }

    /**
     * Gets a random number between 0 and the specified number.
     * 
     * @param num The upper bound for the random number
     * @return A random number between 0 and num
     */
    private int getRandom(int num) {
        return (int) (Math.random() * num);
    }
}
