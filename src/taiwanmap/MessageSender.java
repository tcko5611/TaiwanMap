/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanmap;

import static java.lang.Thread.yield;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class MessageSender implements Runnable{
    public MessageSender(ArrayList<Ais> aises, Date startDate, Date endDate, 
            int speed) {
        this.aises = aises;
        aisesIndex = 0;
        this.startDate = startDate;
        this.endDate = endDate;
        currentDate = startDate;
        this.speed = speed * 65536;
        stop = false;
        pause = false;
        observers = new LinkedList<Observer>();
    }
    ArrayList<Ais> aises;
    int aisesIndex;
    Date startDate;
    Date endDate;
    Date currentDate;
    int speed; // min/sec
    LinkedList<Observer> observers;
    Boolean stop;
    Boolean pause;
    public void addObserver(Observer o) {
       observers.add(o);
    }
    public void removeObserver(Observer o) {
       observers.remove(o);
    }
    public void notifyObservers(Ais ais) {
       for(Iterator<Observer> iter = observers.iterator(); iter.hasNext();) {
          Observer obj = iter.next();
          obj.update(ais);
       }
    } 
    @Override
    public void run() {
        currentDate = startDate; 
        while (currentDate.before(endDate) && !getStop()) {
            Debugger.log(aises.size());
            for (; aisesIndex < aises.size(); aisesIndex ++) {
                Ais ais = aises.get(aisesIndex);
                if (ais.getDate().after(currentDate)) break;
                Debugger.log(ais);
                notifyObservers(ais);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
            }
            currentDate.setTime(currentDate.getTime() + getSpeed());
            while (getPause()) {
                yield();
            }
        }
        return;
    }
    public synchronized void setStop() {
        stop = true;
    }
    public synchronized Boolean getStop() {
        return stop;
    }
    public synchronized void setPause() {
        pause = true;
    }
    public synchronized void unsetPause() {
        pause = false;
    }
    public synchronized Boolean getPause() {
        return pause;
    }
    public synchronized void slowSpeed() {
        speed = speed/2;
        if (speed < 1024) speed = 1024;
    }
    public synchronized void fastSpeed() {
       speed *= 2;
       if (speed > 4194304) speed = 4194304;
    }
    public synchronized int getSpeed() {
      return speed;  
    }
}

interface Observer {
    public void update(Ais ais);
}