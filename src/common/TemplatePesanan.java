package common;

import java.sql.Connection;
import model.Barang;
import model.Pesanan;
import panel.EntriBarangPanel;

/**
 *
 * @author a_lpha
 */
public class TemplatePesanan extends javax.swing.JPanel {

    private final Pesanan pesanan;

    private final Connection connection;

    private final EntriBarangPanel context;

    public TemplatePesanan(EntriBarangPanel context, Connection connection, Pesanan pesanan) {
        this.context = context;
        this.connection = connection;
        this.pesanan = pesanan;
        initComponents();

        Barang barang = new Barang().get(connection, pesanan.idBarang);
        tv_namaBarang.setText(barang.namaBarang);
        tv_jumlahBarang.setText("× " + pesanan.jumlahBarang);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        tv_namaBarang = new javax.swing.JLabel();
        tv_jumlahBarang = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(380, 28));

        tv_namaBarang.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_namaBarang.setText("Coca Cola Zero Sugar Vanilla");

        tv_jumlahBarang.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_jumlahBarang.setText("x 4");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_close_grey_20.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_close_20.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tv_namaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addComponent(tv_jumlahBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tv_namaBarang)
                        .addComponent(tv_jumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        context.removeFromCart(this, pesanan);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel tv_jumlahBarang;
    private javax.swing.JLabel tv_namaBarang;
    // End of variables declaration//GEN-END:variables
}
