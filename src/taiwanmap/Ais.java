/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanmap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class Ais implements Comparable<Ais> {

    public Ais(Date date, int mmsi, String shipName, double lng, double lat, 
            double speed, int cog, int heading, String rot, String status) {
        this.date = date;
        this.mmsi = mmsi;
        this.shipName = shipName;
        this.lng = lng;
        this.lat = lat;
        this.speed = speed;
        this.cog = cog;
        this.heading = heading;
        this.rot = rot;
        this.status = status;
    }
    public Date getDate() {
        return date;
    }
    public int getMmsi() {
        return mmsi;
    }
    public String getShipName() {
        return shipName;
    }
    public double getLng() {
        return lng;
    }
    public double getLat() {
        return lat;
    }
    public double getSpeed() {
        return speed;
    }
    public int getCog() {
        return cog;
    }
    public int getHeading() {
        return heading;
    }
    public String getRot() {
        return rot;
    }
    public String getStatus() {
        return status;
    }
   private Date date; 
   private Integer mmsi;
   private String shipName;
   private double lng;
   private double lat;
   private double speed;
   private int cog;
   private int heading;
   private String rot;
   private String status;
   @ Override  
    public String toString() {
      String str; 
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      str = sdf.format(date) + "," + mmsi + "," + shipName + "," + lng + "," + 
              lat + "," + speed + "," + cog + "," + heading + "," + rot + "," + 
              status;
      return str;
    }

    @Override
    public int compareTo(Ais o) {
        //return date.compareTo(o.date);
        return date.compareTo(o.date);
    }
    
}
