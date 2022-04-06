package Game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import enemy.Ninja;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Game extends GameApplication {

    private final PlayerFactory playerFactory = new PlayerFactory();
    private final Ninja ninja1 = new Ninja(300, 300);
    private Entity player;
    private Entity ninja;

    public static void main(String[] args) {
        launch(args);
    }

    public enum EntityType {
        PLAYER, BULLET, ENEMY
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setHeight(750);
        settings.setWidth(1000);
        settings.setTitle("The Four Nerds and the Social Adventures of Terror");
        settings.setAppIcon("");
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(this.playerFactory);

        // Add the player
        this.player = spawn("player", 50, 650);
        this.ninja = spawn("enemy", ninja1.getSpawnX(), ninja1.getSpawnY());
        // Add a new enemy every second

    }


    @Override
    protected void initInput() {
        onKey(KeyCode.W, () -> player.translateY(-5));
        onKey(KeyCode.S, () -> player.translateY(5));
        onKey(KeyCode.A, () -> player.translateX(-5));
        onKey(KeyCode.D, () -> player.translateX(5));
        onBtnDown(MouseButton.PRIMARY, () ->
                spawn("bullet", player.getCenter()));
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0,700);
        onCollisionBegin(EntityType.BULLET, EntityType.ENEMY, (bullet, enemy) -> {
            bullet.removeFromWorld();
            enemy.removeFromWorld();
        });

        onCollisionBegin(EntityType.ENEMY, EntityType.PLAYER, (enemy, player) -> {
            showMessage("You Died!", () -> {
                getGameController().startNewGame();
            });
        });
    }
}








































//import com.almasb.fxgl.app.GameApplication;
//import com.almasb.fxgl.app.GameSettings;
//import com.almasb.fxgl.dsl.FXGL;
//import com.almasb.fxgl.entity.Entity;
//import javafx.scene.input.KeyCode;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//
//public class Game extends GameApplication {
//
//    private Entity player;
//
//    @Override
//    protected void initSettings(GameSettings gameSettings) {
//        gameSettings.setVersion("1.0");
//        gameSettings.setTitle("The Four Nerds and the Social Adventures of Terror");
//        gameSettings.setHeight(800);
//        gameSettings.setWidth(800);
//    }
//
//    @Override
//    protected void initGame(){
//        player = FXGL.entityBuilder()
//                .at(200, 200)
//                .view("mario.png")
//                .buildAndAttach();
//    }
//
//    @Override
//    protected void initInput(){
//        FXGL.onKey(KeyCode.W, () -> {
//            player.translateY(-5);
//        });
//
//        FXGL.onKey(KeyCode.A, () -> {
//            player.translateX(-5);
//        });
//
//        FXGL.onKey(KeyCode.S, () -> {
//            player.translateY(5);
//        });
//
//        FXGL.onKey(KeyCode.D, () -> {
//            player.translateX(5);
//        });
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
