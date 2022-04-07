package Game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.entity.Entity;
import enemy.Ninja;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

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
        PLAYER, BULLET, ENEMY, GROUND
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
//        setLevelFromMap("Level1.tmx");


        //music
        Music music = getAssetLoader().loadMusic("Music.wav");

        getAudioPlayer().playMusic(music);

        // Add the player
        this.player = spawn("player", 100, 600);
        this.ninja = spawn("enemy", ninja1.getSpawnX(), ninja1.getSpawnY());
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