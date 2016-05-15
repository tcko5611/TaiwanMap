/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disandbeta;

/**
 *
 * @author DELL
 */
public class Location {
        private double latitude;
    private double longitude;
    public Location(double y, double x) {
	latitude = y;
	longitude  = x;
    }
    public Location(Location a) {
	latitude = a.latitude;
	longitude  = a.longitude;
    }
    public double getLatitude() {
	return latitude;
    }
    public double getLongitude() {
	return longitude;
    }
}
