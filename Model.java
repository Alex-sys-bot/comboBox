package sample;

import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    public int countTableField(String tableName, Connection conn) throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        ResultSetMetaData rsmd = result.getMetaData();
        return rsmd.getColumnCount();
    }

    public void writeFile(String pathFile, String nameFile, List<String> list) throws IOException {
        File file = new File(pathFile + "/" + nameFile + ".txt");
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write(list.toString());
        fw.close();
    }
    ////////
    public List<String> createQuery(String tableName, Connection conn, int countColumn){
        List<String> list = new ArrayList<>();

        String sql = "SELECT * FROM " + tableName;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                for (int i = 1; i <= countColumn; i++) {
                    list.add(resultSet.getString(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
