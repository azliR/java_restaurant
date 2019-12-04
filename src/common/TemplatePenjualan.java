package common;

import java.awt.Color;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DetailPenjualan;
import model.EntriMeja;
import model.Penjualan;
import model.StatusPesanan;
import panel.EntriPenjualanPanel;
import styles.Colors;

/**
 *
 * @author a_lpha
 */
public class TemplatePenjualan extends RoundedPanel {

    private final List<TemplateDetailPenjualan> templateDetailPenjualans = new ArrayList<>();
    private final List<DetailPenjualan> detailPenjualans = new ArrayList<>();

    private final int borderRadius = 16;
    private final int[] borderInsets = {0, 0, 0, 0};
    private final Color inactiveBorderColor = Colors.borderColor;
    private final Color activeBorderColor = Colors.accentColor;

    private final Color inactiveBackgroundColor = Colors.primaryColor;
    private final Color inactiveTextColor = Colors.blackTextColor;
    private final Color activeBackgroundColor = Colors.blueBackgroundColor;
    private final Color activeTextColor = Colors.accentColor;

    private final EntriPenjualanPanel context;

    public final Penjualan penjualan;

    private Connection connection;

    public TemplatePenjualan(Connection connection, EntriPenjualanPanel context, Penjualan penjualan) {
        super(16);
        initComponents();
        this.context = context;
        this.connection = connection;
        this.penjualan = penjualan;

        tv_id.setText("#" + penjualan.id);
        tv_nomorMeja.setText("Meja " + new EntriMeja().get(connection, penjualan.idMeja).nomorMeja);
        tv_pengguna.setText(penjualan.atasNama);
        tv_status.setText(new StatusPesanan().get(connection, penjualan.idStatus).namaStatus);
        tv_total.setText("Rp. " + a_.convertCurrency(penjualan.total));

        try {
            tv_tanggalPenjualan.setText(a_.convertTimestamp(penjualan.tanggalPenjualan));

        } catch (ParseException ex) {
            Logger.getLogger(TemplatePenjualan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSelected(boolean isSelected) {
        setBackground(isSelected ? activeBackgroundColor : inactiveBackgroundColor);
        setBorder(new RoundedBorder(borderRadius, borderInsets, isSelected ? activeBorderColor : inactiveBorderColor));
        tv_id.setForeground(isSelected ? activeTextColor : inactiveTextColor);
        tv_nomorMeja.setForeground(isSelected ? activeTextColor : inactiveTextColor);
        tv_pengguna.setForeground(isSelected ? activeTextColor : inactiveTextColor);
        tv_status.setForeground(isSelected ? activeTextColor : inactiveTextColor);
        tv_tanggalPenjualan.setForeground(isSelected ? activeTextColor : inactiveTextColor);
        tv_total.setForeground(isSelected ? activeTextColor : inactiveTextColor);
    }

    private void showDetailPesanan() {
        setSelected(!b_expandDetail.isSelected());
        detailPenjualanPanel.setVisible(!b_expandDetail.isSelected());
        b_expandDetail.setSelected(!b_expandDetail.isSelected());
        revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tv_id = new javax.swing.JLabel();
        tv_nomorMeja = new javax.swing.JLabel();
        tv_pengguna = new javax.swing.JLabel();
        tv_status = new javax.swing.JLabel();
        tv_total = new javax.swing.JLabel();
        tv_tanggalPenjualan = new javax.swing.JLabel();
        detailPenjualanPanel = new javax.swing.JPanel();
        b_expandDetail = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new RoundedBorder(borderRadius)
        );
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        tv_id.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        tv_id.setText("#114");

        tv_nomorMeja.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        tv_nomorMeja.setText("Meja 8");

        tv_pengguna.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        tv_pengguna.setText("a_lpha");

        tv_status.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        tv_status.setText("Sedang Disiapkan");

        tv_total.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        tv_total.setText("Rp. 47.000");

        tv_tanggalPenjualan.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_tanggalPenjualan.setText("18.00  |  08 Agustus 2019");

        detailPenjualanPanel.setBackground(new java.awt.Color(255, 255, 255));
        detailPenjualanPanel.setOpaque(false);
        detailPenjualanPanel.setLayout(new java.awt.GridLayout(0, 1));

        b_expandDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_chevron-down_grey.png"))); // NOI18N
        b_expandDetail.setBorderPainted(false);
        b_expandDetail.setContentAreaFilled(false);
        b_expandDetail.setFocusPainted(false);
        b_expandDetail.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_chevron-down.png"))); // NOI18N
        b_expandDetail.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_chevron-up.png"))); // NOI18N
        b_expandDetail.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_chevron-up_grey.png"))); // NOI18N
        b_expandDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_expandDetailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(detailPenjualanPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tv_tanggalPenjualan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tv_id, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(tv_nomorMeja, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(tv_pengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(tv_status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(70, 70, 70)
                        .addComponent(tv_total))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_expandDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tv_id)
                    .addComponent(tv_nomorMeja)
                    .addComponent(tv_pengguna)
                    .addComponent(tv_total)
                    .addComponent(tv_status))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(b_expandDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tv_tanggalPenjualan)
                        .addGap(18, 18, 18)))
                .addComponent(detailPenjualanPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        detailPenjualanPanel.setVisible(false);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        context.showDetailPesanan(this, penjualan);
    }//GEN-LAST:event_formMouseClicked

    private void b_expandDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_expandDetailActionPerformed
        showDetailPesanan();
    }//GEN-LAST:event_b_expandDetailActionPerformed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        if (getBackground() != activeBackgroundColor) {
            setBorder(new RoundedBorder(borderRadius, borderInsets, Colors.accentColor));
        }
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        if (getBackground() != activeBackgroundColor) {
            setBorder(new RoundedBorder(borderRadius, borderInsets, Colors.borderColor));
        }
    }//GEN-LAST:event_formMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton b_expandDetail;
    private javax.swing.JPanel detailPenjualanPanel;
    private javax.swing.JLabel tv_id;
    private javax.swing.JLabel tv_nomorMeja;
    private javax.swing.JLabel tv_pengguna;
    private javax.swing.JLabel tv_status;
    private javax.swing.JLabel tv_tanggalPenjualan;
    private javax.swing.JLabel tv_total;
    // End of variables declaration//GEN-END:variables
}
