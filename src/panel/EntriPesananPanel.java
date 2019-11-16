package panel;

import common.RoundedButton;
import common.TemplateDetailPesanan;
import common.TemplatePenjualan;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import model.DetailPenjualan;
import model.Penjualan;
import model.Pesanan;
import pages.MainPageWhiteTheme;
import styles.Colors;

/**
 *
 * @author a_lpha
 */
public class EntriPesananPanel extends javax.swing.JPanel {

    private final List<TemplatePenjualan> templatePesanans = new ArrayList<>();
    private final DefaultListModel<Pesanan> listModel = new DefaultListModel<>();

    public List<Pesanan> pesanans = new ArrayList<>();

    private final int circleRadius = 64;
    private final int buttonRadius = 8;

    private Penjualan selectedPesanan;

    private GridLayout gridLayout;
    private final MainPageWhiteTheme context;
    private final Connection connection;

    public EntriPesananPanel(MainPageWhiteTheme context, Connection connection) {
        this.context = context;
        this.connection = connection;

        initComponents();
        init();
    }

    private void init() {
        gridLayout = new GridLayout(0, 1);
        gridLayout.setHgap(12);
        gridLayout.setVgap(12);
        entriPesananPanel.setLayout(gridLayout);

        entriPesananScroll.getVerticalScrollBar().setUnitIncrement(12);

        loadBarang(new Penjualan().get(connection));
    }

    public void loadBarang(List<Penjualan> penjualans) {
        entriPesananPanel.removeAll();

        penjualans.forEach((_penjualan) -> {
            TemplatePenjualan templatePenjualan = new TemplatePenjualan(connection, this, _penjualan);
            templatePesanans.add(templatePenjualan);
            entriPesananPanel.add(templatePenjualan);
        });
        entriPesananPanel.revalidate();
    }

    public void showDetailPesanan(TemplatePenjualan templatePenjualan, Penjualan penjualan) {
        selectedPesanan = penjualan;

        templatePesanans.forEach(((_templateBarang) -> {
            _templateBarang.setSelected(_templateBarang == templatePenjualan);
        }));

        detailPesananListPanel.removeAll();

        List<DetailPenjualan> detailPenjualans = new DetailPenjualan().get(connection, penjualan.getId());
        detailPenjualans.forEach((_detailPenjualan) -> {
            detailPesananListPanel.add(new TemplateDetailPesanan(connection, _detailPenjualan));
        });
        detailPesananListPanel.revalidate();
    }

    private void hideDetailPesanan() {
        detailPesananPanel.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        entriPesananScroll = new common.a_ScrollPane(jPanel1);
        jPanel1 = new javax.swing.JPanel();
        entriPesananPanel = new javax.swing.JPanel();
        detailPesananPanel = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        b_konfirmasiPesanan = new RoundedButton(buttonRadius);
        b_closeDetailPesanan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
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

        entriPesananScroll.setBackground(new java.awt.Color(255, 255, 255));
        entriPesananScroll.setBorder(null);
        entriPesananScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

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

        entriPesananScroll.setViewportView(jPanel1);

        detailPesananPanel.setBackground(new java.awt.Color(255, 255, 255));
        detailPesananPanel.setMinimumSize(new java.awt.Dimension(360, 360));

        jLabel41.setFont(new java.awt.Font("Google Sans", 0, 22)); // NOI18N
        jLabel41.setForeground(Colors.blackTextColor);
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_information.png"))); // NOI18N
        jLabel41.setText("Detail Pesanan");
        jLabel41.setIconTextGap(18);

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

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        panelFlow.setBackground(new java.awt.Color(255, 255, 255));
        panelFlow.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 5));

        detailPesananListPanel.setBackground(new java.awt.Color(255, 255, 255));
        detailPesananListPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 4));
        panelFlow.add(detailPesananListPanel);

        jScrollPane2.setViewportView(panelFlow);

        javax.swing.GroupLayout detailPesananPanelLayout = new javax.swing.GroupLayout(detailPesananPanel);
        detailPesananPanel.setLayout(detailPesananPanelLayout);
        detailPesananPanelLayout.setHorizontalGroup(
            detailPesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPesananPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(detailPesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addGroup(detailPesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(b_konfirmasiPesanan, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addGroup(detailPesananPanelLayout.createSequentialGroup()
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(b_closeDetailPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 24, 24))
        );
        detailPesananPanelLayout.setVerticalGroup(
            detailPesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPesananPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(detailPesananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_closeDetailPesanan, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2)
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
                .addComponent(entriPesananScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1538, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(detailPesananPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(entriPesananScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
            .addComponent(detailPesananPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void b_konfirmasiPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_konfirmasiPesananActionPerformed

    }//GEN-LAST:event_b_konfirmasiPesananActionPerformed

    private void b_closeDetailPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_closeDetailPesananActionPerformed
        hideDetailPesanan();
    }//GEN-LAST:event_b_closeDetailPesananActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_closeDetailPesanan;
    private javax.swing.JButton b_konfirmasiPesanan;
    private javax.swing.JPanel detailPesananListPanel;
    public javax.swing.JPanel detailPesananPanel;
    private javax.swing.JPanel entriPesananPanel;
    private javax.swing.JScrollPane entriPesananScroll;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel panelFlow;
    // End of variables declaration//GEN-END:variables
}
