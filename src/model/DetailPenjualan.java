package model;

import com.mysql.jdbc.PreparedStatement;
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
public class DetailPenjualan {

    public int id, idPenjualan, idBarang, jumlahBarang;

    public DetailPenjualan() {
    }

    public List<DetailPenjualan> get(Connection connection) {
        List<DetailPenjualan> detailPenjualans = new ArrayList<>();
        String sql = "SELECT * FROM detail_penjualan";

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                DetailPenjualan detailPenjualan = new DetailPenjualan();
                detailPenjualan.id = resultSet.getInt("id");
                detailPenjualan.idPenjualan = resultSet.getInt("id_penjualan");
                detailPenjualan.idBarang = resultSet.getInt("id_barang");
                detailPenjualan.jumlahBarang = resultSet.getInt("jumlah_barang");

                detailPenjualans.add(detailPenjualan);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return detailPenjualans;
    }

    public List<DetailPenjualan> get(Connection connection, int id) {
        List<DetailPenjualan> detailPenjualans = new ArrayList<>();
        String sql = "SELECT * FROM detail_penjualan WHERE id_penjualan='" + id + "'";

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                DetailPenjualan detailPenjualan = new DetailPenjualan();
                detailPenjualan.id = resultSet.getInt("id");
                detailPenjualan.idPenjualan = resultSet.getInt("id_penjualan");
                detailPenjualan.idBarang = resultSet.getInt("id_barang");
                detailPenjualan.jumlahBarang = resultSet.getInt("jumlah_barang");

                detailPenjualans.add(detailPenjualan);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return detailPenjualans;
    }

    public boolean insert(Connection c) {
        String sql = "INSERT INTO detail_penjualan VALUES (?, ?, ?, ?)";
        try ( PreparedStatement p = (PreparedStatement) c.prepareStatement(sql)) {
            p.setInt(1, id);
            p.setInt(2, idPenjualan);
            p.setInt(3, idBarang);
            p.setInt(4, jumlahBarang);

            p.executeUpdate();
            p.close();

            return true;
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
}
