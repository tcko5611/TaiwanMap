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
public class Debugger{
    public static boolean isEnabled(){
        return true;
    }

    public static void log(Object o){
        if (isEnabled()) {
          System.out.println(o.toString());
        }
    }

    static void err(Object o) {
        if (isEnabled()) {
          System.err.println(o.toString());
        }      
    }
}
