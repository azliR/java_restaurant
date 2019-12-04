package pages;

import common.RoundedBorder;
import common.RoundedButton;
import common.a_Chip;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Barang;
import model.EntriMeja;
import model.HakAkses;
import model.JenisBarang;
import model.Pengguna;
import model.Penjualan;
import panel.BerandaPanel;
import panel.EntriBarangPanel;
import panel.EntriMejaPanel;
import panel.EntriPenjualanPanel;
import panel.EntriTransaksiPanel;
import panel.LaporanPanel;
import panel.TambahBarangPanel;
import services.DBHelper;
import styles.Colors;

/**
 *
 * @author a_lpha
 */
public class MainPage extends javax.swing.JFrame {

    public final Pengguna pengguna;
    private final Connection connection = DBHelper.getConnection();
    private final int idHakAkses;

    private int xMouse;
    private int yMouse;
    private final int searchRadius = 14;
    private final int circleRadius = 36;
    private final int navRadius = 52;

    private boolean isSearchFilled = false;

    private Component selectedComponent = new BerandaPanel();
    public static Component parent;

    private final String serverAddress = "localhost";
    public Scanner in;
    public PrintWriter out;

    public MainPage(int idPengguna) {
        parent = this;
        pengguna = new Pengguna().get(connection, idPengguna);
        idHakAkses = pengguna.idHakAkses;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();

        tv_namaPengguna.setText(pengguna.namaPengguna);
        tv_hakAkses.setText(new HakAkses().get(connection, idHakAkses).namaAkses);

        loadContent(new BerandaPanel());
    }

    public final void loadContent(Component panel) {
        boolean isCanceled = false;
        if (selectedComponent.getClass() == EntriBarangPanel.class) {
            if (((EntriBarangPanel) selectedComponent).pesanans.size() > 0) {
                int input = JOptionPane.showConfirmDialog(parent, "Terdapat barang yang sudah ditambahkan kedalam keranjang.\nYakin ingin membuangnya?", "Buang Pesanan?", JOptionPane.YES_NO_OPTION);
                isCanceled = input == 1;
            }
        }
        if (!isCanceled) {
            selectedComponent = panel;

            content.removeAll();
            content.add(panel);
            content.repaint();
            content.revalidate();

            setNavigationColor();

            chipsPanel.removeAll();
            chipsPanel.setVisible(true);

            b_keranjang.setVisible(panel.getClass() == EntriBarangPanel.class);

            if (panel.getClass() == EntriBarangPanel.class) {
                loadChipJenisBarang((EntriBarangPanel) panel);

            } else if (panel.getClass() == EntriMejaPanel.class) {
                loadChipStatusMeja((EntriMejaPanel) panel);

            } else {
                chipsPanel.setVisible(false);
            }
        } else {
            nav_entriBarang.setSelected(true);
        }
    }

