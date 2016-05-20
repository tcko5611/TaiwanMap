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
 * Design for send ais message in order
 * @author T.C.KO
 */
public class MessageSender implements Runnable{

    ArrayList<Ais> aises;
    int aisesIndex;
    Date startDate;
    Date endDate;
    Date currentDate;
    int speed; // min/sec
    LinkedList<Observer> observers;
    Boolean stop;
    Boolean pause;
    /**
     * 
     * @param aises : list of aises
     * @param startDate : the begin date of play
     * @param endDate : the end date of play
     * @param speed : speed of send out ais, speed *65536 msec/sec ~ speed min/sec
     */
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
    /**
     * 
     * @param o observer of receive sending out information 
     */
    public void addObserver(Observer o) {
       observers.add(o);
    }
    /**
     * 
     * @param o remove observer o 
     */
    public void removeObserver(Observer o) {
       observers.remove(o);
    }
    /**
     *  call observers to upddate
     * @param ais : ais send to observers,  
     */
    public void notifyObservers(Ais ais) {
       for(Iterator<Observer> iter = observers.iterator(); iter.hasNext();) {
          Observer obj = iter.next();
          obj.update(ais);
       }
    } 
    /**
     * thread function to send ais in date order and notify observer, 
     * and be able to notify stop, pause and continue signals from setting 
     */
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
/**
 * Observer interface that use for MessageSender
 * @see MessageSender
 * @author T.C.KO
 */
interface Observer {
    public void update(Ais ais);
}