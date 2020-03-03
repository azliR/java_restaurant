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
public class StatusPesanan {

    public int id;
    public String namaStatus;

    public StatusPesanan() {
    }

    public StatusPesanan get(Connection connection, int id) {
        StatusPesanan statusPesanan = new StatusPesanan();
        String sql = "SELECT * FROM status_pesanan WHERE id='" + id + "'";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                statusPesanan.id = resultSet.getInt("id");
                statusPesanan.namaStatus = resultSet.getString("nama_status");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return statusPesanan;
    }
}
