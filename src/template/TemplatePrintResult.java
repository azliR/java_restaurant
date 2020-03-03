package template;

import common.a_;
import java.sql.Connection;
import model.Barang;
import model.DetailPenjualan;

/**
 *
 * @author rizal
 */
public class TemplatePrintResult extends javax.swing.JPanel {

    final DetailPenjualan detailPenjualan;

    public TemplatePrintResult(Connection connection, DetailPenjualan detailPenjualan) {
        this.detailPenjualan = detailPenjualan;
        initComponents();

        final var harga = new Barang().get(connection, detailPenjualan.idBarang).harga;

        tv_qty.setText(String.valueOf(detailPenjualan.jumlahBarang));
        tv_namaBarang.setText(detailPenjualan.namaBarang);
        tv_harga.setText(a_.convertCurrency(harga));
        tv_subtotal.setText(a_.convertCurrency(harga * detailPenjualan.jumlahBarang));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tv_qty = new javax.swing.JLabel();
        tv_namaBarang = new javax.swing.JLabel();
        tv_harga = new javax.swing.JLabel();
        tv_subtotal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        tv_qty.setFont(new java.awt.Font("Fira Code", 0, 10)); // NOI18N
        tv_qty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tv_qty.setText("99");

        tv_namaBarang.setFont(new java.awt.Font("Fira Code", 0, 10)); // NOI18N
        tv_namaBarang.setText("Jus Buah Naga Vanilla");

        tv_harga.setFont(new java.awt.Font("Fira Code", 0, 10)); // NOI18N
        tv_harga.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tv_harga.setText("1.000.000");

        tv_subtotal.setFont(new java.awt.Font("Fira Code", 0, 10)); // NOI18N
        tv_subtotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        tv_subtotal.setText("5000");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(tv_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(tv_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(tv_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(tv_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tv_qty)
                    .addComponent(tv_namaBarang)
                    .addComponent(tv_harga)
                    .addComponent(tv_subtotal))
                .addGap(2, 2, 2))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel tv_harga;
    private javax.swing.JLabel tv_namaBarang;
    private javax.swing.JLabel tv_qty;
    private javax.swing.JLabel tv_subtotal;
    // End of variables declaration//GEN-END:variables
}
