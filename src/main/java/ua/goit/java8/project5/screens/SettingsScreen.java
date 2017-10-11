package ua.goit.java8.project5.screens;

import com.alibaba.fastjson.JSON;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.goit.java8.project5.Main;
import ua.goit.java8.project5.tools.FileUtils;
import ua.goit.java8.project5.tools.SettingsSet;

import java.io.IOException;

/**
 * Created by t.oleksiv on 27/09/2017.
 */

// Екран з налаштуваннями
public class SettingsScreen {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 200;
    private static final String DEFAULT_CACHE_FOLDER = "cache";

    private SettingsSet settingsSet;
    private FileUtils fileUtils = new FileUtils();

    public SettingsScreen(){
        this.settingsSet = Main.settingsSet;
    }


    public void show(Event eventLast){
        Stage stage = new Stage();
        GridPane grid = new GridPane();     //grid для зручності вирівнювання, а можна і Pane root
        stage.setTitle("SettingsScreen");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        stage.setScene(new Scene(grid, WIDTH, HEIGHT));

        stage.initModality(Modality.WINDOW_MODAL);      //запускаєм вікно в модальному виді для того, щоб стартове вікно було неактивне

        //вказуєм нижче власника модального вікна по параметру event (в нашому випадку - це стартове вікно)
        stage.initOwner(
                ((Node)eventLast.getSource()).getScene().getWindow() );

        // заповнюєм елементами вікно
        // створюєм елементи, створюєм контейнери і запихаєм елементи в контейнери

        Button back = new Button("Back");
        back.setOnMouseClicked(event -> {
            // закриваєм активне вікно
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });

        CheckBox cbUseCache = new CheckBox("Use Cache");

        CheckBox cbShowTime = new CheckBox("Show Execution Time");

        Label lblPathToCache = new Label("Path to cache");

        TextField txtPathToCache = new TextField("");
        txtPathToCache.setPrefWidth(300);

        Button save = new Button("Save");
        save.setOnMouseClicked(event -> {
            saveSettings(cbUseCache,cbShowTime,txtPathToCache);
            // закриваєм активне вікно
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });

        Text scenetitle = new Text("SettingsScreen");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 1, 1);

        // контейнер для чекбокса  Use Cache
        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER_LEFT);
        hbox2.setPrefWidth(WIDTH);
        hbox2.setPrefHeight(HEIGHT/5);
        hbox2.getChildren().add(cbUseCache);
        grid.add(hbox2, 0, 1);


        // контейнер для  вибору шляху до кешу
        HBox hbox3 = new HBox(10);
        hbox3.setAlignment(Pos.CENTER_LEFT);
        hbox3.setPrefWidth(WIDTH);
        hbox3.setPrefHeight(HEIGHT/5);
        hbox3.getChildren().addAll(lblPathToCache,txtPathToCache);
        grid.add(hbox3, 0, 2,2,1);

        // контейнер для  чекбокса showTime
        HBox hbox4 = new HBox(10);
        hbox4.setAlignment(Pos.CENTER_LEFT);
        hbox4.setPrefWidth(WIDTH);
        hbox4.setPrefHeight(HEIGHT/5);
        hbox4.getChildren().add(cbShowTime);
        grid.add(hbox4, 0, 3);

        // контейнер для кнопки Back
        HBox hbox5 = new HBox(10);
        hbox5.setAlignment(Pos.CENTER_RIGHT);
        hbox5.setPrefWidth(WIDTH/2);
        hbox5.setPrefHeight(HEIGHT/5);
        hbox5.getChildren().add(back);
        grid.add(hbox5, 0, 4);

        // контейнер для кнопки Save
        HBox hbox6 = new HBox(10);
        hbox6.setAlignment(Pos.CENTER_LEFT);
        hbox6.setPrefWidth(WIDTH/2);
        hbox6.setPrefHeight(HEIGHT/5);
        hbox5.getChildren().add(save);
        grid.add(hbox6, 1, 4);

        readSettings(cbUseCache,cbShowTime,txtPathToCache);
        if ((settingsSet.getPathToCache().equals(null)) || (settingsSet.getPathToCache().equals(""))) {
            txtPathToCache.setText(fileUtils.getApplicationPath() + "\\" + DEFAULT_CACHE_FOLDER);
        }

        stage.show();
    }

    // зберігаєм налаштування
    private void saveSettings(CheckBox cbUseCache, CheckBox cbShowTime, TextField txtPathToCache){
        settingsSet.setUseCache(cbUseCache.isSelected()?true:false);
        settingsSet.setShowTime(cbShowTime.isSelected()?true:false);
        settingsSet.setPathToCache(txtPathToCache.getText());
        Main.settingsSet = settingsSet;

        String json = JSON.toJSONString(settingsSet);

        // якщо файл із налаштуваннями не існує, створюєм його перед збереженням даних
        if (!fileUtils.fileExists(fileUtils.getApplicationPath() + "/" + Main.PATH_TO_SETTINGS)) {
            try {
                fileUtils.createFile(fileUtils.getApplicationPath() + "/" + Main.PATH_TO_SETTINGS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // записуєм налаштування у файл в JSON форматі
        try {
            fileUtils.writeToFile(json, Main.PATH_TO_SETTINGS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // перевіряєм чи існує вказана папка з кешом; якщо не існує, то створюєм
        if (!fileUtils.dirExists(settingsSet.getPathToCache())) {
            try {
                fileUtils.createDir(settingsSet.getPathToCache());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // виводимо прочитані з файлу налаштування у поля на екрані
    private void readSettings(CheckBox cbUseCache, CheckBox cbShowTime, TextField txtPathToCache){
        cbUseCache.setSelected(settingsSet.getUseCache());
        cbShowTime.setSelected(settingsSet.getShowTime());
        txtPathToCache.setText(settingsSet.getPathToCache());
    }
}
