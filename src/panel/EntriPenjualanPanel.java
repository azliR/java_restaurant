package panel;

import common.RoundedButton;
import common.TemplateDetailPenjualan;
import common.TemplatePenjualan;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.Timer;
import model.DetailPenjualan;
import model.Penjualan;
import model.Pesanan;
import pages.MainPage;
import styles.Colors;

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
                Timer timer = new Timer(1, (ActionEvent ae) -> {
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
        gridLayout = new GridLayout(0, 1);
        gridLayout.setHgap(12);
        gridLayout.setVgap(12);
        entriPesananPanel.setLayout(gridLayout);

        entriPenjualanScroll.getVerticalScrollBar().setUnitIncrement(12);

        loadPesanan(new Penjualan().get(connection));
    }

    public void loadPesanan(List<Penjualan> penjualans) {
        templatePenjualans.clear();
        entriPesananPanel.removeAll();

        int width = context.content.getWidth() - detailPenjualanPanel.getPreferredSize().width - 16;
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

        templatePenjualans.forEach(((_templateBarang) -> {
            _templateBarang.setSelected(_templateBarang == templatePenjualan);
        }));

        detailPesananListPanel.removeAll();

        List<DetailPenjualan> detailPenjualans = new DetailPenjualan().get(connection, penjualan.id);

        int i = 1;
        for (DetailPenjualan detailPenjualan : detailPenjualans) {
            TemplateDetailPenjualan templateDetailPenjualan = new TemplateDetailPenjualan(connection, detailPenjualan, i);
            int width = detailPesananScroll.getWidth() - 8;
            int height = templateDetailPenjualan.getPreferredSize().height;

            templateDetailPenjualan.setPreferredSize(new Dimension(width, height));
            detailPesananListPanel.add(templateDetailPenjualan);
            i++;
        }
        detailPesananListPanel.revalidate();
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
        detailPesananScroll = new javax.swing.JScrollPane();
        panelFlow = new javax.swing.JPanel();
        detailPesananListPanel = new javax.swing.JPanel();
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

        panelFlow.setBackground(new java.awt.Color(255, 255, 255));
        panelFlow.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 5));

        detailPesananListPanel.setBackground(new java.awt.Color(255, 255, 255));
        detailPesananListPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 4));
        panelFlow.add(detailPesananListPanel);

        detailPesananScroll.setViewportView(panelFlow);

        javax.swing.GroupLayout detailPenjualanPanelLayout = new javax.swing.GroupLayout(detailPenjualanPanel);
        detailPenjualanPanel.setLayout(detailPenjualanPanelLayout);
        detailPenjualanPanelLayout.setHorizontalGroup(
            detailPenjualanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPenjualanPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(detailPenjualanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(detailPesananScroll)
                    .addComponent(b_konfirmasiPesanan, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        detailPenjualanPanelLayout.setVerticalGroup(
            detailPenjualanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPenjualanPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(detailPesananScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_konfirmasiPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(entriPenjualanScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1538, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(detailPenjualanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(entriPenjualanScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
            .addComponent(detailPenjualanPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void b_konfirmasiPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_konfirmasiPesananActionPerformed

    }//GEN-LAST:event_b_konfirmasiPesananActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_konfirmasiPesanan;
    public javax.swing.JPanel detailPenjualanPanel;
    private javax.swing.JPanel detailPesananListPanel;
    private javax.swing.JScrollPane detailPesananScroll;
    private javax.swing.JScrollPane entriPenjualanScroll;
    private javax.swing.JPanel entriPesananPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel panelFlow;
    // End of variables declaration//GEN-END:variables
}
