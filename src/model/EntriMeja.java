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

    int id, idTipeMeja, nomorMeja;
    String atasNama, maksOrang;

    public static enum GET_TYPE {
        SEMUA, KOSONG, TERISI, DIPESAN
    }

    public EntriMeja() {
    }

    public EntriMeja(int id, int idTipeMeja, int nomorMeja, String atasNama, String jumlahOrang) {
        this.id = id;
        this.idTipeMeja = idTipeMeja;
        this.nomorMeja = nomorMeja;
        this.atasNama = atasNama;
        this.maksOrang = jumlahOrang;
    }

    public List<EntriMeja> get(Connection connection, GET_TYPE type) {
        List<EntriMeja> entriMejas = new ArrayList<>();
        String sql = null;

        switch (type) {
            case SEMUA:
                sql = "SELECT * FROM entri_meja ORDER BY nomor_meja ASC";
                break;
            case KOSONG:
                sql = "SELECT * FROM entri_meja WHERE atas_nama IS NULL ORDER BY nomor_meja ASC";
                break;
            case TERISI:
                sql = "SELECT * FROM entri_meja WHERE atas_nama IS NOT NULL ORDER BY nomor_meja ASC";
                break;
            case DIPESAN:
                sql = "SELECT * FROM entri_meja WHERE waktu_pesanan IS NOT NULL ORDER BY nomor_meja ASC";
                break;
        }

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                EntriMeja entriMeja = new EntriMeja();
                entriMeja.setId(resultSet.getInt("id"));
                entriMeja.setIdTipeMeja(resultSet.getInt("id_tipe_meja"));
                entriMeja.setNomorMeja(resultSet.getInt("nomor_meja"));
                entriMeja.setAtasNama(resultSet.getString("atas_nama"));
                entriMeja.setMaksOrang(resultSet.getString("maks_orang"));

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

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                entriMeja.setId(resultSet.getInt("id"));
                entriMeja.setIdTipeMeja(resultSet.getInt("id_tipe_meja"));
                entriMeja.setNomorMeja(resultSet.getInt("nomor_meja"));
                entriMeja.setAtasNama(resultSet.getString("atas_nama"));
                entriMeja.setMaksOrang(resultSet.getString("maks_orang"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return entriMeja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTipeMeja() {
        return idTipeMeja;
    }

    public void setIdTipeMeja(int idTipeMeja) {
        this.idTipeMeja = idTipeMeja;
    }

    public int getNomorMeja() {
        return nomorMeja;
    }

    public void setNomorMeja(int nomorMeja) {
        this.nomorMeja = nomorMeja;
    }

    public String getAtasNama() {
        return atasNama;
    }

    public void setAtasNama(String atasNama) {
        this.atasNama = atasNama;
    }

    public String getMaksOrang() {
        return maksOrang;
    }

    public void setMaksOrang(String maksOrang) {
        this.maksOrang = maksOrang;
    }
}
