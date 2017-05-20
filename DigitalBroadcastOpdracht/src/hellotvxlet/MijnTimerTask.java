/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.util.TimerTask;

/**
 *
 * @author student
 */
public class MijnTimerTask extends TimerTask {
MijnComponent mc;
public boolean gestart=true;

    public MijnTimerTask(MijnComponent mc)
    {
        this.mc=mc;
    }
    
    public void run() {
        if (gestart){
          mc.tick();  
        }
    }

  

}
