package ua.goit.java8.project5;

import com.alibaba.fastjson.JSON;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.goit.java8.project5.extra.FileUtils;
import ua.goit.java8.project5.extra.SettingsSet;

import java.io.IOException;

/**
 * Created by t.oleksiv on 27/09/2017.
 */
public class YouTubeAnalytics {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private SettingsSet settingsSet;

    public YouTubeAnalytics(SettingsSet settingsSet){
        this.settingsSet = settingsSet;
    }

    public void show(Event eventLast){
        Stage stage = new Stage();
        GridPane grid = new GridPane();     //grid для зручності вирівнювання, а можна і Pane root
        stage.setTitle("YouTube Analytics");
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

        // кнопка Back
        Button back = new Button("Back");
        back.setPrefWidth(100);
        back.setOnMouseClicked(event -> {
            // закриваєм активне вікно
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });

        // кнопка Execute
        Button execute = new Button("Execute");
        execute.setPrefWidth(100);
        execute.setOnMouseClicked(event -> {

        });

        // листбокс для вибору дій
        ChoiceBox choiceBoxAction = new ChoiceBox();
        choiceBoxAction.setItems(FXCollections.observableArrayList(
                "Отобразить глобальную информацию о канале",
                "Сравнить глобальную информацию о каналах",
                "Сортировать каналы по их данным",
                "Медиа резонанс",
                "Сравнить Медиа резонанс",
                "Сортировать по Медиа резонансу")
        );

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(WIDTH/2);
        rectangle.setHeight(HEIGHT - 30);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);

        // назва вікна
        Text scenetitle = new Text("YouTube Analytics");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 1, 1);

        // назва дії
        Text actionTitle = new Text("Choose action:");
        actionTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));

        // контейнер для  назви секції дій
        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER_LEFT);
        hbox2.setPrefWidth(WIDTH/4);
        hbox2.setPrefHeight(HEIGHT/30);
        hbox2.getChildren().add(actionTitle);
        grid.add(hbox2, 0, 1);

        // контейнер для  вибору дії
        HBox hbox3 = new HBox(10);
        hbox3.setAlignment(Pos.CENTER_LEFT);
        hbox3.setPrefWidth(WIDTH/3);
        hbox3.setPrefHeight(HEIGHT/20);
        hbox3.getChildren().add(choiceBoxAction);
        grid.add(hbox3, 0, 2);

        // контейнер для  кнопки Execute
        HBox hbox4 = new HBox(10);
        hbox4.setAlignment(Pos.CENTER_LEFT);
        hbox4.setPrefWidth(WIDTH/4);
        hbox4.setPrefHeight(HEIGHT/20);
        hbox4.getChildren().add(execute);
        grid.add(hbox4, 0, 3);

        // контейнер для кнопки Back
        HBox hbox5 = new HBox(10);
        hbox5.setAlignment(Pos.CENTER_LEFT);
        hbox5.setPrefWidth(WIDTH/4);
        hbox5.setPrefHeight(HEIGHT/20);
        hbox5.getChildren().add(back);
        grid.add(hbox5, 0, 4);

        // нижній блок
        HBox hbox6 = new HBox(10);
        hbox6.setAlignment(Pos.CENTER_LEFT);
        hbox6.setPrefWidth(WIDTH/4);
        hbox6.setPrefHeight(HEIGHT/2);
        grid.add(hbox6, 0, 5);

        // правий блок
        HBox hbox7 = new HBox(10);
        hbox7.setAlignment(Pos.CENTER_LEFT);
        hbox7.setPrefWidth(WIDTH/2);
        hbox7.setPrefHeight(HEIGHT);
        hbox7.getChildren().add(rectangle);
        grid.add(hbox7, 1, 0, 1, 6);

        // приклад коду для закриття попереднього вікна, з якого було відкрито дане
        //((Node)(event.getSource())).getScene().getWindow().hide();

        stage.show();
    }

}
