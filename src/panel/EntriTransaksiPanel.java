package panel;

import common.RoundedButton;
import common.a_;
import common.a_TextField;
import java.sql.Connection;
import java.util.List;
import model.DetailPenjualan;
import model.Penjualan;
import styles.Colors;
import styles.Fonts;
import template.TemplateDetailPenjualan;

/**
 *
 * @author a_lpha
 */
public class EntriTransaksiPanel extends javax.swing.JPanel {

    private final Penjualan penjualan;
    private final List<DetailPenjualan> detailPenjualans;
    private final Connection connection;

    public EntriTransaksiPanel(Connection connection, Penjualan penjualan, List<DetailPenjualan> detailPenjualans) {
        this.connection = connection;
        this.penjualan = penjualan;
        this.detailPenjualans = detailPenjualans;
        initComponents();

        if (penjualan != null) {
            loadDetailPenjualan(detailPenjualans);
            tv_atasNama.setText(penjualan.atasNama);
            tv_jumlahOrang.setText(String.valueOf(penjualan.jumlahOrang));
            tv_tanggal.setText(penjualan.tanggalPenjualan);
            tv_total.setText("Rp. " + a_.convertCurrency(penjualan.total));
            tv_kembalian.setText("Rp. " + a_.convertCurrency(penjualan.total));
        }
    }
    
    private void loadDetailPenjualan(List<DetailPenjualan> detailPenjualans) {
        int i = 1;
        for (var detailPenjualan : detailPenjualans) {
            var templateDetailPenjualan = new TemplateDetailPenjualan(connection, detailPenjualan, i);
            int width = jScrollPane1.getWidth() - 16;
            int height = templateDetailPenjualan.getPreferredSize().height;

            detailPesananListPanel.add(templateDetailPenjualan);
            i++;
        }
        detailPesananListPanel.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        detailPesananListPanel = new javax.swing.JPanel();
        jButton1 = new RoundedButton(8);
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tv_atasNama = new javax.swing.JLabel();
        tv_jumlahOrang = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tv_statusPesanan = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tv_kembalian = new javax.swing.JLabel();
        et_bayar = new a_TextField("Bayar");
        jLabel13 = new javax.swing.JLabel();
        tv_total = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tv_tanggal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 440));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        detailPesananListPanel.setBackground(new java.awt.Color(255, 255, 255));
        detailPesananListPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 6));
        jPanel1.add(detailPesananListPanel);

        jScrollPane1.setViewportView(jPanel1);

        jButton1.setBackground(Colors.accentColor);
        jButton1.setFont(Fonts.PRODUCT_SANS_MEDIUM.deriveFont(16f)
        );
        jButton1.setForeground(Colors.primaryColor);
        jButton1.setText("Bayar");
        jButton1.setBorder(null);

        jLabel2.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(11f)
        );
        jLabel2.setForeground(Colors.greyTextColor);
        jLabel2.setText("BARANG YANG DIPESAN");

        jLabel3.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(11f)
        );
        jLabel3.setForeground(Colors.greyTextColor);
        jLabel3.setText("ATAS NAMA");

        tv_atasNama.setFont(Fonts.ROBOTO_REGULAR.deriveFont(16f)
        );
        tv_atasNama.setForeground(Colors.blackTextColor);
        tv_atasNama.setText("Rizal Hadiyansah");

        tv_jumlahOrang.setFont(Fonts.ROBOTO_REGULAR.deriveFont(16f)
        );
        tv_jumlahOrang.setForeground(Colors.blackTextColor);
        tv_jumlahOrang.setText("4 Orang");

        jLabel6.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(12f)
        );
        jLabel6.setForeground(Colors.greyTextColor);
        jLabel6.setText("JUMLAH ORANG");

        jLabel7.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(11f)

        );
        jLabel7.setForeground(Colors.greyTextColor);
        jLabel7.setText("STATUS PESANAN");

        tv_statusPesanan.setFont(Fonts.ROBOTO_REGULAR.deriveFont(16f)
        );
        tv_statusPesanan.setForeground(Colors.blackTextColor);
        tv_statusPesanan.setText("Menunggu Pembayaran");

        jLabel9.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(11f)

        );
        jLabel9.setForeground(Colors.greyTextColor);
        jLabel9.setText("MEJA");

        jLabel10.setFont(Fonts.ROBOTO_LIGHT.deriveFont(64f)
        );
        jLabel10.setForeground(Colors.blackTextColor);
        jLabel10.setText("5");

        jLabel11.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(11f)

        );
        jLabel11.setForeground(Colors.greyTextColor);
        jLabel11.setText("KEMBALIAN");

        tv_kembalian.setFont(Fonts.ROBOTO_LIGHT.deriveFont(20f)
        );
        tv_kembalian.setForeground(Colors.blackTextColor);
        tv_kembalian.setText("Rp. 300.000");

        et_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                et_bayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                et_bayarKeyTyped(evt);
            }
        });

        jLabel13.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(11f)

        );
        jLabel13.setForeground(Colors.greyTextColor);
        jLabel13.setText("TOTAL");

        tv_total.setFont(Fonts.ROBOTO_LIGHT.deriveFont(34f)
        );
        tv_total.setForeground(Colors.blackTextColor);
        tv_total.setText("Rp. 128.000");

        jLabel15.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(11f)

        );
        jLabel15.setForeground(Colors.greyTextColor);
        jLabel15.setText("TANGGAL PEMESANAN");

        tv_tanggal.setFont(Fonts.ROBOTO_REGULAR.deriveFont(16f)
        );
        tv_tanggal.setForeground(Colors.blackTextColor);
        tv_tanggal.setText("08:34 | 08/01/2020");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(tv_atasNama)
                                    .addComponent(tv_jumlahOrang)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(24, 24, 24))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(tv_statusPesanan)
                                    .addComponent(jLabel13)
                                    .addComponent(tv_total))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tv_tanggal, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(et_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tv_kembalian)))
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(8, 8, 8)
                                .addComponent(tv_atasNama)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(8, 8, 8)
                                .addComponent(tv_jumlahOrang)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(8, 8, 8)
                                .addComponent(tv_statusPesanan))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tv_tanggal)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tv_total)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(6, 6, 6)
                                .addComponent(tv_kembalian))
                            .addComponent(et_bayar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void et_bayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_et_bayarKeyTyped
        char newChar = evt.getKeyChar();
        if (!(Character.isDigit(newChar))) {
            evt.consume();
        }
    }//GEN-LAST:event_et_bayarKeyTyped

    private void et_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_et_bayarKeyReleased
        final int total = penjualan.total;
        final int bayar = Integer.parseInt(et_bayar.getText().isBlank() ? "0" : et_bayar.getText());

        tv_kembalian.setText("Rp. " + a_.convertCurrency(bayar - total));
    }//GEN-LAST:event_et_bayarKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel detailPesananListPanel;
    private javax.swing.JTextField et_bayar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel tv_atasNama;
    private javax.swing.JLabel tv_jumlahOrang;
    private javax.swing.JLabel tv_kembalian;
    private javax.swing.JLabel tv_statusPesanan;
    private javax.swing.JLabel tv_tanggal;
    private javax.swing.JLabel tv_total;
    // End of variables declaration//GEN-END:variables
}
