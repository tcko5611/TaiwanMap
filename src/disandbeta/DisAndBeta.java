/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package disandbeta;

/**
 *
 * @author ktc
 */
import java.util.*;

class ShipTrace {
    private String shipName;
    private List<Location>  poss;
    private double vx;
    private double vy;
    public ShipTrace(String name) {
        shipName = name;
        poss = new ArrayList<Location>();
    }
    public void addPos(Location p) {
        poss.add(p);
    }
    public Location getLastPos(){
        return poss.get(poss.size()-1);
    }
}

class GenTrace {
    private String shipName;
    private Location  pos;
    private double vx; // for latitude
    private double vy; // for longitude
    public GenTrace(String name, double lat, double lng, double vx, double vy) {
        shipName = name;
        pos = new Location(lat, lng);
        this.vx = vx;
        this.vy = vy;
    }
    public String getShipName() {
        return shipName;
    }
    public Location getPos() {
        return pos;
    }
    public void addPos() {
        double lat = pos.getLatitude() + vx;
        double lng = pos.getLongitude() + vy;
        pos = new Location(lat, lng);
    }    
    
}
public class DisAndBeta {
    public final static double AVERAGE_RADIUS_OF_EARTH = 6371;
    private Location a;
    private Location b;
    public DisAndBeta(Location a, Location b) {
	this.a = a;
	this.b = b;
    }
    public static double getDis(Location a, Location b) {
	// source
	double lat1 = Math.toRadians( a.getLatitude());
	double lng1 = Math.toRadians(a.getLongitude());
	
	// destination
	double lat2 = Math.toRadians(b.getLatitude());
	double lng2 = Math.toRadians(b.getLongitude());

	double dLat = lat2 - lat1;
	double dLon = lng2 - lng1 ;
	double ah = Math.sin(dLat / 2) * Math.sin(dLat / 2)
	    + Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(lat1))
	    * Math.sin(dLon / 2) * Math.sin(dLon / 2);

	double c = 2 * Math.atan2(Math.sqrt(ah), Math.sqrt(1 - ah));

	return AVERAGE_RADIUS_OF_EARTH * c;
    }
    public static double getBeta(Location a, Location b) {
	// source
	double lat1 = Math.toRadians(a.getLatitude());
	double lng1 = Math.toRadians(a.getLongitude());
	
	// destination
	double lat2 = Math.toRadians(b.getLatitude());
	double lng2 = Math.toRadians(b.getLongitude());

	double dLon = (lng2 - lng1);
	double y = Math.sin(dLon) * Math.cos(lat2);
	double x = Math.cos(lat1)*Math.sin(lat2) - Math.sin(lat1)*Math.cos(lat2)*Math.cos(dLon);
	double brng = Math.toDegrees((Math.atan2(y, x)));
	brng = 360 - (brng + 360) % 360;
	return brng;
    }
    public static void main(String argv[]) {
	double cx = 25.050852;
        double cy = 121.445808;
        Location cen = new Location(25.050852, 121.445808);
        
      
        double x = cx + 0.1;
        double y = cy;
        Location l2 = new Location(x, y);
        DisAndBeta pt = new DisAndBeta(cen, l2);
        System.out.println("d: " + DisAndBeta.getDis(cen, l2) + "beta:" + DisAndBeta.getBeta(cen, l2));
        x = cx + 0.1;
        y = cy - 0.1;
        l2 = new Location(x, y);
        pt = new DisAndBeta(cen, l2);
        System.out.println("d: " + DisAndBeta.getDis(cen, l2) + "beta:" + DisAndBeta.getBeta(cen,l2));        
        x = cx;
        y = cy - 0.1;
        l2 = new Location(x, y);
        pt = new DisAndBeta(cen, l2);
        System.out.println("d: " + DisAndBeta.getDis(cen, l2) + "beta:" + DisAndBeta.getBeta(cen,l2));        
            
        x = cx - 0.1;
        y = cy - 0.1;
        l2 = new Location(x, y);
        pt = new DisAndBeta(cen, l2);
        System.out.println("d: " + DisAndBeta.getDis(cen, l2) + "beta:" + DisAndBeta.getBeta(cen,l2));
        
        x = cx - 0.1;
        y = cy ;
        l2 = new Location(x, y);
        pt = new DisAndBeta(cen, l2);
        System.out.println("d: " + DisAndBeta.getDis(cen, l2) + "beta:" + DisAndBeta.getBeta(cen, l2)); 
        
        x = cx - 0.1;
        y = cy + 0.1;
        l2 = new Location(x, y);
        pt = new DisAndBeta(cen, l2);
        System.out.println("d: " + DisAndBeta.getDis(cen, l2) + "beta:" + DisAndBeta.getBeta(cen,l2)); 
        
        x = cx ;
        y = cy + 0.1;
        l2 = new Location(x, y);
        pt = new DisAndBeta(cen, l2);
        System.out.println("d: " + DisAndBeta.getDis(cen, l2) + "beta:" + DisAndBeta.getBeta(cen, l2)); 
        
        x = cx + 0.1;
        y = cy + 0.1;
        l2 = new Location(x, y);
        pt = new DisAndBeta(cen, l2);
        System.out.println("d: " + DisAndBeta.getDis(cen,l2) + "beta:" + DisAndBeta.getBeta(cen, l2));    
    }
} 

