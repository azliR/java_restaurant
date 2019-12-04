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

    public int id, idBarang;
    public String namaVarian;
    public byte[] gambar;

    public Varian() {
    }

    public List<Varian> getByIdBarang(Connection connection, int id) {
        List<Varian> varians = new ArrayList<>();
        String sql = "SELECT * FROM varian_barang WHERE id_barang='" + id + "'";

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Varian varian = new Varian();
                Blob gambarBlob = (Blob) resultSet.getBlob("gambar");

                byte gambarByte[] = null;
                if (gambarBlob != null) {
                    gambarByte = gambarBlob.getBytes(1, (int) gambarBlob.length());
                }

                varian.id = resultSet.getInt("id");
                varian.idBarang = resultSet.getInt("id_barang");
                varian.namaVarian = resultSet.getString("nama_varian");
                varian.gambar = gambarByte;

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

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            Varian varian = new Varian();
            while (resultSet.next()) {
                Blob gambarBlob = (Blob) resultSet.getBlob("gambar");
                byte gambarByte[] = gambarBlob.getBytes(1, (int) gambarBlob.length());

                varian.id = resultSet.getInt("id");
                varian.idBarang = resultSet.getInt("id_barang");
                varian.namaVarian = resultSet.getString("nama_varian");
                varian.gambar = gambarByte;
            }
            resultSet.close();
            statement.close();

            return varian;
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
