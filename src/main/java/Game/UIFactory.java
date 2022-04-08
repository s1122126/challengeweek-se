package Game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;

public class UIFactory extends SceneFactory {
    public FXGLMenu newMainMenu() {return new MainMenu(MenuType.MAIN_MENU);}
}
