/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanmap;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.Locale;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author DELL
 */
public class Test extends javax.swing.JFrame {
    
    public Test() {
        initComponents();
    }
    private void readFileMenuMousePressed(MouseEvent evt) {
        graphPanel.stop();
        JFileChooser c = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("csv file", new String[] {"csv"});
        c.setFileFilter(filter);
        // Demonstrate "Open" dialog:
        int rVal = c.showOpenDialog(this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String fileName = c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName();
            readFilePanel.readFile(fileName);
            dataPanel.setAises(readFilePanel.getAises());
            graphPanel.setAises(readFilePanel.getAises());
        }
    }

       private void initComponents() {
        /*
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new DataAnalysisPanel();
        jPanel3 = new GraphAnalysisPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());


        jTabbedPane1.addTab("tab1", jPanel2);


        jPanel3.setLayout(new java.awt.BorderLayout());


        jTabbedPane1.addTab("tab2", jPanel3);

        jPanel1.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        add(jPanel1);

        pack();
           */
        jPanel3 = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1020, 800);
        jPanel3.setLayout(new BoxLayout(jPanel3,BoxLayout.Y_AXIS));
        readFilePanel = new ReadFilePanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        dataPanel = new DataAnalysisPanel();
        jTabbedPane1.addTab("資料分析", dataPanel);
        //frame.jPanel1.setPreferredSize(new Dimension(998, 589));
        graphPanel = new GraphAnalysisPanel();
        jTabbedPane1.addTab("圖型分析", graphPanel);
        // frame.jPanel2.setPreferredSize(new Dimension(763, 589));
        jPanel3.add(readFilePanel);
        jPanel3.add(jTabbedPane1);
        // menu
        jMenuBar = new javax.swing.JMenuBar();
        readFileMenu = new javax.swing.JMenu();
        quitMenu = new javax.swing.JMenu();
        aboutMenu = new javax.swing.JMenu();
        readFileMenu.setText("檔案");
        readFileMenu.setFont(new java.awt.Font("微軟正黑體", 0, 14)); // NOI18N
        readFileMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                readFileMenuMousePressed(evt);
            }


        });
        jMenuBar.add(readFileMenu);

        quitMenu.setText("離開");
        quitMenu.setFont(new java.awt.Font("微軟正黑體", 0, 14)); // NOI18N
        quitMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                quitMenuMousePressed(evt);
            }

            private void quitMenuMousePressed(MouseEvent evt) {
                System.exit(0);
            }
        });
        jMenuBar.add(quitMenu);

        aboutMenu.setText("關於");
        aboutMenu.setFont(new java.awt.Font("微軟正黑體", 0, 14)); // NOI18N
        aboutMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                aboutMenuMousePressed(evt);
            }

            private void aboutMenuMousePressed(MouseEvent evt) {
                    String str = "鴻祺航太有限公司 製作銷售\n 台北市民權東路6段264號2樓\n @2016\n 設計者:\n Hungchi. Liu\n Dr. T. Ko\n" + 
                " Dr. C. Tsai\n LINE ID: hc66\n" + " Tel: 02-2634-9343\n" + " Fax: 02-2634-9342\n" + " www.hasco.com.tw";
                    JOptionPane.showMessageDialog(null, str);
            }
        });
        jMenuBar.add(aboutMenu);

        setJMenuBar(jMenuBar);
        // end of set menu
        setTitle("test data analysis");
        getContentPane().add(jPanel3);
        setLocationRelativeTo(null);
    }


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Test frame = new Test();
                /*
                String fileName = "d:\\AIS\\0933aisdr.csv";
                Aises aises = new Aises(fileName);
                frame.jPanel1 = new DataAnalysisPanel();
                ((DataAnalysisPanel)frame.jPanel1).setAises(aises);
                frame.jPanel1.setBounds(0, 0, 998, 589);
                frame.jPanel1.setSize(new Dimension(998, 589));
               
                frame.jPanel2 = new GraphAnalysisPanel();              
                frame.jPanel2.setBounds(0, 0, 763, 589);
                frame.jPanel2.setSize(new Dimension(763, 589));
                
                frame.jTabbedPane1 = new javax.swing.JTabbedPane();
                frame.jTabbedPane1.setSize(1196, 680);
                frame.jTabbedPane1.addTab("tab1", frame.jPanel1);
                frame.jTabbedPane1.addTab("tab2", frame.jPanel2);
                */
                /*
                frame.jPanel3 = new JPanel();
                
                frame.jPanel3.setLayout(new BoxLayout(frame.jPanel3,BoxLayout.X_AXIS));
                frame.jPanel1 = new DataAnalysisPanel();
                //frame.jPanel1.setPreferredSize(new Dimension(998, 589));
                frame.jPanel2 = new GraphAnalysisPanel();
                // frame.jPanel2.setPreferredSize(new Dimension(763, 589));
                frame.jPanel3.add(frame.jPanel1);
                frame.jPanel3.add(frame.jPanel2);
                frame.setTitle("test data analysis");
                frame.getContentPane().add(frame.jPanel3);
                */
                // frame.setSize(1800, 680);
                // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //frame.setTitle("test data analysis");
                //frame.setLocationRelativeTo(null);
                //frame.getContentPane().add(frame.jPanel3);
                frame.setVisible(true);
                // new Test().setVisible(true);
            }
        });
    }

    private ReadFilePanel readFilePanel;
    private GraphAnalysisPanel graphPanel;
    private DataAnalysisPanel dataPanel;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenu quitMenu;
    private javax.swing.JMenu readFileMenu;
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JMenuBar jMenuBar;
}

