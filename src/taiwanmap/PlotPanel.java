/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanmap;

import disandbeta.Location;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JPanel;
import java.lang.Integer;


/**
 *
 * @author DELL
 */
public class PlotPanel extends JPanel implements Observer{
    ArrayList<Location> locs; // Taiwan map
    ArrayList<Point> drawPts; // Taiwan map
    private HashMap<Integer, Aises> shipsTrace;
    private HashMap<Integer, Ais> ships;
    HashMap<Integer, ArrayList<Point> > shipsTracePts;
    ArrayList<Point> shipsPts;
    ArrayList<Integer>mmsiPts;
    double x0; // the left
    double y0; // the top 
    double x1; // the right
    double y1; // the lowest
    Boolean showMmsi;
    Boolean showTrace;
    DrawShips drawShips;
    @Override 
    public void update(Ais ais){
        addAis(ais);
        ships.put(ais.getMmsi(), ais);
        shipsPts.clear();
        mmsiPts.clear();
        for (Map.Entry<Integer, Ais> entry : ships.entrySet()) {
            double x = entry.getValue().getLng();
            double y = entry.getValue().getLat();
            // int xMax = this.getWidth();
            // int yMax = this.getHeight();
            // int dx = (int) ((x-x0) * xMax / (x1-x0));
            // int dy = (int) ((y-y0) * yMax /(y1-y0));
            // System.out.println("x:" + dx + ", y:" + dy);
            Point p = translateLocToPoint(x, y);
            if (p != null) {
                shipsPts.add(p);
                mmsiPts.add(entry.getKey());
            }  
        }
        repaint();
    }
    public PlotPanel() {
        TaiwanMap taiwanMap = new TaiwanMap();
        locs = taiwanMap.getLocs();
        drawPts = new ArrayList<Point>();
        shipsTrace = new HashMap<Integer, Aises>();
        ships = new HashMap<Integer, Ais>();
        shipsTracePts = new HashMap<Integer, ArrayList<Point> >();
        shipsPts = new ArrayList<Point>();
        mmsiPts = new ArrayList<Integer>();
        x0 = 119;
        x1 = 123;
        y0 = 25.5;
        y1 = 21.5;
        showMmsi = false;
        showTrace = false;
        drawShips = new DrawMmsiShips();
    }
    public void showMmsi() {
        showMmsi = true;
    }
    public void unshowMmsi() {
        showMmsi = false;
    }
    public void showTrace() {
        showTrace = true;
    }
    public void unshowTrace() {
        showTrace = false;
    }
    // return draw point for input x, y
    private Point translateLocToPoint(double x, double y) {
        Point p = null;
        int xMax = this.getWidth();
        int yMax = this.getHeight();
        int dx = (int) ((x-x0) * xMax / (x1-x0));
        int dy = (int) ((y-y0) * yMax /(y1-y0));
        if ((dx >= 0) && (dx <= xMax) && (dy>=0) && (dy <= yMax)) {
            p = new Point(dx,dy);
        }
        return p;
    }
    private synchronized void addAis(Ais ais) {
        Integer mmsi = ais.getMmsi();
        double x = ais.getLng();
        double y = ais.getLat();
        Point pt = this.translateLocToPoint(x, y);
        if (shipsTrace.containsKey(mmsi)) { 
            Aises aises = shipsTrace.get(mmsi);
            ArrayList<Point> pts = shipsTracePts.get(mmsi);
            aises.addAis(ais);
            if (pt != null) pts.add(pt);
        } else {
            Aises aises = new Aises();
            ArrayList<Point> pts = new ArrayList<Point>();
            aises.addAis(ais);
            if (pt != null) pts.add(pt);
            shipsTrace.put(mmsi, aises);
            shipsTracePts.put(mmsi, pts);
        }
    }
    
