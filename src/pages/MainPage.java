package pages;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.HakAkses;
import model.Pengguna;
import services.DBHelper;

/**
 *
 * @author a_lpha
 */
public class MainPage extends javax.swing.JFrame {

    private final Connection connection = DBHelper.getConnection();
    private final Pengguna pengguna;
    private final int idHakAkses;

    private int xMouse;
    private int yMouse;

    public static Component parent;

    public MainPage(int idPengguna) {
        parent = this;
        pengguna = new Pengguna().get(connection, idPengguna);

        idHakAkses = pengguna.getIdHakAkses();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();

        setNavigationColor();

        tv_title.setText("Selamat Datang, " + pengguna.getNamaLengkap());
        tv_namaPengguna.setText(pengguna.getNamaPengguna());
        tv_hakAkses.setText(new HakAkses().get(connection, idHakAkses).getNamaAkses());
    }

    // FUNCTION ================================================================
    private void loadContent(JPanel panel) {
        content.removeAll();
        content.add(panel);
        content.repaint();
        content.revalidate();
    }

    private void setNavigationColor() {
        Color inactiveBackgroundColor = new Color(0, 38, 70);
        Color inactiveTextColor = new Color(168, 168, 168);
        Color activeBackgroundColor = new Color(0, 70, 129);

        List<AbstractButton> listButtons = Collections.list(navGroup.getElements());

        listButtons.forEach((button) -> {
            if (button.isSelected()) {
                button.setForeground(Color.WHITE);
                button.setBackground(activeBackgroundColor);
            } else {
                button.setForeground(inactiveTextColor);
                button.setBackground(inactiveBackgroundColor);
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navGroup = new javax.swing.ButtonGroup();
        header = new javax.swing.JPanel();
        exit = new javax.swing.JButton();
        minimize = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tv_profile = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        nav_beranda = new javax.swing.JRadioButton();
        nav_entriMeja = new javax.swing.JRadioButton();
        nav_entriBarang = new javax.swing.JRadioButton();
        nav_entriOrder = new javax.swing.JRadioButton();
        nav_entriTransaksi = new javax.swing.JRadioButton();
        nav_laporan = new javax.swing.JRadioButton();
        tv_namaPengguna = new javax.swing.JLabel();
        tv_hakAkses = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tv_title = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        content = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Receptionist App");
        setUndecorated(true);
        setSize(new java.awt.Dimension(1080, 640));

        header.setBackground(new java.awt.Color(0, 22, 42));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        exit.setBackground(new java.awt.Color(0, 22, 42));
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/titlebutton-close.png"))); // NOI18N
        exit.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 12, 2, 12));
        exit.setContentAreaFilled(false);
        exit.setFocusPainted(false);
        exit.setOpaque(true);
        exit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/titlebutton-close-hover.png"))); // NOI18N
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        minimize.setBackground(new java.awt.Color(0, 22, 42));
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/titlebutton-minimize.png"))); // NOI18N
        minimize.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 12, 2, 12));
        minimize.setContentAreaFilled(false);
        minimize.setFocusPainted(false);
        minimize.setOpaque(true);
        minimize.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/titlebutton-minimize-hover.png"))); // NOI18N
        minimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("URW Gothic", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Receptionist App v0.2");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 0, 0, 0));

        jLabel3.setFont(new java.awt.Font("URW Gothic", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("a_");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 0, 0, 0));

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(minimize, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(minimize, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 24, 44));

        tv_profile.setFont(new java.awt.Font("URW Gothic", 1, 14)); // NOI18N
        tv_profile.setForeground(new java.awt.Color(255, 255, 255));
        tv_profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_account-circle.png"))); // NOI18N
        tv_profile.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        tv_profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tv_profileMouseClicked(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));

