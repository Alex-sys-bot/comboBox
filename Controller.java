package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {


    Connection conn;
    public Controller() throws ClassNotFoundException {
        conn = ConnectionUtil.connDB();
    }



    Model model = new Model();

    ////////
    List<String> list = new ArrayList();
    private String tableName;


    @FXML
    private Label statusid;
    @FXML
    private TextField txtPath;
    @FXML
    private ComboBox<String> comboBoxList;

    ObservableList<String> ObservableComboBoxList = FXCollections.observableArrayList();



    @FXML
    void initialize(){
        comboBoxList.setItems(ObservableComboBoxList);
        try {
            DatabaseMetaData tableDBMD = conn.getMetaData();
            ResultSet result = tableDBMD.getTables("courseswithdata", null, null, null);
            while (result.next()){
                ObservableComboBoxList.add(result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void comboBoxAction(ActionEvent event) throws SQLException {
        tableName = ((ComboBox<String>) event.getSource()).getValue();

        ////////
        int countTable =  model.countTableField(tableName, conn);
        ////////
        list = model.createQuery(tableName, conn, countTable);

    }

    @FXML
    void buttonPath(ActionEvent event) throws IOException {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Path");
        File path = chooser.showDialog(new Stage());
        if(!(path == null)){
            txtPath.setText(path.getAbsolutePath());
        }
    }

    @FXML
    void buttonCreateFile(ActionEvent event) throws IOException {
        model.writeFile(txtPath.getText(), tableName, list);
    }
}
