package common;

import java.sql.Connection;
import model.Barang;
import model.DetailPenjualan;

/**
 *
 * @author a_lpha
 */
public class TemplateDetailPenjualan extends javax.swing.JPanel {

    private final int borderRadius = 16;

    public TemplateDetailPenjualan(Connection connection, DetailPenjualan detailPenjualan) {
        initComponents();

        Barang barang = new Barang().get(connection, detailPenjualan.getIdBarang());

        tv_no.setText(String.valueOf(detailPenjualan.getId()));
        tv_namaBarang.setText(barang.getNamaBarang());
        tv_jumlahBarang.setText("× " + detailPenjualan.getJumlahBarang());
        tv_subTotal.setText("Rp. " + a_.convertCurrency(barang.getHarga() * detailPenjualan.getJumlahBarang()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tv_no = new javax.swing.JLabel();
        tv_namaBarang = new javax.swing.JLabel();
        tv_jumlahBarang = new javax.swing.JLabel();
        tv_subTotal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        tv_no.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_no.setText("1");

        tv_namaBarang.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_namaBarang.setText("Coca Cola Zero Sugar Vanilla");

        tv_jumlahBarang.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_jumlahBarang.setText("x2");

        tv_subTotal.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_subTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        tv_subTotal.setText("Rp. 12.000");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(tv_no)
                .addGap(24, 24, 24)
                .addComponent(tv_namaBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                .addComponent(tv_jumlahBarang)
                .addGap(18, 18, 18)
                .addComponent(tv_subTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tv_no)
                    .addComponent(tv_namaBarang)
                    .addComponent(tv_jumlahBarang)
                    .addComponent(tv_subTotal))
                .addGap(8, 8, 8))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel tv_jumlahBarang;
    private javax.swing.JLabel tv_namaBarang;
    private javax.swing.JLabel tv_no;
    private javax.swing.JLabel tv_subTotal;
    // End of variables declaration//GEN-END:variables
}
