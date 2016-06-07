/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanmap;

import java.awt.Dimension;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class GraphAnalysisPanel extends JPanel implements Observer{
    private Aises aises; // aises group
    DateFormat sdff = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    MessageSender messageSender;
    ExecutorService messageExecutor;
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
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String fileName = "d:\\AIS\\0933aisdr.csv";
                Aises aises = new Aises(fileName);
                JPanel panel = new GraphAnalysisPanel();
                               
                panel.setBounds(0, 0, 1196, 680);
                panel.setSize(new Dimension(1196, 680));
                
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1216, 710);
                frame.setLayout(null);
                frame.setTitle("test data analysis");
                frame.getContentPane().add(panel);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                 ((GraphAnalysisPanel)panel).setAises(aises);
            }
        });
    }    
    public void setAises(Aises aises) {
        this.aises = aises;
        Date beginDate = aises.getBeginDate();
        Date endDate = aises.getEndDate();
        startTextField.setText(sdff.format(beginDate));
        endTextField.setText(sdff.format(endDate));
        startButton.setEnabled(true);
        double lng = Double.parseDouble(lng0TextField.getText());
        double lat = Double.parseDouble(lat0TextField.getText());
        double range = Double.parseDouble(rangeTextField.getText());
        Double lng0 = lng - range/2.0;
        Double lng1 = lng + range/2.0;
        Double lat0 = lat + range/2.0;
        Double lat1 = lat - range/2.0;
        ((PlotPanel)  plotPanel).setBoundary(lng0, lat0, lng1, lat1);
        okButtonActionPerformed(null);
    }
    public void stop() {
        // messageExecutor.shutdownNow();
        if (messageSender != null)messageSender.setStop(); // TODO add your handling code here:
        ((PlotPanel) plotPanel).clearData();
        this.startButton.setEnabled(true);
        this.pauseButton.setEnabled(false);
        this.continueButton.setEnabled(false);
        this.slowButton.setEnabled(false);
        this.fastButton.setEnabled(false);
        this.stopButton.setEnabled(false);
        this.startTextField.setEditable(true);
        this.okButton.setEnabled(true);
        // this.readFileButton.setEnabled(true);
        this.allTimeRadioButton.setEnabled(true);
        this.realTimeRadioButton.setEnabled(true);
        this.mmsiCheckBox.setEnabled(true);
        this.traceCheckBox.setEnabled(false);
    }

    /**
     * Creates new form GraphAnalysisPanel
     */
    public GraphAnalysisPanel() {
        initComponents();
        buttonGroup1.add(realTimeRadioButton);
        buttonGroup1.add(allTimeRadioButton);
        realTimeRadioButton.setSelected(true);
        this.mmsiCheckBox.setEnabled(true);
        this.traceCheckBox.setEnabled(false);
        messageSender = null;
        messageExecutor = Executors.newSingleThreadExecutor();
        infoTable.getTableHeader().setFont(new java.awt.Font("新細明體", 0, 14));
        
        ((MyTable)infoTable).setSelfModel();
        
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
        this.forwardButton.setEnabled(false);
        this.backwardButton.setEnabled(false);
        this.startTextField.setEditable(false);
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
        jProgressBar1 = new MyProgressBar();
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
        forwardButton = new javax.swing.JButton();
        backwardButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        endTextField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        realTimeRadioButton = new javax.swing.JRadioButton();
        mmsiCheckBox = new javax.swing.JCheckBox();
        allTimeRadioButton = new javax.swing.JRadioButton();
        traceCheckBox = new javax.swing.JCheckBox();

        setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N

        plotPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        plotPanel.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        plotPanel.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout plotPanelLayout = new javax.swing.GroupLayout(plotPanel);
        plotPanel.setLayout(plotPanelLayout);
        plotPanelLayout.setHorizontalGroup(
            plotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
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
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lat0TextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel8)
                        .addComponent(lng0TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rangeTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(okButton)))
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

        forwardButton.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        forwardButton.setText("前進一筆");
        forwardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardButtonActionPerformed(evt);
            }
        });

        backwardButton.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        backwardButton.setText("後退一筆");
        backwardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backwardButtonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel15.setText("結束時間：");
        jLabel15.setToolTipText("");

        endTextField.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        endTextField.setText("2016.05.14 00:40:00");

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
                            .addComponent(fastButton)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(forwardButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(backwardButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(endTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(startTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(endTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(forwardButton)
                    .addComponent(backwardButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitButton)
                    .addComponent(jLabel13)
                    .addComponent(speedLabel)
                    .addComponent(jLabel14))
                .addGap(22, 22, 22))
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
                .addContainerGap(69, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(plotPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(plotPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        double lng = Double.parseDouble(lng0TextField.getText());
        double lat = Double.parseDouble(lat0TextField.getText());
        double range = Double.parseDouble(rangeTextField.getText());
        Double lng0 = lng - range/2.0;
        Double lng1 = lng + range/2.0;
        Double lat0 = lat + range/2.0;
        Double lat1 = lat - range/2.0;
        // PlotPanel plotPanel1 = (PlotPanel) plotPanel;
        ((PlotPanel)  plotPanel).setBoundary(lng0, lat0, lng1, lat1);
        // plotPanel1.repaint();// TODO add your handling code here:
    }//GEN-LAST:event_okButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        try {
            Date startDate = sdff.parse(startTextField.getText());
            Date endDate = sdff.parse(endTextField.getText());
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
            MyTable infoTable1 = (MyTable) infoTable;
            MyProgressBar myProgressBar = (MyProgressBar) jProgressBar1;
            messageSender.addObserver(infoTable1);
            messageSender.addObserver((PlotPanel) plotPanel);
            messageSender.addObserver(myProgressBar);
            messageSender.addObserver(this);
            messageExecutor.execute(messageSender);
            this.startButton.setEnabled(false);
            this.pauseButton.setEnabled(true);
            this.slowButton.setEnabled(true);
            this.fastButton.setEnabled(true);
            this.stopButton.setEnabled(true);
            this.startTextField.setEditable(false);
            this.okButton.setEnabled(false);
            // this.readFileButton.setEnabled(false);
            this.allTimeRadioButton.setEnabled(false);
            this.realTimeRadioButton.setEnabled(false);
            this.mmsiCheckBox.setEnabled(false);
            this.traceCheckBox.setEnabled(false);
            speedLabel.setText("1");
            repaint();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "軌跡開始時間格式錯誤\n請用 yyyy.MM.dd hh:mm:ss");
            //Logger.getLogger(AisForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        // TODO add your handling code here:
        messageSender.setPause();
        continueButton.setEnabled(true);
        pauseButton.setEnabled(false);
        // stopButton.setEnabled(false);
        okButton.setEnabled(true);
        this.forwardButton.setEnabled(true);
        this.backwardButton.setEnabled(true);
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void continueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueButtonActionPerformed
        messageSender.unsetPause();// TODO add your handling code here:
        continueButton.setEnabled(false);
        pauseButton.setEnabled(true);
        // stopButton.setEnabled(true);
        okButton.setEnabled(false);
        this.forwardButton.setEnabled(false);
        this.backwardButton.setEnabled(false);
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

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
       stop();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);// TODO add your handling code here:
    }//GEN-LAST:event_exitButtonActionPerformed

    private void forwardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardButtonActionPerformed
        // TODO add your handling code here:
        this.messageSender.forwardOneStep();
    }//GEN-LAST:event_forwardButtonActionPerformed

    private void backwardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backwardButtonActionPerformed
        // TODO add your handling code here:
        this.messageSender.backwardOneStep();
    }//GEN-LAST:event_backwardButtonActionPerformed

    private void realTimeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realTimeRadioButtonActionPerformed
        this.mmsiCheckBox.setEnabled(true);        // TODO add your handling code here:
        this.traceCheckBox.setSelected(false);
        this.traceCheckBox.setEnabled(false);
        ((PlotPanel)this.plotPanel).setRealTimeDraw();
    }//GEN-LAST:event_realTimeRadioButtonActionPerformed

    private void mmsiCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mmsiCheckBoxItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.DESELECTED) {
            PlotPanel plotPanel1 = (PlotPanel) plotPanel;
            plotPanel1.unshowMmsi();
        } else if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
            PlotPanel plotPanel1 = (PlotPanel) plotPanel;
            plotPanel1.showMmsi();
        }// TODO add your handling code here:
    }//GEN-LAST:event_mmsiCheckBoxItemStateChanged

    private void allTimeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allTimeRadioButtonActionPerformed
        this.mmsiCheckBox.setEnabled(false);        // TODO add your handling code here:
        this.mmsiCheckBox.setSelected(false);
        this.traceCheckBox.setEnabled(true);        // TODO add your handling code here:
        ((PlotPanel)this.plotPanel).setAllTimeDraw();
    }//GEN-LAST:event_allTimeRadioButtonActionPerformed

    private void traceCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_traceCheckBoxItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.DESELECTED) {
            PlotPanel plotPanel1 = (PlotPanel) plotPanel;
            plotPanel1.unshowTrace();
        } else if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
            PlotPanel plotPanel1 = (PlotPanel) plotPanel;
            plotPanel1.showTrace();
        }// TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_traceCheckBoxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton allTimeRadioButton;
    private javax.swing.JButton backwardButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton continueButton;
    private javax.swing.JTextField endTextField;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton fastButton;
    private javax.swing.JButton forwardButton;
    private javax.swing.JTable infoTable;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField lat0TextField;
    private javax.swing.JTextField lng0TextField;
    private javax.swing.JCheckBox mmsiCheckBox;
    private javax.swing.JButton okButton;
    private javax.swing.JButton pauseButton;
    private javax.swing.JPanel plotPanel;
    private javax.swing.JTextField rangeTextField;
    private javax.swing.JRadioButton realTimeRadioButton;
    private javax.swing.JButton slowButton;
    private javax.swing.JLabel speedLabel;
    private javax.swing.JButton startButton;
    private javax.swing.JTextField startTextField;
    private javax.swing.JButton stopButton;
    private javax.swing.JCheckBox traceCheckBox;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Ais ais) {
        return;
    }

    @Override
    public void updateAises(ArrayList<Ais> aises) {
       return;
    }

    @Override
    public void updateStatus(boolean hasNextData, boolean hasPrevData) {
        if (!hasNextData) {
            this.forwardButton.setEnabled(false);
        } else {
            this.forwardButton.setEnabled(true);
        }
        if (!hasPrevData) {
            this.backwardButton.setEnabled(false);
        } else {
            this.backwardButton.setEnabled(true);
        }
    }
}
