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

    int id, idHakAkses;
    String namaPengguna, namaLengkap, kataSandi, alamat, noTelepon;

    public Pengguna() {
    }

    public Pengguna(int id, int idHakAkses, String namaPengguna, String namaLengkap, String kataSandi, String alamat, String noTelepon) {
        this.id = id;
        this.idHakAkses = idHakAkses;
        this.namaPengguna = namaPengguna;
        this.namaLengkap = namaLengkap;
        this.kataSandi = kataSandi;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
    }

    public Pengguna get(Connection connection, int id) {
        Pengguna pengguna = new Pengguna();
        String sql = "SELECT * FROM pengguna WHERE id='" + id + "'";

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                pengguna.setId(resultSet.getInt("id"));
                pengguna.setNamaLengkap(resultSet.getString("nama_lengkap"));
                pengguna.setNamaPengguna(resultSet.getString("nama_pengguna"));
                pengguna.setKataSandi(resultSet.getString("kata_sandi"));
                pengguna.setAlamat(resultSet.getString("alamat"));
                pengguna.setNoTelepon(resultSet.getString("no_telepon"));
                pengguna.setIdHakAkses(resultSet.getInt("id_hak_akses"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return pengguna;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHakAkses() {
        return idHakAkses;
    }

    public void setIdHakAkses(int idHakAkses) {
        this.idHakAkses = idHakAkses;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getKataSandi() {
        return kataSandi;
    }

    public void setKataSandi(String kataSandi) {
        this.kataSandi = kataSandi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

}
