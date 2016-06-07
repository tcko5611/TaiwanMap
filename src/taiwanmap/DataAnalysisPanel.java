package taiwanmap;

import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class DataAnalysisPanel extends javax.swing.JPanel {
   TableRowSorter<AllAisesTableModel> sorter;
   AllAisesTableModel model;
   Aises aises;
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
                JPanel panel = new DataAnalysisPanel();
                ((DataAnalysisPanel)panel).setAises(aises);
                panel.setBounds(0, 0, 998, 589);
                panel.setSize(new Dimension(998, 589));
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1008, 629);
                frame.setLayout(null);
                frame.setTitle("test data analysis");
                frame.getContentPane().add(panel);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    /**
     * Creates new form DataAnalysisPanel
     */
    public DataAnalysisPanel() {
        initComponents();
        
        
    }
    public void setAises(Aises aises) {
        this.aises = aises;
        ArrayList<Ais> aises1 = aises.getRegionsAis(aises.getBeginDate(), aises.getEndDate());
        model = new  AllAisesTableModel(aises1);
        jTable1.setModel(model);
        sorter = new TableRowSorter<AllAisesTableModel>(model);
        jTable1.setRowSorter(sorter);
        newFilter();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(220);
        // jTable1.getColumnModel().getColumn(0).setWidth(200);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
    }
    private void newFilter() {
        RowFilter<AllAisesTableModel, Integer> rf = new RowFilter<AllAisesTableModel, Integer>() {
            @Override
            public boolean include(RowFilter.Entry<? extends AllAisesTableModel, ? extends Integer> entry) {
                boolean ret = true;
                SimpleDateFormat dt = new SimpleDateFormat("yyyyy.MM.dd HH:mm:ss");
                String mmsiText = mmsiTextField.getText();
                String startDateText = startDateTextField.getText();
                String stopDateText = stopDateTextField.getText();
                
                if (!mmsiTextField.getText().equals("")) {
                    int mmsi = Integer.parseInt(mmsiTextField.getText());
                    int mmsi1 = Integer.parseInt(entry.getValue(1).toString());
                    if (mmsi != mmsi1) ret = false;
                }
                try {
                    Date date = dt.parse(entry.getValue(0).toString());
                    String startDateStr = startDateTextField.getText();
                    String stopDateStr = stopDateTextField.getText();
                    if (!startDateStr.equals("")) {
                        Date startDate = dt.parse(startDateStr);
                        if (date.before(startDate)) ret = false;
                    }
                    if (!stopDateStr.equals("")) {
                        Date stopDate = dt.parse(stopDateStr);
                        if (date.after(stopDate)) ret = false;
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(TestFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                return ret;
            }
        };
        sorter.setRowFilter(rf);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mmsiTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        startDateTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        stopDateTextField = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel1.setText("mmsi：");

        mmsiTextField.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        mmsiTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmsiTextFieldActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel2.setText("開始時間：");

        startDateTextField.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        startDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startDateTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        jLabel3.setText("結束時間：");

        stopDateTextField.setFont(new java.awt.Font("新細明體", 0, 14)); // NOI18N
        stopDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopDateTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stopDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mmsiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mmsiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(startDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(stopDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mmsiTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmsiTextFieldActionPerformed
        model.fireTableDataChanged();// TODO add your handling code here:
    }//GEN-LAST:event_mmsiTextFieldActionPerformed

    private void startDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startDateTextFieldActionPerformed
        // TODO add your handling code here:
        model.fireTableDataChanged();
    }//GEN-LAST:event_startDateTextFieldActionPerformed

    private void stopDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopDateTextFieldActionPerformed
        // TODO add your handling code here:
        model.fireTableDataChanged();
    }//GEN-LAST:event_stopDateTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField mmsiTextField;
    private javax.swing.JTextField startDateTextField;
    private javax.swing.JTextField stopDateTextField;
    // End of variables declaration//GEN-END:variables
}
