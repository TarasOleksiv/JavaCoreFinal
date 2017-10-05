package ua.goit.java8.project5;

import com.alibaba.fastjson.JSON;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ua.goit.java8.project5.extra.FileUtils;
import ua.goit.java8.project5.extra.MyObjectMapper;
import ua.goit.java8.project5.extra.SettingsSet;
import ua.goit.java8.project5.screens.SettingsScreen;
import ua.goit.java8.project5.screens.YouTubeAnalyticsScreen;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Taras on 26.09.2017.
 */

// Головний екран
public class Main extends Application {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 275;
    private GridPane grid = new GridPane();
    public static final String PATH_TO_SETTINGS = "settings/settings.ini";
    public static SettingsSet settingsSet;

    public static void main(String[] args) {
        String json = null;
        try {
            json = FileUtils.readFromFile(PATH_TO_SETTINGS);
            settingsSet = JSON.parseObject(json,SettingsSet.class);
        } catch (FileNotFoundException e){
            settingsSet = new SettingsSet();
            settingsSet.setUseCache(false);
            settingsSet.setShowTime(false);
            settingsSet.setPathToCache("");
            System.out.println("The system cannot find the file with settings: \"" + PATH_TO_SETTINGS + "\"");
            System.out.println("Once the Save button for settings is pressed it will be created automatically.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //на випадок якщо буде потрібно використати парсер JSONа з Unirest asObject
        MyObjectMapper myObjectMapper = new MyObjectMapper();

        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //малюєм стартове вікно у GridPane
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

        Label versionNumber = new Label("version # 1.9");
        grid.add(versionNumber, 1, 1);

        Button buttonYouTubeAnalytics = new Button("YouTube Analytics");
        buttonYouTubeAnalytics.setOnMouseClicked(event -> {
            // ініціалізація вікна YouTubeAnalyticsScreen
            YouTubeAnalyticsScreen youTubeAnalyticsScreen = new YouTubeAnalyticsScreen();
            // запускаєм нове вікно в модальному виді
            youTubeAnalyticsScreen.show(event);
        });

        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.CENTER);
        hbBtn1.getChildren().add(buttonYouTubeAnalytics);
        grid.add(hbBtn1, 0, 2);

        Button buttonSettings = new Button("SettingsScreen");
        buttonSettings.setOnMouseClicked(event -> {
            // ініціалізація вікна SettingsScreen
            SettingsScreen settingsScreen = new SettingsScreen();
            // запускаєм нове вікно в модальному виді
            settingsScreen.show(event);
        });

        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.CENTER);
        hbBtn2.getChildren().add(buttonSettings);
        grid.add(hbBtn2, 1, 2);

        primaryStage.show();

    }
}