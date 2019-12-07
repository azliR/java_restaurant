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

    public int id, idJenis, harga;
    public boolean stokBarang;
    public String namaBarang, deskripsi;
    public byte[] gambar;

    public Barang() {
    }

    public Barang get(Connection connection, int id) {
        Barang barang = new Barang();
        String sql = "SELECT * FROM barang WHERE id='" + id + "'";

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Blob gambarBlob = (Blob) resultSet.getBlob("gambar");
                byte gambarByte[] = gambarBlob.getBytes(1, (int) gambarBlob.length());

                barang.id = resultSet.getInt("id");
                barang.idJenis = resultSet.getInt("id_jenis");
                barang.harga = resultSet.getInt("harga");
                barang.stokBarang = (resultSet.getInt("stok_barang") == 1);
                barang.namaBarang = resultSet.getString("nama_barang");
                barang.deskripsi = resultSet.getString("deskripsi");
                barang.gambar = gambarByte;
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

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Blob gambarBlob = (Blob) resultSet.getBlob(3);
                byte gambarByte[] = gambarBlob.getBytes(1, (int) gambarBlob.length());

                Barang barang = new Barang();
                barang.id = resultSet.getInt("id");
                barang.idJenis = resultSet.getInt("id_jenis");
                barang.harga = resultSet.getInt("harga");
                barang.stokBarang = (resultSet.getInt("stok_barang") == 1);
                barang.namaBarang = resultSet.getString("nama_barang");
                barang.deskripsi = resultSet.getString("deskripsi");
                barang.gambar = gambarByte;

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

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Blob gambarBlob = (Blob) resultSet.getBlob(3);
                byte gambarByte[] = gambarBlob.getBytes(1, (int) gambarBlob.length());

                Barang barang = new Barang();
                barang.id = resultSet.getInt("id");
                barang.idJenis = resultSet.getInt("id_jenis");
                barang.harga = resultSet.getInt("harga");
                barang.stokBarang = (resultSet.getInt("stok_barang") == 1);
                barang.namaBarang = resultSet.getString("nama_barang");
                barang.deskripsi = resultSet.getString("deskripsi");
                barang.gambar = gambarByte;

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
        String sql = "INSERT INTO barang VALUES (?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement p = (PreparedStatement) c.prepareStatement(sql)) {
            FileInputStream fileInputStream = new FileInputStream(gambarFile);

            p.setInt(1, id);
            p.setInt(2, idJenis);
            p.setBinaryStream(3, fileInputStream, gambarFile.length());
            p.setString(4, namaBarang);
            p.setInt(5, harga);
            p.setInt(6, stokBarang ? 1 : 0);
            p.setString(7, deskripsi);

            p.executeUpdate();
            p.close();

            return true;
        } catch (SQLException | FileNotFoundException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
}
