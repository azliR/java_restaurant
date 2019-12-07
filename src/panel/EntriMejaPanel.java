package panel;

import common.RoundedBorder;
import common.RoundedButton;
import template.TemplateEntriMeja;
import common.a_;
import common.a_TextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import model.Meja;
import model.TipeMeja;
import pages.MainPage;
import styles.Colors;
import styles.Fonts;

/**
 *
 * @author a_lpha
 */
public class EntriMejaPanel extends javax.swing.JPanel {

    private final List<TemplateEntriMeja> templateEntriMejas = new ArrayList<>();
    private List<Meja> mejas = new ArrayList<>();

    private final int buttonRadius = 8;
    private final int padding = 16;

    private Meja selectedMeja = new Meja();

    private GridLayout gridLayout;

    private final MainPage context;
    private final Connection connection;

    public EntriMejaPanel(MainPage context, Connection connection) {
        this.connection = connection;
        this.context = context;
        initComponents();
        init();
    }

    private void init() {
        gridLayout = new GridLayout(0, 4);
        gridLayout.setHgap(12);
        gridLayout.setVgap(12);
        entriMejaPanel.setLayout(gridLayout);
        entriMejaScroll.getVerticalScrollBar().setUnitIncrement(12);

        infoMejaPanel.setVisible(false);
        loadEntriMeja(new Meja().get(connection, Meja.GET_TYPE.SEMUA));
    }

    public void loadEntriMeja(List<Meja> mejas) {
        entriMejaPanel.removeAll();
        templateEntriMejas.clear();
        this.mejas = mejas;

        if (infoMejaPanel.isVisible()) {
            infoMejaPanel.setVisible(false);
        }

        final int width = context.content.getWidth() / 4 - padding;

        mejas.forEach((_entriMeja) -> {
            TemplateEntriMeja templateEntriMeja = new TemplateEntriMeja(connection, this, _entriMeja);
            templateEntriMeja.setPreferredSize(new Dimension(width, (int) templateEntriMeja.getPreferredSize().getHeight()));
            templateEntriMejas.add(templateEntriMeja);
            entriMejaPanel.add(templateEntriMeja);
        });
        gridLayout.setColumns(4);
        entriMejaPanel.revalidate();
    }

    public void showInfoMeja(TemplateEntriMeja templateEntriMeja, Meja meja) {
        selectedMeja = meja;

        final int width = (context.content.getWidth() - infoMejaPanel.getPreferredSize().width) / 3 - padding;

        templateEntriMejas.forEach(((_templateEntriMeja) -> {
            _templateEntriMeja.setSelected(_templateEntriMeja == templateEntriMeja);
            if (!infoMejaPanel.isVisible()) {
                _templateEntriMeja.setPreferredSize(new Dimension(width, _templateEntriMeja.getHeight()));
                _templateEntriMeja.revalidate();
            }
        }));

        if (!infoMejaPanel.isVisible()) {
            gridLayout.setColumns(3);
            infoMejaPanel.setVisible(true);
            entriMejaPanel.revalidate();
        }

        tv_atasNama.setText(meja.atasNama);
        tv_jumlahOrang.setText(meja.jumlahOrang + " Orang");
        tv_nomorMeja.setText(String.valueOf(meja.nomorMeja));
        tv_maksOrang.setText(meja.maksOrang + " Orang");

        tv_tipeMeja.setText(new TipeMeja().get(connection, meja.idTipeMeja).namaTipe);
        b_pilihMeja.setText(meja.atasNama != null ? "Sudah Dipesan" : "Pesan Meja");
        b_pilihMeja.setBackground(meja.atasNama != null ? Colors.primaryColor : Colors.accentColor);
        b_pilihMeja.setEnabled(meja.atasNama == null);
        b_detailPesanan.setVisible(meja.atasNama != null);

        tv_jumlahOrang.setVisible(meja.jumlahOrang != 0);
        l_jumlahOrang.setVisible(meja.jumlahOrang != 0);
        s_jumlahOrang.setVisible(meja.jumlahOrang != 0);

        tv_atasNama.setVisible(meja.atasNama != null);
        l_atasNama.setVisible(meja.atasNama != null);
        s_atasNama.setVisible(meja.atasNama != null);
    }

