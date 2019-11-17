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

    int id = -1, idTipeMeja = -1, nomorMeja = -1;
    String atasNama, maksOrang, waktuPesanan;

    public static enum GET_TYPE {
        SEMUA, KOSONG, TERISI, DIPESAN
    }

    public EntriMeja() {
    }

    public EntriMeja(int id, int idTipeMeja, int nomorMeja, String atasNama, String maksOrang, String waktuPesanan) {
        this.id = id;
        this.idTipeMeja = idTipeMeja;
        this.nomorMeja = nomorMeja;
        this.atasNama = atasNama;
        this.maksOrang = maksOrang;
        this.waktuPesanan = waktuPesanan;
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

    public boolean update(Connection c) {
        StringBuilder sql = new StringBuilder("UPDATE entri_meja SET ");
        if (idTipeMeja != -1) {
            sql.append("id_tipe_meja = '").append(idTipeMeja).append("', ");
        }
        if (nomorMeja != -1) {
            sql.append("nomor_meja = '").append(nomorMeja).append("', ");
        }
        if (atasNama != null) {
            sql.append("atas_nama = '").append(atasNama).append("', ");
        }
        if (maksOrang != null) {
            sql.append("maks_orang = '").append(maksOrang).append("', ");
        }
        if (waktuPesanan != null) {
            sql.append("waktu_pesanan = '").append(waktuPesanan).append("', ");
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append("WHERE id = '").append(id).append("'");
        System.out.println(sql);
        try (Statement s = c.createStatement()) {
            s.executeUpdate(sql.toString());

            return true;
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
        return false;
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

    public String getWaktuPesanan() {
        return waktuPesanan;
    }

    public void setWaktuPesanan(String waktuPesanan) {
        this.waktuPesanan = waktuPesanan;
    }
}
