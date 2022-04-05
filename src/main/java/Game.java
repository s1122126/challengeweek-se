import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Game extends GameApplication {

    private Player player_1 = new Player(200, 200, true);

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
        player_1.LoadPlayer();

        FXGL.entityBuilder()
                .at(600, 600)
                .viewWithBBox(new Circle(10, Color.WHITE))
                .with(new CollidableComponent(true))
                .type(EntityTypes.ENEMY)
                .buildAndAttach();

        FXGL.getGameScene().setBackgroundColor(Color.BLACK);
    }


//    Werkt nu nog niet. moet ff kijken hoe ik de controls buiten de game klasse krijg.
//    @Override
//    protected void initInput(){
//        FXGL.onKey(KeyCode.W, () -> {
//            player_1.translateY(-2);
//        });
//
//        FXGL.onKey(KeyCode.A, () -> {
//            player.translateX(-2);
//        });
//
//        FXGL.onKey(KeyCode.S, () -> {
//            player.translateY(2);
//        });
//
//        FXGL.onKey(KeyCode.D, () -> {
//            player.translateX(2);
//        });
//    }

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
