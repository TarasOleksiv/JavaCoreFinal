package ua.goit.java8.project5.reports;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ua.goit.java8.project5.test.ChannelIn;


/**
 * Created by t.oleksiv on 03/10/2017.
 */
public class TableViewChannelInfo extends Application {

    public void show() {
        Stage stage = new Stage();
        TableView<ChannelIn> table = new TableView<ChannelIn>();

        // Create column ChannelId (Data type of String).
        TableColumn<ChannelIn,String> idCol = new TableColumn<>("Channel Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        ObservableList<ChannelIn> list = getChannelList();
        table.getColumns().add(idCol);
        table.setItems(list);

        StackPane root = new StackPane();
        root.setPadding(new Insets(5));
        root.getChildren().add(table);

        stage.setTitle("Sorting Channels Array");

        Scene scene = new Scene(root, 550, 300);
        stage.setScene(scene);
        stage.show();
    }

        private ObservableList<ChannelIn> getChannelList() {

        ChannelIn channelIn1 = new ChannelIn("1");
        ChannelIn channelIn2 = new ChannelIn("2");
        ChannelIn channelIn3 = new ChannelIn("3");
        ObservableList<ChannelIn> list = FXCollections.observableArrayList(channelIn1,channelIn2,channelIn3);
        return list;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