    public void run() {
        try ( Socket socket = new Socket(serverAddress, 59001)) {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);

            while (in.hasNextLine()) {
                String line = in.nextLine();

                if (line.startsWith("SUBMIT_ID")) {
                    out.println(pengguna.id);

                } else if (line.startsWith("PESANAN_ADDED")) {
                    if (pengguna.id != Integer.parseInt(line.substring(13))) {
                        if (selectedComponent instanceof EntriPenjualanPanel) {
                            ((EntriPenjualanPanel) selectedComponent).loadPesanan(new Penjualan().get(connection));

                        } else {
                            int dialog = JOptionPane.showConfirmDialog(parent, "Terdapat pesanan baru tersedia. Ingin melihat detailnya?", "Pesanan Baru Tersedia", JOptionPane.YES_NO_OPTION);

                            if (dialog == 0) {
                                loadContent(new EntriPenjualanPanel(this, connection));
                                setNavigationColor();
                            } else {
                                nav_entriOrder.setForeground(Color.RED);
                            }
                        }
                    }
                }

            }
            out.close();
            in.close();
            socket.close();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(parent, "Aplikasi tidak dapat tersambung ke server. Silahkan cek sambungan Anda atau coba kembali dalam beberapa menit", "Tidak dapat tersambung ke server", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void setNavigationColor() {
        Color inactiveBackgroundColor = new Color(255, 255, 255);
        Color inactiveTextColor = Colors.greyTextColor;
        Color activeBackgroundColor = Colors.blueBackgroundColor;
        Color activeTextColor = Colors.accentColor;

        List<AbstractButton> listButtons = Collections.list(navGroup.getElements());

        listButtons.forEach((button) -> {
            if (!button.isSelected()) {
                button.setForeground(inactiveTextColor);
                button.setBackground(inactiveBackgroundColor);
                button.setBorder(BorderFactory.createMatteBorder(0, 36, 0, 0, Colors.primaryColor));

            } else {
                button.setForeground(activeTextColor);
                button.setBackground(activeBackgroundColor);
                button.setBorder(BorderFactory.createMatteBorder(0, 36, 0, 0, activeBackgroundColor));

                tv_title.setText(button.getText());
            }
        });
    }

    private void loadChipStatusMeja(EntriMejaPanel entriMejaPanel) {
        entriMejaPanel.removeAll();

        List<EntriMeja.GET_TYPE> entriMejas = new ArrayList<>();
        entriMejas.add(EntriMeja.GET_TYPE.SEMUA);
        entriMejas.add(EntriMeja.GET_TYPE.KOSONG);
        entriMejas.add(EntriMeja.GET_TYPE.TERISI);
        entriMejas.add(EntriMeja.GET_TYPE.DIPESAN);

        entriMejas.forEach((_statusMeja) -> {
            a_Chip chip = new a_Chip(_statusMeja.name());
            chipGroup.add(chip);
            if (_statusMeja == EntriMeja.GET_TYPE.SEMUA) {
                chipGroup.setSelected(chip.getModel(), true);
                setChipSelected(chip);
            }

            chip.addActionListener((ae) -> {
                chipGroup.setSelected(chip.getModel(), true);

                List<AbstractButton> chips = Collections.list(chipGroup.getElements());

                chips.forEach((_chip) -> {
                    setChipSelected((a_Chip) _chip);
                });
                entriMejaPanel.loadEntriMeja(new EntriMeja().get(connection, _statusMeja));
            });
            chipsPanel.add(chip);
        });
    }

    private void loadChipJenisBarang(EntriBarangPanel entriBarangPanel) {
        chipsPanel.removeAll();

        List<JenisBarang> jenisBarangs = new ArrayList<>();
        jenisBarangs.add(new JenisBarang(0, "Semua"));
        jenisBarangs.addAll(new JenisBarang().get(connection));
        jenisBarangs.forEach((_jenisBarang) -> {
            a_Chip chip = new a_Chip(_jenisBarang.namaJenis);
            chipGroup.add(chip);
            if (_jenisBarang.id == 0) {
                chipGroup.setSelected(chip.getModel(), true);
                setChipSelected(chip);
            }

            chip.addActionListener((ae) -> {
                chipGroup.setSelected(chip.getModel(), true);

                List<AbstractButton> chips = Collections.list(chipGroup.getElements());
                chips.forEach((_chip) -> {
                    setChipSelected((a_Chip) _chip);
                });

                if (_jenisBarang.id == 0) {
                    entriBarangPanel.loadBarang(new Barang().get(connection));
                } else {
                    entriBarangPanel.loadBarang(new Barang().getByJenis(connection, _jenisBarang.id));
                }
            });
            chipsPanel.add(chip);
        });
    }

    private void setChipSelected(a_Chip chip) {
        final Color activeTextColor = Colors.accentColor;
        final Color activeBackgroundColor = Colors.blueBackgroundColor;
        final Color inactiveTextColor = Colors.greyTextColor;
        final Color inactiveBackgroundColor = Colors.primaryColor;
        final int[] insets = {9, 12, 9, 12};

        chip.setBackground(chip.isSelected() ? activeBackgroundColor : inactiveBackgroundColor);
        chip.setForeground(chip.isSelected() ? activeTextColor : inactiveTextColor);
        chip.setBorder(new RoundedBorder(32, insets, chip.isSelected() ? activeBackgroundColor : Colors.borderColor));
    }

    @Override
    public void dispose() {
        try {
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navGroup = new javax.swing.ButtonGroup();
        chipGroup = new javax.swing.ButtonGroup();
        header = new javax.swing.JPanel();
        exit = new javax.swing.JButton();
        minimize = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tv_profile = new javax.swing.JLabel();
        nav_beranda = new common.a_SideNavigation(navRadius);
        nav_entriMeja = new common.a_SideNavigation(navRadius);
        nav_entriBarang = new common.a_SideNavigation(navRadius);
        nav_entriOrder = new common.a_SideNavigation(navRadius);
        nav_entriTransaksi = new common.a_SideNavigation(navRadius);
        nav_laporan = new common.a_SideNavigation(navRadius);
        tv_namaPengguna = new javax.swing.JLabel();
        tv_hakAkses = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        nav_entriMeja1 = new common.a_SideNavigation(navRadius);
        jSeparator2 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new common.RoundedPanel(searchRadius);
        jLabel1 = new javax.swing.JLabel();
        et_search = new javax.swing.JTextField();
        tv_title = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        b_keranjang = new RoundedButton(circleRadius);
        chipsPanel = new javax.swing.JPanel();
        content = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Receptionist App");
        setUndecorated(true);
        setSize(new java.awt.Dimension(1080, 640));

        header.setBackground(new java.awt.Color(255, 255, 255));
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

        exit.setBackground(new java.awt.Color(255, 255, 255));
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

        minimize.setBackground(new java.awt.Color(255, 255, 255));
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

        jLabel2.setBackground(new java.awt.Color(0, 24, 44));
        jLabel2.setFont(new java.awt.Font("Product Sans", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 24, 44));
        jLabel2.setText("Restaurant v0.62");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 0, 0, 0));

        jLabel3.setBackground(new java.awt.Color(0, 24, 44));
        jLabel3.setFont(new java.awt.Font("URW Gothic", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 24, 44));
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tv_profile.setFont(new java.awt.Font("URW Gothic", 1, 14)); // NOI18N
        tv_profile.setForeground(new java.awt.Color(0, 24, 44));
        tv_profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_account-circle.png"))); // NOI18N
        tv_profile.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        tv_profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tv_profileMouseClicked(evt);
            }
        });

        nav_beranda.setBackground(Colors.blueBackgroundColor);
        navGroup.add(nav_beranda);
        nav_beranda.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        nav_beranda.setForeground(Colors.accentColor);
        nav_beranda.setSelected(true);
        nav_beranda.setText("Beranda");
        nav_beranda.setBorder(null);
        nav_beranda.setBorderPainted(true);
        nav_beranda.setFocusPainted(false);
        nav_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_home-variant-outline_grey.png"))); // NOI18N
        nav_beranda.setIconTextGap(20);
        nav_beranda.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_home-variant.png"))); // NOI18N
        nav_beranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_berandaActionPerformed(evt);
            }
        });

        nav_entriMeja.setBackground(new java.awt.Color(255, 255, 255));
        navGroup.add(nav_entriMeja);
        nav_entriMeja.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        nav_entriMeja.setForeground(Colors.greyTextColor);
        nav_entriMeja.setText("Entri Meja");
        nav_entriMeja.setBorder(null);
        nav_entriMeja.setBorderPainted(true);
        nav_entriMeja.setFocusPainted(false);
        nav_entriMeja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_table-chair_grey.png"))); // NOI18N
        nav_entriMeja.setIconTextGap(20);
        nav_entriMeja.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_table-chair.png"))); // NOI18N
        nav_entriMeja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_entriMejaActionPerformed(evt);
            }
        });

        nav_entriBarang.setBackground(new java.awt.Color(255, 255, 255));
        navGroup.add(nav_entriBarang);
        nav_entriBarang.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        nav_entriBarang.setForeground(Colors.greyTextColor);
        nav_entriBarang.setText("Entri Barang");
        nav_entriBarang.setBorder(null);
        nav_entriBarang.setBorderPainted(true);
        nav_entriBarang.setFocusPainted(false);
        nav_entriBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_food_grey.png"))); // NOI18N
        nav_entriBarang.setIconTextGap(20);
        nav_entriBarang.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_food.png"))); // NOI18N
        nav_entriBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_entriBarangActionPerformed(evt);
            }
        });

        nav_entriOrder.setBackground(new java.awt.Color(255, 255, 255));
        navGroup.add(nav_entriOrder);
        nav_entriOrder.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        nav_entriOrder.setForeground(Colors.greyTextColor);
        nav_entriOrder.setText("Entri Order");
        nav_entriOrder.setBorder(null);
        nav_entriOrder.setBorderPainted(true);
        nav_entriOrder.setFocusPainted(false);
        nav_entriOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_file-document-edit-outline_grey.png"))); // NOI18N
        nav_entriOrder.setIconTextGap(20);
        nav_entriOrder.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_file-document-edit.png"))); // NOI18N
        nav_entriOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_entriOrderActionPerformed(evt);
            }
        });

        nav_entriTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        navGroup.add(nav_entriTransaksi);
        nav_entriTransaksi.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        nav_entriTransaksi.setForeground(Colors.greyTextColor);
        nav_entriTransaksi.setText("Entri Transaksi");
        nav_entriTransaksi.setBorder(null);
        nav_entriTransaksi.setBorderPainted(true);
        nav_entriTransaksi.setFocusPainted(false);
        nav_entriTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_cash-register_grey.png"))); // NOI18N
        nav_entriTransaksi.setIconTextGap(20);
        nav_entriTransaksi.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_cash-register.png"))); // NOI18N
        nav_entriTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_entriTransaksiActionPerformed(evt);
            }
        });

        nav_laporan.setBackground(new java.awt.Color(255, 255, 255));
        navGroup.add(nav_laporan);
        nav_laporan.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        nav_laporan.setForeground(Colors.greyTextColor);
        nav_laporan.setText("Laporan");
        nav_laporan.setBorder(null);
        nav_laporan.setBorderPainted(true);
        nav_laporan.setFocusPainted(false);
        nav_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_chart-line_grey.png"))); // NOI18N
        nav_laporan.setIconTextGap(20);
        nav_laporan.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_chart-areaspline.png"))); // NOI18N
        nav_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_laporanActionPerformed(evt);
            }
        });

        tv_namaPengguna.setBackground(new java.awt.Color(255, 255, 255));
        tv_namaPengguna.setFont(new java.awt.Font("URW Gothic", 1, 18)); // NOI18N
        tv_namaPengguna.setForeground(new java.awt.Color(0, 24, 44));
        tv_namaPengguna.setText("a_lpha");

        tv_hakAkses.setBackground(new java.awt.Color(255, 255, 255));
        tv_hakAkses.setFont(new java.awt.Font("Raleway", 0, 12)); // NOI18N
        tv_hakAkses.setForeground(new java.awt.Color(0, 24, 44));
        tv_hakAkses.setText("Admin");

        jSeparator1.setForeground(Colors.borderColor);

        nav_entriMeja1.setBackground(new java.awt.Color(255, 255, 255));
        navGroup.add(nav_entriMeja1);
        nav_entriMeja1.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        nav_entriMeja1.setForeground(Colors.greyTextColor);
        nav_entriMeja1.setText("Tambah Barang");
        nav_entriMeja1.setBorder(null);
        nav_entriMeja1.setBorderPainted(true);
        nav_entriMeja1.setFocusPainted(false);
        nav_entriMeja1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_table-chair_grey.png"))); // NOI18N
        nav_entriMeja1.setIconTextGap(20);
        nav_entriMeja1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_table-chair.png"))); // NOI18N
        nav_entriMeja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nav_entriMeja1ActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(Colors.borderColor);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_exit-to-app_grey.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_exit-to-app.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(nav_entriTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                        .addComponent(nav_entriOrder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nav_entriBarang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nav_entriMeja, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nav_beranda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nav_entriMeja1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(nav_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(tv_profile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tv_namaPengguna)
                    .addComponent(tv_hakAkses))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(tv_profile)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(tv_namaPengguna)
                            .addGap(0, 0, 0)
                            .addComponent(tv_hakAkses)))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(nav_beranda, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nav_entriMeja, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nav_entriBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nav_entriOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nav_entriTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav_entriMeja1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(231, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(241, 243, 244));

        jLabel1.setFont(new java.awt.Font("Product Sans Light", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(111, 112, 112));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_magnify.png"))); // NOI18N

        et_search.setBackground(new java.awt.Color(111, 112, 112));
        et_search.setFont(new java.awt.Font("Google Sans", 0, 17)); // NOI18N
        et_search.setForeground(new java.awt.Color(111, 112, 112));
        et_search.setText("Telusuri Pesanan");
        et_search.setBorder(null);
        et_search.setOpaque(false);
        et_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                et_searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                et_searchFocusLost(evt);
            }
        });
        et_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                et_searchActionPerformed(evt);
            }
        });
        et_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                et_searchKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(et_search, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(et_search, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tv_title.setFont(new java.awt.Font("Google Sans", 0, 18));
        tv_title.setText("Drive Saya");

        jSeparator4.setForeground(Colors.borderColor);

        b_keranjang.setBackground(Colors.blueBackgroundColor);
        b_keranjang.setFont(new java.awt.Font("Product Sans Medium", 0, 14)); // NOI18N
        b_keranjang.setForeground(Colors.accentColor);
        b_keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_cart-outline_accent.png"))); // NOI18N
        b_keranjang.setText("Rp. 0");
        b_keranjang.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 14));
        b_keranjang.setBorderPainted(false);
        b_keranjang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_keranjang.setFocusPainted(false);
        b_keranjang.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        b_keranjang.setOpaque(false);
        b_keranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_keranjangActionPerformed(evt);
            }
        });

        chipsPanel.setBackground(new java.awt.Color(255, 255, 255));
        chipsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tv_title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chipsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_keranjang)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 127, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chipsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tv_title, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(b_keranjang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
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
                    .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        loadContent(new BerandaPanel());
    }//GEN-LAST:event_nav_berandaActionPerformed

    private void nav_entriMejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_entriMejaActionPerformed
        loadContent(new EntriMejaPanel(this, connection));
    }//GEN-LAST:event_nav_entriMejaActionPerformed

    private void nav_entriBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_entriBarangActionPerformed
        loadContent(new EntriBarangPanel(this, connection));
    }//GEN-LAST:event_nav_entriBarangActionPerformed

    private void nav_entriOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_entriOrderActionPerformed
        loadContent(new EntriPenjualanPanel(this, connection));
    }//GEN-LAST:event_nav_entriOrderActionPerformed

    private void nav_entriTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_entriTransaksiActionPerformed
        loadContent(new EntriTransaksiPanel());
    }//GEN-LAST:event_nav_entriTransaksiActionPerformed

    private void nav_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_laporanActionPerformed
        loadContent(new LaporanPanel());
    }//GEN-LAST:event_nav_laporanActionPerformed

    private void et_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_et_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_et_searchActionPerformed

    private void et_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_et_searchFocusGained
        if (!isSearchFilled) {
            et_search.setText(null);
            et_search.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_et_searchFocusGained

    private void et_searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_et_searchFocusLost
        if (et_search.getText().isBlank()) {
            et_search.setText("Telusuri Pesanan");
            et_search.setForeground(new Color(111, 112, 112));

            isSearchFilled = false;
        } else {
            isSearchFilled = true;
        }
    }//GEN-LAST:event_et_searchFocusLost

    private void et_searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_et_searchKeyTyped
