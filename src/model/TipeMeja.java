package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a_lpha
 */
public class TipeMeja {

    int id;
    String namaTipe;

    public TipeMeja() {
    }

    public TipeMeja(int id, String namaTipe) {
        this.id = id;
        this.namaTipe = namaTipe;
    }

    public TipeMeja get(Connection connection, int id) {
        String sql = "SELECT * FROM tipe_meja WHERE id='" + id + "'";

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            TipeMeja entriMeja = new TipeMeja();
            while (resultSet.next()) {
                entriMeja.setId(resultSet.getInt("id"));
                entriMeja.setNamaTipe(resultSet.getString("nama_tipe"));
            }
            resultSet.close();
            statement.close();

            return entriMeja;
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaTipe() {
        return namaTipe;
    }

    public void setNamaTipe(String namaTipe) {
        this.namaTipe = namaTipe;
    }
}
