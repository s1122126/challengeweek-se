package enemy;

import enemy.Enemy;

public class Boss extends Enemy {

    public Boss(int health, int damageAmount, int movementSpeed, String image, int spawnX, int spawnY) {
        super(health, damageAmount, movementSpeed, image, spawnX, spawnY);
    }
}
