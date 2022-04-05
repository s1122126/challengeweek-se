import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.scene.input.KeyCode;

import java.awt.*;

public class Player extends Component{

    private int Xcords;
    private int Ycords;
    private boolean collidable;

    private PhysicsComponent physics;

    public Player(int xcords, int ycords, boolean collidable) {
        Xcords = xcords;
        Ycords = ycords;
        this.collidable = collidable;
    }

    public void LoadPlayer(){
        FXGL.entityBuilder()
                .at(Xcords, Ycords)
                .viewWithBBox("mario.png")
                .with(new CollidableComponent(collidable))
                .scale(0.5, 0.5)
                .type(EntityTypes.PLAYER)
                .buildAndAttach();
    }

//    @Override
//    protected void initInput(){
//        FXGL.onKey(KeyCode.W, () -> {
//            player.translateY(-2);
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

}
