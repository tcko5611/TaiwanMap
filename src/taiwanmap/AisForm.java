/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanmap;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author DELL
 */
public class AisForm extends javax.swing.JFrame {
    private Aises aises; // aises group
    // private MyTableModel myTableModel ;
    DateFormat sdff;
    MessageSender messageSender;
    /**
     * Creates new form AisForm
     */
    public AisForm() throws IOException {
        initComponents();
        buttonGroup1.add(realTimeRadioButton);
        buttonGroup1.add(allTimeRadioButton);
        realTimeRadioButton.setSelected(true);
        this.mmsiCheckBox.setEnabled(true);
        this.traceCheckBox.setEnabled(false);
        sdff = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        messageSender = null;
        setTitle("鴻祺航太AIS-Map船舶記錄資料軌跡繪圖1.0");
        ClassLoader classLoader = getClass().getClassLoader();
        BufferedImage title = ImageIO.read(classLoader.getResourceAsStream("ico/title.png"));
        setIconImage(title);
        readFileButton.addActionListener(new OpenL());
        jTextArea1.setText("\n 鴻祺航太有限公司 製作銷售\n 台北市民權東路6段264號2樓\n @2016\n 設計者:\n Hungchi. Liu\n Dr. T. Ko\n" +
            " Dr. C. Tsai\n LINE ID: hc66\n" + " Tel: 02-2634-9343\n" + " Fax: 02-2634-9342\n" + " www.hasco.com.tw");
        infoTable.getTableHeader().setFont(new java.awt.Font("新細明體", 0, 14));
        
        ((MyTable)infoTable).setSelfModel();
        // myTableModel = new MyTableModel();
        // infoTable.setModel(myTableModel);
        // disable all unneeded buttons
        
        infoTable.getColumnModel().getColumn(1).setPreferredWidth(90);
        infoTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        infoTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        this.okButton.setEnabled(false);
        this.startButton.setEnabled(false);
        this.pauseButton.setEnabled(false);
        this.continueButton.setEnabled(false);
        this.slowButton.setEnabled(false);
        this.fastButton.setEnabled(false);
        this.stopButton.setEnabled(false);
        this.startTextField.setEditable(false);
        this.setLocationRelativeTo(null);
        Dimension dim = new Dimension(37,25);
        fileNameLabel.setMaximumSize(dim);
        fileNameLabel.setMinimumSize(dim);
        fileNameLabel.setPreferredSize(dim);
    }
    /**
    * listener for file choose button
    */
    class OpenL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("csv file", new String[] {"csv"});
            c.setFileFilter(filter);
            // Demonstrate "Open" dialog:
            int rVal = c.showOpenDialog(AisForm.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                fileNameLabel.setText(c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName());
                aises = new Aises(fileNameLabel.getText());
                Date beginDate = aises.getBeginDate();
                Date endDate = aises.getEndDate();
                double maxLat = aises.getMaxLat();
                double minLat = aises.getMinLat();
                double maxLng = aises.getMaxLng();
                double minLng = aises.getMinLng();
        
                beginDateLabel.setText(sdff.format(beginDate));
                endDateLabel.setText(sdff.format(endDate));
                maxLatLabel.setText(" N:" + String.format("%.2f", maxLat));
                minLatLabel.setText(" N:" + String.format("%.2f", minLat));
                maxLngLabel.setText(" E:" + String.format("%.2f", maxLng));
                minLngLabel.setText(" E:" + String.format("%.2f", minLng));
                okButton.setEnabled(true);
                startButton.setEnabled(true);
                startTextField.setText(sdff.format(beginDate));
                startTextField.setEditable(true);
                PlotPanel plotPanel1 = (PlotPanel) plotPanel;
                plotPanel1.setBoundary(119, 25.5, 123, 21.5);
                int interval = (int) ((endDate.getTime() - beginDate.getTime())/1000);
                MyProgressBar jProgressBar11 = (MyProgressBar) jProgressBar1;
                jProgressBar11.setMaximum(interval);
                jProgressBar11.setMinimum(0);
                jProgressBar11.setValue(0);
                jProgressBar11.setBeginDate(beginDate);
                jProgressBar11.setEndDate(endDate);
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        plotPanel = new PlotPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        infoTable = new MyTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        readFileButton = new javax.swing.JButton();
        fileNameLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        beginDateLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        endDateLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        maxLatLabel = new javax.swing.JLabel();
        minLatLabel = new javax.swing.JLabel();
        minLngLabel = new javax.swing.JLabel();
        maxLngLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lat0TextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lng0TextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rangeTextField = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        startTextField = new javax.swing.JTextField();
        startButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        continueButton = new javax.swing.JButton();
        slowButton = new javax.swing.JButton();
        fastButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        speedLabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        realTimeRadioButton = new javax.swing.JRadioButton();
        mmsiCheckBox = new javax.swing.JCheckBox();
        allTimeRadioButton = new javax.swing.JRadioButton();
        traceCheckBox = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jProgressBar1 = new MyProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("AisForm"); // NOI18N
        setResizable(false);

        plotPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        plotPanel.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        plotPanel.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout plotPanelLayout = new javax.swing.GroupLayout(plotPanel);
        plotPanel.setLayout(plotPanelLayout);
        plotPanelLayout.setHorizontalGroup(
            plotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        plotPanelLayout.setVerticalGroup(
            plotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
        );

        infoTable.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        infoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(infoTable);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel2.setText("AIS船舶記錄資料");

        readFileButton.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        readFileButton.setText("讀取檔案");

        fileNameLabel.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        fileNameLabel.setText("a.csv");
        fileNameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel3.setText("開始時間：");
        jLabel3.setToolTipText("");

        beginDateLabel.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        beginDateLabel.setText("2016.04.08 12:40:00");
        beginDateLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel1.setText("結束時間：");

        endDateLabel.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        endDateLabel.setText("2016.05.14 18:05:00");
        endDateLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel6.setText("座標最高：");

        jLabel7.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel7.setText("座標最低：");

        maxLatLabel.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        maxLatLabel.setText(" N:27.10");
        maxLatLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        minLatLabel.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        minLatLabel.setText(" N:25.10");
        minLatLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        minLngLabel.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        minLngLabel.setText(" E:120.10");
        minLngLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        maxLngLabel.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        maxLngLabel.setText(" E:122.10");
        maxLngLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(endDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(beginDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(minLatLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(maxLatLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(maxLngLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                            .addComponent(minLngLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(readFileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readFileButton)
                    .addComponent(fileNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(beginDateLabel)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(maxLatLabel)
                    .addComponent(maxLngLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(minLatLabel)
                        .addComponent(minLngLabel)
                        .addComponent(endDateLabel))
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel4.setText("地圖中央座標");

        jLabel5.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel5.setText("N：");

        lat0TextField.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        lat0TextField.setText("23.5");
        lat0TextField.setMaximumSize(new java.awt.Dimension(30, 24));
        lat0TextField.setMinimumSize(new java.awt.Dimension(30, 24));

        jLabel8.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel8.setText("E：");

        lng0TextField.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        lng0TextField.setText("121");
        lng0TextField.setMaximumSize(new java.awt.Dimension(30, 24));
        lng0TextField.setMinimumSize(new java.awt.Dimension(30, 24));
        lng0TextField.setPreferredSize(new java.awt.Dimension(30, 24));

        jLabel9.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel9.setText("範圍：");

        rangeTextField.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        rangeTextField.setText("4");
        rangeTextField.setMaximumSize(new java.awt.Dimension(30, 24));
        rangeTextField.setMinimumSize(new java.awt.Dimension(30, 24));
        rangeTextField.setPreferredSize(new java.awt.Dimension(30, 24));

        okButton.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rangeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lat0TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lng0TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(okButton))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(lng0TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lat0TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(okButton)
                    .addComponent(rangeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel10.setText("開始時間：");
        jLabel10.setToolTipText("");

        startTextField.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        startTextField.setText("2016.05.13 00:40:00");

        startButton.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        startButton.setText("開始繪圖");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        pauseButton.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        pauseButton.setText("暫停");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        continueButton.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        continueButton.setText("繼續");
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtonActionPerformed(evt);
            }
        });

        slowButton.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        slowButton.setText("變慢");
        slowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slowButtonActionPerformed(evt);
            }
        });

        fastButton.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        fastButton.setText("加快");
        fastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fastButtonActionPerformed(evt);
            }
        });

        stopButton.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        stopButton.setText("停止繪圖");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        exitButton.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        exitButton.setText("離開程式");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel12.setText("繪圖選項");

        jLabel13.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel13.setText("繪圖速度：");

        speedLabel.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        speedLabel.setText("1");

        jLabel14.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel14.setText("筆/秒");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(startTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(startButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pauseButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(continueButton))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(exitButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(speedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(stopButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(slowButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(fastButton))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(startTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(pauseButton)
                    .addComponent(continueButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stopButton)
                    .addComponent(slowButton)
                    .addComponent(fastButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitButton)
                    .addComponent(jLabel13)
                    .addComponent(speedLabel)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel11.setText("顯示選項");

        realTimeRadioButton.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        realTimeRadioButton.setText("即時軌跡");
        realTimeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realTimeRadioButtonActionPerformed(evt);
            }
        });

        mmsiCheckBox.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        mmsiCheckBox.setText("MMSI");
        mmsiCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mmsiCheckBoxItemStateChanged(evt);
            }
        });

