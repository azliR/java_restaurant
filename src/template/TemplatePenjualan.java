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

        tv_nomorMeja.setText("Meja " + new Meja().get(connection, penjualan.idMeja).nomorMeja);
        tv_atasNama.setText(penjualan.atasNama);
        tv_total.setText("Rp. " + a_.convertCurrency(penjualan.total));

        switch (penjualan.idStatus) {
            case 1:
                tv_nomorMeja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_clock-alert-outline.png")));
                break;
            case 2:
                tv_nomorMeja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_clock-outline.png")));
                break;
            case 3:
                tv_nomorMeja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_credit-card-clock-outline.png")));
                break;
            case 4:
                tv_nomorMeja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_checkbox-marked-circle-outline.png")));
                break;
            case 5:
                tv_nomorMeja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_alert-circle-outline.png")));
                break;
            default:
                System.err.println("Cannot find icon for status");
        }

        try {
            tv_tanggalPenjualan.setText(a_.convertTimestamp(penjualan.tanggalPenjualan));

        } catch (ParseException ex) {
            Logger.getLogger(TemplatePenjualan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSelected(boolean isSelected) {
        setBackground(isSelected ? activeBackgroundColor : inactiveBackgroundColor);
        setBorder(new RoundedBorder(borderRadius, borderInsets, isSelected ? activeBorderColor : inactiveBorderColor));
        tv_nomorMeja.setForeground(isSelected ? activeTextColor : inactiveTextColor);
        jSeparator1.setBackground(isSelected ? activeBackgroundColor : inactiveBackgroundColor);
//        jSeparator3.setBackground(isSelected ? activeBackgroundColor : inactiveBackgroundColor);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tv_nomorMeja = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tv_atasNama = new javax.swing.JLabel();
        tv_total = new javax.swing.JLabel();
        tv_tanggalPenjualan = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

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

        tv_nomorMeja.setFont(Fonts.GOOGLE_SANS.deriveFont(16f)
        );
        tv_nomorMeja.setForeground(Colors.blackTextColor);
        tv_nomorMeja.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tv_nomorMeja.setText("Meja 8");
        tv_nomorMeja.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        tv_nomorMeja.setIconTextGap(14);

        jLabel2.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(11f)
        );
        jLabel2.setForeground(Colors.greyTextColor);
        jLabel2.setText("ATAS NAMA");

        jLabel3.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(11f)
        );
        jLabel3.setForeground(Colors.greyTextColor);
        jLabel3.setText("TOTAL");

        tv_atasNama.setFont(Fonts.ROBOTO_REGULAR.deriveFont(14f)
        );
        tv_atasNama.setForeground(Colors.blackTextColor);
        tv_atasNama.setText("a_lpha");

        tv_total.setFont(Fonts.ROBOTO_REGULAR.deriveFont(14f)
        );
        tv_total.setForeground(Colors.blackTextColor);
        tv_total.setText("Rp. 47.000");

        tv_tanggalPenjualan.setFont(Fonts.ROBOTO_REGULAR.deriveFont(12f)
        );
        tv_tanggalPenjualan.setForeground(Colors.greyTextColor);
        tv_tanggalPenjualan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tv_tanggalPenjualan.setText("26 menit yang lalu");

        jSeparator1.setForeground(Colors.borderColor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jSeparator1)
                .addGap(1, 1, 1))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tv_atasNama)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tv_tanggalPenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tv_nomorMeja, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tv_total, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(tv_nomorMeja, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tv_atasNama)
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tv_total)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(tv_tanggalPenjualan)
                .addGap(12, 12, 12))
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel tv_atasNama;
    private javax.swing.JLabel tv_nomorMeja;
    private javax.swing.JLabel tv_tanggalPenjualan;
    private javax.swing.JLabel tv_total;
    // End of variables declaration//GEN-END:variables
}
