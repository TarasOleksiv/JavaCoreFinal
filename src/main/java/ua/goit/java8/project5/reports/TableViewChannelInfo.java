package ua.goit.java8.project5.reports;

/**
 * Created by t.oleksiv on 03/10/2017.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import ua.goit.java8.project5.Main;
import ua.goit.java8.project5.youtube.entities.channels.Channel;

import java.util.Date;

public class TableViewChannelInfo {
    private Channel[] channels;
    private VBox outputVBox;

    public TableViewChannelInfo(Channel[] channels, VBox outputVBox){
        this.channels = channels;
        this.outputVBox = outputVBox;
    }

    public void show(long startTime){

        TableView<ChannelIn> table = new TableView<ChannelIn>();

        // Create column id (Data type of String).
        TableColumn<ChannelIn, String> idCol //
                = new TableColumn<ChannelIn, String>("Channel Id");

        // Create column title (Data type of String).
        TableColumn<ChannelIn, String> titleCol //
                = new TableColumn<ChannelIn, String>("Channel Title");

        // Create column publishedAt (Data type of Date).
        TableColumn<ChannelIn, Date> publishedAtCol//
                = new TableColumn<ChannelIn, Date>("Published At");

        // Create column subscriberCount (Data type of Long).
        TableColumn<ChannelIn, Long> subscriberCountCol//
                = new TableColumn<ChannelIn, Long>("Subscribers Count");

        // Create column videoCount (Long).
        TableColumn<ChannelIn, Long> videoCountCol//
                = new TableColumn<ChannelIn, Long>("Video Count");

        // Create column viewCount (Long).
        TableColumn<ChannelIn, Long> viewCountCol //
                = new TableColumn<ChannelIn, Long>("View Count");


        // Defines how to fill data for each cell.
        // Get value from property of ChannelIn. .
        idCol.setCellValueFactory(new PropertyValueFactory<ChannelIn, String>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<ChannelIn, String>("title"));
        publishedAtCol.setCellValueFactory(new PropertyValueFactory<ChannelIn, Date>("publishedAt"));
        subscriberCountCol.setCellValueFactory(new PropertyValueFactory<ChannelIn, Long>("subscriberCount"));
        videoCountCol.setCellValueFactory(new PropertyValueFactory<ChannelIn, Long>("videoCount"));
        viewCountCol.setCellValueFactory(new PropertyValueFactory<ChannelIn, Long>("viewCount"));

        // Display row data
        ObservableList<ChannelIn> list = getChannelList();

        table.getColumns().addAll(idCol, titleCol, publishedAtCol, subscriberCountCol, videoCountCol, viewCountCol);
        table.setItems(list);

        Label gap1 = new Label();
        Label gap2 = new Label();
        Label lblTitle = new Label("Sort Channels By Data");
        lblTitle.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));
        outputVBox.getChildren().addAll(gap1, lblTitle, gap2, table);

        long duration = System.currentTimeMillis() - startTime;

        if (Main.settingsSet.getShowTime()) {
            Label gap = new Label();
            Label lblExecutionTime = new Label("Execution time:     " + duration);
            outputVBox.getChildren().addAll(gap, lblExecutionTime);
        }
    }


    // отримуєм list для заповнення клітинок таблиці
    private ObservableList<ChannelIn> getChannelList() {
        ObservableList<ChannelIn> list = FXCollections.observableArrayList();
        for (Channel channel:channels) {
            ChannelIn channelIn = new ChannelIn(channel.id,
                                                channel.snippet.title,
                                                channel.snippet.publishedAt,
                                                channel.statistics.subscriberCount,
                                                channel.statistics.videoCount,
                                                channel.statistics.viewCount);
            list.add(channelIn);
        }
        return list;
    }

    // допоміжний клас каналів для виводу у TableView
    public class ChannelIn {
        private String id;
        private String title;
        private Date publishedAt;
        private Long subscriberCount;
        private Long videoCount;
        private Long viewCount;

        public ChannelIn(String id, String title, Date publishedAt, Long subscriberCount, Long videoCount, Long viewCount){
            this.id = id;
            this.title = title;
            this.publishedAt = publishedAt;
            this.subscriberCount = subscriberCount;
            this.videoCount = videoCount;
            this.viewCount = viewCount;
        }

        public String getId(){return id;}
        public String getTitle(){return title;}
        public Date getPublishedAt(){return publishedAt;}
        public Long getSubscriberCount(){return subscriberCount;}
        public Long getVideoCount(){return videoCount;}
        public Long getViewCount(){return viewCount;}

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPublishedAt(Date publishedAt) {
            this.publishedAt = publishedAt;
        }

        public void setSubscriberCount(Long subscriberCount) {
            this.subscriberCount = subscriberCount;
        }

        public void setVideoCount(Long videoCount) {
            this.videoCount = videoCount;
        }

        public void setViewCount(Long viewCount) {
            this.viewCount = viewCount;
        }
    }
}