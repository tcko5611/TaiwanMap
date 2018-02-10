/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanmap;

import disandbeta.Location;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Store taiwan map point for use
 * @author T.C.KO
 */
public class TaiwanMap {
    ArrayList<Location> locs;
    public static void main(String[] args) {
	TaiwanMap obj = new TaiwanMap();
        obj.getInfo();	
    }
    public TaiwanMap() {
        locs = new ArrayList<Location>();
        getFile();
    }
    /**
     * 
     */
    void getInfo() {
        double maxLat = 0.0;
        double maxLong = 0.0;
        double minLat = 200;
        double minLong = 200;
        for (Location loc: locs) {
            if (loc.getLatitude() > maxLat) {
                maxLat = loc.getLatitude();
            }
            if (loc.getLatitude() < minLat) {
                minLat = loc.getLatitude();
            }
            if (loc.getLongitude() > maxLong) {
                maxLong = loc.getLongitude();            
            }
            if (loc.getLongitude() < minLong) {
                minLong = loc.getLongitude();            
            }
        }
        Debugger.log("max lat: " + maxLat + ", min lat: " + minLat);
        Debugger.log("max long: " + maxLong + ", min long: " + minLong);
    }
    /**
     * 
     * @return location points of taiwan map 
     */
    public ArrayList<Location> getLocs() {
        return locs;
    }
    private void getFile() {
        //Get file from resources folder
	ClassLoader classLoader = getClass().getClassLoader();
	// File file = new File(classLoader.getResource("file/TaiwanLatLon.csv").getFile());
        BufferedReader br = new BufferedReader(new InputStreamReader(
                  classLoader.getResourceAsStream("file/MapLatLon_TaiwanV2.csv")));
        // File file = new File("file/TaiwanLatLon.csv");
	try {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                StringTokenizer stok = new StringTokenizer(line, ",");
                double lat = Double.parseDouble(stok.nextToken());
                double lon = Double.parseDouble(stok.nextToken());
                locs.add(new Location(lat, lon));
            }
            //  scanner.close();
	} catch (IOException e) {
            e.printStackTrace();
	}
    }
}

