package enemy;

public abstract class Enemy {
    private String health;
    private String image;
    private int damageAmount;
    private int movementSpeed;
    private int spawnX;
    private int spawnY;

    public Enemy(String health, String image, int damageAmount, int movementSpeed, int spawnX, int spawnY) {
        this.health = health;
        this.image = image;
        this.damageAmount = damageAmount;
        this.movementSpeed = movementSpeed;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
    }

    public Enemy(int spawnX, int spawnY) {
        this.spawnX = spawnX;
        this.spawnY = spawnY;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void chase(){
        // Checkt of de enemy de player raakt
    }
}
