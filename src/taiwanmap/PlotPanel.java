/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanmap;

import disandbeta.Location;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class PlotPanel extends JPanel implements Observer{
    ArrayList<Location> locs; // Taiwan map
    ArrayList<Point> drawPts; // Taiwan map
    private HashMap<Integer, Aises> shipsTrace;
    private HashMap<Integer, Ais> ships;
    ArrayList<Point> shipsPts;
    // int dxMax = 700;
    // int dyMax = 700;
    double x0; // the left
    double y0; // the top 
    double x1; // the right
    double y1; // the lowest
    @Override 
    public void update(Ais ais){
        addAis(ais);
        ships.put(ais.getMmsi(), ais);
        shipsPts.clear();
        for (Map.Entry<Integer, Ais> entry : ships.entrySet()) {
            double x = entry.getValue().getLng();
            double y = entry.getValue().getLat();
            int xMax = this.getWidth();
            int yMax = this.getHeight();
            int dx = (int) ((x-x0) * xMax / (x1-x0));
            int dy = (int) ((y-y0) * yMax /(y1-y0));
            // System.out.println("x:" + dx + ", y:" + dy);
            if ((dx >= 0) && (dy>=0) && (dx <= xMax) && (dy <= yMax)) {
                shipsPts.add(new Point(dx, dy));
            }  
        }
        repaint();
    }
    public PlotPanel() {
        drawPts = new ArrayList<Point>();
        TaiwanMap taiwanMap = new TaiwanMap();
        locs = taiwanMap.getLocs();
        shipsTrace = new HashMap<Integer, Aises>();
        ships = new HashMap<Integer, Ais>();
        shipsPts = new ArrayList<Point>();
    }
    public synchronized void addAis(Ais ais) {
        Integer mmsi = ais.getMmsi();
        if (ships.containsKey(mmsi)) { 
            Aises aises = shipsTrace.get(mmsi);
            aises.addAis(ais);
        } else {
            Aises aises = new Aises();
            aises.addAis(ais);
            shipsTrace.put(mmsi, aises);
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
        translateLocToPoint();
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
            if ((dx >= 0) && (dy>=0)) {
                drawPts.add(new Point(dx,dy));
                
            }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
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
        for (int i = 0; i < shipsPts.size(); i++) {
            g2d.fillOval(shipsPts.get(i).getX(), shipsPts.get(i).getY(), 10, 10);
        }
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