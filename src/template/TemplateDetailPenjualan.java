package template;

import common.RoundedBorder;
import common.a_;
import java.awt.Image;
import java.sql.Connection;
import javax.swing.ImageIcon;
import model.Barang;
import model.DetailPenjualan;
import styles.Fonts;

/**
 *
 * @author a_lpha
 */
public class TemplateDetailPenjualan extends javax.swing.JPanel {

    private final int borderRadius = 16;

    public TemplateDetailPenjualan(Connection connection, DetailPenjualan detailPenjualan, int index) {
        initComponents();
        Barang barang = new Barang().get(connection, detailPenjualan.idBarang);

        final int maxWidth = 64;
        final int maxHeight = 64;

        Image image = new ImageIcon(barang.gambar).getImage();
        Image scaledImage = a_.scaleImage(image, maxWidth, maxHeight);
        Image croppedImage = a_.cropImage(
                a_.toBufferedImage(new ImageIcon(scaledImage).getImage()),
                maxWidth,
                maxHeight);
        Image roundedImage = a_.convertRoundedImage(croppedImage, borderRadius);

        iv_gambar.setIcon(new ImageIcon(roundedImage));
        tv_harga.setText("Rp. " + a_.convertCurrency(barang.harga));
        tv_namaBarang.setText(detailPenjualan.namaBarang);
        tv_jumlahBarang.setText("× " + detailPenjualan.jumlahBarang);
        tv_subTotal.setText("Rp. " + a_.convertCurrency(barang.harga * detailPenjualan.jumlahBarang));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tv_namaBarang = new javax.swing.JLabel();
        tv_jumlahBarang = new javax.swing.JLabel();
        tv_subTotal = new javax.swing.JLabel();
        iv_gambar = new javax.swing.JLabel();
        tv_harga = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new RoundedBorder(borderRadius)
        );
        setOpaque(false);

        tv_namaBarang.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_namaBarang.setText("Coca Cola Zero Sugar Vanilla");

        tv_jumlahBarang.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(14f)
        );
        tv_jumlahBarang.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        tv_jumlahBarang.setText("x2");

        tv_subTotal.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(14f)
        );
        tv_subTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        tv_subTotal.setText("Rp. 24.000");

        tv_harga.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(14f)
        );
        tv_harga.setText("Rp. 12.000");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(iv_gambar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tv_harga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tv_jumlahBarang)
                        .addGap(36, 36, 36)
                        .addComponent(tv_subTotal))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tv_namaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 78, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(iv_gambar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tv_namaBarang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tv_jumlahBarang)
                            .addComponent(tv_subTotal)
                            .addComponent(tv_harga))))
                .addGap(6, 6, 6))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iv_gambar;
    private javax.swing.JLabel tv_harga;
    private javax.swing.JLabel tv_jumlahBarang;
    private javax.swing.JLabel tv_namaBarang;
    private javax.swing.JLabel tv_subTotal;
    // End of variables declaration//GEN-END:variables
}
