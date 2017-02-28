import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.io.*;
import javax.imageio.*;
import java.awt.Polygon;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.*;
import java.awt.*;

public class WorldMapInit  {
    JFrame f = new JFrame();
    
    
    public WorldMapInit()throws FileNotFoundException, IOException, ClassNotFoundException {
        
        f.setVisible(true);
        f.pack();
        //Initializes the map
        try {
            f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("sistariskcolored.png")))));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        
        //Initializes the buttons on the districts 
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("zones.txt")); 
        for(int i=0; i<42; i++){
            Object object = in.readObject();
            if (object != null) {
                f.getContentPane().add((PolygonButton) object);
            }
        }
        
        WindowListener wndCloser = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      };
      f.addWindowListener(wndCloser);
      f.setVisible(true);
      f.pack();
      f.setResizable(true);  
    }
}
