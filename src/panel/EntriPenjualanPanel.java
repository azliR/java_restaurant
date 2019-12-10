package panel;

import common.RoundedBorder;
import common.RoundedButton;
import common.a_;
import common.a_ScrollPane;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import model.DetailPenjualan;
import model.Penjualan;
import model.Pesanan;
import model.StatusPesanan;
import pages.MainPage;
import styles.Colors;
import styles.Fonts;
import template.TemplateDetailPenjualan;
import template.TemplatePenjualan;

/**
 *
 * @author a_lpha
 */
public class EntriPenjualanPanel extends javax.swing.JPanel {

    private final List<TemplatePenjualan> templatePenjualans = new ArrayList<>();
    private final DefaultListModel<Pesanan> listModel = new DefaultListModel<>();

    public List<Pesanan> pesanans = new ArrayList<>();

    private final int buttonRadius = 8;

    private Penjualan selectedPesanan;

    private GridLayout gridLayout;
    private final MainPage context;
    private final Connection connection;

    public EntriPenjualanPanel(MainPage context, Connection connection) {
        this.context = context;
        this.connection = connection;

        initComponents();
        init();
    }

    public EntriPenjualanPanel(MainPage context, Connection connection, int id) {
        this.context = context;
        this.connection = connection;

        initComponents();
        init();
        Penjualan penjualan = new Penjualan().get(connection, id);
        for (TemplatePenjualan templatePenjualan : templatePenjualans) {
            if (templatePenjualan.penjualan.id == id) {
                Timer timer = new Timer(100, (ActionEvent ae) -> {
                    showDetailPesanan(templatePenjualan, penjualan);
                });
                timer.setRepeats(false);
                timer.start();
                selectedPesanan = penjualan;
                return;
            }
        }
    }

    private void init() {
        gridLayout = new GridLayout(0, 4);
        gridLayout.setHgap(12);
        gridLayout.setVgap(12);
        entriPesananPanel.setLayout(gridLayout);

        entriPenjualanScroll.getVerticalScrollBar().setUnitIncrement(12);

        loadPesanan(new Penjualan().get(connection));
    }

    public void loadPesanan(List<Penjualan> penjualans) {
        templatePenjualans.clear();
        entriPesananPanel.removeAll();

        int width = context.content.getWidth() / 4 - 16;
        penjualans.forEach((_penjualan) -> {
            TemplatePenjualan templatePenjualan = new TemplatePenjualan(connection, this, _penjualan);
            templatePenjualan.setPreferredSize(new Dimension(width, templatePenjualan.getPreferredSize().height));

            templatePenjualans.add(templatePenjualan);
            entriPesananPanel.add(templatePenjualan);
        });
        entriPesananPanel.revalidate();
    }

    public final void showDetailPesanan(TemplatePenjualan templatePenjualan, Penjualan penjualan) {
        selectedPesanan = penjualan;
        if (!detailPenjualanPanel.isVisible()) {
            detailPenjualanPanel.setVisible(true);
            gridLayout.setColumns(2);
            entriPesananPanel.revalidate();
        }

        int penjualanWidth = (context.content.getWidth() - detailPenjualanPanel.getPreferredSize().width) / 2 - 16;
        templatePenjualans.forEach(((_templatePenjualan) -> {
            _templatePenjualan.setSelected(_templatePenjualan == templatePenjualan);
            _templatePenjualan.setPreferredSize(new Dimension(penjualanWidth, _templatePenjualan.getHeight()));
            _templatePenjualan.revalidate();
        }));

        detailPesananListPanel.removeAll();

        List<DetailPenjualan> detailPenjualans = new DetailPenjualan().get(connection, penjualan.id);

        int i = 1;
        for (DetailPenjualan detailPenjualan : detailPenjualans) {
            TemplateDetailPenjualan templateDetailPenjualan = new TemplateDetailPenjualan(connection, detailPenjualan, i);
            int width = detailPesananScroll.getWidth() - 16;
            int height = templateDetailPenjualan.getPreferredSize().height;

            templateDetailPenjualan.setPreferredSize(new Dimension(width, height));
            detailPesananListPanel.add(templateDetailPenjualan);
            i++;
        }
        detailPesananListPanel.revalidate();

        tv_id.setText("#" + penjualan.id);
        tv_atasNama.setText(penjualan.atasNama);
        tv_jumlahOrang.setText(penjualan.jumlahOrang + " Orang");
        tv_status.setText(new StatusPesanan().get(connection, penjualan.idStatus).namaStatus);
        tv_total.setText("Rp. " + a_.convertCurrency(penjualan.total));

        jToggleButton1.setSelected(false);
    }

