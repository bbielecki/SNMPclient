import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.snmp4j.smi.OID;

import java.io.IOException;

/**
 * Created by Bartłomiej on 03.12.2016.
 */
public class Main extends Application{

    public static final void main(String args[]) throws IOException {

        SimpleSnmpClient client = new SimpleSnmpClient("udp:127.0.0.1/161");
        String sysDescr = client.getAsString(new OID("1.3.6.1.2.1.1.1.0"));
        String sysDescr2 = client.getNextAsString(new OID("1.3.6.1.2.1.1.1"));
        System.out.println(sysDescr);
        System.out.println(sysDescr2);

        launch(args);



    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Viewer.fxml"));
        primaryStage.setTitle("SNMP Client");
        primaryStage.setScene(new Scene(root, 550, 300));
        primaryStage.show();
    }
}
