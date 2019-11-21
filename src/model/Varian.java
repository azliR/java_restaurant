package model;

import com.mysql.jdbc.Blob;
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
public class Varian {

    int id, idBarang;
    String namaVarian;
    byte[] gambar;

    public Varian() {
    }

    public Varian(int id, int idBarang, String namaVarian, byte[] gambar) {
        this.id = id;
        this.idBarang = idBarang;
        this.namaVarian = namaVarian;
        this.gambar = gambar;
    }

    public List<Varian> getByIdBarang(Connection connection, int id) {
        List<Varian> varians = new ArrayList<>();
        String sql = "SELECT * FROM varian_barang WHERE id_barang='" + id + "'";

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Varian varian = new Varian();
                Blob gambarBlob = (Blob) resultSet.getBlob("gambar");

                byte gambarByte[] = null;
                if (gambarBlob != null) {
                    gambarByte = gambarBlob.getBytes(1, (int) gambarBlob.length());
                }

                varian.setId(resultSet.getInt("id"));
                varian.setIdBarang(resultSet.getInt("id_barang"));
                varian.setNamaVarian(resultSet.getString("nama_varian"));
                varian.setGambar(gambarByte);

                varians.add(varian);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return varians;
    }

    public Varian get(Connection connection, int id) {
        String sql = "SELECT * FROM varian_barang WHERE id='" + id + "'";

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            Varian varian = new Varian();
            while (resultSet.next()) {
                Blob gambarBlob = (Blob) resultSet.getBlob("gambar");
                byte gambarByte[] = gambarBlob.getBytes(1, (int) gambarBlob.length());

                varian.setId(resultSet.getInt("id"));
                varian.setIdBarang(resultSet.getInt("id_barang"));
                varian.setNamaVarian(resultSet.getString("nama_varian"));
                varian.setGambar(gambarByte);
            }
            resultSet.close();
            statement.close();

            return varian;
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaVarian() {
        return namaVarian;
    }

    public void setNamaVarian(String namaVarian) {
        this.namaVarian = namaVarian;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
    }
}
