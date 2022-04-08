package Game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Game extends GameApplication {

    private final PlayerFactory playerFactory = new PlayerFactory();

    private Entity player;
    private Entity enemy;

    public static void main(String[] args) {
        launch(args);
    }

    public enum EntityType {
        PLAYER, BULLET, ENEMY, GROUND, PLATFORM, WALL
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setHeight(1000);
        settings.setWidth(2000);
        settings.setTitle("The Four Nerds and the Social Adventures of Terror");
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new UIFactory());
    }

    @Override
    protected void initGame() {
        Viewport viewport = getGameScene().getViewport();

        getGameWorld().addEntityFactory(this.playerFactory);
//        setLevelFromMap("testlevel.tmx");
        setLevelFromMap("Level_cave.tmx");

        //music
        Music music = getAssetLoader().loadMusic("Music.wav");

        getAudioPlayer().playMusic(music);

        // Add the player
        this.player = spawn("player", 100, 1);
        this.enemy = spawn("enemy", 300, 1);
        // set view to player
//        viewport.setBounds(0,0,2000,1000);
//        viewport.setZoom(6.25);
        viewport.bindToEntity(this.player,  getAppWidth() / 2, (getAppHeight() / 2) + 300);

        viewport.setBounds(0,0,2000,1000);
        viewport.setZoom(6.25);
        viewport.bindToEntity(this.player, getAppWidth() / 2, (getAppHeight() / 2) + 300);

        viewport.setLazy(true);
    }

    @Override
    protected void initInput() {

        Sound shootSound = getAssetLoader().loadSound("shoot.wav");

        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onActionBegin() {
                player.getComponent(PlayerComponent.class).jump();
            }
        }, KeyCode.SPACE, VirtualButton.UP);

        getInput().addAction(new UserAction("left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).left();
            }
            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.A, VirtualButton.LEFT);

        getInput().addAction(new UserAction("right") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).right();
            }
            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }

        }, KeyCode.D, VirtualButton.RIGHT);

        onBtnDown(MouseButton.PRIMARY, () -> {
                    spawn("bullet", player.getCenter());

                    getAudioPlayer().playSound(shootSound);
                });
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0,700);
        onCollisionBegin(EntityType.BULLET, EntityType.ENEMY, (bullet, enemy) -> {
            bullet.removeFromWorld();
            enemy.removeFromWorld();
        });

        onCollisionBegin(EntityType.BULLET, EntityType.GROUND, (bullet, ground) -> {
            bullet.removeFromWorld();
        });

        onCollisionBegin(EntityType.BULLET, EntityType.WALL, (bullet, wall) -> {
            bullet.removeFromWorld();
        });

        onCollisionBegin(EntityType.BULLET, EntityType.PLATFORM, (bullet, platform) -> {
            bullet.removeFromWorld();
        });

        onCollisionBegin(EntityType.ENEMY, EntityType.PLAYER, (enemy, player) -> showMessage("You Died!", () -> getGameController().startNewGame()));
        onCollisionBegin(EntityType.PLATFORM, EntityType.ENEMY, (platform, enemy) -> {
            enemy.getComponent(EnemyController.class).turn();
        });
        onCollisionBegin(EntityType.WALL, EntityType.ENEMY, (wall, enemy) -> {
            enemy.getComponent(EnemyController.class).turn();
        });



    }
}