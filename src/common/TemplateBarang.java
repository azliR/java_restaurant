package common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.sql.Connection;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.Barang;
import model.Varian;
import panel.EntriBarangPanel;
import styles.Colors;

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

    public TemplateBarang(Connection connection, EntriBarangPanel context, Barang barang) {
        super(16);
        this.context = context;
        this.barang = barang;
        this.connection = connection;
        initComponents();

        tv_image.setIcon(new ImageIcon(a_.convertRoundedImage(a_.toBufferedImage(new ImageIcon(barang.getGambar()).getImage(), new Rectangle(220, 140)), borderRadius)));
        tv_hargaBarang.setText("Rp. " + a_.convertCurrency(barang.getHarga()));
        tv_namaBarang.setText("<html>" + barang.getNamaBarang() + "</html>");

        List<Varian> varians = new Varian().getByIdBarang(connection, barang.getId());
        varians.forEach((_varian) -> {
            JLabel chip = new JLabel();
            chip.setFont(new Font("Roboto Medium", 0, 10));
            chip.setForeground(inactiveTextColor);
            chip.setBorder(new RoundedBorder(30, chipInsets, inactiveBorderColor));
            chip.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            chip.setText(_varian.getNamaVarian().toUpperCase());
            variansPanel.add(chip);
        });
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
        jPanel1 = new javax.swing.JPanel();
        variansPanel = new javax.swing.JPanel();

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 0, 0));

        variansPanel.setBackground(new java.awt.Color(255, 255, 255));
        variansPanel.setOpaque(false);
        variansPanel.setLayout(new java.awt.GridLayout(1, 0, 4, 0));
        jPanel1.add(variansPanel);

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
                        .addComponent(tv_namaBarang))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tv_hargaBarang)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(tv_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tv_hargaBarang))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(tv_image, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1))
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel tv_hargaBarang;
    private javax.swing.JLabel tv_image;
    private javax.swing.JLabel tv_namaBarang;
    private javax.swing.JPanel variansPanel;
    // End of variables declaration//GEN-END:variables
}
