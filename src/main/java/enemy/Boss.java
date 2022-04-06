package enemy;

import enemy.Enemy;

public class Boss extends Enemy {
    public Boss(String health, String image, int damageAmount, int movementSpeed, int spawnX, int spawnY) {
        super(health, image, damageAmount, movementSpeed, spawnX, spawnY);
    }
}
