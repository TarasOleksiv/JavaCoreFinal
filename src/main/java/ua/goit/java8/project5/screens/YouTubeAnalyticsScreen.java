package ua.goit.java8.project5.screens;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.goit.java8.project5.reports.CompareGlobalChannelInfo;
import ua.goit.java8.project5.reports.GlobalChannelInfo;


/**
 * Created by t.oleksiv on 27/09/2017.
 */

// Екран вибору та запуску завдань
public class YouTubeAnalyticsScreen {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private VBox inputVBox;
    private VBox outputVBox;
    //private SettingsSet settingsSet;

    private Button execute;
    private Button back;

    //public YouTubeAnalyticsScreen(){
    //    this.settingsSet = Main.settingsSet;
    //}

    public void show(Event eventLast){
        Stage stage = new Stage();
        Pane root = new Pane();
        stage.setTitle("YouTube Analytics");

        stage.setScene(new Scene(root, WIDTH, HEIGHT));

        stage.initModality(Modality.WINDOW_MODAL);      //запускаєм вікно в модальному виді для того, щоб стартове вікно було неактивне

        //вказуєм нижче власника модального вікна по параметру event (в нашому випадку - це стартове вікно)
        stage.initOwner(
                ((Node)eventLast.getSource()).getScene().getWindow() );

        // заповнюєм елементами вікно

        // назва вікна
        Text scenetitle = new Text("YouTube Analytics");
        scenetitle.setTranslateX(20);
        scenetitle.setTranslateY(40);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        root.getChildren().add(scenetitle);

        // назва дії
        Text actionTitle = new Text("Choose action:");
        actionTitle.setTranslateX(20);
        actionTitle.setTranslateY(90);
        actionTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        root.getChildren().add(actionTitle);

        // листбокс для вибору дій
        ChoiceBox choiceBoxAction = new ChoiceBox();
        choiceBoxAction.setTranslateX(20);
        choiceBoxAction.setTranslateY(100);
        choiceBoxAction.setItems(FXCollections.observableArrayList(
                "Show global channel info",
                "Compare global channel info",
                "Sort channels by data",
                "Media resonance",
                "Compare media resonance",
                "Sort by media resonance")
        );
        choiceBoxAction.getSelectionModel().select(0);
        root.getChildren().add(choiceBoxAction);

        // кнопка Back
        back = new Button("Back");
        back.setTranslateX(20);
        back.setTranslateY(180);
        back.setPrefWidth(100);
        back.setOnMouseClicked(event -> {
            // закриваєм активне вікно
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });
        root.getChildren().add(back);

        // кнопка Execute
        execute = new Button("Execute");
        execute.setTranslateX(20);
        execute.setTranslateY(140);
        execute.setPrefWidth(100);
        execute.setOnMouseClicked(event -> {
            executeAction(choiceBoxAction.getSelectionModel().getSelectedIndex());
        });
        root.getChildren().add(execute);

        Text inputTitle = new Text("Input");
        inputTitle.setTranslateX(10);
        inputTitle.setTranslateY(10);
        inputTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));

        // бокс для вводу вхідних даних
        inputVBox = new VBox();
        inputVBox.setTranslateX(20);
        inputVBox.setTranslateY(250);
        inputVBox.setPrefWidth(400);
        inputVBox.setPrefHeight(300);
        inputVBox.setSpacing(30);
        inputVBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;");
        inputVBox.getChildren().add(inputTitle);
        root.getChildren().add(inputVBox);

        Text outputTitle = new Text("Output");
        outputTitle.setTranslateX(10);
        outputTitle.setTranslateY(10);
        outputTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));

        // бокс для виводу результатів з блоку вхідних даних
        outputVBox = new VBox();
        outputVBox.setTranslateX(450);
        outputVBox.setTranslateY(90);
        outputVBox.setPrefWidth(500);
        outputVBox.setPrefHeight(460);
        outputVBox.setSpacing(10);
        outputVBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;");
        outputVBox.getChildren().add(outputTitle);
        root.getChildren().add(outputVBox);

        stage.show();
    }

    // метод розподілу вибраних дій
    public void executeAction(int choice){
        clearInputVBox();
        clearOutputVBox();
        switch (choice){
            case 0:
                GlobalChannelInfo globalChannelInfo = new GlobalChannelInfo(inputVBox,outputVBox,execute,back);
                globalChannelInfo.show();
                //showGlobalChannelInfo();
                break;
            case 1:
                CompareGlobalChannelInfo compareGlobalChannelInfo = new CompareGlobalChannelInfo(inputVBox,outputVBox,execute,back);
                compareGlobalChannelInfo.show();
                //compareGlobalChannelInfo();
                break;
            case 2:
                sortChannelsByData();
                break;
            case 3:
                showMediaResonance();
                break;
            case 4:
                compareMediaResonance();
                break;
            case 5:
                sortByMediaResonance();
                break;
            default:
                break;
        }
    }

    // очистка елементів боксу інпута
    private void clearInputVBox(){
        if (inputVBox != null){
            inputVBox.getChildren().clear();
            drawInputTitle(inputVBox);
        }
    }

    // малюєм назву боксу інпута
    private void drawInputTitle(VBox inputHBox){
        Text inputTitle = new Text("Input");
        inputTitle.setTranslateX(10);
        inputTitle.setTranslateY(10);
        inputTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        inputHBox.getChildren().add(inputTitle);
    }

    // очистка елементів боксу аутпута
    private void clearOutputVBox(){
        if (outputVBox != null){
            outputVBox.getChildren().clear();
            drawOutputTitle(outputVBox);
        }
    }

    // малюєм назву боксу аутпута
    private void drawOutputTitle(VBox outputVBox){
        Text outputTitle = new Text("Output");
        outputTitle.setTranslateX(10);
        outputTitle.setTranslateY(10);
        outputTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        outputVBox.getChildren().add(outputTitle);
    }

    private void showGlobalChannelInfo(){
        System.out.println("0");
    }

    private void compareGlobalChannelInfo(){
        System.out.println("1");
    }

    private void sortChannelsByData(){
        System.out.println("2");
    }

    private void showMediaResonance(){
        System.out.println("3");
    }

    private void compareMediaResonance(){
        System.out.println("4");
    }

    private void sortByMediaResonance(){
        System.out.println("5");
    }


    public Button getExecute(){return execute;}
    public Button getBack(){return back;}
}
