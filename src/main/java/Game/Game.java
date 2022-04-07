package Game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import enemy.Ninja;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public class Game extends GameApplication {

    private final PlayerFactory playerFactory = new PlayerFactory();

    private Entity player;
    private Entity enemy;

    public static void main(String[] args) {
        launch(args);
    }

    public enum EntityType {
        PLAYER, BULLET, ENEMY, GROUND
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setHeight(1000);
        settings.setWidth(2000);
        settings.setTitle("The Four Nerds and the Social Adventures of Terror");
    }

    @Override
    protected void initGame() {
        Viewport viewport = getGameScene().getViewport();

        getGameWorld().addEntityFactory(this.playerFactory);
//        setLevelFromMap("testlevel.tmx");
        setLevelFromMap("Level1.tmx");


        //music
        Music music = getAssetLoader().loadMusic("Music.wav");

        getAudioPlayer().playMusic(music);

        // Add the player
        this.player = spawn("player", 100, 1);
        this.enemy = spawn("enemy", 300, 1);
        // set view to player
        viewport.bindToEntity(this.player, getAppWidth() / 2, getAppHeight() / 2);
        viewport.setLazy(true);


    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onActionBegin() {
                player.getComponent(PlayerComponent.class).jump();
            }
        }, KeyCode.SPACE, VirtualButton.UP);
//        onKey(KeyCode.S, () -> player.getComponent(PlayerComponent.class).()
        onKey(KeyCode.A, () -> player.getComponent(PlayerComponent.class).left());
        onKey(KeyCode.D, () -> player.getComponent(PlayerComponent.class).right());
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