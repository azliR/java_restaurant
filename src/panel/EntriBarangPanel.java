package panel;

import common.RoundedBorder;
import common.RoundedButton;
import common.TemplateBarang;
import common.TemplatePesanan;
import common.a_;
import common.a_ScrollPane;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.Barang;
import model.DetailPenjualan;
import model.EntriMeja;
import model.JenisBarang;
import model.Penjualan;
import model.Pesanan;
import model.Varian;
import pages.MainPage;
import styles.Colors;

/**
 *
 * @author a_lpha
 */
public class EntriBarangPanel extends javax.swing.JPanel {

    private List<Barang> barangs = new ArrayList<>();
    private final List<TemplateBarang> templateBarangs = new ArrayList<>();

    public List<Pesanan> pesanans = new ArrayList<>();

    private final int circleRadius = 64;
    private final int buttonRadius = 8;

    private final int padding = 16;

    public final EntriMeja entriMeja;
    private Barang selectedBarang;

    private GridLayout gridLayout;
    private final MainPage context;
    private final Connection connection;

    public EntriBarangPanel(MainPage context, Connection connection) {
        this.context = context;
        this.connection = connection;
        this.entriMeja = null;

        initComponents();
        init();
    }

    public EntriBarangPanel(MainPage context, Connection connection, EntriMeja entriMeja) {
        this.context = context;
        this.connection = connection;
        this.entriMeja = entriMeja;

        initComponents();
        init();
    }

    private void init() {
        gridLayout = new GridLayout(0, 2);
        gridLayout.setHgap(12);
        gridLayout.setVgap(12);
        entriBarangPanel.setLayout(gridLayout);

        entriBarangScroll.getVerticalScrollBar().setUnitIncrement(14);
        daftarPesananScroll.getVerticalScrollBar().setUnitIncrement(14);

        if (entriMeja != null) {
            tv_nomorMeja.setText(String.valueOf(entriMeja.getNomorMeja()));
        }

        detailBarangPanel.setVisible(false);
        detailPesananPanel.setVisible(false);
        b_kurang.setVisible(entriMeja != null);
        b_tambah.setVisible(entriMeja != null);
        b_pesan.setVisible(entriMeja != null);
        et_jumlah.setVisible(entriMeja != null);
        context.b_keranjang.setText("Rp. 0");

        loadBarang(new Barang().get(connection));
    }

    public void loadBarang(List<Barang> barangs) {
        entriBarangPanel.removeAll();
        templateBarangs.clear();
        this.barangs = barangs;

        if (detailBarangPanel.isVisible()) {
            detailBarangPanel.setVisible(false);
        }
        if (detailPesananPanel.isVisible()) {
            detailPesananPanel.setVisible(false);
        }

        final int width = context.content.getWidth() / 2 - padding;

        barangs.forEach((_barang) -> {
            TemplateBarang templateBarang = new TemplateBarang(connection, this, _barang);
            templateBarang.setPreferredSize(new Dimension(width, (int) templateBarang.getPreferredSize().getHeight()));

            templateBarangs.add(templateBarang);
            entriBarangPanel.add(templateBarang);
        });
        gridLayout.setColumns(2);
        entriBarangPanel.revalidate();
    }

    public void addToCart(Pesanan pesanan) {
        pesanans.add(pesanan);

        TemplatePesanan templatePesanan = new TemplatePesanan(this, connection, pesanan);
        int width = tv_daftarPesananTitle.getWidth() - 8;
        int height = templatePesanan.getPreferredSize().height;
        templatePesanan.setPreferredSize(new Dimension(width, height));
        daftarPesananPanel.add(templatePesanan);

        b_pesan.setText("");
        b_pesan.setEnabled(false);
        b_pesan.setBackground(Colors.primaryColor);
        b_pesan.setBorder(new RoundedBorder(buttonRadius));

        int total = 0;
        total = pesanans.stream().map((_pesanan) -> _pesanan.getHargaBarang() * _pesanan.getJumlahBarang()).map((subTotal) -> subTotal).reduce(total, Integer::sum);
        context.b_keranjang.setText("Rp. " + a_.convertCurrency(total));
    }

