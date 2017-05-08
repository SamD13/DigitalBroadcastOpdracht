/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Timer;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HComponent;

/**
 *
 * @author student
 */
public class MijnComponent extends HComponent implements UserEventListener{
    
    int x,y,b,h;
    Image schip;
    int schipx=350;
    
    Image achtergrond;
    int ay=0;
    int schipy=250;
    boolean up,down,left,right=false;
    
    public MijnComponent(int x, int y, int b, int h) 
    { 
        this.x=x; this.y=y; this.b=b; this.h=h;
        this.setBounds(x,y,b,h);
        schip=this.getToolkit().getImage("spaceship.png");
        achtergrond=this.getToolkit().getImage("sterren.png");
        MediaTracker mt=new MediaTracker(this);
        mt.addImage(schip,1); mt.addImage(achtergrond, 2);
        
        try{
            mt.waitForAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        UserEventRepository repo= new UserEventRepository("repo");
        repo.addAllArrowKeys();
        
        EventManager man=EventManager.getInstance();
        
        man.addUserEventListener(this, repo);
        
        
        
        Timer t=new Timer();
        MijnTimerTask mtt = new MijnTimerTask(this);
        
        t.scheduleAtFixedRate(mtt, 0, 50);
    }
    
   
    public void paint (Graphics g)
    {
       g.drawImage(achtergrond, 0, ay, this);
       g.drawImage(achtergrond, 0, ay-570, this);
       g.drawImage(schip, schipx, schipy, this);
       g.drawRect(200, 200, 5, 5);
    }
    
    public void tick()
    {
        
        if (up)
        {
            schipy-=5;
            right=false;
            left=false;
            down=false;
        }
        else if (down)
        {
            schipy+=5;
            right=false;
            left=false;
            up=false;
        }
        else if (left)
        {
            schipx-=5;
            right=false;
            down=false;
            up=false;
        }
        else if (right)
        {
            schipx+=5;
            left=false;
            down=false;
            up=false;
        }
        
        System.out.println("Tick");
        ay=ay+1;
        this.repaint();
        if (ay>570) ay=0;
        
    }


    public void userEventReceived(UserEvent e) {
        
        if (e.getType() == HRcEvent.KEY_PRESSED){
          if (e.getCode()== HRcEvent.VK_LEFT)
        {
              left=true;
            this.repaint();
        }  
          if (e.getCode() == HRcEvent.VK_RIGHT)
          {
              right=true;
            this.repaint();
          }
          if (e.getCode() == HRcEvent.VK_DOWN)
          {
              down=true;
            this.repaint();
          }
          if (e.getCode() == HRcEvent.VK_UP)
          {
              up=true;
            this.repaint();
          }
          
        }
        
        

    }
}
