/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanmap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author DELL
 */
public class Aises {
    public static void main(String[] args) throws ParseException {
        String fileName = "d:\\AIS\\0933aisdr.csv";
        Aises aises = new Aises(fileName);
        SimpleDateFormat dt = new SimpleDateFormat("yyyyy.MM.dd HH:mm:ss");
        aises.dump();
        System.out.println(dt.format(aises.beginDate));
        System.out.println(dt.format(aises.endDate));
        // String begin = "2016.05.13 07:32:42";
        // String end = "2016.05.13 07:50:08";
        // Date beginDate = dt.parse(begin);
        // Date endDate = dt.parse(end);
        // ArrayList<Ais> newAises = aises.getRegionsAis(beginDate, endDate);
        // for(Ais ais : newAises) {
        //     System.out.println(ais);
        //}      
    }
    private ArrayList<Ais> aises;
    private Date beginDate;
    private Date endDate;
    private double maxLat;
    private double minLat;
    private double maxLng;
    private double minLng;
    public void dump() {
        for (Ais ais: aises) {
            System.out.println(ais);
        }
    }
    public Aises(String fileName) {
        aises = new ArrayList<Ais>();
        try {
            parseAisFile(fileName);
            getBoundary();
        } catch (Exception e) {
            System.err.println(fileName + " is not right");
        }
    }
    public Aises() {
       aises = new ArrayList<Ais>();
    }
    public void addAis(Ais ais) {
        aises.add(ais);
    }
    public ArrayList<Ais> getRegionsAis(Date beginDate, Date endDate) {
        ArrayList<Ais> newAises = new ArrayList<Ais>();
        if (beginDate.before(endDate)) {
            for(Ais ais : aises) {
                if (ais.getDate().after(beginDate) && 
                    ais.getDate().before(endDate)) {
                    newAises.add(ais);
                }
            }
        }
        return newAises;
    }
    private void getBoundary() {
        beginDate = aises.get(0).getDate();
        endDate = aises.get(aises.size() -1).getDate();
        maxLat = 0; minLat = 200;
        maxLng = 0; minLng = 200;
        for (Ais ais: aises) {
            if (maxLat < ais.getLat() ) maxLat = ais.getLat();
            if (minLat > ais.getLat() ) minLat = ais.getLat();
            if (maxLng < ais.getLng() ) maxLng = ais.getLng();
            if (minLng > ais.getLng() ) minLng = ais.getLng();
        }
    }
    public Date getBeginDate() {
        return beginDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public Double getMaxLat() {
        return maxLat;
    }
    public Double getMinLat() {
        return minLat;
    }
    public Double getMaxLng() {
        return maxLng;
    }
    public Double getMinLng() {
        return minLng;
    }    
    private void parseAisFile(String fileName) throws FileNotFoundException, ParseException, IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String str;
        in.readLine();
        in.readLine();
        while ((str = in.readLine()) != null) {
           String[] items= str.split(",");
           String dateStr = items[0] + " " + items[1];
           SimpleDateFormat dt = new SimpleDateFormat("yyyyy.MM.dd hh:mm:ss"); 
	   Date date = dt.parse(dateStr);
           int mmsi = Integer.parseInt(items[2]);
           String shipName = items[3];
           if (shipName.equals("null")) shipName = "";
           StringTokenizer st1 =  new StringTokenizer(items[4], " ");
           double lng = Double.parseDouble(st1.nextToken()) + Double.parseDouble(st1.nextToken())/ 60.0;
           st1 =  new StringTokenizer(items[5], " ");
           double lat = Double.parseDouble(st1.nextToken()) + Double.parseDouble(st1.nextToken())/ 60.0;
           double speed = Double.parseDouble(items[6]);
           int cog = Integer.parseInt(items[7]);
           int heading = 0;
           if (!(items[8].equals(""))) {
                heading = Integer.parseInt(items[8]);
           }
           String rot = items[9];
           String status = items[10];
           aises.add(new Ais(date, mmsi, shipName, lng, lat, speed, cog, heading, rot, status));
	}
        Collections.sort(aises);
    }
    public ArrayList<Ais> getRegionAises(Date startDate, Date endDate) {
       ArrayList<Ais> localAises = new ArrayList<Ais>();
       return localAises;
    }
}
