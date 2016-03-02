package GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This GUI is used to add an application to the list of
 * applications J.A.R.V.I.S. is able to run
 * Created by Michael T on 2/12/2016.
 */
public class addAppGUI extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane border = new BorderPane();

        //set left of border
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
        border.setCenter(vbox);

        //set bottom
        Button ok = new Button();
        ok.setText("Ok");
        ok.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                exe.addApp(tFieldname.getText(), tFieldlocation.getText());
                Platform.exit();
            }
        });


        //set the stage
        primaryStage.setScene(new Scene( border, 600, 500 ));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
