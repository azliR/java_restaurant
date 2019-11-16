package common;

import java.awt.Component;
import java.sql.Connection;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.Barang;
import model.Pesanan;

/**
 *
 * @author a_lpha
 */
public class TemplateListPesanan extends javax.swing.JPanel implements ListCellRenderer<Pesanan> {

    private final Connection connection;

    public TemplateListPesanan(Connection connection) {
        this.connection = connection;
        initComponents();
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Pesanan> jlist, Pesanan pesanan, int i, boolean bln, boolean bln1) {
        Barang barang = new Barang().get(connection, pesanan.getIdBarang());
        tv_namaBarang.setText(barang.getNamaBarang());
        tv_jumlahBarang.setText("× " + pesanan.getJumlahBarang());

        return this;
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
                .addGap(8, 8, 8)
                .addComponent(tv_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 170, Short.MAX_VALUE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(tv_jumlahBarang)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tv_namaBarang)
                    .addComponent(tv_jumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel tv_jumlahBarang;
    private javax.swing.JLabel tv_namaBarang;
    // End of variables declaration//GEN-END:variables
}