        nav_beranda.setBackground(new java.awt.Color(0, 38, 70));
        navGroup.add(nav_beranda);
        nav_beranda.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        nav_beranda.setForeground(new java.awt.Color(168, 168, 168));
        nav_beranda.setText("Beranda");
        nav_beranda.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 24, 8, 24));
        nav_beranda.setFocusPainted(false);
        nav_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_home-variant-outline.png"))); // NOI18N
        nav_beranda.setIconTextGap(16);
        nav_beranda.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_home-variant-outline_white.png"))); // NOI18N
        nav_beranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_berandaActionPerformed(evt);
            }
        });

        nav_entriMeja.setBackground(new java.awt.Color(0, 38, 70));
        navGroup.add(nav_entriMeja);
        nav_entriMeja.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        nav_entriMeja.setForeground(new java.awt.Color(168, 168, 168));
        nav_entriMeja.setText("Entri Meja");
        nav_entriMeja.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 24, 8, 24));
        nav_entriMeja.setFocusPainted(false);
        nav_entriMeja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_table-chair.png"))); // NOI18N
        nav_entriMeja.setIconTextGap(16);
        nav_entriMeja.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_table-chair_white.png"))); // NOI18N
        nav_entriMeja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_entriMejaActionPerformed(evt);
            }
        });

        nav_entriBarang.setBackground(new java.awt.Color(0, 38, 70));
        navGroup.add(nav_entriBarang);
        nav_entriBarang.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        nav_entriBarang.setForeground(new java.awt.Color(168, 168, 168));
        nav_entriBarang.setText("Entri Barang");
        nav_entriBarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 24, 8, 24));
        nav_entriBarang.setFocusPainted(false);
        nav_entriBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_package-variant.png"))); // NOI18N
        nav_entriBarang.setIconTextGap(16);
        nav_entriBarang.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_package-variant_white.png"))); // NOI18N
        nav_entriBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_entriBarangActionPerformed(evt);
            }
        });

        nav_entriOrder.setBackground(new java.awt.Color(0, 38, 70));
        navGroup.add(nav_entriOrder);
        nav_entriOrder.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        nav_entriOrder.setForeground(new java.awt.Color(168, 168, 168));
        nav_entriOrder.setText("Entri Order");
        nav_entriOrder.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 24, 8, 24));
        nav_entriOrder.setFocusPainted(false);
        nav_entriOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_file-document-edit-outline.png"))); // NOI18N
        nav_entriOrder.setIconTextGap(16);
        nav_entriOrder.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_file-document-edit-outline_white.png"))); // NOI18N
        nav_entriOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_entriOrderActionPerformed(evt);
            }
        });

        nav_entriTransaksi.setBackground(new java.awt.Color(0, 38, 70));
        navGroup.add(nav_entriTransaksi);
        nav_entriTransaksi.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        nav_entriTransaksi.setForeground(new java.awt.Color(168, 168, 168));
        nav_entriTransaksi.setText("Entri Transaksi");
        nav_entriTransaksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 24, 8, 24));
        nav_entriTransaksi.setFocusPainted(false);
        nav_entriTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_cash-register.png"))); // NOI18N
        nav_entriTransaksi.setIconTextGap(16);
        nav_entriTransaksi.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_cash-register_white.png"))); // NOI18N
        nav_entriTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_entriTransaksiActionPerformed(evt);
            }
        });

        nav_laporan.setBackground(new java.awt.Color(0, 38, 70));
        navGroup.add(nav_laporan);
        nav_laporan.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        nav_laporan.setForeground(new java.awt.Color(168, 168, 168));
        nav_laporan.setText("Laporan");
        nav_laporan.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 24, 8, 24));
        nav_laporan.setFocusPainted(false);
        nav_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_file-chart.png"))); // NOI18N
        nav_laporan.setIconTextGap(16);
        nav_laporan.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_file-chart_white.png"))); // NOI18N
        nav_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_laporanActionPerformed(evt);
            }
        });

        tv_namaPengguna.setFont(new java.awt.Font("URW Gothic", 1, 18)); // NOI18N
        tv_namaPengguna.setForeground(new java.awt.Color(255, 255, 255));
        tv_namaPengguna.setText("a_lpha");

        tv_hakAkses.setFont(new java.awt.Font("Raleway", 0, 12)); // NOI18N
        tv_hakAkses.setForeground(new java.awt.Color(255, 255, 255));
        tv_hakAkses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_shield-check_1.png"))); // NOI18N
        tv_hakAkses.setText("Admin");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nav_beranda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nav_entriMeja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nav_entriBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nav_entriOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nav_entriTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nav_laporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 33, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tv_profile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tv_namaPengguna)
                            .addComponent(tv_hakAkses))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tv_profile)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tv_namaPengguna)
                        .addGap(0, 0, 0)
                        .addComponent(tv_hakAkses)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(nav_beranda, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nav_entriMeja, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nav_entriBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nav_entriOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nav_entriTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nav_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(252, Short.MAX_VALUE))
        );

        nav_beranda.setVisible(pengguna.getIdHakAkses() == 1);
        nav_beranda.setVisible(pengguna.getIdHakAkses() == 1);
        nav_beranda.setVisible(pengguna.getIdHakAkses() == 1);
        nav_beranda.setVisible(pengguna.getIdHakAkses() == 1);
        nav_beranda.setVisible(pengguna.getIdHakAkses() == 1);
        nav_beranda.setVisible(pengguna.getIdHakAkses() == 1);

        jPanel1.setBackground(new java.awt.Color(0, 38, 70));

        tv_title.setFont(new java.awt.Font("Raleway", 1, 36)); // NOI18N
        tv_title.setForeground(new java.awt.Color(255, 255, 255));
        tv_title.setText("Selamat Siang, Rizal!");

        jButton1.setBackground(new java.awt.Color(0, 38, 70));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_logout.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(tv_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(tv_title))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, 1111, Short.MAX_VALUE)))
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        setSize(new java.awt.Dimension(1366, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void minimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeActionPerformed
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_minimizeActionPerformed

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void tv_profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tv_profileMouseClicked

    }//GEN-LAST:event_tv_profileMouseClicked

    private void nav_berandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_berandaActionPerformed

    }//GEN-LAST:event_nav_berandaActionPerformed

    private void nav_entriMejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_entriMejaActionPerformed

    }//GEN-LAST:event_nav_entriMejaActionPerformed

    private void nav_entriBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_entriBarangActionPerformed

    }//GEN-LAST:event_nav_entriBarangActionPerformed

    private void nav_entriOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_entriOrderActionPerformed

    }//GEN-LAST:event_nav_entriOrderActionPerformed

    private void nav_entriTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_entriTransaksiActionPerformed

    }//GEN-LAST:event_nav_entriTransaksiActionPerformed

    private void nav_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_laporanActionPerformed

    }//GEN-LAST:event_nav_laporanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin keluar?", "Keluar?", JOptionPane.YES_NO_OPTION);
        if (dialog == 0) {
            dispose();
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new MainPage(1).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    private javax.swing.JButton exit;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton minimize;
    private javax.swing.ButtonGroup navGroup;
    private javax.swing.JRadioButton nav_beranda;
    private javax.swing.JRadioButton nav_entriBarang;
    private javax.swing.JRadioButton nav_entriMeja;
    private javax.swing.JRadioButton nav_entriOrder;
    private javax.swing.JRadioButton nav_entriTransaksi;
    private javax.swing.JRadioButton nav_laporan;
    private javax.swing.JLabel tv_hakAkses;
    private javax.swing.JLabel tv_namaPengguna;
    private javax.swing.JLabel tv_profile;
    private javax.swing.JLabel tv_title;
    // End of variables declaration//GEN-END:variables
}
