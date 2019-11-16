package common;

import java.awt.Color;
import java.sql.Connection;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EntriMeja;
import model.Penjualan;
import model.StatusPesanan;
import panel.EntriPesananPanel;
import styles.Colors;

/**
 *
 * @author a_lpha
 */
public class TemplatePenjualan extends RoundedPanel {

    private final int borderRadius = 16;
    private final int[] borderInsets = {0, 0, 0, 0};
    private final Color inactiveBorderColor = Colors.borderColor;
    private final Color activeBorderColor = Colors.accentColor;

    private final Color inactiveBackgroundColor = Colors.primaryColor;
    private final Color inactiveTextColor = Colors.blackTextColor;
    private final Color activeBackgroundColor = Colors.blueBackgroundColor;
    private final Color activeTextColor = Colors.accentColor;

    private final EntriPesananPanel context;

    private final Penjualan penjualan;

    public TemplatePenjualan(Connection connection, EntriPesananPanel context, Penjualan penjualan) {
        super(16);
        initComponents();
        this.context = context;
        this.penjualan = penjualan;

        tv_id.setText("#" + penjualan.getId());
        tv_nomorMeja.setText("Meja " + new EntriMeja().get(connection, penjualan.getIdMeja()).getNomorMeja());
        tv_pengguna.setText(penjualan.getAtasNama());
        tv_status.setText(new StatusPesanan().get(connection, penjualan.getIdStatus()).getNamaStatus());
        tv_total.setText("Rp. " + a_.convertCurrency(penjualan.getTotal()));

        try {
            tv_tanggalPenjualan.setText(a_.convertTimestamp(penjualan.getTanggalPenjualan()));

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tv_id = new javax.swing.JLabel();
        tv_nomorMeja = new javax.swing.JLabel();
        tv_pengguna = new javax.swing.JLabel();
        tv_status = new javax.swing.JLabel();
        tv_total = new javax.swing.JLabel();
        tv_tanggalPenjualan = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new RoundedBorder(borderRadius)
        );
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
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

        tv_tanggalPenjualan.setFont(new java.awt.Font("DejaVu Sans Mono", 0, 14)); // NOI18N
        tv_tanggalPenjualan.setText("18.00 | 08 Agustus 2019");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(tv_id, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(tv_nomorMeja, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(tv_pengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(tv_status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tv_total))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(486, 486, 486)
                        .addComponent(tv_tanggalPenjualan)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tv_tanggalPenjualan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        context.showDetailPesanan(this, penjualan);
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel tv_id;
    private javax.swing.JLabel tv_nomorMeja;
    private javax.swing.JLabel tv_pengguna;
    private javax.swing.JLabel tv_status;
    private javax.swing.JLabel tv_tanggalPenjualan;
    private javax.swing.JLabel tv_total;
    // End of variables declaration//GEN-END:variables
}