//        String cari = et_search.getText();
//        System.out.println(cari);
//        entriMejaPanel.removeAll();
//        entriMejaPanel.entriMejas.stream().filter((entriMeja) -> (String.valueOf(entriMeja.getNomorMeja()).toLowerCase().contains(cari))).forEachOrdered((entriMeja) -> {
//            entriMejaPanel.add(new TemplateEntriMeja(connection, entriMejaPanel, entriMeja));
//        });
//        entriMejaPanel.revalidate();
    }//GEN-LAST:event_et_searchKeyTyped

    private void nav_entriMeja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nav_entriMeja1ActionPerformed
        loadContent(new TambahBarangPanel(connection));
    }//GEN-LAST:event_nav_entriMeja1ActionPerformed

    private void b_keranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_keranjangActionPerformed
        EntriBarangPanel entriBarangPanel = (EntriBarangPanel) selectedComponent;
        if (entriBarangPanel.entriMeja != null) {
            entriBarangPanel.showDetailPesanan();
        } else {
            JOptionPane.showMessageDialog(parent, "Untuk menambahkan barang ke keranjang,\nsilahkan pilih meja terlebih dahulu");
        }

    }//GEN-LAST:event_b_keranjangActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin keluar?", "Keluar?", JOptionPane.YES_NO_OPTION);
        if (dialog == 0) {
            dispose();
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
    public javax.swing.JButton b_keranjang;
    private javax.swing.ButtonGroup chipGroup;
    private javax.swing.JPanel chipsPanel;
    public javax.swing.JPanel content;
    private javax.swing.JTextField et_search;
    private javax.swing.JButton exit;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton minimize;
    private javax.swing.ButtonGroup navGroup;
    private javax.swing.JRadioButton nav_beranda;
    public javax.swing.JRadioButton nav_entriBarang;
    private javax.swing.JRadioButton nav_entriMeja;
    private javax.swing.JRadioButton nav_entriMeja1;
    public javax.swing.JRadioButton nav_entriOrder;
    private javax.swing.JRadioButton nav_entriTransaksi;
    private javax.swing.JRadioButton nav_laporan;
    private javax.swing.JLabel tv_hakAkses;
    private javax.swing.JLabel tv_namaPengguna;
    private javax.swing.JLabel tv_profile;
    private javax.swing.JLabel tv_title;
    // End of variables declaration//GEN-END:variables
}
