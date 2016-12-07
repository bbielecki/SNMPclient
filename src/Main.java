
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class Main extends Application {

    public static final void main(String args[]) throws IOException {

        //SimpleSnmpClient client = new SimpleSnmpClient("udp:127.0.0.1/162");
        /// String sysDescr = client.getAsString(new OID("1.3.6.1.2.1.1.1.0"));
        ///  String sysDescr2 = client.getNextAsString(new OID("1.3.6.1.2.1.1.1"));
        ///  System.out.println(sysDescr);
        ///  System.out.println(sysDescr2);

        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Viewer.fxml"));
        primaryStage.setTitle("SNMP Client");
        primaryStage.setScene(new Scene(root, 715, 509));
        primaryStage.show();
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(640);
        String musicFile = "moonlightsonata.mp3";     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();


    }
}