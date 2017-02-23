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

public class WorldMapInit{
    
    public WorldMapInit(){
        JFrame f = new JFrame();
        //Initializes the map
        try {
            f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("untitled-1.jpg")))));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        //Initializes the buttons on the districts -- refactor
        Polygon p = new Polygon();
        p.addPoint(10, 135);
        p.addPoint(60, 140);
        p.addPoint(30, 40);
        PolygonButton btn = new PolygonButton(p, "button");
        f.getContentPane().add(btn);
        
        Polygon pol = new Polygon();
        pol.addPoint(12, 125);
        pol.addPoint(70, 150);
        pol.addPoint(90,200);
        PolygonButton bttn = new PolygonButton(p, "button");
        f.getContentPane().add(bttn);
        
        WindowListener wndCloser = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      };
      f.addWindowListener(wndCloser);
      f.setVisible(true);
      f.pack();
      f.setResizable(false);  
    }
}