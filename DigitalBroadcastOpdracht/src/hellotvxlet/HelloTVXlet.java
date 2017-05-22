package hellotvxlet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;


public class HelloTVXlet implements Xlet, HActionListener{


public MijnComponent mc1;
public HScene scene;
public HStaticText label1;
  
    public HelloTVXlet() {
        
    }
    
    public void initXlet(XletContext ctx) throws XletStateChangeException {
      mc1 = new MijnComponent(0,0,720,576);
      scene = HSceneFactory.getInstance().getDefaultHScene();
      
      label1 = new HStaticText("Score: " + mc1.getScore());
      label1.setBackgroundMode(HVisible.BACKGROUND_FILL);
      label1.setBackground(Color.BLACK);
      
      scene.add(label1); 
      scene.add(mc1);      
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
