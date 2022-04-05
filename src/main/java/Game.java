import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.handlers.CollectibleHandler;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Game extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setVersion("1.0");
        gameSettings.setTitle("The Four Nerds and the Social Adventures of Terror");
        gameSettings.setHeight(800);
        gameSettings.setWidth(800);
        gameSettings.setAppIcon("glasses.png");
    }

    @Override
    protected void initGame(){
        player = FXGL.entityBuilder()
                .at(200, 200)
                .viewWithBBox("mario.png")
                .with(new CollidableComponent(true))
                .scale(0.5, 0.5)
                .type(EntityTypes.PLAYER)
                .buildAndAttach();

        FXGL.entityBuilder()
                .at(600, 600)
                .viewWithBBox(new Circle(10, Color.WHITE))
                .with(new CollidableComponent(true))
                .type(EntityTypes.ENEMY)
                .buildAndAttach();

        FXGL.getGameScene().setBackgroundColor(Color.BLACK);
    }

    @Override
    protected void initInput(){
        FXGL.onKey(KeyCode.W, () -> {
            player.translateY(-2);
        });

        FXGL.onKey(KeyCode.A, () -> {
            player.translateX(-2);
        });

        FXGL.onKey(KeyCode.S, () -> {
            player.translateY(2);
        });

        FXGL.onKey(KeyCode.D, () -> {
            player.translateX(2);
        });
    }

    @Override
    protected void initPhysics(){
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityTypes.PLAYER, EntityTypes.ENEMY) {
            @Override
            protected void onCollision(Entity player, Entity enemy) {
                enemy.removeFromWorld();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
