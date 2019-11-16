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
public class HakAkses {

    int id;
    String namaAkses;

    public HakAkses() {
    }

    public HakAkses(int id, String hakAkses) {
        this.id = id;
        this.namaAkses = hakAkses;
    }

    public HakAkses get(Connection connection, int id) {
        HakAkses hakAkses = new HakAkses();
        String sql = "SELECT * FROM hak_akses WHERE id='" + id + "'";

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                hakAkses.setId(resultSet.getInt("id"));
                hakAkses.setNamaAkses(resultSet.getString("nama_akses"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return hakAkses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaAkses() {
        return namaAkses;
    }

    public void setNamaAkses(String namaAkses) {
        this.namaAkses = namaAkses;
    }

}