    public void removeFromCart(TemplatePesanan templatePesanan, Pesanan pesanan) {
        pesanans.remove(pesanan);
        daftarPesananPanel.remove(templatePesanan);

        int total = 0;
        total = pesanans.stream().map((_pesanan) -> _pesanan.getHargaBarang() * _pesanan.getJumlahBarang()).map((subTotal) -> subTotal).reduce(total, Integer::sum);
        context.b_keranjang.setText("Rp. " + a_.convertCurrency(total));
    }

    public void showDetailBarang(TemplateBarang templateBarang, Barang barang) {
        selectedBarang = barang;

        final int width = context.content.getWidth() - detailBarangPanel.getPreferredSize().width - padding;
        final int height = templateBarang.getHeight();
        final int itemPosition[] = {0};
        final boolean isSucceed[] = {false};

        templateBarangs.forEach(((_templateBarang) -> {
            if (_templateBarang == templateBarang) {
                _templateBarang.setSelected(true);
                isSucceed[0] = true;

            } else {
                _templateBarang.setSelected(false);
            }
            if (!detailBarangPanel.isVisible() && !detailPesananPanel.isVisible()) {
                _templateBarang.setPreferredSize(new Dimension(width, _templateBarang.getHeight()));
                _templateBarang.revalidate();
            }
            if (!isSucceed[0]) {
                itemPosition[0]++;
            }
        }));

        setScrollBar(itemPosition[0], height);

        if (!detailBarangPanel.isVisible()) {
            detailBarangPanel.setVisible(true);
            gridLayout.setColumns(1);
            entriBarangPanel.revalidate();
        }

        if (detailPesananPanel.isVisible()) {
            detailPesananPanel.setVisible(false);
        }

        if (isOrdered()) {
            b_pesan.setText("");
            b_pesan.setEnabled(false);
            b_pesan.setBackground(Colors.primaryColor);
            b_pesan.setBorder(new RoundedBorder(buttonRadius));
        } else {
            b_pesan.setText("Rp. " + a_.convertCurrency(barang.getHarga()));
            b_pesan.setEnabled(true);
            b_pesan.setBackground(Colors.accentColor);
            b_pesan.setBorder(null);
        }

        Image image = new ImageIcon(barang.getGambar()).getImage();
        BufferedImage bufferedImage = a_.toBufferedImage(image, new Rectangle(220, 140));

        iv_gambarBarang.setIcon(new ImageIcon(a_.convertRoundedImage(bufferedImage, 16)));
        tv_namaBarangSubTitle.setText(("NAMA " + new JenisBarang().get(connection, barang.getIdJenis()).getNamaJenis()).toUpperCase());
        tv_namaBarang.setText("<html>" + barang.getNamaBarang() + "</html>");
        et_jumlah.setText("1");

        List<Varian> varians = new Varian().getByIdBarang(connection, barang.getId());
        DefaultComboBoxModel<Varian> comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addAll(varians);

        cb_varian.setVisible(!varians.isEmpty());
        tv_varian.setVisible(!varians.isEmpty());
        s_varian.setVisible(!varians.isEmpty());

        if (!varians.isEmpty()) {
            cb_varian.setModel(comboBoxModel);
            comboBoxModel.setSelectedItem(varians.get(0));
        }
    }

    private void hideDetailBarang() {
        selectedBarang = null;

        detailBarangPanel.setVisible(false);
        gridLayout.setColumns(2);
        entriBarangPanel.revalidate();

        final int width = context.content.getWidth() / 2 - padding;

        templateBarangs.forEach(((_templateBarang) -> {
            _templateBarang.setSelected(false);
            _templateBarang.setPreferredSize(new Dimension(width, _templateBarang.getHeight()));
            _templateBarang.revalidate();
        }));

        resetScrollBar();
    }

    public void showDetailPesanan() {
        final int width = context.content.getWidth() - detailPesananPanel.getPreferredSize().width - padding;

        templateBarangs.forEach(((_templateBarang) -> {
            if (detailBarangPanel.isVisible()) {
                _templateBarang.setSelected(false);
            }
            if (!detailBarangPanel.isVisible() && !detailPesananPanel.isVisible()) {
                _templateBarang.setPreferredSize(new Dimension(width, _templateBarang.getHeight()));
                _templateBarang.revalidate();
            }
        }));

        int position = entriBarangScroll.getVerticalScrollBar().getValue();
        setScrollBar(position, 2);

        if (!detailBarangPanel.isVisible() && !detailPesananPanel.isVisible()) {
            gridLayout.setColumns(1);
            entriBarangPanel.revalidate();
        }
        if (detailBarangPanel.isVisible()) {
            detailBarangPanel.setVisible(false);
        }
        if (!detailPesananPanel.isVisible()) {
            detailPesananPanel.setVisible(true);
        }

        selectedBarang = null;
    }

