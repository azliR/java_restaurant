package template;

import common.RoundedBorder;
import common.RoundedButton;
import common.a_;
import java.awt.Image;
import java.sql.Connection;
import javax.swing.ImageIcon;
import model.Barang;
import model.Pesanan;
import panel.EntriBarangPanel;
import styles.Colors;
import styles.Fonts;

/**
 *
 * @author a_lpha
 */
public class TemplatePesanan extends javax.swing.JPanel {

    private final int borderRadius = 16;

    private final Pesanan pesanan;
    private final Barang barang;

    private final Connection connection;
    private final EntriBarangPanel context;

    public TemplatePesanan(EntriBarangPanel context, Connection connection, Pesanan pesanan) {
        this.context = context;
        this.connection = connection;
        this.pesanan = pesanan;
        this.barang = new Barang().get(connection, pesanan.idBarang);
        initComponents();

        final int maxWidth = 64;
        final int maxHeight = 64;

        Image image = new ImageIcon(pesanan.gambar).getImage();
        Image scaledImage = a_.scaleImage(image, maxWidth, maxHeight);
        Image croppedImage = a_.cropImage(
                a_.toBufferedImage(new ImageIcon(scaledImage).getImage()),
                maxWidth,
                maxHeight);
        Image roundedImage = a_.convertRoundedImage(croppedImage, borderRadius);

        iv_gambar.setIcon(new ImageIcon(roundedImage));
        tv_namaBarang.setText(barang.namaBarang);
        tv_harga.setText("Rp. " + a_.convertCurrency(barang.harga));
        et_jumlah.setText(String.valueOf(pesanan.jumlahBarang));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        tv_namaBarang = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        iv_gambar = new javax.swing.JLabel();
        et_jumlah = new javax.swing.JTextField();
        b_kurang = new RoundedButton(borderRadius);
        b_tambah = new RoundedButton(borderRadius);
        tv_harga = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new RoundedBorder(borderRadius)
        );
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(380, 72));

        tv_namaBarang.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_namaBarang.setText("Coca Cola Zero Sugar Vanilla");

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

        et_jumlah.setFont(Fonts.ROBOTO_REGULAR.deriveFont(12f)
        );
        et_jumlah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        et_jumlah.setText("1");
        et_jumlah.setBorder(new RoundedBorder(borderRadius)
        );
        et_jumlah.setOpaque(false);

        b_kurang.setBackground(new java.awt.Color(255, 255, 255));
        b_kurang.setFont(Fonts.ROBOTO_REGULAR.deriveFont(12f));
        b_kurang.setForeground(Colors.greyTextColor);
        b_kurang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_minus_14_grey.png"))); // NOI18N
        b_kurang.setBorder(null);
        b_kurang.setBorderPainted(false);
        b_kurang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_kurang.setFocusPainted(false);
        b_kurang.setOpaque(false);
        b_kurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_kurangActionPerformed(evt);
            }
        });

        b_tambah.setBackground(new java.awt.Color(255, 255, 255));
        b_tambah.setFont(Fonts.ROBOTO_REGULAR.deriveFont(12f));
        b_tambah.setForeground(Colors.greyTextColor);
        b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_plus_14_grey.png"))); // NOI18N
        b_tambah.setBorder(null);
        b_tambah.setBorderPainted(false);
        b_tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_tambah.setFocusPainted(false);
        b_tambah.setOpaque(false);
        b_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_tambahActionPerformed(evt);
            }
        });

        tv_harga.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(14f)
        );
        tv_harga.setText("Rp. 25.000");

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
                        .addComponent(b_kurang, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(et_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tv_namaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jButton1)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tv_harga)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tv_namaBarang)
                            .addComponent(jButton1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(et_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b_kurang, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(tv_harga))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(iv_gambar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        context.removeFromCart(this, pesanan);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void b_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_tambahActionPerformed
        int jumlah = Integer.parseInt(et_jumlah.getText());
        jumlah++;

        et_jumlah.setText(String.valueOf(jumlah));

        pesanan.jumlahBarang = jumlah;
        context.pesanans.set(context.pesanans.indexOf(pesanan), pesanan);
        context.setTotal();
    }//GEN-LAST:event_b_tambahActionPerformed

    private void b_kurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_kurangActionPerformed
        int jumlah = Integer.parseInt(et_jumlah.getText());

        if (jumlah > 1) {
            jumlah--;
            et_jumlah.setText(String.valueOf(jumlah));

            pesanan.jumlahBarang = jumlah;
            context.pesanans.set(context.pesanans.indexOf(pesanan), pesanan);
            context.setTotal();
        }
    }//GEN-LAST:event_b_kurangActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_kurang;
    private javax.swing.JButton b_tambah;
    private javax.swing.JTextField et_jumlah;
    private javax.swing.JLabel iv_gambar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel tv_harga;
    private javax.swing.JLabel tv_namaBarang;
    // End of variables declaration//GEN-END:variables
}
