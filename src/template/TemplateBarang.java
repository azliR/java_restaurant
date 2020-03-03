package template;

import common.RoundedBorder;
import common.RoundedPanel;
import common.a_;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.util.List;
import javax.swing.ImageIcon;
import model.Barang;
import model.Varian;
import panel.EntriBarangPanel;
import styles.Colors;
import styles.Fonts;

/**
 *
 * @author a_lpha
 */
public class TemplateBarang extends RoundedPanel {

    private final Barang barang;

    private final int borderRadius = 16;
    private final int[] borderInsets = {0, 0, 0, 0};
    private final int[] chipInsets = {8, 12, 8, 12};
    private final Color inactiveBorderColor = Colors.borderColor;
    private final Color activeBorderColor = Colors.accentColor;

    private final Color inactiveBackgroundColor = new Color(255, 255, 255);
    private final Color inactiveTextColor = Colors.blackTextColor;
    private final Color activeBackgroundColor = Colors.blueBackgroundColor;
    private final Color activeTextColor = Colors.accentColor;

    private final EntriBarangPanel context;

    private final Connection connection;

    public TemplateBarang(Connection connection, EntriBarangPanel context,
            Barang barang) {
        super(16);
        this.context = context;
        this.barang = barang;
        this.connection = connection;
        initComponents();

        final int maxWidth = 220;
        final int maxHeight = 140;

        Image image = new ImageIcon(barang.gambar).getImage();
        if (image.getWidth(null) > maxWidth || image.getHeight(null) > maxHeight) {
            image = a_.scaleImage(image, maxWidth, maxHeight);
        }
        Image resizedImage = a_.cropImage(
                a_.toBufferedImage(new ImageIcon(image).getImage()),
                maxWidth,
                maxHeight);
        BufferedImage roundedImage = a_.convertRoundedImage(
                resizedImage,
                borderRadius);

        tv_image.setIcon(new ImageIcon(roundedImage));
        tv_hargaBarang.setText("Rp. " + a_.convertCurrency(barang.harga));
        tv_namaBarang.setText("<html>" + barang.namaBarang + "</html>");

        List<Varian> varians = new Varian().getByIdBarang(connection, barang.id);
        tv_varian.setText("Terdapat " + varians.size() + " varian");
        tv_varian.setVisible(!varians.isEmpty());
    }

    public void setSelected(boolean isSelected) {
        setBackground(isSelected ? activeBackgroundColor
                : inactiveBackgroundColor);
        setBorder(new RoundedBorder(borderRadius, borderInsets, isSelected
                ? activeBorderColor : inactiveBorderColor));
        tv_namaBarang.setForeground(isSelected ? activeTextColor
                : inactiveTextColor);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tv_image = new javax.swing.JLabel();
        tv_namaBarang = new javax.swing.JLabel();
        tv_hargaBarang = new javax.swing.JLabel();
        tv_varian = new javax.swing.JLabel();

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

        tv_hargaBarang.setFont(Fonts.PRODUCT_SANS_MEDIUM.deriveFont(18f)
        );
        tv_hargaBarang.setText("Rp. 8.000");

        tv_varian.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(10f)
        );
        tv_varian.setForeground(Colors.greyTextColor);
        tv_varian.setText("Terdapat 3 Varian");

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
                        .addComponent(tv_namaBarang)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tv_hargaBarang)
                            .addComponent(tv_varian))
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
                .addComponent(tv_varian)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tv_hargaBarang)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        if (getBackground() != Colors.blueBackgroundColor) {
            setBorder(new RoundedBorder(borderRadius, borderInsets,
                    activeBorderColor));
        }
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        if (getBackground() != Colors.blueBackgroundColor) {
            setBorder(new RoundedBorder(borderRadius, borderInsets,
                    inactiveBorderColor));
        }
    }//GEN-LAST:event_formMouseExited

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        context.showDetailBarang(this, barang);
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel tv_hargaBarang;
    private javax.swing.JLabel tv_image;
    private javax.swing.JLabel tv_namaBarang;
    private javax.swing.JLabel tv_varian;
    // End of variables declaration//GEN-END:variables
}