    private void hideDetailPesanan() {
        detailPenjualanPanel.setVisible(false);
        gridLayout.setColumns(4);

        final int width = context.content.getWidth() / 4 - 16;

        templatePenjualans.forEach(((_templatePenjualan) -> {
            _templatePenjualan.setSelected(false);
            _templatePenjualan.setPreferredSize(new Dimension(width, _templatePenjualan.getHeight()));
            _templatePenjualan.revalidate();
        }));
        entriPesananPanel.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        entriPenjualanScroll = new common.a_ScrollPane(jPanel1);
        jPanel1 = new javax.swing.JPanel();
        entriPesananPanel = new javax.swing.JPanel();
        detailPenjualanPanel = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        b_konfirmasiPesanan = new RoundedButton(buttonRadius);
        detailPesananScroll = new a_ScrollPane(panelFlow);
        panelFlow = new javax.swing.JPanel();
        detailPesananFlow = new javax.swing.JPanel();
        detailPesananListPanel = new javax.swing.JPanel();
        l_daftarPesanan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        tv_status = new javax.swing.JLabel();
        tv_id = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tv_jumlahOrang = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        tv_atasNama = new javax.swing.JLabel();
        tv_total = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jToggleButton1 = new javax.swing.JToggleButton();
        b_closeDetailPesanan = new javax.swing.JButton();
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

        entriPenjualanScroll.setBackground(new java.awt.Color(255, 255, 255));
        entriPenjualanScroll.setBorder(null);
        entriPenjualanScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 0, 8));

        entriPesananPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout entriPesananPanelLayout = new javax.swing.GroupLayout(entriPesananPanel);
        entriPesananPanel.setLayout(entriPesananPanelLayout);
        entriPesananPanelLayout.setHorizontalGroup(
            entriPesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        entriPesananPanelLayout.setVerticalGroup(
            entriPesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(entriPesananPanel);

        entriPenjualanScroll.setViewportView(jPanel1);

        detailPenjualanPanel.setBackground(new java.awt.Color(255, 255, 255));
        detailPenjualanPanel.setMinimumSize(new java.awt.Dimension(360, 360));

        jLabel41.setFont(new java.awt.Font("Google Sans", 0, 22)); // NOI18N
        jLabel41.setForeground(Colors.blackTextColor);
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_information.png"))); // NOI18N
        jLabel41.setText("Detail Pesanan");
        jLabel41.setIconTextGap(18);

        b_konfirmasiPesanan.setBackground(Colors.blueBackgroundColor);
        b_konfirmasiPesanan.setFont(new java.awt.Font("Product Sans Medium", 0, 16)); // NOI18N
        b_konfirmasiPesanan.setText("Fitur Baru Segera Datang");
        b_konfirmasiPesanan.setBorder(null);
        b_konfirmasiPesanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_konfirmasiPesanan.setFocusPainted(false);
        b_konfirmasiPesanan.setIconTextGap(8);
        b_konfirmasiPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_konfirmasiPesananActionPerformed(evt);
            }
        });

        detailPesananScroll.setBackground(new java.awt.Color(255, 255, 255));
        detailPesananScroll.setBorder(null);
        detailPesananScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelFlow.setBackground(new java.awt.Color(255, 255, 255));
        panelFlow.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 16));

        detailPesananFlow.setBackground(new java.awt.Color(255, 255, 255));
        detailPesananFlow.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        detailPesananListPanel.setBackground(new java.awt.Color(255, 255, 255));
        detailPesananListPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 6));
        detailPesananFlow.add(detailPesananListPanel);

        l_daftarPesanan.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        l_daftarPesanan.setForeground(Colors.greyTextColor);
        l_daftarPesanan.setText("DAFTAR PESANAN");

        jLabel2.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(11f)
        );
        jLabel2.setForeground(Colors.greyTextColor);
        jLabel2.setText("ID");

        jLabel3.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(11f)
        );
        jLabel3.setForeground(Colors.greyTextColor);
        jLabel3.setText("STATUS PESANAN");

        jSeparator2.setForeground(Colors.borderColor);
        jSeparator2.setOpaque(true);

        jSeparator3.setForeground(Colors.borderColor);
        jSeparator3.setOpaque(true);

        tv_status.setFont(Fonts.ROBOTO_REGULAR.deriveFont(14f)
        );
        tv_status.setForeground(Colors.blackTextColor);
        tv_status.setText("Menunggu Pembayaran");

        tv_id.setFont(Fonts.ROBOTO_REGULAR.deriveFont(14f)
        );
        tv_id.setForeground(Colors.blackTextColor);
        tv_id.setText("#115");

        jLabel4.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(11f)
        );
        jLabel4.setForeground(Colors.greyTextColor);
        jLabel4.setText("JUMLAH ORANG");

        tv_jumlahOrang.setFont(Fonts.ROBOTO_REGULAR.deriveFont(14f)
        );
        tv_jumlahOrang.setForeground(Colors.blackTextColor);
        tv_jumlahOrang.setText("4 Orang");

        jSeparator4.setForeground(Colors.borderColor);
        jSeparator4.setOpaque(true);

        jLabel5.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(11f)
        );
        jLabel5.setForeground(Colors.greyTextColor);
        jLabel5.setText("ATAS NAMA");

        jSeparator5.setForeground(Colors.borderColor);
        jSeparator5.setOpaque(true);

        tv_atasNama.setFont(Fonts.ROBOTO_REGULAR.deriveFont(14f)
        );
        tv_atasNama.setForeground(Colors.blackTextColor);
        tv_atasNama.setText("a_lpha");

        tv_total.setFont(Fonts.ROBOTO_REGULAR.deriveFont(14f)
        );
        tv_total.setForeground(Colors.blackTextColor);
        tv_total.setText("Rp. 46.000");

        jLabel6.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(11f)
        );
        jLabel6.setForeground(Colors.greyTextColor);
        jLabel6.setText("TOTAL");

        jSeparator6.setForeground(Colors.borderColor);
        jSeparator6.setOpaque(true);

        jToggleButton1.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(12f)
        );
        jToggleButton1.setForeground(Colors.blackTextColor);
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_menu-down.png"))); // NOI18N
        jToggleButton1.setText("Selengkapnya");
        jToggleButton1.setBorder(new RoundedBorder(32, new int[]{4, 16, 4, 8}, Colors.borderColor)
        );
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton1.setFocusPainted(false);
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jToggleButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleButton1StateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelFlowLayout = new javax.swing.GroupLayout(panelFlow);
        panelFlow.setLayout(panelFlowLayout);
        panelFlowLayout.setHorizontalGroup(
            panelFlowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelFlowLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tv_id))
            .addGroup(panelFlowLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tv_status))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFlowLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tv_jumlahOrang))
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFlowLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tv_atasNama))
            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFlowLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tv_total))
            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelFlowLayout.createSequentialGroup()
                .addComponent(l_daftarPesanan)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(detailPesananFlow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelFlowLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFlowLayout.setVerticalGroup(
            panelFlowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFlowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFlowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tv_id))
                .addGap(16, 16, 16)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(panelFlowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tv_atasNama))
                .addGap(16, 16, 16)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(panelFlowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tv_status))
                .addGap(16, 16, 16)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(panelFlowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tv_jumlahOrang))
                .addGap(16, 16, 16)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(panelFlowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tv_total))
                .addGap(16, 16, 16)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(l_daftarPesanan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailPesananFlow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(jToggleButton1)
                .addGap(8, 8, 8))
        );

        detailPesananFlow.setVisible(false);
        l_daftarPesanan.setVisible(false);

        detailPesananScroll.setViewportView(panelFlow);

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

        javax.swing.GroupLayout detailPenjualanPanelLayout = new javax.swing.GroupLayout(detailPenjualanPanel);
        detailPenjualanPanel.setLayout(detailPenjualanPanelLayout);
        detailPenjualanPanelLayout.setHorizontalGroup(
            detailPenjualanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPenjualanPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(detailPenjualanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(detailPesananScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(detailPenjualanPanelLayout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_closeDetailPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(b_konfirmasiPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        detailPenjualanPanelLayout.setVerticalGroup(
            detailPenjualanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPenjualanPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(detailPenjualanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_closeDetailPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(detailPesananScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_konfirmasiPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        detailPesananScroll.getVerticalScrollBar().setUnitIncrement(12);

        jSeparator1.setForeground(Colors.borderColor);
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(entriPenjualanScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1394, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(detailPenjualanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(entriPenjualanScroll)
            .addComponent(detailPenjualanPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
        );

        detailPenjualanPanel.setVisible(false);
    }// </editor-fold>//GEN-END:initComponents

    private void b_konfirmasiPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_konfirmasiPesananActionPerformed

    }//GEN-LAST:event_b_konfirmasiPesananActionPerformed

    private void b_closeDetailPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_closeDetailPesananActionPerformed
        hideDetailPesanan();
    }//GEN-LAST:event_b_closeDetailPesananActionPerformed

    private void jToggleButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleButton1StateChanged
        if (!jToggleButton1.isSelected()) {
            l_daftarPesanan.setVisible(false);
            detailPesananFlow.setVisible(false);
            jToggleButton1.setText("Selengkapnya");
            jToggleButton1.setIcon(new ImageIcon(getClass().getResource("/images/ic_menu-down.png")));
        } else {
            l_daftarPesanan.setVisible(true);
            detailPesananFlow.setVisible(true);
            jToggleButton1.setText("Lebih Sedikit");
            jToggleButton1.setIcon(new ImageIcon(getClass().getResource("/images/ic_menu-up.png")));
        }
    }//GEN-LAST:event_jToggleButton1StateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_closeDetailPesanan;
    private javax.swing.JButton b_konfirmasiPesanan;
    public javax.swing.JPanel detailPenjualanPanel;
    private javax.swing.JPanel detailPesananFlow;
    private javax.swing.JPanel detailPesananListPanel;
    private javax.swing.JScrollPane detailPesananScroll;
    private javax.swing.JScrollPane entriPenjualanScroll;
    private javax.swing.JPanel entriPesananPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel l_daftarPesanan;
    private javax.swing.JPanel panelFlow;
    private javax.swing.JLabel tv_atasNama;
    private javax.swing.JLabel tv_id;
    private javax.swing.JLabel tv_jumlahOrang;
    private javax.swing.JLabel tv_status;
    private javax.swing.JLabel tv_total;
    // End of variables declaration//GEN-END:variables
}