    private void hideInfoMeja() {
        selectedMeja = new Meja();

        infoMejaPanel.setVisible(false);
        gridLayout.setColumns(4);
        entriMejaPanel.revalidate();

        final int width = context.content.getWidth() / 4 - padding;

        templateEntriMejas.forEach(((_templateEntriMeja) -> {
            _templateEntriMeja.setSelected(false);
            _templateEntriMeja.setPreferredSize(new Dimension(width, _templateEntriMeja.getHeight()));
            _templateEntriMeja.revalidate();
        }));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        atasNamaDialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        et_atasNama = new a_TextField("Atas Nama");
        tv_jumlah = new javax.swing.JLabel();
        b_okeAtasNama = new RoundedButton(buttonRadius);
        cb_jumlahOrang = new javax.swing.JComboBox<>();
        tv_hint = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        entriMejaScroll = new common.a_ScrollPane(jPanel2);
        jPanel2 = new javax.swing.JPanel();
        entriMejaPanel = new javax.swing.JPanel();
        infoMejaPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        l_atasNama = new javax.swing.JLabel();
        tv_nomorMeja = new javax.swing.JLabel();
        tv_tipeMeja = new javax.swing.JLabel();
        tv_atasNama = new javax.swing.JLabel();
        b_pilihMeja = new RoundedButton(buttonRadius);
        l_jumlahOrang = new javax.swing.JLabel();
        tv_jumlahOrang = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        s_atasNama = new javax.swing.JSeparator();
        s_jumlahOrang = new javax.swing.JSeparator();
        b_detailPesanan = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        l_maksOrang = new javax.swing.JLabel();
        tv_maksOrang = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();

        atasNamaDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        atasNamaDialog.setTitle("Pesan Meja");
        atasNamaDialog.setBackground(new java.awt.Color(255, 255, 255));
        atasNamaDialog.setFont(Fonts.PRODUCT_SANS_MEDIUM.deriveFont(14f)
        );
        atasNamaDialog.setModal(true);
        atasNamaDialog.setPreferredSize(new java.awt.Dimension(337, 275));
        atasNamaDialog.setResizable(false);
        atasNamaDialog.setSize(new java.awt.Dimension(400, 300));
        atasNamaDialog.setType(java.awt.Window.Type.POPUP);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        et_atasNama.setBorder(null);
        et_atasNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                et_atasNamaActionPerformed(evt);
            }
        });

        tv_jumlah.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(14f)
        );
        tv_jumlah.setForeground(Colors.greyTextColor);
        tv_jumlah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tv_jumlah.setText("Jumlah Orang");

        b_okeAtasNama.setBackground(Colors.accentColor);
        b_okeAtasNama.setFont(Fonts.PRODUCT_SANS_MEDIUM.deriveFont(14f)
        );
        b_okeAtasNama.setForeground(new java.awt.Color(255, 255, 255));
        b_okeAtasNama.setText("Oke");
        b_okeAtasNama.setBorder(null);
        b_okeAtasNama.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_okeAtasNama.setFocusPainted(false);
        b_okeAtasNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_okeAtasNamaActionPerformed(evt);
            }
        });

        cb_jumlahOrang.setFont(Fonts.ROBOTO_MEDIUM.deriveFont(14f)
        );
        cb_jumlahOrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 Orang" }));

        tv_hint.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tv_hint.setForeground(Colors.greyTextColor);
        tv_hint.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel4.setFont(Fonts.PRODUCT_SANS_MEDIUM.deriveFont(20f)
        );
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Pesan Meja");
        jLabel4.setIconTextGap(16);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_okeAtasNama, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tv_hint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(et_atasNama, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(tv_jumlah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cb_jumlahOrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 124, Short.MAX_VALUE)))))
                .addGap(36, 36, 36))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addGap(16, 16, 16)
                .addComponent(et_atasNama, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tv_hint)
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_jumlahOrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tv_jumlah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(b_okeAtasNama, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        tv_hint.setVisible(false);

        javax.swing.GroupLayout atasNamaDialogLayout = new javax.swing.GroupLayout(atasNamaDialog.getContentPane());
        atasNamaDialog.getContentPane().setLayout(atasNamaDialogLayout);
        atasNamaDialogLayout.setHorizontalGroup(
            atasNamaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(atasNamaDialogLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        atasNamaDialogLayout.setVerticalGroup(
            atasNamaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        atasNamaDialog.setLocationRelativeTo(pages.MainPage.parent);

        setBackground(new java.awt.Color(255, 255, 255));

        entriMejaScroll.setBackground(new java.awt.Color(255, 255, 255));
        entriMejaScroll.setBorder(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 0, 8));

        entriMejaPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout entriMejaPanelLayout = new javax.swing.GroupLayout(entriMejaPanel);
        entriMejaPanel.setLayout(entriMejaPanelLayout);
        entriMejaPanelLayout.setHorizontalGroup(
            entriMejaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        entriMejaPanelLayout.setVerticalGroup(
            entriMejaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel2.add(entriMejaPanel);

        entriMejaScroll.setViewportView(jPanel2);

        infoMejaPanel.setBackground(new java.awt.Color(255, 255, 255));
        infoMejaPanel.setMinimumSize(new java.awt.Dimension(360, 360));

        jLabel1.setFont(new java.awt.Font("Product Sans", 0, 24)); // NOI18N
        jLabel1.setForeground(Colors.blackTextColor);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_information.png"))); // NOI18N
        jLabel1.setText("Informasi Meja");
        jLabel1.setIconTextGap(16);

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        jLabel2.setForeground(Colors.greyTextColor);
        jLabel2.setText("NO. MEJA");

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        jLabel3.setForeground(Colors.greyTextColor);
        jLabel3.setText("TIPE MEJA");

        l_atasNama.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        l_atasNama.setForeground(Colors.greyTextColor);
        l_atasNama.setText("ATAS NAMA");

        tv_nomorMeja.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_nomorMeja.setForeground(Colors.blackTextColor);
        tv_nomorMeja.setText("01");

        tv_tipeMeja.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_tipeMeja.setForeground(Colors.blackTextColor);
        tv_tipeMeja.setText("Standar");

        tv_atasNama.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_atasNama.setForeground(Colors.blackTextColor);
        tv_atasNama.setText("Rizal Hadiyansah");

        b_pilihMeja.setBackground(Colors.accentColor);
        b_pilihMeja.setFont(Fonts.PRODUCT_SANS_MEDIUM.deriveFont(16f)
        );
        b_pilihMeja.setForeground(new java.awt.Color(255, 255, 255));
        b_pilihMeja.setText("Pesan Meja");
        b_pilihMeja.setBorder(null);
        b_pilihMeja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_pilihMeja.setFocusPainted(false);
        b_pilihMeja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_pilihMejaActionPerformed(evt);
            }
        });

        l_jumlahOrang.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        l_jumlahOrang.setForeground(Colors.greyTextColor);
        l_jumlahOrang.setText("JUMLAH ORANG");

        tv_jumlahOrang.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_jumlahOrang.setForeground(Colors.blackTextColor);
        tv_jumlahOrang.setText("1 Orang");

        jSeparator2.setForeground(new java.awt.Color(218, 220, 224));

        jSeparator3.setForeground(new java.awt.Color(218, 220, 224));

        s_atasNama.setForeground(new java.awt.Color(218, 220, 224));

        s_jumlahOrang.setForeground(new java.awt.Color(218, 220, 224));

        b_detailPesanan.setBackground(Colors.primaryColor);
        b_detailPesanan.setFont(Fonts.PRODUCT_SANS_MEDIUM.deriveFont(16f)
        );
        b_detailPesanan.setForeground(Colors.blackTextColor);
        b_detailPesanan.setText("Detail Pesanan");
        b_detailPesanan.setBorder(new RoundedBorder(buttonRadius)
        );
        b_detailPesanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_detailPesanan.setFocusPainted(false);
        b_detailPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_detailPesananActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_close_grey.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ic_close.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        l_maksOrang.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        l_maksOrang.setForeground(Colors.greyTextColor);
        l_maksOrang.setText("MAKSIMAL ORANG");

        tv_maksOrang.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tv_maksOrang.setForeground(Colors.blackTextColor);
        tv_maksOrang.setText("4 Orang");

        jSeparator5.setForeground(new java.awt.Color(218, 220, 224));

        javax.swing.GroupLayout infoMejaPanelLayout = new javax.swing.GroupLayout(infoMejaPanel);
        infoMejaPanel.setLayout(infoMejaPanelLayout);
        infoMejaPanelLayout.setHorizontalGroup(
            infoMejaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoMejaPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(infoMejaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator2)
                    .addComponent(s_jumlahOrang)
                    .addComponent(jSeparator3)
                    .addComponent(b_pilihMeja, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(s_atasNama)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tv_nomorMeja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tv_tipeMeja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(l_jumlahOrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tv_jumlahOrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(l_atasNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tv_atasNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(infoMejaPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(b_detailPesanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(l_maksOrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tv_maksOrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator5))
                .addGap(24, 24, 24))
        );
        infoMejaPanelLayout.setVerticalGroup(
            infoMejaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoMejaPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(infoMejaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addComponent(tv_nomorMeja)
                .addGap(16, 16, 16)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(8, 8, 8)
                .addComponent(tv_tipeMeja)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(l_maksOrang)
                .addGap(8, 8, 8)
                .addComponent(tv_maksOrang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(l_jumlahOrang)
                .addGap(8, 8, 8)
                .addComponent(tv_jumlahOrang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(s_jumlahOrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(l_atasNama)
                .addGap(8, 8, 8)
                .addComponent(tv_atasNama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(s_atasNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(b_detailPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_pilihMeja, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jSeparator1.setForeground(Colors.borderColor);
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(entriMejaScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1328, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(infoMejaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(infoMejaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(entriMejaScroll, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void b_pilihMejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_pilihMejaActionPerformed
        String[] jumlahOrang = new String[selectedMeja.maksOrang];
        for (int i = 0; i < selectedMeja.maksOrang; i++) {
            jumlahOrang[i] = (i + 1) + " Orang";
        }
        cb_jumlahOrang.setModel(new DefaultComboBoxModel<>(jumlahOrang));
        atasNamaDialog.setVisible(true);
    }//GEN-LAST:event_b_pilihMejaActionPerformed

    private void b_okeAtasNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_okeAtasNamaActionPerformed
        if (et_atasNama.getText().isBlank()) {
            tv_hint.setText("Masukkan nama pelanggan untuk melanjutkan!");
            tv_hint.setIcon(new ImageIcon(getClass().getResource("/images/ic_alert-circle.png")));
            tv_hint.setForeground(Color.RED);
            return;
        }

        selectedMeja.atasNama = et_atasNama.getText();
        selectedMeja.jumlahOrang = a_.extractNumber(cb_jumlahOrang.getSelectedItem().toString());

        context.nav_entriBarang.setSelected(true);
        context.setNavigationColor();
        context.loadContent(new EntriBarangPanel(context, connection, selectedMeja));

        atasNamaDialog.dispose();
    }//GEN-LAST:event_b_okeAtasNamaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        hideInfoMeja();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void et_atasNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_et_atasNamaActionPerformed
        b_okeAtasNama.doClick();
    }//GEN-LAST:event_et_atasNamaActionPerformed

    private void b_detailPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_detailPesananActionPerformed
        context.loadContent(new EntriPenjualanPanel(context, connection, selectedMeja.idPenjualan));
        context.nav_entriOrder.setSelected(true);
        context.setNavigationColor();
    }//GEN-LAST:event_b_detailPesananActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog atasNamaDialog;
    private javax.swing.JButton b_detailPesanan;
    private javax.swing.JButton b_okeAtasNama;
    private javax.swing.JButton b_pilihMeja;
    private javax.swing.JComboBox<String> cb_jumlahOrang;
    private javax.swing.JPanel entriMejaPanel;
    private javax.swing.JScrollPane entriMejaScroll;
    private javax.swing.JTextField et_atasNama;
    private javax.swing.JPanel infoMejaPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel l_atasNama;
    private javax.swing.JLabel l_jumlahOrang;
    private javax.swing.JLabel l_maksOrang;
    private javax.swing.JSeparator s_atasNama;
    private javax.swing.JSeparator s_jumlahOrang;
    private javax.swing.JLabel tv_atasNama;
    private javax.swing.JLabel tv_hint;
    private javax.swing.JLabel tv_jumlah;
    private javax.swing.JLabel tv_jumlahOrang;
    private javax.swing.JLabel tv_maksOrang;
    private javax.swing.JLabel tv_nomorMeja;
    private javax.swing.JLabel tv_tipeMeja;
    // End of variables declaration//GEN-END:variables
}