    public void setLocs(ArrayList<Location> locs) {
        this.locs = locs;
    }
    public void setBoundary(double x0, double y0, double x1, double y1) {
        drawPts = new ArrayList<Point>();
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        shipsTrace.clear();
        ships.clear();
        shipsPts.clear();
        mmsiPts.clear();
        translateLocToPoint();
        repaint();
    }
    private void translateLocToPoint() {
        
        for (Location loc: locs) {
            double x = loc.getLongitude();
            double y = loc.getLatitude();
            int xMax = this.getWidth();
            int yMax = this.getHeight();
            int dx = (int) ((x-x0) * xMax / (x1-x0));
            int dy = (int) ((y-y0) * yMax /(y1-y0));
            System.out.println("x:" + dx + ", y:" + dy);
            if ((dx >= 0) && (dx <= xMax) && (dy>=0) && (dy <= yMax)) {
                drawPts.add(new Point(dx,dy));
                
            }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        int xMax = this.getWidth();
        int yMax = this.getHeight();
        g2d.setRenderingHint 
        (
            RenderingHints.KEY_ANTIALIASING,
	    RenderingHints.VALUE_ANTIALIAS_ON
         );
        g2d.setColor(Color.BLACK);
        for (int i = 1; i < drawPts.size(); i++) {
            g2d.drawLine(drawPts.get(i-1).getX(), drawPts.get(i-1).getY(),
                    drawPts.get(i).getX(), drawPts.get(i).getY()); 
        }
        float[] dash1 = { 2f, 0f, 2f };
        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, 
            BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f );
        g2d.setStroke(bs1);
        // g2d.setStroke(new BasicStroke(1f));
        for(int i = 1; i < 10; i++) {
            int dx = xMax/10 *i;
            int dy = yMax/10 *i;
            double x = x0 + ((x1-x0) *dx) / xMax;
            double y = y0 + ((y1-y0)*dy) / yMax;
            g2d.drawLine(dx, 0, dx, yMax);
            g2d.drawLine(0, dy, xMax, dy);
            g2d.drawString(String.format("%.2f", x), dx, yMax - 5);
            g2d.drawString(String.format("%.2f", y), 0, dy);
        }
        g2d.setStroke(new BasicStroke(1f));
        // for (int i = 0; i < shipsPts.size(); i++) {
        //    g2d.fillOval(shipsPts.get(i).getX(), shipsPts.get(i).getY(), 5, 5);
        //}
        // drawPoints(g2d);
        this.drawShips.drawShips(g2d);
    }
    private void drawPoints(Graphics2D g2d) {
        
        for (int i = 0; i < shipsPts.size(); i++) {
            int x;
            Point pt = shipsPts.get(i);
            x = shipsPts.get(i).getX();
            int y = shipsPts.get(i).getY();
            g2d.fillOval(x, y, 5, 5);
            if(showMmsi) {
                g2d.drawString(mmsiPts.get(i).toString(), x, y);
            }
            
        }    
    }
    
    interface DrawShips {
        public void drawShips(Graphics2D g2d);
    }
    class DrawMmsiShips implements DrawShips {
        @Override
        public void drawShips(Graphics2D g2d) {
            for (int i = 0; i < shipsPts.size(); i++) {
                int x = shipsPts.get(i).getX();
                int y = shipsPts.get(i).getY();
                g2d.fillOval(x-2, y-2, 4, 4);
                if(showMmsi) {
                    g2d.drawString(mmsiPts.get(i).toString(), x, y);
                }  
            }    
        }
    }
    class DrawTraceShips implements DrawShips {
        @Override
        public void drawShips(Graphics2D g2d) {
            for(Entry<Integer, ArrayList<Point> > entry : shipsTracePts.entrySet()) {
                Integer mmis = entry.getKey();
                ArrayList<Point> pts= entry.getValue();
                for(Point pt : pts) {
                   g2d.fillOval(pt.getX()-2, pt.getY()-2, 4, 4); 
                }
                if (showTrace && pts.size() >= 2) {
                    for (int i = 1; i < pts.size(); i++) {
                        g2d.drawLine(pts.get(i-1).getX(), pts.get(i-1).getY(), 
                                pts.get(i).getX(), pts.get(i).getY());
                    }                  
                }
            }
        }    
    }
    void setRealTimeDraw(){
        this.drawShips = new DrawMmsiShips();
    }
    void setAllTimeDraw(){
        this.drawShips = new DrawTraceShips();
    }
    void clearData() {
        
        this.ships.clear();
        this.shipsPts.clear();
        this.shipsTrace.clear();
        this.shipsTracePts.clear();
        this.mmsiPts.clear();
    }
}
class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}