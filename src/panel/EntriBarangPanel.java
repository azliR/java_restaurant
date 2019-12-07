package panel;

import common.RoundedBorder;
import common.RoundedButton;
import template.TemplateBarang;
import template.TemplatePesanan;
import common.a_;
import common.a_ScrollPane;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.Barang;
import model.DetailPenjualan;
import model.JenisBarang;
import model.Meja;
import model.Penjualan;
import model.Pesanan;
import model.Varian;
import pages.MainPage;
import styles.Colors;
import styles.Fonts;

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

    public final Meja meja;
    private Barang selectedBarang;

    private GridLayout gridLayout;
    private final MainPage context;
    private final Connection connection;

    public EntriBarangPanel(MainPage context, Connection connection) {
        this.context = context;
        this.connection = connection;
        this.meja = null;

        initComponents();
        init();
    }

    public EntriBarangPanel(MainPage context, Connection connection, Meja meja) {
        this.context = context;
        this.connection = connection;
        this.meja = meja;

        initComponents();
        init();
    }

    private void init() {
        gridLayout = new GridLayout(0, 2);
        gridLayout.setHgap(12);
        gridLayout.setVgap(12);
        entriBarangPanel.setLayout(gridLayout);

        if (meja != null) {
            tv_nomorMeja.setText(String.valueOf(meja.nomorMeja));
        }

        detailBarangPanel.setVisible(false);
        detailPesananPanel.setVisible(false);
        b_kurang.setVisible(meja != null);
        b_tambah.setVisible(meja != null);
        b_pesan.setVisible(meja != null);
        et_jumlah.setVisible(meja != null);
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
        int width = tv_daftarPesananTitle.getWidth() - 16;
        int height = templatePesanan.getPreferredSize().height;
        templatePesanan.setPreferredSize(new Dimension(width, height));
        daftarPesananPanel.add(templatePesanan);

        b_pesan.setText("");
        b_pesan.setEnabled(false);
        b_pesan.setBackground(Colors.primaryColor);
        b_pesan.setBorder(new RoundedBorder(buttonRadius));

        setTotal();
    }

    public void setTotal() {
        int total = 0;
        total = pesanans.stream().map((_pesanan) -> _pesanan.hargaBarang * _pesanan.jumlahBarang).map((subTotal) -> subTotal).reduce(total, Integer::sum);
        context.b_keranjang.setText("Rp. " + a_.convertCurrency(total));
    }

    public void removeFromCart(TemplatePesanan templatePesanan, Pesanan pesanan) {
        pesanans.remove(pesanan);
        daftarPesananPanel.remove(templatePesanan);

        int total = 0;
        total = pesanans.stream().map((_pesanan) -> _pesanan.hargaBarang * _pesanan.jumlahBarang).map((subTotal) -> subTotal).reduce(total, Integer::sum);
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
            if (!detailBarangPanel.isVisible()) {
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
            b_pesan.setText("Rp. " + a_.convertCurrency(barang.harga));
            b_pesan.setEnabled(true);
            b_pesan.setBackground(Colors.accentColor);
            b_pesan.setBorder(null);
        }

        final int maxWidth = 380;
        final int maxHeight = 221;
        final int borderRadius = 16;

        Image image = new ImageIcon(barang.gambar).getImage();
        Image scaledImage = a_.scaleImage(image, maxWidth, maxHeight);
        Image croppedImage = a_.cropImage(
                a_.toBufferedImage(new ImageIcon(scaledImage).getImage()),
                maxWidth,
                maxHeight);
        Image roundedImage = a_.convertRoundedImage(croppedImage, borderRadius);

        iv_gambarBarang.setIcon(new ImageIcon(roundedImage));
        tv_namaBarangSubTitle.setText(("NAMA " + new JenisBarang().get(connection, barang.idJenis).namaJenis).toUpperCase());
        tv_namaBarang.setText("<html>" + barang.namaBarang + "</html>");
        tv_deskripsi.setText("<html>" + barang.deskripsi + "</html>");;
        et_jumlah.setText("1");

        List<Varian> varians = new Varian().getByIdBarang(connection, barang.id);
        DefaultComboBoxModel<Varian> comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addAll(varians);

        cb_varian.setVisible(!varians.isEmpty());
        tv_varian.setVisible(!varians.isEmpty());
        s_varian.setVisible(!varians.isEmpty());

        tv_deskripsiSubTitle.setVisible(!barang.deskripsi.isBlank());
        tv_deskripsi.setVisible(!barang.deskripsi.isBlank());

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
            if (!detailPesananPanel.isVisible()) {
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
            if (_pesanan.idBarang == selectedBarang.id) {
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
        jSeparator1 = new javax.swing.JSeparator();
        detailBarangPanel = new javax.swing.JPanel();
        detailBarangScrol = new a_ScrollPane(jPanel6);
        jPanel6 = new javax.swing.JPanel();
        tv_deskripsiSubTitle = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        cb_varian = new javax.swing.JComboBox<>();
        tv_namaBarang = new javax.swing.JLabel();
        tv_deskripsi = new javax.swing.JLabel();
        s_varian = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        b_closeDetailBarang = new javax.swing.JButton();
        iv_gambarBarang = new javax.swing.JLabel();
        tv_namaBarangSubTitle = new javax.swing.JLabel();
        tv_varian = new javax.swing.JLabel();
        et_jumlah = new javax.swing.JTextField();
        b_kurang = new RoundedButton(circleRadius);
        b_pesan = new RoundedButton(buttonRadius);
        b_tambah = new RoundedButton(circleRadius);
        jSeparator3 = new javax.swing.JSeparator();

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
        b_konfirmasiPesanan.setFont(Fonts.PRODUCT_SANS_MEDIUM.deriveFont(16f)
        );
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
        daftarPesananPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 6));
        jPanel3.add(daftarPesananPanel);

        daftarPesananScroll.setViewportView(jPanel3);
        daftarPesananScroll.getVerticalScrollBar().setUnitIncrement(14);

        javax.swing.GroupLayout detailPesananPanelLayout = new javax.swing.GroupLayout(detailPesananPanel);
        detailPesananPanel.setLayout(detailPesananPanelLayout);
        detailPesananPanelLayout.setHorizontalGroup(
            detailPesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPesananPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(detailPesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(daftarPesananScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
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
                    .addComponent(tv_daftarPesananTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
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

        daftarPesananScroll.getVerticalScrollBar().setUnitIncrement(12);

        jSeparator1.setForeground(Colors.borderColor);
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        detailBarangPanel.setBackground(new java.awt.Color(255, 255, 255));

        detailBarangScrol.setBackground(new java.awt.Color(255, 255, 255));
        detailBarangScrol.setBorder(null);
        detailBarangScrol.setOpaque(false);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tv_deskripsiSubTitle.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        tv_deskripsiSubTitle.setForeground(Colors.greyTextColor);
        tv_deskripsiSubTitle.setText("DESKRIPSI");

        jSeparator2.setForeground(Colors.borderColor);

        cb_varian.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cb_varian.setForeground(Colors.blackTextColor);
        cb_varian.setToolTipText("");
        cb_varian.setOpaque(false);

        tv_namaBarang.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_namaBarang.setForeground(Colors.blackTextColor);
        tv_namaBarang.setText("Coca Cola Zero Sugar Vanilla");

        tv_deskripsi.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_deskripsi.setForeground(Colors.blackTextColor);
        tv_deskripsi.setText("Rasa hidangan istimewa, bukan berarti cara membuatnya harus susah, sajian Iga Bakar Petis ini dibuat sangatlah mudah. Rasanya sungguh khas, karena iga bakar dibumbui dengan Saus Tiram Selera dan beragam rempah serta petis. Dibungkus menggunakan daun pisang layaknya pepes, iga bakar petis akan menghasilkan aroma sedap nan menggoda saat dibakar. Rasa petis yang khas berpadu dengan gurihnya santan dan ekstrak tiram, membuat menu ini menjadi lauk paket lengkap yang wajib dicoba!");

        s_varian.setForeground(Colors.borderColor);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        b_closeDetailBarang.setBackground(Colors.blueBackgroundColor);
        b_closeDetailBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_close-circle_transparent.png"))); // NOI18N
        b_closeDetailBarang.setBorder(null);
        b_closeDetailBarang.setContentAreaFilled(false);
        b_closeDetailBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_closeDetailBarang.setFocusPainted(false);
        b_closeDetailBarang.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_close-circle.png"))); // NOI18N
        b_closeDetailBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_closeDetailBarangActionPerformed(evt);
            }
        });
        jPanel4.add(b_closeDetailBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 5, 24, 24));

        iv_gambarBarang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iv_gambarBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/a_ logo.png"))); // NOI18N
        iv_gambarBarang.setMaximumSize(new java.awt.Dimension(260, 160));
        jPanel4.add(iv_gambarBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 221));

        tv_namaBarangSubTitle.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        tv_namaBarangSubTitle.setForeground(Colors.greyTextColor);
        tv_namaBarangSubTitle.setText("NAMA MAKANAN");

        tv_varian.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        tv_varian.setForeground(Colors.greyTextColor);
        tv_varian.setText("VARIAN");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(s_varian)
                        .addComponent(tv_varian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2)
                        .addComponent(cb_varian, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tv_namaBarangSubTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tv_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tv_deskripsiSubTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tv_deskripsi, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tv_deskripsiSubTitle)
                .addGap(8, 8, 8)
                .addComponent(tv_deskripsi)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        detailBarangScrol.setViewportView(jPanel6);

        et_jumlah.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        et_jumlah.setForeground(Colors.blackTextColor);
        et_jumlah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        et_jumlah.setText("1");
        et_jumlah.setBorder(new RoundedBorder(buttonRadius));

        b_kurang.setBackground(Colors.accentColor);
        b_kurang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_minus.png"))); // NOI18N
        b_kurang.setBorder(null);
        b_kurang.setBorderPainted(false);
        b_kurang.setContentAreaFilled(false);
        b_kurang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_kurang.setFocusPainted(false);
        b_kurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_kurangActionPerformed(evt);
            }
        });

        b_pesan.setBackground(Colors.accentColor);
        b_pesan.setFont(Fonts.PRODUCT_SANS_MEDIUM.deriveFont(16f)
        );
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

        b_tambah.setBackground(Colors.accentColor);
        b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_plus.png"))); // NOI18N
        b_tambah.setBorder(null);
        b_tambah.setBorderPainted(false);
        b_tambah.setContentAreaFilled(false);
        b_tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_tambah.setFocusPainted(false);
        b_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_tambahActionPerformed(evt);
            }
        });

        jSeparator3.setForeground(Colors.borderColor);

        javax.swing.GroupLayout detailBarangPanelLayout = new javax.swing.GroupLayout(detailBarangPanel);
        detailBarangPanel.setLayout(detailBarangPanelLayout);
        detailBarangPanelLayout.setHorizontalGroup(
            detailBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(detailBarangScrol)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailBarangPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(detailBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(detailBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(b_pesan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(detailBarangPanelLayout.createSequentialGroup()
                            .addComponent(b_kurang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(et_jumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 24, 24))
        );
        detailBarangPanelLayout.setVerticalGroup(
            detailBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailBarangPanelLayout.createSequentialGroup()
                .addComponent(detailBarangScrol)
                .addGap(0, 0, 0)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(detailBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailBarangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(et_jumlah, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(b_kurang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_pesan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        detailBarangScrol.getVerticalScrollBar().setUnitIncrement(14);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(entriBarangScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1382, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(detailPesananPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(detailBarangPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(entriBarangScroll)
            .addComponent(detailPesananPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addComponent(detailBarangPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        entriBarangScroll.getVerticalScrollBar().setUnitIncrement(14);
        detailBarangPanel.setVisible(false);
    }// </editor-fold>//GEN-END:initComponents

    private void b_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_tambahActionPerformed
        if (!isOrdered()) {
            int jumlah = Integer.parseInt(et_jumlah.getText());
            int hargaBarang = selectedBarang.harga;

            jumlah++;
            hargaBarang *= jumlah;
            et_jumlah.setText(String.valueOf(jumlah));
            b_pesan.setText("Rp. " + a_.convertCurrency(hargaBarang));
        }
    }//GEN-LAST:event_b_tambahActionPerformed

    private void b_pesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_pesanActionPerformed
        Pesanan pesanan = new Pesanan();
        pesanan.idBarang = selectedBarang.id;
        pesanan.namaBarang = selectedBarang.namaBarang;
        pesanan.hargaBarang = selectedBarang.harga;
        pesanan.jumlahBarang = Integer.parseInt(et_jumlah.getText());
        pesanan.gambar = selectedBarang.gambar;
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
        total = pesanans.stream().map((_pesanan) -> _pesanan.hargaBarang * _pesanan.jumlahBarang).map((subTotal) -> subTotal).reduce(total, Integer::sum);

        Penjualan penjualan = new Penjualan();
        penjualan.idMeja = meja.id;
        penjualan.idPengguna = context.pengguna.id;
        penjualan.idStatus = 1;
        penjualan.atasNama = meja.atasNama;
        penjualan.jumlahOrang = meja.jumlahOrang;
        penjualan.total = total;

        int idPenjualan = penjualan.insert(connection);

        if (idPenjualan != -1) {
            boolean isSucceed = true;
            for (Pesanan _pesanan : pesanans) {
                DetailPenjualan detailPenjualan = new DetailPenjualan();
                detailPenjualan.idBarang = _pesanan.idBarang;
                detailPenjualan.idPenjualan = idPenjualan;
                detailPenjualan.namaBarang = _pesanan.namaBarang;
                detailPenjualan.jumlahBarang = _pesanan.jumlahBarang;

                if (!detailPenjualan.insert(connection)) {
                    isSucceed = false;
                }
            }

            if (isSucceed) {
                meja.idPenjualan = idPenjualan;
                if (meja.update(connection)) {
                    if (context.out != null) {
                        context.out.println("PESANAN_ADDED" + context.pengguna.id);
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
            int hargaBarang = selectedBarang.harga;

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
    private javax.swing.JScrollPane detailBarangScrol;
    public javax.swing.JPanel detailPesananPanel;
    private javax.swing.JPanel entriBarangPanel;
    public javax.swing.JScrollPane entriBarangScroll;
    private javax.swing.JTextField et_jumlah;
    private javax.swing.JLabel iv_gambarBarang;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator32;
    private javax.swing.JSeparator s_varian;
    private javax.swing.JLabel tv_daftarPesananTitle;
    private javax.swing.JLabel tv_deskripsi;
    private javax.swing.JLabel tv_deskripsiSubTitle;
    private javax.swing.JLabel tv_namaBarang;
    private javax.swing.JLabel tv_namaBarangSubTitle;
    private javax.swing.JLabel tv_nomorMeja;
    private javax.swing.JLabel tv_varian;
    // End of variables declaration//GEN-END:variables
}
