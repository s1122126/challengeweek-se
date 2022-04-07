package Game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.image;

public class EnemyController extends Component{
    private AnimationChannel animWalk;
    private PhysicsComponent physics;
    private int jumps = 2;
    private AnimatedTexture texture;


    private final double x;
    private final double y;

    private final Texture left;
    private final Texture right;
    private final Texture upDown;

    public EnemyController(double x, double y) {
        Image image = image("mario.png");
        animWalk = new AnimationChannel(image, 4, 32, 42,Duration.seconds(0.66), 0, 3);
        this.x = x;
        this.y = y;
        left = FXGL.texture("mario");
        right = FXGL.texture("mario");
        upDown = FXGL.texture("mario");
    }

    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
    }


    public void left() {
        getEntity().setScaleX(-1);
        physics.setVelocityX(-170);
    }

    public void right() {
        getEntity().setScaleX(1);
        physics.setVelocityX(170);
    }

//    public void stop() {
//        physics.setVelocityX(0);
//    }

    public void jump() {
        if (jumps == 0)
            return;

        physics.setVelocityY(-300);

        jumps--;
    }

}
