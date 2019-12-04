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
public class Penjualan {

    public int id, idPengguna, idMeja, idStatus, total;
    public String atasNama, tanggalPenjualan, keterangan;

    public Penjualan() {
    }

    public List<Penjualan> get(Connection connection) {
        List<Penjualan> penjualans = new ArrayList<>();
        String sql = "SELECT * FROM penjualan ORDER BY tanggal_penjualan DESC";

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Penjualan penjualan = new Penjualan();
                penjualan.id = resultSet.getInt("id");
                penjualan.idPengguna = resultSet.getInt("id_pengguna");
                penjualan.idMeja = resultSet.getInt("id_meja");
                penjualan.idStatus = resultSet.getInt("id_status");
                penjualan.total = resultSet.getInt("total");
                penjualan.atasNama = resultSet.getString("atas_nama");
                penjualan.tanggalPenjualan = resultSet.getString("tanggal_penjualan");
                penjualan.keterangan = resultSet.getString("keterangan");

                penjualans.add(penjualan);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return penjualans;
    }

    public List<Penjualan> getByIdMeja(Connection connection, int idMeja) {
        List<Penjualan> penjualans = new ArrayList<>();
        String sql = "SELECT * FROM penjualan ORDER BY tanggal_penjualan DESC WHERE id_meja='" + idMeja + "'";

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Penjualan penjualan = new Penjualan();
                penjualan.id = resultSet.getInt("id");
                penjualan.idPengguna = resultSet.getInt("id_pengguna");
                penjualan.idMeja = resultSet.getInt("id_meja");
                penjualan.idStatus = resultSet.getInt("id_status");
                penjualan.total = resultSet.getInt("total");
                penjualan.atasNama = resultSet.getString("atas_nama");
                penjualan.tanggalPenjualan = resultSet.getString("tanggal_penjualan");
                penjualan.keterangan = resultSet.getString("keterangan");

                penjualans.add(penjualan);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return penjualans;
    }

    public Penjualan get(Connection connection, int id) {
        Penjualan penjualan = new Penjualan();
        String sql = "SELECT * FROM penjualan WHERE id='" + id + "'";

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                penjualan.id = resultSet.getInt("id");
                penjualan.idPengguna = resultSet.getInt("id_pengguna");
                penjualan.idMeja = resultSet.getInt("id_meja");
                penjualan.idStatus = resultSet.getInt("id_status");
                penjualan.total = resultSet.getInt("total");
                penjualan.atasNama = resultSet.getString("atas_nama");
                penjualan.tanggalPenjualan = resultSet.getString("tanggal_penjualan");
                penjualan.keterangan = resultSet.getString("keterangan");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return penjualan;
    }

    public int insert(Connection c) {
        int generatedId = -1;
        String sql = "INSERT INTO penjualan VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement p = (PreparedStatement) c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            p.setInt(1, id);
            p.setInt(2, idPengguna);
            p.setInt(3, idMeja);
            p.setInt(4, idStatus);
            p.setString(5, atasNama);
            p.setString(6, tanggalPenjualan);
            p.setInt(7, total);
            p.setString(8, keterangan);

            p.executeUpdate();

            try ( ResultSet resultSet = p.getGeneratedKeys()) {
                if (resultSet.next()) {
                    generatedId = resultSet.getInt(1);
                }
                resultSet.close();
            }
            p.close();
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
        return generatedId;
    }

    public boolean update(Connection c) {
        StringBuilder sql = new StringBuilder("UPDATE entri_meja SET ");
        if (id == 0) {
            return false;
        }
        if (idPengguna != 0) {
            sql.append("id_pengguna = '").append(idPengguna).append("', ");
        }
        if (idMeja != 0) {
            sql.append("id_meja = '").append(atasNama).append("', ");
        }
        if (idStatus != 0) {
            sql.append("id_status = '").append(idStatus).append("', ");
        }
        if (atasNama != null) {
            sql.append("atas_nama = '").append(atasNama).append("', ");
        }
        if (tanggalPenjualan != null) {
            sql.append("tanggal_penjualan = '").append(tanggalPenjualan).append("', ");
        }
        if (total != 0) {
            sql.append("total = '").append(total).append("', ");
        }
        if (keterangan != null) {
            sql.append("keterangan = '").append(keterangan).append("', ");
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
