/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanmap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author T.C.KO
 */
public class AllAisesTable extends javax.swing.JTable {
    public static void main(String[] args) throws ParseException {
        String fileName = "d:\\AIS\\0933aisdr.csv";
        Aises aises = new Aises(fileName);
        ArrayList<Ais> aises1 = aises.getRegionsAis(aises.getBeginDate(), aises.getEndDate());
        AllAisesTableModel model = new  AllAisesTableModel(aises1);
        JTable table = new JTable(model);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        table.getTableHeader().setFont(new java.awt.Font("新細明體", 0, 18));
        table.setFont(new java.awt.Font("新細明體", 0, 18));
        // List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        // sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        // sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        // sorter.setSortKeys(sortKeys);

        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(table));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);        
       
    }
   
}
/**
 * my table model for my table
 * @author T.C.KO
 */
class AllAisesTableModel extends AbstractTableModel{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    ArrayList<Ais> aises;
    String[] columns={"時間","MMSI","船名","經度","緯度","航速", "航向", "船向", "轉率", "狀態"};
    AllAisesTableModel(ArrayList<Ais> aises) {
        this.aises = aises;
    }
    @Override 
     public String getColumnName(int col) {
        return columns[col];
    }
    @Override
    public int getColumnCount() {return columns.length;}
    @Override
    public int getRowCount() {return aises.size();}
    @Override
    public Object getValueAt(int row, int col) {
        Ais ais = aises.get(row);
        String str = "";
        switch (col) {
            case 0:
                str = sdf.format(ais.getDate());
                break;
            case 1:
                str = ais.getMmsi().toString();
                break;
            case 2:
                str = ais.getShipName();
                break;
            case 3:
                str = String.format("%.4f", ais.getLng());
                break;
            case 4:
                str = String.format("%.4f", ais.getLat());
                break;
            case 5:
                str = String.format("%.1f", ais.getSpeed());
                break;
            case 6:
                str = ais.getCog().toString();
                break;
            case 7:
                str = ais.getHeading().toString();
                break;
            case 8:
                str = ais.getRot().toString();
                break;
            case 9:
                str = ais.getStatus();
                break;
        }
        return str;
    }
    
}
