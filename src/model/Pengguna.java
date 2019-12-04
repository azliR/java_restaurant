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
public class Pengguna {

    public int id, idHakAkses;
    public String namaPengguna, namaLengkap, kataSandi, alamat, noTelepon;

    public Pengguna() {
    }

    public Pengguna get(Connection connection, int id) {
        Pengguna pengguna = new Pengguna();
        String sql = "SELECT * FROM pengguna WHERE id='" + id + "'";

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                pengguna.id = resultSet.getInt("id");
                pengguna.namaLengkap = resultSet.getString("nama_lengkap");
                pengguna.namaPengguna = resultSet.getString("nama_pengguna");
                pengguna.kataSandi = resultSet.getString("kata_sandi");
                pengguna.alamat = resultSet.getString("alamat");
                pengguna.noTelepon = resultSet.getString("no_telepon");
                pengguna.idHakAkses = resultSet.getInt("id_hak_akses");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return pengguna;
    }
}
