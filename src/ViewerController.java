import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.snmp4j.smi.OID;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Bartłomiej on 03.12.2016.
 */
public class ViewerController implements Initializable{

    @FXML private TextField oidText;

    @FXML private ComboBox commands;

    @FXML private TableView resultTable;


    private String OIDstring;
    private SimpleSnmpClient client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        client = new SimpleSnmpClient("udp:127.0.0.1/161");

        OIDstring = new String("");
        ObservableList<String> com = FXCollections.observableArrayList("Get", "Get Next", "Get Table");

        commands.setItems(com);
    }


    public void readOID(ActionEvent action) throws Exception{

        OIDstring = oidText.getText();

        System.out.println(OIDstring);
    }

    public void selectCommandType(ActionEvent event) throws IOException {

        switch (commands.getSelectionModel().getSelectedItem().toString()) {
            case "Get": {
                OIDstring = oidText.getText();
                String sysDescr = client.getAsString(new OID(OIDstring));
                System.out.println(sysDescr);

                break;
            }
            case "Get Next":{
                OIDstring = oidText.getText();
                String sysDescr = client.getNextAsString(new OID(OIDstring));
                System.out.println(sysDescr);

                break;
            }
            case "Get Table":{
                OIDstring = oidText.getText();
                String sysDescr = client.getAsString(new OID(OIDstring));
                System.out.println(sysDescr);

                createTable();
                resultTable.getColumns().addAll();

                break;
            }
            default: {
                break;
            }

        }
    }



    private void createTable() throws IOException {
        String tableOID = OIDstring;
        String nextItemOID = OIDstring;
        String columnOID = tableOID + ".1.1";
        ArrayList<ArrayList<String>> table = new ArrayList<>();
        ArrayList<TableColumn> printedColumns = new ArrayList<>();
        int sufix = 1;

        nextItemOID = client.getNextOIDAsString(new OID(OIDstring));
        while (nextItemOID.contains(tableOID)){
            TableColumn tb = new TableColumn();
            tb.setText(columnOID);
            printedColumns.add(tb);
            //table.add(new ArrayList<>());
            ArrayList<String> col = new ArrayList<>();
            while (nextItemOID.contains(columnOID)){
                String item = client.getNextAsString(new OID(nextItemOID));
                col.add(item);
                nextItemOID = client.getNextOIDAsString(new OID(nextItemOID));
            }
            table.add(col);
            sufix++;

            columnOID = columnOID.substring(0,columnOID.length()-1);
            columnOID = "" + sufix;

        }

        resultTable.getColumns().addAll(printedColumns);
        for(ArrayList<String> array : table){


        }

    }

}
