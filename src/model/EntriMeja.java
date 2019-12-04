package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a_lpha
 */
public class EntriMeja {

    public int id, idTipeMeja, idPenjualan;
    public String nomorMeja, atasNama;
    public int maksOrang, jumlahOrang;
    public String waktuPesanan;

    public static enum GET_TYPE {
        SEMUA, KOSONG, TERISI, DIPESAN
    }

    public EntriMeja() {
    }

    public List<EntriMeja> get(Connection connection, GET_TYPE type) {
        List<EntriMeja> entriMejas = new ArrayList<>();
        String sql = null;

        switch (type) {
            case SEMUA:
                sql = "SELECT * FROM entri_meja ORDER BY id ASC";
                break;
            case KOSONG:
                sql = "SELECT * FROM entri_meja WHERE atas_nama IS NULL ORDER BY id ASC";
                break;
            case TERISI:
                sql = "SELECT * FROM entri_meja WHERE atas_nama IS NOT NULL ORDER BY id ASC";
                break;
            case DIPESAN:
                sql = "SELECT * FROM entri_meja WHERE waktu_pesanan IS NOT NULL ORDER BY id ASC";
                break;
        }

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                EntriMeja entriMeja = new EntriMeja();
                entriMeja.id = resultSet.getInt("id");
                entriMeja.idTipeMeja = resultSet.getInt("id_tipe_meja");
                entriMeja.idPenjualan = resultSet.getInt("id_penjualan");
                entriMeja.nomorMeja = resultSet.getString("nomor_meja");
                entriMeja.atasNama = resultSet.getString("atas_nama");
                entriMeja.maksOrang = resultSet.getInt("maks_orang");
                entriMeja.jumlahOrang = resultSet.getInt("jumlah_orang");
                entriMeja.waktuPesanan = resultSet.getString("waktu_pesanan");

                entriMejas.add(entriMeja);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return entriMejas;
    }

    public EntriMeja get(Connection connection, int id) {
        EntriMeja entriMeja = new EntriMeja();
        String sql = "SELECT * FROM entri_meja WHERE id='" + id + "'";

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                entriMeja.id = resultSet.getInt("id");
                entriMeja.idTipeMeja = resultSet.getInt("id_tipe_meja");
                entriMeja.idPenjualan = resultSet.getInt("id_penjualan");
                entriMeja.nomorMeja = resultSet.getString("nomor_meja");
                entriMeja.atasNama = resultSet.getString("atas_nama");
                entriMeja.maksOrang = resultSet.getInt("maks_orang");
                entriMeja.jumlahOrang = resultSet.getInt("jumlah_orang");
                entriMeja.waktuPesanan = resultSet.getString("waktu_pesanan");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return entriMeja;
    }

    public boolean update(Connection c) {
        StringBuilder sql = new StringBuilder("UPDATE entri_meja SET ");
        if (id == 0) {
            return false;
        }
        if (idTipeMeja != 0) {
            sql.append("id_tipe_meja = '").append(idTipeMeja).append("', ");
        }
        if (idPenjualan != 0) {
            sql.append("id_penjualan = '").append(idPenjualan).append("', ");
        }
        if (nomorMeja != null) {
            sql.append("nomor_meja = '").append(nomorMeja).append("', ");
        }
        if (atasNama != null) {
            sql.append("atas_nama = '").append(atasNama).append("', ");
        }
        if (maksOrang != 0) {
            sql.append("maks_orang = '").append(maksOrang).append("', ");
        }
        if (jumlahOrang != 0) {
            sql.append("jumlah_orang = '").append(jumlahOrang).append("', ");
        }
        if (waktuPesanan != null) {
            sql.append("waktu_pesanan = '").append(waktuPesanan).append("', ");
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append("WHERE id = '").append(id).append("'");

        try ( Statement s = c.createStatement()) {
            s.executeUpdate(sql.toString());

            return true;
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
}