        allTimeRadioButton.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        allTimeRadioButton.setText("全部軌跡");
        allTimeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allTimeRadioButtonActionPerformed(evt);
            }
        });

        traceCheckBox.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        traceCheckBox.setText("軌跡連線");
        traceCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                traceCheckBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(realTimeRadioButton)
                    .addComponent(jLabel11)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(traceCheckBox)
                            .addComponent(mmsiCheckBox)))
                    .addComponent(allTimeRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(realTimeRadioButton)
                    .addComponent(mmsiCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allTimeRadioButton)
                    .addComponent(traceCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(220, 220, 220));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setBorder(null);
        jTextArea1.setOpaque(false);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plotPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plotPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
    * exit button lisener method 
    * @param evt 
    */
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);// TODO add your handling code here:
    }//GEN-LAST:event_exitButtonActionPerformed
    /**
    * ok button lisener, will set the plotPanel boundary 
    * @param evt 
    */
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
            double lng = Double.parseDouble(lng0TextField.getText());
            double lat = Double.parseDouble(lat0TextField.getText());
            double range = Double.parseDouble(rangeTextField.getText());
            Double lng0 = lng - range/2.0;
            Double lng1 = lng + range/2.0;
            Double lat0 = lat + range/2.0;
            Double lat1 = lat - range/2.0;
            PlotPanel plotPanel1 = (PlotPanel) plotPanel;
            plotPanel1.setBoundary(lng0, lat0, lng1, lat1);
            // plotPanel1.repaint();// TODO add your handling code here:
    }//GEN-LAST:event_okButtonActionPerformed
    /**
    * start button lisener, 
    * 1. will get the play start time and end time, then get region aises from Aises,
    * 2. build message sender for about 5 min/sec
    * 3. add obersers to message sender
    * 4. told message sender start to send message from another thread
    * @param evt 
    */
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        try {
            Date startDate = sdff.parse(startTextField.getText());
            Date endDate = sdff.parse(this.endDateLabel.getText());
            int interval = (int) ((endDate.getTime() - startDate.getTime())/1000);
            MyProgressBar jProgressBar11 = (MyProgressBar) jProgressBar1;
            jProgressBar11.setBeginDate(startDate);
            jProgressBar11.setEndDate(endDate);
            jProgressBar11.setMaximum(interval);
            jProgressBar11.setMinimum(0);
            jProgressBar11.setValue(0);

            // Debugger.log(s);
            ArrayList<Ais> localAises = aises.getRegionsAis(startDate, endDate);
            messageSender = new MessageSender(localAises, startDate, endDate, 1);        // TODO add your handling code here:
            // messageSender.run();
            MyTable infoTable1 = (MyTable) infoTable;
            MyProgressBar myProgressBar = (MyProgressBar) jProgressBar1;
            messageSender.addObserver(infoTable1);
            messageSender.addObserver((PlotPanel) plotPanel);
            messageSender.addObserver(myProgressBar);
            // messageSender.addObserver((MyLabel) currentTimeLabel);
            Executor sExecutor = Executors.newSingleThreadExecutor();
            sExecutor.execute(messageSender);
            this.startButton.setEnabled(false);
            this.pauseButton.setEnabled(true);
            // this.continueButton.setEnabled(true);
            this.slowButton.setEnabled(true);
            this.fastButton.setEnabled(true);
            this.stopButton.setEnabled(true);
            this.startTextField.setEditable(false);
            this.okButton.setEnabled(false);
            this.readFileButton.setEnabled(false);
            this.allTimeRadioButton.setEnabled(false);
            this.realTimeRadioButton.setEnabled(false);
            this.mmsiCheckBox.setEnabled(false);
            this.traceCheckBox.setEnabled(false);
            speedLabel.setText("1");
            repaint();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "軌跡開始時間格式錯誤\n請用 yyyy.MM.dd hh:mm:ss");
            Logger.getLogger(AisForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
       messageSender.setStop(); // TODO add your handling code here:
       ((PlotPanel) plotPanel).clearData();
        this.startButton.setEnabled(true);
        this.pauseButton.setEnabled(false);
        this.continueButton.setEnabled(false);
        this.slowButton.setEnabled(false);
        this.fastButton.setEnabled(false);
        this.stopButton.setEnabled(false);
        this.startTextField.setEditable(true);
        this.okButton.setEnabled(true);
        this.readFileButton.setEnabled(true);
        this.allTimeRadioButton.setEnabled(true);
        this.realTimeRadioButton.setEnabled(true);
        this.mmsiCheckBox.setEnabled(true);
        this.traceCheckBox.setEnabled(false);
    }//GEN-LAST:event_stopButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        // TODO add your handling code here:
        messageSender.setPause();
        continueButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        // okButton.setEnabled(true);
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void continueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueButtonActionPerformed
        messageSender.unsetPause();// TODO add your handling code here:
        continueButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
        okButton.setEnabled(false);
    }//GEN-LAST:event_continueButtonActionPerformed

    private void slowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slowButtonActionPerformed
        messageSender.slowSpeed();// TODO add your handling code here:
        Integer speeds[] = {1,2,4,8,16};
        Integer speed = Integer.parseInt(speedLabel.getText());
        for (int i = speeds.length - 1; i >= 0; i--) {
            if (speed > speeds[i]) {
                speed = speeds[i];
                break;
            }
        }
        speedLabel.setText(speed.toString());
        // this.speedLabel.setText(Integer.toString(messageSender.getSpeed()));
    }//GEN-LAST:event_slowButtonActionPerformed

    private void fastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fastButtonActionPerformed
        messageSender.fastSpeed();// TODO add your handling code here:
        Integer speeds[] = {1,2,4,8,16};
        Integer speed = Integer.parseInt(speedLabel.getText());
        for (int i = 0; i < speeds.length; i++) {
            if (speed < speeds[i]) {
                speed = speeds[i];
                break;
            }
        }
        speedLabel.setText(speed.toString());
        // this.speedLabel.setText(Integer.toString(messageSender.getSpeed()));
    }//GEN-LAST:event_fastButtonActionPerformed

    private void realTimeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realTimeRadioButtonActionPerformed
       this.mmsiCheckBox.setEnabled(true);        // TODO add your handling code here:
       this.traceCheckBox.setSelected(false);
       this.traceCheckBox.setEnabled(false);
       ((PlotPanel)this.plotPanel).setRealTimeDraw();
    }//GEN-LAST:event_realTimeRadioButtonActionPerformed

    private void allTimeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allTimeRadioButtonActionPerformed
       this.mmsiCheckBox.setEnabled(false);        // TODO add your handling code here:
       this.mmsiCheckBox.setSelected(false);
       this.traceCheckBox.setEnabled(true);        // TODO add your handling code here:
       ((PlotPanel)this.plotPanel).setAllTimeDraw();
    }//GEN-LAST:event_allTimeRadioButtonActionPerformed

    private void mmsiCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mmsiCheckBoxItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.DESELECTED) {
            PlotPanel plotPanel1 = (PlotPanel) plotPanel;
            plotPanel1.unshowMmsi();
        } else if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
            PlotPanel plotPanel1 = (PlotPanel) plotPanel;
            plotPanel1.showMmsi();
        }// TODO add your handling code here:
    }//GEN-LAST:event_mmsiCheckBoxItemStateChanged

    private void traceCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_traceCheckBoxItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.DESELECTED) {
            PlotPanel plotPanel1 = (PlotPanel) plotPanel;
            plotPanel1.unshowTrace();
        } else if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
            PlotPanel plotPanel1 = (PlotPanel) plotPanel;
            plotPanel1.showTrace();
        }// TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_traceCheckBoxItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AisForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AisForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AisForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AisForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AisForm().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(AisForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton allTimeRadioButton;
    private javax.swing.JLabel beginDateLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton continueButton;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton fastButton;
    private javax.swing.JLabel fileNameLabel;
    private javax.swing.JTable infoTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField lat0TextField;
    private javax.swing.JTextField lng0TextField;
    private javax.swing.JLabel maxLatLabel;
    private javax.swing.JLabel maxLngLabel;
    private javax.swing.JLabel minLatLabel;
    private javax.swing.JLabel minLngLabel;
    private javax.swing.JCheckBox mmsiCheckBox;
    private javax.swing.JButton okButton;
    private javax.swing.JButton pauseButton;
    private javax.swing.JPanel plotPanel;
    private javax.swing.JTextField rangeTextField;
    private javax.swing.JButton readFileButton;
    private javax.swing.JRadioButton realTimeRadioButton;
    private javax.swing.JButton slowButton;
    private javax.swing.JLabel speedLabel;
    private javax.swing.JButton startButton;
    private javax.swing.JTextField startTextField;
    private javax.swing.JButton stopButton;
    private javax.swing.JCheckBox traceCheckBox;
    // End of variables declaration//GEN-END:variables
}
/**
 * Create my table because it need to observe message ender
 * @author T.C.KO
 */
