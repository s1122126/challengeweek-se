package Game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenu extends FXGLMenu {

    public MainMenu(MenuType type){
        super(type);
        int width = getAppWidth();
        int height = getAppHeight();
//        var bg = new Rectangle(width, height, Color.RED);
        BackgroundImage mainBackground = new BackgroundImage(new Image("Assets/Textures/main_menu_background.png", FXGL.getAppHeight(), FXGL.getAppWidth() * 1.8, true, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true, true, false, true));
        Text titel = new Text("The Four Nerds");
        Text titel2 = new Text("and the");
        Text titel3 = new Text("Social Adventures of Terror");
        titel.setFont(Font.font("verdana",40));
        titel.setFill(Color.DARKTURQUOISE);
        titel2.setFont(Font.font("verdana",40));
        titel2.setFill(Color.DARKTURQUOISE);
        titel3.setFont(Font.font("verdana",40));
        titel3.setFill(Color.DARKTURQUOISE);
        VBox vBox = new VBox(2);
        vBox.setBackground(new Background(mainBackground));
        vBox.getChildren().add(titel);
        vBox.getChildren().add(titel2);
        vBox.getChildren().add(titel3);
        getContentRoot().getChildren().add(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinWidth(width);
        vBox.setMinHeight(height);

        VBox vBox2 = new VBox(2);
        vBox2.setBackground(new Background(mainBackground));
        TextField text = new TextField("Enter your name");
        vBox2.getChildren().add(text);

        Button button = new Button("Start");
        button.setOnAction(e->{
            getContentRoot().getChildren().remove(vBox);
            getContentRoot().getChildren().add(vBox2);
        });
        Button button2 = new Button("Exit");
        button2.setOnAction(e->{
            fireExit();
        });
        Button button3 = new Button("Submit");
        button3.setOnAction(e->{
            fireNewGame();
        });

        button.setMinSize(200, 100);
        button.setStyle("-fx-background-color: white");
        button2.setMinSize(200, 100);
        button2.setStyle("-fx-background-color: white");
        button.setStyle("-fx-text-fill: black");
        button2.setStyle("-fx-text-fill: black");

        vBox.getChildren().add(button);
        vBox.getChildren().add(button2);
        vBox2.getChildren().add(button3);
    }
}