    private void hideDetailPesanan() {
        detailPesananPanel.setVisible(false);
        gridLayout.setColumns(2);
        entriBarangPanel.revalidate();

        final int width = context.content.getWidth() / 2 - padding;

        templateBarangs.forEach(((_templateBarang) -> {
            _templateBarang.setPreferredSize(new Dimension(width, _templateBarang.getHeight()));
            _templateBarang.revalidate();
        }));

        resetScrollBar();
    }

    private boolean isOrdered() {
        final boolean[] isOrdered = {false};

        pesanans.forEach(((_pesanan) -> {
            if (_pesanan.getIdBarang() == selectedBarang.getId()) {
                isOrdered[0] = true;
            }
        }));
        return isOrdered[0];
    }

    private void resetScrollBar() {
        int position = entriBarangScroll.getVerticalScrollBar().getValue();
        entriBarangScroll.getVerticalScrollBar().setValue(position / 2);
    }

    private void setScrollBar(int itemCount, int height) {
        if (!detailBarangPanel.isVisible() && !detailPesananPanel.isVisible()) {
            Timer timer = new Timer(1, (ActionEvent ae) -> {
                entriBarangScroll.getVerticalScrollBar().setValue(itemCount * height);
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        entriBarangScroll = new common.a_ScrollPane(jPanel1);
        jPanel1 = new javax.swing.JPanel();
        entriBarangPanel = new javax.swing.JPanel();
        detailPesananPanel = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        tv_daftarPesananTitle = new javax.swing.JLabel();
        tv_nomorMeja = new javax.swing.JLabel();
        b_konfirmasiPesanan = new RoundedButton(buttonRadius);
        jSeparator32 = new javax.swing.JSeparator();
        b_closeDetailPesanan = new javax.swing.JButton();
        daftarPesananScroll = new a_ScrollPane(jPanel3);
        jPanel3 = new javax.swing.JPanel();
        daftarPesananPanel = new javax.swing.JPanel();
        detailBarangPanel = new javax.swing.JPanel();
        tv_detailTitle = new javax.swing.JLabel();
        iv_gambarBarang = new javax.swing.JLabel();
        tv_namaBarang = new javax.swing.JLabel();
        b_kurang = new RoundedButton(circleRadius);
        et_jumlah = new javax.swing.JTextField();
        b_tambah = new RoundedButton(circleRadius);
        b_pesan = new RoundedButton(buttonRadius);
        tv_namaBarangSubTitle = new javax.swing.JLabel();
        tv_varian = new javax.swing.JLabel();
        s_varian = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        b_closeDetailBarang = new javax.swing.JButton();
        cb_varian = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(255, 255, 255));

        entriBarangScroll.setBackground(new java.awt.Color(255, 255, 255));
        entriBarangScroll.setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 0, 8));

        entriBarangPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout entriBarangPanelLayout = new javax.swing.GroupLayout(entriBarangPanel);
        entriBarangPanel.setLayout(entriBarangPanelLayout);
        entriBarangPanelLayout.setHorizontalGroup(
            entriBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        entriBarangPanelLayout.setVerticalGroup(
            entriBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(entriBarangPanel);

        entriBarangScroll.setViewportView(jPanel1);

        detailPesananPanel.setBackground(new java.awt.Color(255, 255, 255));
        detailPesananPanel.setMinimumSize(new java.awt.Dimension(360, 360));

        jLabel41.setFont(new java.awt.Font("Google Sans", 0, 22)); // NOI18N
        jLabel41.setForeground(Colors.blackTextColor);
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_cart_grey.png"))); // NOI18N
        jLabel41.setText("Keranjang");
        jLabel41.setIconTextGap(16);

        jLabel42.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        jLabel42.setForeground(Colors.greyTextColor);
        jLabel42.setText("NO. MEJA");

        tv_daftarPesananTitle.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        tv_daftarPesananTitle.setForeground(Colors.greyTextColor);
        tv_daftarPesananTitle.setText("DAFTAR PESANAN");

        tv_nomorMeja.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_nomorMeja.setForeground(Colors.blackTextColor);
        tv_nomorMeja.setText("01");

        b_konfirmasiPesanan.setBackground(Colors.accentColor);
        b_konfirmasiPesanan.setFont(new java.awt.Font("Product Sans Medium", 0, 16)); // NOI18N
        b_konfirmasiPesanan.setForeground(new java.awt.Color(255, 255, 255));
        b_konfirmasiPesanan.setText("Konfirmasi Pesanan");
        b_konfirmasiPesanan.setBorder(null);
        b_konfirmasiPesanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_konfirmasiPesanan.setFocusPainted(false);
        b_konfirmasiPesanan.setIconTextGap(8);
        b_konfirmasiPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_konfirmasiPesananActionPerformed(evt);
            }
        });

