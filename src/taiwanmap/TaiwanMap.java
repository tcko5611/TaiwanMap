/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanmap;

/**
 *
 * @author DELL
 */

import disandbeta.Location;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TaiwanMap {
    ArrayList<Location> locs;
    public static void main(String[] args) {
	TaiwanMap obj = new TaiwanMap();
        obj.getInfo();
	// System.out.println(obj.getFile());		
    }
    public TaiwanMap() {
        locs = new ArrayList<Location>();
        getFile();
    }
    public void getInfo() {
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
        System.out.println("max lat: " + maxLat + ", min lat: " + minLat);
        System.out.println("max long: " + maxLong + ", min long: " + minLong);
    }
    public ArrayList<Location> getLocs() {
        return locs;
    }
    private void getFile() {
        //Get file from resources folder
	ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource("file/TaiwanLatLon.csv").getFile());

	try (Scanner scanner = new Scanner(file)) {

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
                        StringTokenizer stok = new StringTokenizer(line, ",");
                        double lat = Double.parseDouble(stok.nextToken());
                        double lon = Double.parseDouble(stok.nextToken());
                        locs.add(new Location(lat, lon));
		}

		scanner.close();

	} catch (IOException e) {
		e.printStackTrace();
	}
    }
}

