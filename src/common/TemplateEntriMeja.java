package common;

import java.awt.Color;
import java.sql.Connection;
import model.EntriMeja;
import panel.EntriMejaPanel;
import styles.Colors;

/**
 *
 * @author a_lpha
 */
public class TemplateEntriMeja extends RoundedPanel {

    private final EntriMeja currentMeja;

    private final int borderRadius = 16;
    private final int[] borderInsets = {0, 0, 0, 0};
    private final Color inactiveBorderColor = Colors.borderColor;
    private final Color activeBorderColor = Colors.accentColor;
    private final Color inactiveBackgroundColor = new Color(255, 255, 255);
    private final Color inactiveTextColor = Colors.blackTextColor;
    private final Color activeBackgroundColor = Colors.blueBackgroundColor;
    private final Color activeTextColor = Colors.accentColor;

    private final EntriMejaPanel context;
    private final Connection connection;

    public TemplateEntriMeja(Connection connection, EntriMejaPanel context, EntriMeja entriMeja) {
        super(16);
        this.context = context;
        this.currentMeja = entriMeja;
        this.connection = connection;
        initComponents();

        tv_noMeja.setText(String.valueOf(entriMeja.nomorMeja));
        tv_noMeja.setForeground(entriMeja.atasNama != null ? activeTextColor : Color.BLACK);
    }

    public void setSelected(boolean isSelected) {
        if (isSelected) {
            setBackground(activeBackgroundColor);
            setBorder(new RoundedBorder(borderRadius, borderInsets, activeBorderColor));
            tv_meja.setForeground(activeTextColor);
        } else {
            setBackground(inactiveBackgroundColor);
            setBorder(new RoundedBorder(borderRadius, borderInsets, inactiveBorderColor));
            tv_meja.setForeground(inactiveTextColor);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tv_noMeja = new javax.swing.JLabel();
        tv_meja = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new RoundedBorder(borderRadius, borderInsets, inactiveBorderColor)
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

        tv_noMeja.setFont(new java.awt.Font("Product Sans", 1, 86)); // NOI18N
        tv_noMeja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tv_noMeja.setText("8");
        tv_noMeja.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        tv_meja.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        tv_meja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tv_meja.setText("MEJA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tv_noMeja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tv_meja, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(tv_meja)
                .addGap(4, 4, 4)
                .addComponent(tv_noMeja)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        if (getBackground() != Colors.blueBackgroundColor) {
            setBorder(new RoundedBorder(borderRadius, borderInsets, activeBorderColor));
        }
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        if (getBackground() != Colors.blueBackgroundColor) {
            setBorder(new RoundedBorder(borderRadius, borderInsets, inactiveBorderColor));
        }
    }//GEN-LAST:event_formMouseExited

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        context.showInfoMeja(this, currentMeja);
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel tv_meja;
    private javax.swing.JLabel tv_noMeja;
    // End of variables declaration//GEN-END:variables
}