        jSeparator32.setForeground(new java.awt.Color(218, 220, 224));

        b_closeDetailPesanan.setBackground(new java.awt.Color(255, 255, 255));
        b_closeDetailPesanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_close_grey.png"))); // NOI18N
        b_closeDetailPesanan.setBorder(null);
        b_closeDetailPesanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_closeDetailPesanan.setFocusPainted(false);
        b_closeDetailPesanan.setOpaque(false);
        b_closeDetailPesanan.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_close.png"))); // NOI18N
        b_closeDetailPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_closeDetailPesananActionPerformed(evt);
            }
        });

        daftarPesananScroll.setBackground(new java.awt.Color(255, 255, 255));
        daftarPesananScroll.setBorder(null);
        daftarPesananScroll.setOpaque(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 0, 0));

        daftarPesananPanel.setBackground(new java.awt.Color(255, 255, 255));
        daftarPesananPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 2));
        jPanel3.add(daftarPesananPanel);

        daftarPesananScroll.setViewportView(jPanel3);

        javax.swing.GroupLayout detailPesananPanelLayout = new javax.swing.GroupLayout(detailPesananPanel);
        detailPesananPanel.setLayout(detailPesananPanelLayout);
        detailPesananPanelLayout.setHorizontalGroup(
            detailPesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPesananPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(detailPesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(daftarPesananScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(b_konfirmasiPesanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(detailPesananPanelLayout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tv_nomorMeja))
                    .addComponent(jSeparator32)
                    .addGroup(detailPesananPanelLayout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_closeDetailPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tv_daftarPesananTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        detailPesananPanelLayout.setVerticalGroup(
            detailPesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPesananPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(detailPesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_closeDetailPesanan, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(detailPesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(tv_nomorMeja))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tv_daftarPesananTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(daftarPesananScroll)
                .addGap(18, 18, 18)
                .addComponent(b_konfirmasiPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        detailBarangPanel.setBackground(new java.awt.Color(255, 255, 255));

        tv_detailTitle.setFont(new java.awt.Font("Google Sans", 0, 22)); // NOI18N
        tv_detailTitle.setForeground(Colors.blackTextColor);
        tv_detailTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_information.png"))); // NOI18N
        tv_detailTitle.setText("Detail Barang");
        tv_detailTitle.setIconTextGap(16);

        iv_gambarBarang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iv_gambarBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/a_ logo.png"))); // NOI18N
        iv_gambarBarang.setMaximumSize(new java.awt.Dimension(260, 160));

        tv_namaBarang.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_namaBarang.setForeground(Colors.blackTextColor);
        tv_namaBarang.setText("Coca Cola Zero Sugar Vanilla");

        b_kurang.setBackground(Colors.accentColor);
        b_kurang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_minus.png"))); // NOI18N
        b_kurang.setBorder(null);
        b_kurang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_kurang.setFocusPainted(false);
        b_kurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_kurangActionPerformed(evt);
            }
        });

        et_jumlah.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        et_jumlah.setForeground(Colors.blackTextColor);
        et_jumlah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        et_jumlah.setText("1");
        et_jumlah.setBorder(new RoundedBorder(buttonRadius));

        b_tambah.setBackground(Colors.accentColor);
        b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_plus.png"))); // NOI18N
        b_tambah.setBorder(null);
        b_tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_tambah.setFocusPainted(false);
        b_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_tambahActionPerformed(evt);
            }
        });

        b_pesan.setBackground(Colors.accentColor);
        b_pesan.setFont(new java.awt.Font("Product Sans Medium", 0, 16)); // NOI18N
        b_pesan.setForeground(new java.awt.Color(255, 255, 255));
        b_pesan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_cart-plus.png"))); // NOI18N
        b_pesan.setText("Pesan Barang");
        b_pesan.setBorder(null);
        b_pesan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_pesan.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_check_accent.png"))); // NOI18N
        b_pesan.setFocusPainted(false);
        b_pesan.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        b_pesan.setIconTextGap(16);
        b_pesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_pesanActionPerformed(evt);
            }
        });

        tv_namaBarangSubTitle.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        tv_namaBarangSubTitle.setForeground(Colors.greyTextColor);
        tv_namaBarangSubTitle.setText("NAMA MAKANAN");

        tv_varian.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        tv_varian.setForeground(Colors.greyTextColor);
        tv_varian.setText("VARIAN");

        s_varian.setForeground(Colors.borderColor);

        jSeparator2.setForeground(Colors.borderColor);

        b_closeDetailBarang.setBackground(new java.awt.Color(255, 255, 255));
        b_closeDetailBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_close_grey.png"))); // NOI18N
        b_closeDetailBarang.setBorder(null);
        b_closeDetailBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_closeDetailBarang.setFocusPainted(false);
        b_closeDetailBarang.setOpaque(false);
        b_closeDetailBarang.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_close.png"))); // NOI18N
        b_closeDetailBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_closeDetailBarangActionPerformed(evt);
            }
        });

        cb_varian.setBackground(new java.awt.Color(255, 255, 255));
        cb_varian.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cb_varian.setForeground(Colors.blackTextColor);
        cb_varian.setToolTipText("");
        cb_varian.setOpaque(false);

        javax.swing.GroupLayout detailBarangPanelLayout = new javax.swing.GroupLayout(detailBarangPanel);
        detailBarangPanel.setLayout(detailBarangPanelLayout);
        detailBarangPanelLayout.setHorizontalGroup(
            detailBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailBarangPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(detailBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s_varian)
                    .addComponent(tv_varian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tv_namaBarangSubTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(detailBarangPanelLayout.createSequentialGroup()
                        .addComponent(b_kurang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(et_jumlah)
                        .addGap(18, 18, 18)
                        .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(detailBarangPanelLayout.createSequentialGroup()
                        .addComponent(tv_detailTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_closeDetailBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2)
                    .addComponent(cb_varian, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(detailBarangPanelLayout.createSequentialGroup()
                        .addComponent(tv_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(iv_gambarBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_pesan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        detailBarangPanelLayout.setVerticalGroup(
            detailBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailBarangPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(detailBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tv_detailTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_closeDetailBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(iv_gambarBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tv_namaBarangSubTitle)
                .addGap(8, 8, 8)
                .addComponent(tv_namaBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tv_varian)
                .addGap(8, 8, 8)
                .addComponent(cb_varian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(s_varian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(detailBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(et_jumlah, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(b_kurang, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                    .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_pesan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jSeparator1.setForeground(Colors.borderColor);
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(entriBarangScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(detailPesananPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(detailBarangPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(entriBarangScroll)
            .addComponent(detailBarangPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(detailPesananPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void b_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_tambahActionPerformed
        if (!isOrdered()) {
            int jumlah = Integer.parseInt(et_jumlah.getText());
            int hargaBarang = selectedBarang.getHarga();

            jumlah++;
            hargaBarang *= jumlah;
            et_jumlah.setText(String.valueOf(jumlah));
            b_pesan.setText("Rp. " + a_.convertCurrency(hargaBarang));
        }
    }//GEN-LAST:event_b_tambahActionPerformed

    private void b_pesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_pesanActionPerformed
        Pesanan pesanan = new Pesanan();
        pesanan.setIdBarang(selectedBarang.getId());
        pesanan.setNamaBarang(selectedBarang.getNamaBarang());
        pesanan.setHargaBarang(selectedBarang.getHarga());
        pesanan.setJumlahBarang(Integer.parseInt(et_jumlah.getText()));
        addToCart(pesanan);
    }//GEN-LAST:event_b_pesanActionPerformed

    private void b_konfirmasiPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_konfirmasiPesananActionPerformed
        if (pesanans.isEmpty()) {
            JOptionPane.showMessageDialog(context, "Keranjang Anda masih kosong.\nAyo tambahkan beberapa barang!");
            return;
        }

        int dialog = JOptionPane.showConfirmDialog(context, "Konfirmasi Pesanan?", "Konfirmasi?", JOptionPane.YES_NO_OPTION);

        if (dialog != 0) {
            return;
        }

        int total = 0;
        total = pesanans.stream().map((_pesanan) -> _pesanan.getHargaBarang() * _pesanan.getJumlahBarang()).map((subTotal) -> subTotal).reduce(total, Integer::sum);

        Penjualan penjualan = new Penjualan();
        penjualan.setIdMeja(entriMeja.getId());
        penjualan.setIdPengguna(context.pengguna.getId());
        penjualan.setIdStatus(1);
        penjualan.setAtasNama(entriMeja.getAtasNama());
        penjualan.setTotal(total);

        int idPenjualan = penjualan.insert(connection);

        if (idPenjualan != -1) {
            boolean isSucceed = true;
            for (Pesanan _pesanan : pesanans) {
                DetailPenjualan detailPenjualan = new DetailPenjualan();
                detailPenjualan.setIdBarang(_pesanan.getIdBarang());
                detailPenjualan.setIdPenjualan(idPenjualan);
                detailPenjualan.setJumlahBarang(_pesanan.getJumlahBarang());

                if (!detailPenjualan.insert(connection)) {
                    isSucceed = false;
                }
            }

            if (isSucceed) {
                if (entriMeja.update(connection)) {
                    if (context.out != null) {
                        context.out.println("PESANAN_ADDED" + context.pengguna.getId());
                    }

                    a_.showDialog(a_.DialogType.INSERT_SUCCESS);

                    pesanans.clear();
                    context.loadContent(new EntriPenjualanPanel(context, connection));
                    context.nav_entriOrder.setSelected(true);
                    context.setNavigationColor();
                } else {
                    a_.showDialog(a_.DialogType.INSERT_ERROR);
                }

            } else {
                a_.showDialog(a_.DialogType.INSERT_ERROR);
            }
        } else {
            a_.showDialog(a_.DialogType.INSERT_ERROR);
        }
    }//GEN-LAST:event_b_konfirmasiPesananActionPerformed

    private void b_kurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_kurangActionPerformed
        if (!isOrdered()) {
            int jumlah = Integer.parseInt(et_jumlah.getText());
            int hargaBarang = selectedBarang.getHarga();

            if (jumlah > 1) {
                jumlah--;
                hargaBarang *= jumlah;
                et_jumlah.setText(String.valueOf(jumlah));
                b_pesan.setText("Rp. " + a_.convertCurrency(hargaBarang));
            }
        }
    }//GEN-LAST:event_b_kurangActionPerformed

    private void b_closeDetailBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_closeDetailBarangActionPerformed
        hideDetailBarang();
    }//GEN-LAST:event_b_closeDetailBarangActionPerformed

    private void b_closeDetailPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_closeDetailPesananActionPerformed
        hideDetailPesanan();
    }//GEN-LAST:event_b_closeDetailPesananActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_closeDetailBarang;
    private javax.swing.JButton b_closeDetailPesanan;
    private javax.swing.JButton b_konfirmasiPesanan;
    private javax.swing.JButton b_kurang;
    private javax.swing.JButton b_pesan;
    private javax.swing.JButton b_tambah;
    private javax.swing.JComboBox<Varian> cb_varian;
    private javax.swing.JPanel daftarPesananPanel;
    private javax.swing.JScrollPane daftarPesananScroll;
    private javax.swing.JPanel detailBarangPanel;
    public javax.swing.JPanel detailPesananPanel;
    private javax.swing.JPanel entriBarangPanel;
    public javax.swing.JScrollPane entriBarangScroll;
    private javax.swing.JTextField et_jumlah;
    private javax.swing.JLabel iv_gambarBarang;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator32;
    private javax.swing.JSeparator s_varian;
    private javax.swing.JLabel tv_daftarPesananTitle;
    private javax.swing.JLabel tv_detailTitle;
    private javax.swing.JLabel tv_namaBarang;
    private javax.swing.JLabel tv_namaBarangSubTitle;
    private javax.swing.JLabel tv_nomorMeja;
    private javax.swing.JLabel tv_varian;
    // End of variables declaration//GEN-END:variables
}
