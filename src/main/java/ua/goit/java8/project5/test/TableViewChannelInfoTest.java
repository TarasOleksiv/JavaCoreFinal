package ua.goit.java8.project5.test;

/**
 * Created by t.oleksiv on 03/10/2017.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ua.goit.java8.project5.test.UserAccount;
import ua.goit.java8.project5.youtube.entities.channels.Channel;
import ua.goit.java8.project5.youtube.entities.channels.ChannelsResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class TableViewChannelInfoTest {
    private Channel[] channels;
    private ChannelIn channelIn;

    public TableViewChannelInfoTest(Channel[] channels){
        this.channels = channels;
    }

    public void show(){
        Stage stage = new Stage();
        TableView<ChannelIn> table = new TableView<ChannelIn>();

        // Create column ChannelId (Data type of String).
        TableColumn<ChannelIn, String> idCol //
                = new TableColumn<ChannelIn, String>("Channel Id");

        // Defines how to fill data for each cell.
        // Get value from property of Channel. .
        idCol.setCellValueFactory(new PropertyValueFactory<ChannelIn, String>("id"));

        // Display row data
        ObservableList<ChannelIn> list = getChannelList();
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).id);
        }
        //table.getColumns().addAll(channelIdCol, channelTitleCol, publishedAtCol, subscribersCountCol, videoCountCol, viewCountCol);

        table.getColumns().add(idCol);
        //channelIn = new ChannelIn("1");
        table.setItems(list);

        StackPane root = new StackPane();
        root.setPadding(new Insets(5));
        root.getChildren().add(table);

        stage.setTitle("Sorting Channels Array");

        Scene scene = new Scene(root, 550, 300);
        stage.setScene(scene);
        stage.show();
    }


    /*
    private ObservableList<ChannelIn> getChannelList() {

        ObservableList<ChannelIn> list = FXCollections.observableArrayList();
        for (Channel channel:channels) {
            ChannelIn channelIn = new ChannelIn(channel.id,channel.snippet.title,channel.snippet.publishedAt,
                                                channel.statistics.subscriberCount,channel.statistics.videoCount,channel.statistics.viewCount);
            list.add(channelIn);
        }

        return list;
    }
    */

    private ObservableList<ChannelIn> getChannelList() {

        ChannelIn channelIn1 = new ChannelIn("1");
        ChannelIn channelIn2 = new ChannelIn("2");
        ChannelIn channelIn3 = new ChannelIn("3");
        ObservableList<ChannelIn> list = FXCollections.observableArrayList(channelIn1,channelIn2,channelIn3);
        return list;
    }

    private class ChannelIn {
        public String id;
        //private String title;
        //private Date publishedAt;
        //private long subscriberCount;
        //private long videoCount;
        //private long viewCount;

        public ChannelIn(String id){
            this.id = id;
        }

        public ChannelIn(String id, String title, Date publishedAt, long subscriberCount, long videoCount, long viewCount){
            this.id = id;
            //this.title = title;
            //this.publishedAt = publishedAt;
            //this.subscriberCount = subscriberCount;
            //this.videoCount = videoCount;
            //this.viewCount = viewCount;
        }
    }
}