class MyTable extends javax.swing.JTable implements Observer {
    MyTableModel myTableModel;
    // public MyTable() {
    //    super();
    //    myTableModel = new MyTableModel();
    //    setModel(myTableModel);
    // }
    /**
     * need this function to set table model after constructor
     */
    public void setSelfModel() {
        myTableModel = new MyTableModel();
        setModel(myTableModel);   
    }
    /**
     * oberver function for message sender
     * @param ais ais information from messge sender
     */
    @Override
    public void update(Ais ais, Date date) {
        myTableModel.update(ais, date);
        repaint();
    }   
}
/**
 * my table model for my table
 * @author T.C.KO
 */
class MyTableModel extends AbstractTableModel implements Observer{
    Object[][] data={
    {"","","","",""}
    };
    String[] columns={"時間","MMSI","經度","緯度","船名"};
    @Override 
     public String getColumnName(int col) {
        return columns[col];
    }
    @Override
    public int getColumnCount() {return columns.length;}
    @Override
    public int getRowCount() {return data.length;}
    @Override
    public Object getValueAt(int row, int col) {return data[row][col];}
    @Override
    public void update(Ais ais, Date date1) {
        SimpleDateFormat sdff = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String date = sdff.format(ais.getDate());
        Integer mmsi = ais.getMmsi();
        String lng = String.format ("%.4f", ais.getLng());
        String lat = String.format ("%.4f",ais.getLat());
        String shipName = ais.getShipName();
        data[0][0] = date;
        data[0][1] = mmsi;
        data[0][2] = lng;
        data[0][3] = lat;
        data[0][4] = shipName;
        /* not work, becasue STring is like int, Integer, behavior not like a pointer
        Object obj = getValueAt(0,0); obj = date;
        obj = getValueAt(0,1); obj = mmsi;
        obj = getValueAt(0,2); obj = lng;
        obj = getValueAt(0,3); obj = lat;
        obj = getValueAt(0,4); obj = shipName;
        */    
    }
}

class MyProgressBar extends javax.swing.JProgressBar implements Observer {
    Date beginDate;
    Date endDate;
    void setBeginDate(Date beginDate) {
        this.beginDate = new Date(beginDate.getTime());
    }
    void setEndDate(Date endDate) {
        endDate = new Date(endDate.getTime());
    }
    @Override
    public void update(Ais ais, Date date) {
        int interval = (int) ((date.getTime() - beginDate.getTime())/1000);
        setValue(interval);
        DateFormat sdff = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        // Debugger.log("interval:" + interval);
        // Debugger.log("begin Date:" + sdff.format(beginDate));
        // Debugger.log("date:" + sdff.format(date));
    }
}
/*
class MyLabel extends javax.swing.JLabel implements Observer {
    @Override
    public void update(Ais ais, Date date) {
        DateFormat sdff = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        this.setText(sdff.format(date));
    }  
}
*/
