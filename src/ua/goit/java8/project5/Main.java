package ua.goit.java8.project5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Taras on 26.09.2017.
 */
public class Main extends Application {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 275;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //MyObjectMapper myObjectMapper = new MyObjectMapper();
        Pane root = new Pane();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, WIDTH, HEIGHT);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Main Screen");

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label appName = new Label("YouTube Monitor");
        grid.add(appName, 0, 1);

        Label versionNumber = new Label("version # 1.1");
        grid.add(versionNumber, 1, 1);

        Button youTubeAnalytics = new Button("YouTube Analytics");
        youTubeAnalytics.setOnMouseClicked(event -> {
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });

        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.CENTER);
        hbBtn1.getChildren().add(youTubeAnalytics);
        grid.add(hbBtn1, 0, 2);

        Button settings = new Button("Settings");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.CENTER);
        hbBtn2.getChildren().add(settings);
        grid.add(hbBtn2, 1, 2);

        primaryStage.show();

    }
}