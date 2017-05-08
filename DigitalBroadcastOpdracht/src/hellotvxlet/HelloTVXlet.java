package hellotvxlet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;


public class HelloTVXlet implements Xlet, HActionListener{
public HScene scene;
  
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext ctx) throws XletStateChangeException {
       
      scene=HSceneFactory.getInstance().getDefaultHScene();
      
      HStaticText label1=new HStaticText("Wie was de eerste president van de VS?",30,30,600,100);//x,y,b,h
      label1.setBackgroundMode(HVisible.BACKGROUND_FILL);
      label1.setBackground(Color.GRAY);
        
      MijnComponent mc1=new MijnComponent(0,0,720,576);
      scene.add(mc1);
      scene.add(label1);
        
        scene.validate(); scene.setVisible(true);
    }

    public void startXlet() {
    
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent arg0) {
        
        
    }
}
