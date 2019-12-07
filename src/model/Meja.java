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
public class Meja {

    public int id, idTipeMeja, idPenjualan;
    public String nomorMeja, atasNama;
    public int maksOrang, jumlahOrang;
    public String waktuPesanan;

    public static enum GET_TYPE {
        SEMUA, KOSONG, TERISI, DIPESAN
    }

    public Meja() {
    }

    public List<Meja> get(Connection connection, GET_TYPE type) {
        List<Meja> mejas = new ArrayList<>();
        String sql = null;

        switch (type) {
            case SEMUA:
                sql = "SELECT * FROM meja ORDER BY id ASC";
                break;
            case KOSONG:
                sql = "SELECT * FROM meja WHERE atas_nama IS NULL ORDER BY id ASC";
                break;
            case TERISI:
                sql = "SELECT * FROM meja WHERE atas_nama IS NOT NULL ORDER BY id ASC";
                break;
            case DIPESAN:
                sql = "SELECT * FROM meja WHERE waktu_pesanan IS NOT NULL ORDER BY id ASC";
                break;
        }

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Meja meja = new Meja();
                meja.id = resultSet.getInt("id");
                meja.idTipeMeja = resultSet.getInt("id_tipe_meja");
                meja.idPenjualan = resultSet.getInt("id_penjualan");
                meja.nomorMeja = resultSet.getString("nomor_meja");
                meja.atasNama = resultSet.getString("atas_nama");
                meja.maksOrang = resultSet.getInt("maks_orang");
                meja.jumlahOrang = resultSet.getInt("jumlah_orang");
                meja.waktuPesanan = resultSet.getString("waktu_pesanan");

                mejas.add(meja);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return mejas;
    }

    public Meja get(Connection connection, int id) {
        Meja meja = new Meja();
        String sql = "SELECT * FROM meja WHERE id='" + id + "'";

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                meja.id = resultSet.getInt("id");
                meja.idTipeMeja = resultSet.getInt("id_tipe_meja");
                meja.idPenjualan = resultSet.getInt("id_penjualan");
                meja.nomorMeja = resultSet.getString("nomor_meja");
                meja.atasNama = resultSet.getString("atas_nama");
                meja.maksOrang = resultSet.getInt("maks_orang");
                meja.jumlahOrang = resultSet.getInt("jumlah_orang");
                meja.waktuPesanan = resultSet.getString("waktu_pesanan");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return meja;
    }

    public boolean update(Connection c) {
        StringBuilder sql = new StringBuilder("UPDATE meja SET ");
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
