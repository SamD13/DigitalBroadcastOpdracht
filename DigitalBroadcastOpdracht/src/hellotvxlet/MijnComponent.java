/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Random;
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
    int schipy=250;
    int schipWidth = 48;
    int schipHeight = 45;
    
    Image achtergrond;
    int ay=0;
    
    Image enemy;
    int enWidth = 80;
    int enHeight = 77;
    int enx=250;
    int eny=200;
    int enx1 = enx + 100;
    int eny1 = eny  - 100;
    
    int score=0;
    
    boolean up,down,left,right=false;
    
    Random randomnr= new Random();
    int willekeurig;
    
    Timer t = new Timer();
    MijnTimerTask mtt = new MijnTimerTask(this);
    
    UserEventRepository repo= new UserEventRepository("repo");    
    
    HelloTVXlet htvx;
    
    public MijnComponent(int x, int y, int b, int h) 
    {
        this.x=x; this.y=y; this.b=b; this.h=h;
        this.setBounds(x,y,b,h);
        schip=this.getToolkit().getImage("spaceship.png");
        achtergrond=this.getToolkit().getImage("sterren.png");
        enemy=this.getToolkit().getImage("enemy.png");
        MediaTracker mt=new MediaTracker(this);
        mt.addImage(schip,1); mt.addImage(achtergrond, 2); mt.addImage(enemy, 3);
        
        try{
            mt.waitForAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        repo.addAllArrowKeys();
        repo.addKey(org.havi.ui.event.HRcEvent.VK_HOME);
        repo.addKey(org.havi.ui.event.HRcEvent.VK_ESCAPE);
        
        EventManager man=EventManager.getInstance();
        
        man.addUserEventListener(this, repo);              
    }
    
   
    public void paint (Graphics g)
    {
       g.drawImage(achtergrond, 0, ay, this);
       g.drawImage(achtergrond, 0, ay-570, this);
       g.drawImage(schip, schipx, schipy, this);
       g.drawImage(enemy, enx, eny, this);
       g.drawImage(enemy, enx1, eny1, this);
    }
    
    public int getScore(){
        return score;
    }
    
    public void tick()
    {
        willekeurig=randomnr.nextInt(40);
        
        if(willekeurig < 10 ){
             enx+=10;
             eny1+=10;
        }
        if(willekeurig >= 10 && willekeurig < 20){
             enx-=10;
             enx1+=10;
        }
        if(willekeurig >= 20 && willekeurig < 30){
             eny-=10;
             enx1-=10;
        }
        if(willekeurig >= 30 && willekeurig <= 40){
             eny+=10;
             eny1-=10;
        }
        System.out.println("Tick");
        ay=ay+1;
        this.repaint();
        if (ay>570) ay=0;
        //enemy.;
        
        if (eny < 0) {
            eny += 50;
        }
        if (eny1 < 0) {
            eny1 += 50;
        }
        if (enx < 0) {
            enx += 50;
        }
        if (enx1 < 0) {
            enx1 += 50;
        }
        if (eny + enHeight > getHeight()) {
            eny -= 50;
        }
        if (eny1 + enHeight > getHeight()) {
            eny1 -= 50;
        }
        if (enx + enWidth > getWidth()) {
            enx -= 50;
        }
        if (enx1 + enWidth > getWidth()) {
            enx1 -= 50;
        }
        if (schipy + schipHeight > getHeight() || schipx + schipWidth > getWidth() || schipx < 0 || schipy < 0) { 
            mtt.gestart=false;
          }
        
        if ((schipx > enx && schipx < (enx + enWidth)) || ((schipx + schipWidth) > enx && (schipx + schipWidth) < (enx + enWidth)))
        {
            if ((schipy > eny && schipy < (eny + enHeight)) || ((schipy + schipHeight) > eny && (schipy + schipHeight) < (eny + enHeight)))
            {
                mtt.gestart=false;
            }
        }
         if ((schipx > enx1 && schipx < (enx1 + enWidth)) || ((schipx+schipWidth) > enx1 && (schipx+schipWidth) < (enx1 + enWidth)) )
        {
            if ((schipy > eny1 && schipy < (eny1 + enHeight)) || ((schipy + schipHeight) > eny1 && (schipy + schipHeight) < (eny1 + enHeight)))
            {
                mtt.gestart=false;
            }
        }
        
        score += 5;
        System.out.println(score);     
        
    
     }


    public void userEventReceived(UserEvent e) {       
        if (e.getType() == HRcEvent.KEY_PRESSED){
          if (e.getCode()== HRcEvent.VK_LEFT)
          {
            schipx-=10;
            this.repaint();
          }
          if (e.getCode() == HRcEvent.VK_RIGHT)
          {
            schipx+=10;
            this.repaint();
          }
          if (e.getCode() == HRcEvent.VK_DOWN)
          {
            schipy+=10;
            this.repaint();
          }
          if (e.getCode() == HRcEvent.VK_UP)
          {
            schipy-=10;
            this.repaint();
          }
          if (e.getCode() == HRcEvent.VK_HOME)
          {
            t.scheduleAtFixedRate(mtt, 0, 100);
            mtt.gestart=true;
          }
           
          if (e.getCode() == HRcEvent.VK_ESCAPE)
          {
            mtt.gestart=false;
          }   
        }
    }
}
