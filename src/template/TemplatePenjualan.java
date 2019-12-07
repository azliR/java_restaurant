package template;

import common.RoundedBorder;
import common.RoundedPanel;
import common.a_;
import java.awt.Color;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DetailPenjualan;
import model.Meja;
import model.Penjualan;
import panel.EntriPenjualanPanel;
import styles.Colors;
import styles.Fonts;

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
        tv_nomorMeja.setText("Meja " + new Meja().get(connection, penjualan.idMeja).nomorMeja);
        tv_pengguna.setText(penjualan.atasNama);
//        tv_status.setText(new StatusPesanan().get(connection, penjualan.idStatus).namaStatus);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tv_id = new javax.swing.JLabel();
        tv_nomorMeja = new javax.swing.JLabel();
        tv_pengguna = new javax.swing.JLabel();
        tv_total = new javax.swing.JLabel();
        tv_tanggalPenjualan = new javax.swing.JLabel();
        p_statusColor = new RoundedPanel(16);
        tv_status = new javax.swing.JLabel();

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

        tv_id.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(14f)
        );
        tv_id.setText("#114");

        tv_nomorMeja.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(14f)
        );
        tv_nomorMeja.setText("Meja 8");

        tv_pengguna.setFont(Fonts.ROBOTO_REGULAR.deriveFont(12f)
        );
        tv_pengguna.setText("a_lpha");

        tv_total.setFont(Fonts.ROBOTO_REGULAR.deriveFont(12f)
        );
        tv_total.setText("Rp. 47.000");

        tv_tanggalPenjualan.setFont(Fonts.ROBOTO_REGULAR.deriveFont(12f)
        );
        tv_tanggalPenjualan.setText("26 menit yang lalu");

        p_statusColor.setBackground(new java.awt.Color(255, 204, 51));

        tv_status.setBackground(new java.awt.Color(255, 204, 0));
        tv_status.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        tv_status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tv_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_clock-alert-outline_20.png"))); // NOI18N

        javax.swing.GroupLayout p_statusColorLayout = new javax.swing.GroupLayout(p_statusColor);
        p_statusColor.setLayout(p_statusColorLayout);
        p_statusColorLayout.setHorizontalGroup(
            p_statusColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tv_status, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );
        p_statusColorLayout.setVerticalGroup(
            p_statusColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tv_status, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tv_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tv_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tv_pengguna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(36, 36, 36)
                        .addComponent(tv_tanggalPenjualan))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tv_nomorMeja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(27, 27, 27)
                        .addComponent(p_statusColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(p_statusColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tv_nomorMeja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tv_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tv_total)
                    .addComponent(tv_tanggalPenjualan)
                    .addComponent(tv_pengguna))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        context.showDetailPesanan(this, penjualan);
    }//GEN-LAST:event_formMouseClicked

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
    private javax.swing.JPanel p_statusColor;
    private javax.swing.JLabel tv_id;
    private javax.swing.JLabel tv_nomorMeja;
    private javax.swing.JLabel tv_pengguna;
    private javax.swing.JLabel tv_status;
    private javax.swing.JLabel tv_tanggalPenjualan;
    private javax.swing.JLabel tv_total;
    // End of variables declaration//GEN-END:variables
}
