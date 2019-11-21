package model;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class Barang {

    int id, idJenis, harga;
    boolean stokBarang;
    String namaBarang;
    byte[] gambar;

    public Barang() {
    }

    public Barang(int id, int idJenis, int harga, boolean stokBarang, String namaBarang, byte[] gambar) {
        this.id = id;
        this.idJenis = idJenis;
        this.harga = harga;
        this.stokBarang = stokBarang;
        this.namaBarang = namaBarang;
        this.gambar = gambar;
    }

    public Barang get(Connection connection, int id) {
        Barang barang = new Barang();
        String sql = "SELECT * FROM barang WHERE id='" + id + "'";

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Blob gambarBlob = (Blob) resultSet.getBlob("gambar");
                byte gambarByte[] = gambarBlob.getBytes(1, (int) gambarBlob.length());

                barang.setId(resultSet.getInt("id"));
                barang.setIdJenis(resultSet.getInt("id_jenis"));
                barang.setHarga(resultSet.getInt("harga"));
                barang.setStokBarang((resultSet.getInt("stok_barang") == 1));
                barang.setNamaBarang(resultSet.getString("nama_barang"));
                barang.setGambar(gambarByte);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return barang;
    }

    public List<Barang> getByJenis(Connection connection, int idJenis) {
        List<Barang> barangs = new ArrayList<>();
        String sql = "SELECT * FROM barang WHERE id_jenis='" + idJenis + "'";

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Blob gambarBlob = (Blob) resultSet.getBlob(3);
                byte gambarByte[] = gambarBlob.getBytes(1, (int) gambarBlob.length());

                Barang barang = new Barang();
                barang.setId(resultSet.getInt("id"));
                barang.setIdJenis(resultSet.getInt("id_jenis"));
                barang.setHarga(resultSet.getInt("harga"));
                barang.setStokBarang((resultSet.getInt("stok_barang") == 1));
                barang.setNamaBarang(resultSet.getString("nama_barang"));
                barang.setGambar(gambarByte);

                barangs.add(barang);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return barangs;
    }

    public List<Barang> get(Connection connection) {
        List<Barang> barangs = new ArrayList<>();
        String sql = "SELECT * FROM barang";

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Blob gambarBlob = (Blob) resultSet.getBlob(3);
                byte gambarByte[] = gambarBlob.getBytes(1, (int) gambarBlob.length());

                Barang barang = new Barang();
                barang.setId(resultSet.getInt("id"));
                barang.setIdJenis(resultSet.getInt("id_jenis"));
                barang.setHarga(resultSet.getInt("harga"));
                barang.setStokBarang((resultSet.getInt("stok_barang") == 1));
                barang.setNamaBarang(resultSet.getString("nama_barang"));
                barang.setGambar(gambarByte);

                barangs.add(barang);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return barangs;
    }

    public boolean insert(Connection c, File gambarFile) {
        String sql = "INSERT INTO barang VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement p = (PreparedStatement) c.prepareStatement(sql)) {
            FileInputStream fileInputStream = new FileInputStream(gambarFile);

            p.setInt(1, id);
            p.setInt(2, idJenis);
            p.setBinaryStream(3, fileInputStream, gambarFile.length());
            p.setString(4, namaBarang);
            p.setInt(5, harga);
            p.setInt(6, stokBarang ? 1 : 0);

            p.executeUpdate();
            p.close();

            return true;
        } catch (SQLException | FileNotFoundException e) {
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

    public int getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(int idJenis) {
        this.idJenis = idJenis;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public boolean isStokBarang() {
        return stokBarang;
    }

    public void setStokBarang(boolean stokBarang) {
        this.stokBarang = stokBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
    }
}
