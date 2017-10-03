package ua.goit.java8.project5.reports;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import ua.goit.java8.project5.Main;
import ua.goit.java8.project5.youtube.entities.channels.Channel;
import ua.goit.java8.project5.youtube.entities.channels.ChannelsResponse;

import java.io.IOException;

/**
 * Created by t.oleksiv on 03/10/2017.
 */
public class SortChannelsByData {
    private VBox inputVBox;
    private VBox outputVBox;
    private Button execute;
    private Button back;
    private Button run;
    private String[] channelsArray;

    private static final String INPUT_CHANNEL_ARRAY =
                    "UC_x5XG1OV2P6uZZ5FSM9Ttw" + "\n"
                    + "UC-kpTYxx7mgvcSL7g93JLDw" + "\n"
                    + "UCufHx6C7e4t9Ib-IQP7PoeA" + "\n"
                    + "UCLM-4fcdyy2CQK-xURlvdjQ" + "\n"
                    + "UCWJ2lWNubArHWmf3FIHbfcQ" + "\n"
                    + "UCGRIgtqal_cC7V_cD-Zylkg" + "\n"
                    + "UCNTpErdUqTDqWq7YG1nasOQ" + "\n"
                    + "UCE_M8A5yxnLfW0KghEeajjw" + "\n"
                    + "UCpvg0uZH-oxmCagOWJo9p9g" + "\n"
                    + "UC2EU93iTrieTLeYdIO0uF7g" + "\n";

    public SortChannelsByData(VBox inputVBox, VBox outputVBox, Button execute, Button back){
        this.inputVBox = inputVBox;
        this.outputVBox = outputVBox;
        this.execute = execute;
        this.back = back;
    }

    // заповнюєм елементами Input box
    public void show(){

        Label lblChannelId = new Label("Channel Ids");

        // вікно в яке вводимо масив каналів
        TextArea txtChannelsArray = new TextArea();
        txtChannelsArray.setPrefWidth(200);
        txtChannelsArray.setText(INPUT_CHANNEL_ARRAY);

        run = new Button("Run");
        run.setOnMouseClicked(event -> {
            // запускаєм запит
            new Thread(()->{
                // виводимо результат в окреме вікно з допомогою елемента TableView
                TableViewChannelInfo tableViewChannelInfo = new TableViewChannelInfo(getChannels(getChannelsResponses(txtChannelsArray.getText())));
                Platform.runLater(()-> {
                    tableViewChannelInfo.show();
                });
            }).start();
        });

        inputVBox.getChildren().addAll(lblChannelId, txtChannelsArray, run);
    }

    // отримуєм масив id каналів з вікна вводу
    private String[] getChannelsArray(String string){
        String delims = "\n";
        String[] result = string.split(delims);
        return result;
    }

    // метод запуску отримання response
    private ChannelsResponse[] getChannelsResponses(String string){
        String[] channelsArray = getChannelsArray(string);
        ChannelsResponse[] channelsResponses = new ChannelsResponse[channelsArray.length];
        HTTPRequest httpRequest = new HTTPRequest();
        for (int i = 0; i < channelsResponses.length; i++){
            if (Main.settingsSet.getUseCache()){
                try {
                    channelsResponses[i] = httpRequest.getJSONResponse(channelsArray[i]);
                } catch (UnirestException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    channelsResponses[i] = httpRequest.getHTTPResponse(channelsArray[i]);

                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }
        }

        return channelsResponses;
    }

    // отримуєм масив каналів з даними
    private Channel[] getChannels(ChannelsResponse[] channelsResponses){
        Channel[] channels = new Channel[channelsResponses.length];
        for (int i = 0; i < channels.length; i++){
            channels[i] = channelsResponses[i].items.get(0);
        }
        return channels;
    }
}
