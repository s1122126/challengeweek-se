package enemy;

import Game.Game;
import com.almasb.fxgl.dsl.components.RandomMoveComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static com.almasb.fxgl.dsl.FXGL.*;

public abstract class Enemy {
    private int health;
    private int damageAmount;
    private int movementSpeed;
    private String image;
    private int spawnX;
    private int spawnY;


    public Enemy(int health, int damageAmount, int movementSpeed, String image, int spawnX, int spawnY){
        this.health = health;
        this.damageAmount = damageAmount;
        this.movementSpeed = movementSpeed;
        this.image = image;

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

