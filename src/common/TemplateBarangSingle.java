package common;

import java.awt.Color;
import java.awt.Rectangle;
import java.sql.Connection;
import javax.swing.ImageIcon;
import model.Barang;
import model.JenisBarang;
import panel.EntriBarangPanel;
import styles.Colors;

/**
 *
 * @author a_lpha
 */
public class TemplateBarangSingle extends RoundedPanel {

    private final Barang barang;

    private final int borderRadius = 16;
    private final int[] borderInsets = {0, 0, 0, 0};
    private final int[] chipInsets = {9, 12, 9, 12};
    private final Color inactiveBorderColor = Colors.borderColor;
    private final Color activeBorderColor = Colors.accentColor;

    private final Color inactiveBackgroundColor = new Color(255, 255, 255);
    private final Color inactiveTextColor = Colors.blackTextColor;
    private final Color activeBackgroundColor = Colors.blueBackgroundColor;
    private final Color activeTextColor = Colors.accentColor;

    private final EntriBarangPanel context;

    private final Connection connection;

    public TemplateBarangSingle(Connection connection, EntriBarangPanel context, Barang barang) {
        super(16);
        this.context = context;
        this.barang = barang;
        this.connection = connection;
        initComponents();

        tv_image.setIcon(new ImageIcon(a_.convertRoundedImage(a_.toBufferedImage(new ImageIcon(barang.getGambar()).getImage(), new Rectangle(220, 140)), borderRadius)));
        tv_hargaBarang.setText("Rp. " + a_.convertCurrency(barang.getHarga()));
        tv_namaBarang.setText("<html>" + barang.getNamaBarang() + "</html>");
        tv_jenisBarang.setText(new JenisBarang().get(connection, barang.getIdJenis()).getNamaJenis().toUpperCase());
    }

    public void setSelected(boolean isSelected) {
        setBackground(isSelected ? activeBackgroundColor : inactiveBackgroundColor);
        setBorder(new RoundedBorder(borderRadius, borderInsets, isSelected ? activeBorderColor : inactiveBorderColor));
        tv_namaBarang.setForeground(isSelected ? activeTextColor : inactiveTextColor);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tv_image = new javax.swing.JLabel();
        tv_namaBarang = new javax.swing.JLabel();
        tv_hargaBarang = new javax.swing.JLabel();
        tv_jenisBarang = new javax.swing.JLabel();

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

        tv_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tv_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/a_ logo.png"))); // NOI18N
        tv_image.setIconTextGap(0);
        tv_image.setMaximumSize(new java.awt.Dimension(260, 140));

        tv_namaBarang.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        tv_namaBarang.setForeground(Colors.blackTextColor);
        tv_namaBarang.setText("<html>Coca Cola Zero</html>");

        tv_hargaBarang.setFont(new java.awt.Font("Product Sans Medium", 0, 18)); // NOI18N
        tv_hargaBarang.setForeground(new java.awt.Color(0, 0, 0));
        tv_hargaBarang.setText("Rp. 8.000");

        tv_jenisBarang.setFont(new java.awt.Font("Roboto Medium", 0, 10)); // NOI18N
        tv_jenisBarang.setText("MAKANAN");
        tv_jenisBarang.setBorder(new RoundedBorder(32, chipInsets, inactiveBorderColor)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(tv_image, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tv_namaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tv_hargaBarang)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(tv_jenisBarang)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(tv_image, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(tv_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tv_jenisBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tv_hargaBarang)
                .addGap(16, 16, 16))
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
        context.showDetailBarang(this, barang);
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel tv_hargaBarang;
    private javax.swing.JLabel tv_image;
    private javax.swing.JLabel tv_jenisBarang;
    private javax.swing.JLabel tv_namaBarang;
    // End of variables declaration//GEN-END:variables
}
