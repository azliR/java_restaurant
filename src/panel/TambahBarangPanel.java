package panel;

import common.RoundedBorder;
import common.RoundedButton;
import common.a_;
import common.a_TextField;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import model.Barang;
import pages.MainPage;
import services.DBHelper;
import styles.Colors;

/**
 *
 * @author a_lpha
 */
public class TambahBarangPanel extends javax.swing.JPanel {

    private static Connection connection;

    private int idJenisBarang = 1;

    private final int borderRadius = 16;
    private final int buttonRadius = 8;
    private final int[] borderInsets = {8, 8, 8, 8};
    private final Color borderColor = Colors.borderColor;

    private final int maxImageWidth = 480;
    private final int maxImageHeight = 280;

    private File gambarFile;

    public TambahBarangPanel() {
        initComponents();
    }

    public TambahBarangPanel(Connection c) {
        initComponents();

        if (connection == null && c != null) {
            connection = c;
        } else {
            connection = DBHelper.getConnection();
        }
    }

    private void clear() {
        et_namaBarang.setText(null);
        et_hargaBarang.setText(null);
        et_deskripsi.setText(null);
    }

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        et_deskripsi = new a_TextField("Deskripsi");
        b_clear = new javax.swing.JButton();
        b_simpan = new RoundedButton(buttonRadius);
        et_namaBarang = new a_TextField("Nama Barang");
        et_hargaBarang = new a_TextField("Harga Barang");
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        b_clear.setBackground(new java.awt.Color(255, 255, 255));
        b_clear.setFont(new java.awt.Font("Product Sans Medium", 0, 14)); // NOI18N
        b_clear.setForeground(new java.awt.Color(71, 71, 71));
        b_clear.setText("Bersihkan");
        b_clear.setBorder(new RoundedBorder(buttonRadius, borderInsets, borderColor)
        );
        b_clear.setContentAreaFilled(false);
        b_clear.setFocusPainted(false);
        b_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_clearActionPerformed(evt);
            }
        });

        b_simpan.setBackground(Colors.accentColor);
        b_simpan.setFont(new java.awt.Font("Product Sans Medium", 0, 14)); // NOI18N
        b_simpan.setForeground(new java.awt.Color(255, 255, 255));
        b_simpan.setText("Simpan");
        b_simpan.setBorder(null);
        b_simpan.setContentAreaFilled(false);
        b_simpan.setFocusPainted(false);
        b_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_simpanActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(et_namaBarang))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(et_hargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(et_deskripsi, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(et_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247))
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(et_hargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(et_deskripsi, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void b_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_simpanActionPerformed
        String namaBarang = et_namaBarang.getText();
        String hargaBarang = et_hargaBarang.getText();
        String deskripsi = et_deskripsi.getText();

        if (namaBarang.isBlank() || hargaBarang.isBlank() || deskripsi.isBlank()) {
            a_.showDialog(a_.DialogType.EMPTY_FIELD);
            return;
        }

        Barang barang = new Barang();
        barang.namaBarang = namaBarang;
        barang.harga = Integer.parseInt(hargaBarang);
        barang.stokBarang = true;
        barang.idJenis = idJenisBarang;
        barang.deskripsi = deskripsi;

        if (barang.insert(connection, gambarFile)) {
            a_.showDialog(a_.DialogType.INSERT_SUCCESS);
            clear();
        } else {
            a_.showDialog(a_.DialogType.INSERT_ERROR);
        }
    }//GEN-LAST:event_b_simpanActionPerformed

    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_clearActionPerformed
        clear();
    }//GEN-LAST:event_b_clearActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        FileDialog fileDialog = new FileDialog((Frame) MainPage.parent, "Pilih Gambar Produk", FileDialog.LOAD);
        fileDialog.setFilenameFilter((dir, name) -> {
            return name.endsWith(".jpeg") || name.endsWith(".jpg") || name.endsWith(".png");
        });
        fileDialog.setAlwaysOnTop(true);
        fileDialog.setLocationRelativeTo(MainPage.parent);
        fileDialog.setVisible(true);
        fileDialog.toFront();

        String fileDirectory = fileDialog.getDirectory() + fileDialog.getFile();

        if (fileDialog.getFile() == null) {
            return;
        }
        fileDialog.dispose();
        try {
            File selectedFile = new File(fileDirectory);

            Image image = ImageIO.read(selectedFile);

            Image resizedImage = a_.scaleImage(image, maxImageWidth, maxImageHeight);

            File resizedImageFile = new File(System.getProperty("user.dir") + "/bin.png");
            ImageIO.write(a_.toBufferedImage(resizedImage), "png", resizedImageFile);

            gambarFile = resizedImageFile;
            jLabel1.setIcon(new ImageIcon(resizedImage));
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        et_namaBarang.requestFocus();
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_clear;
    private javax.swing.JButton b_simpan;
    private javax.swing.JTextField et_deskripsi;
    private javax.swing.JTextField et_hargaBarang;
    private javax.swing.JTextField et_namaBarang;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
