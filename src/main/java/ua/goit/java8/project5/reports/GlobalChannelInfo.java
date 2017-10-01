package ua.goit.java8.project5.reports;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ua.goit.java8.project5.Main;
import ua.goit.java8.project5.extra.DateUtils;
import ua.goit.java8.project5.youtube.entities.ChannelsResponse;

/**
 * Created by Taras on 29.09.2017.
 */
public class GlobalChannelInfo {
    private VBox inputVBox;
    private VBox outputVBox;
    private Button execute;
    private Button back;
    private Button run;

    public GlobalChannelInfo(VBox inputVBox, VBox outputVBox, Button execute, Button back){
        this.inputVBox = inputVBox;
        this.outputVBox = outputVBox;
        this.execute = execute;
        this.back = back;
    }

    public void show(){
        HBox channelId = new HBox();
        channelId.setSpacing(10);

        Label lblChannelId = new Label("Channel Id");
        channelId.getChildren().add(lblChannelId);

        TextField textChannelId = new TextField("UC_x5XG1OV2P6uZZ5FSM9Ttw");
        textChannelId.setPrefWidth(250);
        channelId.getChildren().add(textChannelId);

        run = new Button("Run");
        run.setOnMouseClicked(event -> {
            // запускаєм запит
            new Thread(()->{
                showResults(textChannelId);
            }).start();
        });

        inputVBox.getChildren().addAll(channelId, run);
    }

    private void showResults(TextField textField){
        long startTime = System.currentTimeMillis();

        Platform.runLater(()-> {
            execute.setDisable(true);
            back.setDisable(true);
            run.setDisable(true);
            clearOutputVBox();
        });
        ChannelsResponse channelsResponse = null;
        HTTPRequest httpRequest = new HTTPRequest();
        try {
            channelsResponse = httpRequest.getResponse(textField.getText());

        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (channelsResponse.items.size() > 0){
            //System.out.println(channelsResponse.items.get(0).snippet.title);
            final ChannelsResponse chResponse = channelsResponse;

            Label gap1 = new Label();
            Label gap2 = new Label();

            Platform.runLater(()-> {
                Label lblTitle = new Label("Global Channel Info");
                Label lblChannelTitle = new Label("Channel Title:   " + chResponse.items.get(0).snippet.title);
                Label lblPublishedAt = new Label("Published At:     " + DateUtils.convertDateToString(chResponse.items.get(0).snippet.publishedAt));
                Label lblSubscriberCount = new Label("Subscribers Count:    " + chResponse.items.get(0).statistics.subscriberCount);
                Label lblVideoCount = new Label("Video Count:   " + chResponse.items.get(0).statistics.videoCount);
                Label lblViewCount = new Label("View Count:     " + chResponse.items.get(0).statistics.viewCount);
                outputVBox.getChildren().addAll(gap1, lblTitle, gap2,
                        lblChannelTitle, lblPublishedAt,
                        lblSubscriberCount, lblVideoCount, lblViewCount);
            });
        }

        long duration = System.currentTimeMillis() - startTime;
        final long dr = duration;

        Platform.runLater(()-> {
            if (Main.settingsSet.getShowTime()){
                Label gap = new Label();
                Label lblExecutionTime = new Label("Execution time:     " + dr);
                outputVBox.getChildren().addAll(gap,lblExecutionTime);
            }
            execute.setDisable(false);
            back.setDisable(false);
            run.setDisable(false);
        });
    }


    private void clearOutputVBox(){
        if (outputVBox != null){
            outputVBox.getChildren().clear();
            drawOutputTitle(outputVBox);
        }
    }

    private void drawOutputTitle(VBox outputVBox){
        Text outputTitle = new Text("Output");
        outputTitle.setTranslateX(10);
        outputTitle.setTranslateY(10);
        outputTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        outputVBox.getChildren().add(outputTitle);
    }
}
