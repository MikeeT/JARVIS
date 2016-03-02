package GUI;

import Exceptions.AppNotFoundException;
import Personality.Talk;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This will contain all the user controls and interface options
 * The main program will be a text box that user can input commands
 * into. Later i hope to incorporate a voice to text translator
 * @author Michael Topolski
 * December 2015
 */
public class JARVISGUI extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Calculator calc = new Calculator();
        BorderPane border = new BorderPane();

        //set the side of the border
        //this section will display all the commands
        TextArea text = new TextArea();
        String fileName = "src/textDocs/help.txt";
        String textbox = "";
        try {
            List lines = Files.readAllLines(Paths.get(fileName),
                    Charset.defaultCharset());
            for (Object line : lines) {
                textbox += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setText(textbox);
        text.setStyle("-fx-text-fill: green; -fx-font-size: 13;");
        text.setMaxWidth(500);
        text.setMaxHeight(320);
        text.setWrapText(true);
        text.setEditable(false);
        border.setLeft(text);

        //set bottom of border
        TextField JARVIS = new TextField();
        JARVIS.setText("Hello Sir");
        JARVIS.setStyle("-fx-text-fill: green; -fx-font-size: 16;");
        JARVIS.setBackground(new Background(
                new BackgroundFill(
                        new Color(0,0,0,0),
                        CornerRadii.EMPTY,
                        Insets.EMPTY  )));
        JARVIS.setEditable(false);
        JARVIS.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.equals(""))
                {
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask()
                    {
                        @Override
                        public void run()
                        {
                            JARVIS.setText("");
                            timer.cancel();
                        }
                    }, 2000, 10);
                }
            }
        });

                TextField tField = new TextField();
        tField.setStyle("-fx-text-fill: green; -fx-font-size: 16;");
        tField.setBackground(new Background(
                new BackgroundFill(
                        new Color(0,0,0,0),
                        CornerRadii.EMPTY,
                        Insets.EMPTY  )));
        tField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.ENTER)
                {
                    String inputLine = tField.getCharacters().toString();
                    String command = inputLine.split(" ")[0];
                    tField.clear();
                    if (command.toLowerCase().equals("help"))
                    {
                        if (text.isVisible()) text.setVisible(false);
                        else text.setVisible(true);
                    }
                    else if (command.toLowerCase().equals("exe"))
                    {
                        String appname = "";
                        try {
                            appname = inputLine.split(" ")[1].toLowerCase();
                            if(appname.equals("add"))
                            {
                                Stage dialog = new Stage();
                                dialog.initStyle(StageStyle.UTILITY);
                                BorderPane dialogborder = new BorderPane();
                                VBox vbox = new VBox();
                                TextField tFieldname = new TextField();
                                tFieldname.setText("Enter Name of application");
                                tFieldname.setStyle("-fx-font-size: 16;");
                                tFieldname.setBackground(new Background(
                                        new BackgroundFill(
                                                new Color(0,0,0,0),
                                                CornerRadii.EMPTY,
                                                Insets.EMPTY  )));
                                TextField tFieldlocation = new TextField();
                                tFieldlocation.setText("Enter file location of application");
                                tFieldlocation.setStyle("-fx-font-size: 16;");
                                tFieldlocation.setBackground(new Background(
                                        new BackgroundFill(
                                                new Color(0,0,0,0),
                                                CornerRadii.EMPTY,
                                                Insets.EMPTY  )));
                                vbox.getChildren().addAll(tFieldname,tFieldlocation);
                                dialogborder.setCenter(vbox);

                                //set bottom
                                Button ok = new Button();
                                ok.setText("Ok");
                                ok.setMaxSize(100,201);
                                ok.setOnMousePressed(new EventHandler<MouseEvent>()
                                {
                                    @Override
                                    public void handle(MouseEvent event)
                                    {
                                        exe.addApp(tFieldname.getText(), tFieldlocation.getText());
                                        dialog.close();
                                    }
                                });
                                dialogborder.setBottom(ok);

                                //set the stage
                                dialog.setScene(new Scene( dialogborder, 300, 100 ));
                                dialog.setResizable(false);
                                dialog.show();
                            }
                            String app = exe.file(appname);
                            String cmds[] = new String[] {"cmd", "/c", app};
                            try {
                                Runtime.getRuntime().exec(cmds);
                            } catch(IOException e){}
                        } catch(ArrayIndexOutOfBoundsException e){
                            JARVIS.setText("You need to enter an application.");
                            System.err.println(e);
                        } catch(AppNotFoundException e) {
                            JARVIS.setText("This application does not exist in database");
                        }
                    }
                    else if (command.toLowerCase().equals("web"))
                    {
                        String web = "";
                        try {
                            web = inputLine.split(" ")[1];
                            Process p = Runtime.getRuntime().exec("cmd /c start http://www." + web);
                        } catch(ArrayIndexOutOfBoundsException e){
                            JARVIS.setText("You need to enter a website.");
                            System.err.println(e);
                        } catch (IOException e) {
                            JARVIS.setText("website " + web + "is not a valid website");
                            System.err.println(e);
                        }
                    }
                    else if (command.toLowerCase().equals("calc"))
                    {
                        JARVIS.setText(calc.Solve(inputLine.split(" ")[1]));
                    }
                    else if (command.toLowerCase().equals("hide"))
                    {
                        primaryStage.setIconified(true);
                    }
                    else if (command.toLowerCase().equals("quit") || command.toLowerCase().equals("exit"))
                    {
                        JARVIS.setVisible(false);
                        text.setVisible(false);
                        BackgroundImage backgroundImage = new BackgroundImage(
                                new Image(this.getClass().getResource("Goodbye.jpg").toExternalForm()),
                                BackgroundRepeat.REPEAT,
                                BackgroundRepeat.REPEAT,
                                BackgroundPosition.CENTER,
                                BackgroundSize.DEFAULT);
                        Background background = new Background(backgroundImage);
                        border.setBackground(background);
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask()
                        {
                            @Override
                            public void run()
                            {
                                Platform.exit();
                            }
                        }, 2000, 10);
                    }
                    else
                    {
                        Talk talk = new Talk();
                        JARVIS.setText(talk.answer(command.toLowerCase()));
                    }
                }
            }
        });
        VBox vbox = new VBox();
        vbox.getChildren().addAll(JARVIS, tField);
        border.setBottom(vbox);

        //set the background of the border
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(this.getClass().getResource("JARVIS%20rotator.jpg").toExternalForm()),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        border.setBackground(background);


        //set the stage
        primaryStage.setTitle("Just A Rather Very Intelligent System");
        primaryStage.setScene(new Scene( border, 600, 500 ));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}